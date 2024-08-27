package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    public static int size = 5;
    private Map<String, Character> characters = new HashMap<>();
    public ArrayList<String> moves = new ArrayList<>();

    public boolean isInBounds(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public void placeCharacter(Character character, int x, int y) {
        if (isInBounds(x, y)) {
            if(getCharacterAt(x,y)!=null){
                removeCharacter(getCharacterAt(x,y));
            }
            character.setPosition(x, y);
            characters.put(character.getName(), character);
            moves.add("Placed " + character.getName() + " at (" +x + ", " + y + ")");
            System.out.println("Placed " + character.getName() + " at (" + x + ", " + y + ")");
        }
    }

    public void clearBoard() {
        characters.clear();
    }

    public Character getCharacter(String name) {
        return characters.get(name);
    }

    public Character getCharacterAt(int x, int y) {
        for (Character c : characters.values()) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }

    public void removeCharacter(Character character) {
        characters.remove(character.getName());
    }


    //gets board state to send to server
    public String getBoardState() {
        StringBuilder sb = new StringBuilder();
        String[][] board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "----";
            }
        }
        for (Character c : characters.values()) {
            board[c.getY()][c.getX()] = c.getName();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    // debugging
    public void printBoard() {
        String[][] board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "----";
            }
        }
        for (Character c : characters.values()) {
            board[c.getY()][c.getX()] = c.getName();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    //moves arraylist stores all the logs and send it to client
    public String getMove() {
        if (moves.isEmpty()) {
            return "Game Started";
        }
        return moves.get(moves.size() - 1);
    }

    // to check if player has any pieces left
    public boolean hasPiecesLeft(String playerPrefix) {
        for (Character character : characters.values()) {
            if (character.getName().startsWith(playerPrefix)) {
                return true;
            }
        }
        return false;
    }
}
