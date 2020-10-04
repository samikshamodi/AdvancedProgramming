package mafiaSimulator;

import java.util.ArrayList;

public class Detective extends Player {
    public Detective(int number) {
        super(number, 800);
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
