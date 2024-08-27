package game;

public class GameController {
    public GameBoard board = new GameBoard();
    private boolean isPlayer1Turn = true; // true=playerA, false=playerB

    public GameBoard getGameBoard() {
        return board;
    }

    public void processMove(String command) {
        String[] parts = command.split(":");
        if (parts.length != 2) {
            board.moves.add("Invalid command format.");
            System.out.println("Invalid command format.");
            return;
        }

        String name = parts[0];
        String move = parts[1];


        boolean isPlayerACommand = name.startsWith("A-");
        boolean isPlayerBCommand = name.startsWith("B-");


        if ((isPlayer1Turn && !isPlayerACommand) || (!isPlayer1Turn && !isPlayerBCommand)) {
            board.moves.add("Not your turn!");
            System.out.println("Not your turn!");
            return;
        }

        Character character = board.getCharacter(name);
        if (character == null) {
            board.moves.add("Character doesn't exist");
            System.out.println("Character doesn't exist");
            return;
        }

        if (!character.isValidMove(move)) {
            board.moves.add("Invalid move command.");
            System.out.println("Invalid move command.");
            return;
        }

        boolean movesuccessful=character.move(board, move);

        if(!movesuccessful){
            return;
        }

        isPlayer1Turn = !isPlayer1Turn;
        board.printBoard();
        checkWinningCondition(); //check if all the pieces of a player are killed
    }

    private void checkWinningCondition() {
        boolean playerA = board.hasPiecesLeft("A-");
        boolean playerB = board.hasPiecesLeft("B-");

        if (!playerA) {
            System.out.println("Player B wins!");
            board.moves.add("Player B wins!");
        } else if (!playerB) {
            System.out.println("Player A wins!");
            board.moves.add("Player A wins!");
        }
    }
}
