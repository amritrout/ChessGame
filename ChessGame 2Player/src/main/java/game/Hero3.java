package game;

public class Hero3 extends Character {
    public Hero3(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public boolean isValidMove(String move ) {
        return move.equals("FL") || move.equals("FR") || move.equals("BL") || move.equals("BR") ||
                move.equals("RF") || move.equals("RB") || move.equals("LF") || move.equals("LB");
    }

    //function returns boolean to check if a move was successful or not
    @Override
    public boolean move(GameBoard board, String move) {
        int newX = getX();
        int newY = getY();

        switch (move) {
            case "FL":
                newX -= 1;
                newY -= 2;
                break;
            case "FR":
                newX += 1;
                newY -= 2;
                break;
            case "BL":
                newX -= 1;
                newY += 2;
                break;
            case "BR":
                newX += 1;
                newY += 2;
                break;
            case "RF":
                newX += 2;
                newY -= 1;
                break;
            case "RB":
                newX += 2;
                newY += 1;
                break;
            case "LF":
                newX -= 2;
                newY -= 1;
                break;
            case "LB":
                newX -= 2;
                newY += 1;
                break;
            default:
                return false;
        }

        if (board.isInBounds(newX, newY)) {
            Character destination = board.getCharacterAt(newX, newY);

            if (destination != null && isSameTeam(destination)) {
                board.moves.add("Move blocked: Destination occupied by a teammate.");
                System.out.println("Move blocked: Destination occupied by a teammate.");
                return false;
            }

            killCharacter(board, newX, newY);
            board.moves.add("Moving Hero3 " + getName() + " to (" + newX + ", " + newY + ")");
            System.out.println("Moving Hero3 " + getName() + " to (" + newX + ", " + newY + ")");
            setPosition(newX, newY);
            return true;
        } else {
            board.moves.add("Move out of bounds.");
            System.out.println("Move out of bounds.");
            return false;
        }
    }
}
