package game;

public class Pawn extends Character {
    public Pawn(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public boolean isValidMove(String move) {
        return move.equals("L") || move.equals("R") || move.equals("F") || move.equals("B");
    }

    //function returns boolean to check if a move was successful or not
    @Override
    public boolean move(GameBoard board, String move) {
        int newX = getX();
        int newY = getY();

        switch (move) {
            case "L":
                newX--;
                break;
            case "R":
                newX++;
                break;
            case "F":
                newY--;
                break;
            case "B":
                newY++;
                break;
        }

        if (board.isInBounds(newX, newY)) {

            //return if someone it at the new destination
            if (board.getCharacterAt(newX, newY)!=null) {
                board.moves.add("Move blocked: Destination occupied by a Piece.");
                System.out.println("Move blocked: Destination occupied by a Piece.");
                return false;
            }

            setPosition(newX, newY); //jump to the new location
            board.moves.add("Moving Pawn " + getName() + " to (" + newX + ", " + newY + ")");
            System.out.println("Moving Pawn " + getName() + " to (" + newX + ", " + newY + ")");
            return true;
        } else {
            board.moves.add("Move out of bounds.");
            System.out.println("Move out of bounds.");
            return false;
        }
    }

}
