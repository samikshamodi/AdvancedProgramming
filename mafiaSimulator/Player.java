package mafiaSimulator;

import java.util.ArrayList;

public abstract class Player {

    private int number;
    protected int hp;
    String status;  //alive or dead

    public Player(int number, int hp) {
        this.number = number;
        this.hp = hp;
        this.status="alive";
    }

    public abstract void playAs(ArrayList<Player> playerList);

    protected int getNumber() {
        return number;
    }

    public String toString()
    {
        String s="Player "+number;
        return s;
    }

    public String getStatus()
    {
        return status;
    }

    public void heal()
    {
        hp+=500;
    }

    public int getHp()
    {
        return hp;
    }

    public void kill()
    {
        status="dead";
    }


}
