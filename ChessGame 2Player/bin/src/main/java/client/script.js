const ws = new WebSocket('ws://localhost:8080');
const gameBoard = document.getElementById('game-board');
const messagesElement = document.getElementById('messages');
const heroPopup = document.getElementById('hero-popup');
const heroOptions = document.getElementById('hero-options');
const closePopupButton = document.getElementById('close-popup');
let currentCell = null;
let initializeMode = false; //if initialize heroes is disabled
let timerInterval;
let gameStarted = false;
let currentPlayer = 'A';
let moveHistory = [];
// hero selection popup
const heroes = ['Hero1', 'Hero2', 'pawn' ,'Hero3'];

ws.onopen = function() {
    messagesElement.textContent = 'Connected to the server.';
};

ws.onmessage = function(event) {
    const message = event.data;
    console.log("Received message:", event.data);
    if (message.startsWith("PlayerCount:")) {
        const playerCount = parseInt(message.split(":")[1], 10);
        if (playerCount === 1) {
            WaitingMsg(true);
        } else if (playerCount === 2) {
            WaitingMsg(false);
            console.log("Both players are connected. Starting the game... "+ playerCount);
        }
    }  else {
        const boardState = event.data;
        updateBoard(boardState);
    }
};

ws.onclose = function() {
    messagesElement.textContent = 'Disconnected from the server.';
};

document.getElementById('initialize-heroes').addEventListener('click', function() {
    initializeMode = true;
    console.log("Initialization mode enabled.");
});

document.getElementById('start-game').addEventListener('click', function() {
    initializeMode = false;
    gameStarted=true;
    startTimer();
    console.log("Game started.");
});

document.getElementById('reset-board').addEventListener('click', function() {
    console.log("Board has been reset");
    initializeMode = true;
    gameStarted=false;
    resetMoveHistory();
    stopTimer();
    ws.send('reset');
});

document.getElementById('send-move').addEventListener('click', function() {
    const moveInput = document.getElementById('move-input').value;
    ws.send(moveInput);
});

function updateBoard(boardState) {
    const [boardPart, historyPart] = boardState.split('<br><br>');

    const rows = boardPart.trim().split('\n');
    gameBoard.innerHTML = '';
    rows.forEach((row, rowIndex) => {
        row.trim().split(' ').forEach((cell, colIndex) => {
            const gridCell = document.createElement('div');
            gridCell.textContent = cell !== '----' ? cell : '';
            gridCell.className = 'grid-cell';
            gridCell.dataset.row = rowIndex;
            gridCell.dataset.col = colIndex;

            if (cell !== '----') {
                gridCell.addEventListener('click', () => handleCharacterClick(cell));
            }
            gridCell.addEventListener('click', handleCellClick);
            gameBoard.appendChild(gridCell);
        });
    });

    updateMoveHistory(historyPart);
}

function updateMoveHistory(move) {

    if (typeof move === 'undefined' || move.trim() === '') {
        return;
    }

    if (move === 'Player B wins!' || move === 'Player A wins!') {
        if (gameStarted) {
            currentPlayer = currentPlayer === 'A' ? 'B' : 'A';
            updateTurnIndicator();
            showWinPopup(move);
        }
        return;
    }else if(move.startsWith('Moving')){
        currentPlayer = currentPlayer === 'A' ? 'B' : 'A';
        updateTurnIndicator();
    }

    moveHistory.push(move);

    const moveHistoryDiv = document.getElementById('move-history');
    moveHistoryDiv.innerHTML = moveHistory.map(m => `<div>${m}</div>`).join('');
    console.log("Updated move history div content:", moveHistoryDiv.innerHTML); // Debugging line
}

function handleCellClick(event) {
    const cell = event.target;
    if (initializeMode) { // Show hero select popup only if in initialization mode
        currentCell = cell;
        HeroSelectionPopup();
    } else {
        console.log("Game is not in initialization mode.");
    }
}

