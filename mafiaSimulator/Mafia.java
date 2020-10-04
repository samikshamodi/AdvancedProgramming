package mafiaSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mafia extends Player {
    public Mafia(int number) {
        super(number, 2500);
    }


    @Override
    public Player action1(ArrayList<Player> playerList,ArrayList<Mafia>mafiaList) {
        //System.out.println("mafia playing");
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Choose a target: ");
                int q = in.nextInt();
                for (Player i : playerList) {
                    if (i.getNumber() == q) {
                        if (!(mafiaList.contains(i))) {
                            return i;
                        } else {
                            System.out.println("Cannot kill another mafia.");
                        }
                    }
                }
                System.out.println("Invalid selection. Choose again.");
            } catch (InputMismatchException inp) {
                System.out.println("Wrong input. Try again.");
            }
        }
    }

    @Override
    public Player action2(ArrayList<Player> playerList,ArrayList<Detective>detectiveList) {
        System.out.println("Detectives have chosen a player to test");
        Collections.shuffle(playerList);
        for(Player i:playerList)
        {
            if(!(detectiveList.contains(i)))
                return i;
        }
        return null;
    }

    @Override
    public Player action3(ArrayList<Player> playerList) {
        System.out.println("Healers have chosen someone to heal");
        Collections.shuffle(playerList);
        return playerList.get(0);
    }

     /*if (chosenTarget.getHp() > 0) {
        System.out.println("No one was killed");
    } else {
        System.out.println(chosenTarget + " died.");
        playerList.remove(chosenTarget);//remove from playerList
        chosenTarget.kill();
    }*/

}
