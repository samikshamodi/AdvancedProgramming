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

    public abstract Player action1(ArrayList<Player> playerList,ArrayList<Mafia>mafiaList,Player user);
    public abstract Player action2(ArrayList<Player> playerList,ArrayList<Detective>detectiveList,Player user, int no_detective_alive);
    public abstract Player action3(ArrayList<Player> playerList,Player user,int no_healer_alive);

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

    public void setHp(int n)
    {
        this.hp=n;
    }


}