// handles the states when you click on a existing character on the grid
function handleCharacterClick(character) {
    const moveOptions = document.getElementById('move-options');
    moveOptions.innerHTML = ''; // Clear previous options

    let possibleMoves = [];

    if (character.startsWith('A-H1') || character.startsWith('B-H1')) {
        possibleMoves = ['F', 'B', 'L', 'R']; // Hero1 moves
    } else if (character.startsWith('A-H2') || character.startsWith('B-H2')) {
        possibleMoves = ['FL', 'FR', 'BL', 'BR']; // Hero2 moves
    } else if (character.startsWith('A-P') || character.startsWith('B-P')) {
        possibleMoves = ['F', 'B', 'L', 'R']; // Pawn moves
    }else if (character.startsWith('A-H3') || character.startsWith('B-H3')) {
        possibleMoves = ['FL', 'FR', 'BL', 'BR', 'RF', 'RB', 'LF', 'LB'];
    }

    // Clear previous highlights
    const cells = document.querySelectorAll('.grid-cell');
    cells.forEach(cell => cell.classList.remove('highlight'));

    // highlight the selected character in the grid
    const selectedCell = Array.from(cells).find(cell => cell.textContent.trim() === character);
    if (selectedCell) {
        selectedCell.classList.add('highlight');
    }

    possibleMoves.forEach(move => {
        const moveButton = document.createElement('button');
        moveButton.textContent = move;
        moveButton.addEventListener('click', () => {
            console.log(`Move ${character} ${move}`);
            ws.send(`${character}:${move}`);
        });
        moveOptions.appendChild(moveButton);
    });

    // Show the move options container which is hidden when clicked
    moveOptions.classList.remove('hidden');
}

function HeroSelectionPopup() {
    heroOptions.innerHTML = '';
    heroes.forEach(hero => {
        const heroElement = document.createElement('div');
        heroElement.textContent = hero;
        heroElement.className = 'hero-option';
        heroElement.addEventListener('click', () => handleHeroSelection(hero));
        heroOptions.appendChild(heroElement);
    });
    heroPopup.classList.remove('hidden');
}

function handleHeroSelection(hero) {
    console.log(`Selected hero: ${hero} for cell at row ${currentCell.dataset.row}, column ${currentCell.dataset.col}`);

    const message = `InitHero:${hero}:${currentCell.dataset.row}:${currentCell.dataset.col}`;
    ws.send(message);

    heroPopup.classList.add('hidden');
}

closePopupButton.addEventListener('click', function() {
    heroPopup.classList.add('hidden');
});

function showWinPopup(message) {
    const popup = document.createElement('div');
    popup.className = 'winPopup';

    popup.innerHTML = `
        <div class="popup-content">
            <h2>${message}</h2>
            <button id="close-win-popup">Close</button>
        </div>
    `;

    document.body.appendChild(popup);

    document.getElementById('close-win-popup').addEventListener('click', () => {
        gameStarted=false;
        resetMoveHistory();
        ws.send("reset");
        document.body.removeChild(popup);
    });
}

//////////Timer//////////////////////
function startTimer() {
    const timerElement = document.getElementById('timer');
    let seconds = 0;
    if (timerInterval) {
        clearInterval(timerInterval);
    }

    timerInterval = setInterval(() => {
        seconds++;
        const minutes = Math.floor(seconds / 60);
        const displaySeconds = seconds % 60;
        timerElement.textContent =
            `${minutes.toString().padStart(2, '0')}:${displaySeconds.toString().padStart(2, '0')}`;
    }, 1000);
}
function stopTimer() {
    if (timerInterval) {
        clearInterval(timerInterval);
    }
    const timerElement = document.getElementById('timer');
    timerElement.textContent = "00:00";
    timerInterval = null; // Clear the interval reference
}
///////////////Timer end//////////////
function resetMoveHistory() {
    moveHistory = []; // reset history array
    const moveHistoryDiv = document.getElementById('move-history');
    if (moveHistoryDiv) {
        moveHistoryDiv.innerHTML = ''; // reset history in webpage
    }
}
function updateTurnIndicator() {
    const turnIndicator = document.getElementById('turn-indicator');
    turnIndicator.textContent = `Player ${currentPlayer}'s Turn`;
}

//used to show the waiting message at the beginning of the game
function WaitingMsg(show) {
    const wait = document.getElementById('waitingMessage');
    if (show) {
        wait.style.display = 'block';
    } else {
        wait.style.display = 'none';
    }
}