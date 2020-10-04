package mafiaSimulator;

import java.util.ArrayList;

public class Healer extends Player{
    public Healer(int number) {
        super(number,800);
    }

    @Override
    public void playAs(ArrayList<Player> playerList) {
        System.out.println("healer playing");
    }


}
