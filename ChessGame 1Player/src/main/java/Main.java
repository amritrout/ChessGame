
import game.Character;
import game.*;

public class Main {
    public static void main(String[] args) {
        // was using for debugging
        GameController controller = new GameController();

        Character pawn = new Pawn("P1", 2, 2);
        Character hero1 = new Hero1("H1", 1, 1);
        Character hero2 = new Hero2("H2", 3, 3);

        controller.getGameBoard().placeCharacter(pawn, 2, 2);
        controller.getGameBoard().placeCharacter(hero1, 1, 1);
        controller.getGameBoard().placeCharacter(hero2, 3, 3);

        controller.getGameBoard().printBoard();

        // Process moves
        controller.processMove("P1:R");
        controller.processMove("H1:F");
        controller.processMove("H2:BR");
    }
}
