package game;

public class Hero1 extends Character {
    public Hero1(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public boolean isValidMove(String move) {
        return move.equals("L") || move.equals("R") || move.equals("F") || move.equals("B");
    }

    //move function returns boolean to check if a move was successful or not
    @Override
    public boolean move(GameBoard board, String move) {
        int newX = getX();
        int newY = getY();
        int pathX = getX(); // pathX and Y are used to check the opponents coming in the characters path to kill them
        int pathY = getY();

        switch (move) {
            case "L":
                newX -= 2;
                pathX-=1;
            break;
            case "R":
                newX += 2;
                pathX+=1;
            break;
            case "F":
                newY -= 2;
                pathY-=1;
            break;
            case "B":
                newY += 2;
                pathY+=1;
            break;
        }

        if (board.isInBounds(newX, newY) ) {

            //block if character in the same team
            if (board.getCharacterAt(newX, newY) != null && isSameTeam(board.getCharacterAt(newX, newY))) {
                board.moves.add("Move blocked: Destination occupied by a teammate.");
                System.out.println("Move blocked: Destination occupied by a teammate.");
                return false;
            }

            killCharacter(board, newX, newY);
            killCharacter(board,pathX,pathY);
            board.moves.add("Moving Hero1 " + getName() + " to (" + newX + ", " + newY + ")");
            System.out.println("Moving Hero1 " + getName() + " to (" + newX + ", " + newY + ")");
            setPosition(newX, newY);
            return true;
        } else {
            board.moves.add("Move out of bounds.");
            System.out.println("Move out of bounds.");
            return false;
        }
    }
}
