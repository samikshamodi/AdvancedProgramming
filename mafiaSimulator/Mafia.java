package mafiaSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mafia extends Player {
    public Mafia(int number) {
        super(number, 2500);
    }

    //returns true if both are of same class
    @Override
    public boolean equals(Object obj) {
        return obj.getClass()==this.getClass();
    }

    @Override
    public Player action1(ArrayList<Player> playerList, ArrayList<Mafia> mafiaList, Player user) {
        //System.out.println("mafia playing");

        if (user.getStatus().equals("dead")) {
            //if user is dead, then return random
            Collections.shuffle(playerList);
            for (Player i : playerList) {
                if (!new Mafia(0).equals(i)) {
                    System.out.println("Mafias have chosen their target");
                    return i;
                }
            }
        }

        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Choose a target: ");
                int q = in.nextInt();
                for (Player i : playerList) {
                    if (i.getNumber() == q) {
                        if (!new Mafia(0).equals(i)) {
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
    public Player action2(ArrayList<Player> playerList, ArrayList<Detective> detectiveList, Player user, int no_detective_alive) {
        System.out.println("Detectives have chosen a player to test");

        if (no_detective_alive > 0) {
            Collections.shuffle(playerList);
            for (Player i : playerList) {
                if (!new Detective(0).equals(i))
                    return i;
            }
        }
        return null;
    }

    @Override
    public Player action3(ArrayList<Player> playerList, Player user, int no_healer_alive) {
        System.out.println("Healers have chosen someone to heal");

        if (no_healer_alive > 0) {
            Collections.shuffle(playerList);
            return playerList.get(0);
        }
        return null;
    }

     /*if (chosenTarget.getHp() > 0) {
        System.out.println("No one was killed");
    } else {
        System.out.println(chosenTarget + " died.");
        playerList.remove(chosenTarget);//remove from playerList
        chosenTarget.kill();
    }*/

}
