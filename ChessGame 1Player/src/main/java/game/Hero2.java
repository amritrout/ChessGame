package game;

public class Hero2 extends Character {
    public Hero2(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public boolean isValidMove(String move) {

        return move.equals("FL") || move.equals("FR" ) || move.equals( "BL") || move.equals( "BR");
    }

    //function returns boolean to check if a move was successful or not
    @Override
    public boolean move(GameBoard board, String move) {
        int newX = getX();
        int newY = getY();
        int pathX = getX();  //path X and Y are used to kill piece in their path.
        int pathY = getY();

        //Calculate next location and the path in which the piece will travel
        switch (move) {
            case "FL":
                newX--;
                newY--;
                pathY--;
            break;
            case "FR":
                newX++;
                newY--;
                pathY--;
            break;
            case "BL":
                newX--;
                newY++;
                pathY++;
            break;
            case "BR":
                newX++;
                newY++;
                pathY++;
            break;
        }

        if (board.isInBounds(newX, newY)) {

            //Return if the destination is occupied by a teammate
            if (board.getCharacterAt(newX, newY) != null && isSameTeam(board.getCharacterAt(newX, newY))) {
                board.moves.add("Move blocked: Destination occupied by a teammate.");
                System.out.println("Move blocked: Destination occupied by a teammate.");
                return false;
            }

            killCharacter(board, newX, newY);//kill at destination
            killCharacter(board,pathX,pathY);//kill opponents in path
            board.moves.add("Moving Hero2 " + getName() + " to (" + newX + ", " + newY + ")");
            System.out.println("Moving Hero2 " + getName() + " to (" + newX + ", " + newY + ")");
            setPosition(newX, newY);
            return true;
        } else {
            board.moves.add("Move out of bounds.");
            System.out.println("Move out of bounds.");
            return false;
        }
    }
}
