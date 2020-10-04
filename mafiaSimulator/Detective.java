package mafiaSimulator;

import java.util.ArrayList;

public class Detective extends Player {
    public Detective(int number) {
        super(number, 800);
    }

    @Override
    public void playAs(ArrayList<Player> playerList) {
        System.out.println("detective playing");
    }


}
