package mafiaSimulator;

public abstract class Player {

    private int number;
    protected int hp;

    public Player(int number, int hp) {
        this.number = number;
        this.hp = hp;
    }

    public abstract void playAs();

    protected int getNumber() {
        return number;
    }

    public String toString()
    {
        String s="Player "+number;
        return s;
    }
}
