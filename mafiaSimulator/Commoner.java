package mafiaSimulator;

import java.util.ArrayList;

public class Commoner extends Player {
    public Commoner(int number) {
        super(number, 1000);
    }


    @Override
    public Player action1(ArrayList<Player> playerList,ArrayList<Mafia>mafiaList) {
        return null;
    }

    @Override
    public Player action2(ArrayList<Player> playerList,ArrayList<Detective>detectiveList) {
        return null;
    }

    @Override
    public Player action3(ArrayList<Player> playerList) {
        return null;
    }
}
