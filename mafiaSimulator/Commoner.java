package mafiaSimulator;

import java.util.ArrayList;

public class Commoner extends Player {
    public Commoner(int number) {
        super(number, 1000);
    }

    @Override
    public void playAs(ArrayList<Player> playerList) {
        System.out.println( "commoner playing");

    }


}
