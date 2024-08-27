# Chess Game

This repository contains two versions of a Chess game: 1 Player and 2 Player. Both are Maven projects with a Java backend and HTML/CSS/JavaScript frontend.

## Project Structure

- ChessGame 1Player (Two players on one screen)
- ChessGame 2Player (Two players on separate screens)

## Important Note
To run the ChessGame 2Player version, you need to open two instances of the HTML file. This can be done either in two different tabs of the same browser or in two separate browsers because it requires 2 players to start the game.

## How to Run

Follow these steps to run either version of the Chess game:
1. Load The Maven Project

2. Choose the version you want to run:
   - For two players on one screen: Open the `ChessGame 1Player` folder
   - For two players on separate screens: Open the `ChessGame 2Player` folder

3. Start the server:
   - Navigate to `ChessGame 1Player/src/main/java/server`
   - Run `GameWebSocketServer.java`

4. Launch the client:
   - Navigate to `ChessGame 1Player/src/main/java/client`
   - Open `index.html` in a web browser
   - If the above doesn't work:
     - Locate the `index.html` file in your file system
     - Double Click on it to Run Or
     - Copy the full file path
     - Paste the path into your web browser's address bar Ex-`file:///C:/Users/Amrit%20Rout/Desktop/AmritRout-21BEC0294-main/ChessGame%201Player/src/main/java/client/index.html`

For the 2Player version, repeat step 3 on another device or browser window.

## Game Modes

- **ChessGame 1Player**: Two players take turns on the same screen.
- **ChessGame 2Player**: Two players can play remotely on separate screens on the same network.

## Requirements
- Java Development Kit (JDK)
- Maven
- IDE (e.g., IntelliJ IDEA, Eclipse)

## Demo
-**ChessGame 1Player**
--**Click on the video to play**

[![Watch the video](https://img.youtube.com/vi/2VAKpT2BIps/0.jpg)](https://www.youtube.com/watch?v=2VAKpT2BIps)

-**ChessGame 2Player**

[![YouTube](http://i.ytimg.com/vi/hkjKyCf3OUk/hqdefault.jpg)](https://www.youtube.com/watch?v=hkjKyCf3OUk)

## Features Implemented

### Server-side Implementation

<details>
<summary>Click to expand</summary>

- [x] Implemented core game logic as described in the Game Rules section.
- [x] Set up a WebSocket server to handle client connections and game events.
- [x] Processed move commands and updated the game state accordingly.
- [x] Implemented thorough server-side move validation.

</details>

### Client-side Implementation

<details>
<summary>Click to expand</summary>

- [x] Created a basic web interface displaying the 5x5 game board.
- [x] Implemented WebSocket communication with the server.
- [x] Implemented client-side move validation, mirroring server-side validation.
- [x] Displayed valid moves for the selected character.
- [x] Sent move commands to the server and handled responses.

</details>

### WebSocket Communication

<details>
<summary>Click to expand</summary>

- [x] Implemented event handling for game initialization, player moves, and game state updates.
- [x] Ensured real-time synchronization of game state between server and all connected clients.

</details>

### Move Validation

<details>
<summary>Click to expand</summary>

- [ ] Prevented selection or movement of opponent's pieces.
- [x] Ensured moves are within the 5x5 grid boundaries.
- [x] Validated moves according to each character type's movement rules.
- [x] Prevented friendly fire (moving onto or through spaces occupied by friendly characters).
- [x] Handled and communicated invalid move attempts to the user.

</details>

### Edge Cases Handling

<details>
<summary>Click to expand</summary>

- [x] Handled simultaneous move attempts by multiple clients.
- [x] Managed disconnection and reconnection of clients during an ongoing game.
- [x] Prevented attempts to make moves out of turn.
- [ ] Managed game state when a player quits mid-game.
- [x] Properly terminated the game when all opponent's pieces are eliminated.

</details>

### Game Flow

<details>
<summary>Click to expand</summary>

- [x] Implemented turn-based gameplay with clear indication of the current player's turn.
- [x] Handled piece elimination upon valid capture moves.
- [x] Detected game end and announced the winner.

</details>

## Troubleshooting

If you encounter any issues while running the game, try the following:

1. If the chess grid is not visible:
   - On Windows, press Ctrl + F5 to perform a hard refresh and clear the browser cache
   - On Mac, press Cmd + Shift + R to perform a hard refresh
   - Alternatively, open the browser's developer tools (usually F12), go to the Network tab, and check "Disable cache" before reloading the page

2. If the game doesn't connect to the server:
   - Ensure that the `GameWebSocketServer.java` is running
   - Check your firewall settings to make sure it's not blocking the connection

3. If you see any JavaScript errors:
   - Open the browser's developer tools (usually F12) and check the Console tab for error messages

## Additional Information

- The backend is written in Java
- The frontend uses HTML, CSS, and JavaScript (Client)
