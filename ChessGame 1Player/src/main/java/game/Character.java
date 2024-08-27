package game;

public abstract class Character {
    private String name;
    private int x, y;

    public Character(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean isValidMove(String move);
    public abstract boolean move(GameBoard board, String move);

    protected void killCharacter(GameBoard board, int x, int y) {
        Character character = board.getCharacterAt(x, y);
        if (character != null) {

            //checks if its Our teammate
            if (isSameTeam(character)) {
                board.moves.add(this.name + " cannot kill a teammate: " + character.getName());
                System.out.println(this.name + " cannot kill a teammate: " + character.getName());
                return;
            }
            /////////////////////////

            board.moves.add(this.name + " kills " + character.getName());
            System.out.println(this.name + " kills " + character.getName());
            board.removeCharacter(character);
        }
    }

    //checks if its Our teammate
    protected boolean isSameTeam(Character other) {
        return (other.getName().startsWith("A-") && this.name.startsWith("A-"))
                || (other.getName().startsWith("B-") && this.name.startsWith("B-"));
    }
}
