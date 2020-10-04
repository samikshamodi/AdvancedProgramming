package mafiaSimulator;

import java.util.ArrayList;

public class Healer extends Player{
    public Healer(int number) {
        super(number,800);
    }


    @Override
    public Player action1(ArrayList<Player> playerList,ArrayList<Mafia>mafiaList,Player user) {
        return null;
    }

    @Override
    public Player action2(ArrayList<Player> playerList,ArrayList<Detective>detectiveList,Player user,int no_detective_alive) {
        return null;
    }

    @Override
    public Player action3(ArrayList<Player> playerList,Player user,int no_healer_alive) {
        return null;
    }
}
