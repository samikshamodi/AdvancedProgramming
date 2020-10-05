package mafiaSimulator;

import java.util.ArrayList;
import java.util.Collections;

public class Commoner extends Player {
    public Commoner(int number) {
        super(number, 1000);
    }


    @Override
    public Player action1(ArrayList<Player> playerList,ArrayList<Mafia>mafiaList,Player user) {
        System.out.println("Mafias have chosen their target");
        Collections.shuffle(playerList);
        for (Player i : playerList) {
            if (!(mafiaList.contains(i)))
                return i;
        }
        return null;
    }

    @Override
    public Player action2(ArrayList<Player> playerList,ArrayList<Detective>detectiveList,Player user,int no_detective_alive) {
        System.out.println("Detectives have chosen a player to test");

        if (no_detective_alive > 0) {
            Collections.shuffle(playerList);
            for (Player i : playerList) {
                if (!(detectiveList.contains(i)))
                    return i;
            }
        }
        return null;
    }

    @Override
    public Player action3(ArrayList<Player> playerList,Player user,int no_healer_alive) {
        System.out.println("Healers have chosen someone to heal");

        if (no_healer_alive > 0) {
            Collections.shuffle(playerList);
            return playerList.get(0);
        }
        return null;
    }
}