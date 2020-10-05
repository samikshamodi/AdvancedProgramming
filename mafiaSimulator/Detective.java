package mafiaSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Detective extends Player {
    public Detective(int number) {
        super(number, 800);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass()==this.getClass();
    }

    @Override
    public Player action1(ArrayList<Player> playerList, ArrayList<Mafia> mafiaList, Player user) {
        System.out.println("Mafias have chosen their target");
        Collections.shuffle(playerList);
        for (Player i : playerList) {
            if (!new Mafia(0).equals(i))
                return i;
        }
        return null;
    }

    @Override
    public Player action2(ArrayList<Player> playerList, ArrayList<Detective> detectiveList, Player user, int no_detective_alive) {

        System.out.println("NO of detective alive : "+no_detective_alive);      //TODO remove
        if(no_detective_alive<=0)
        {
            System.out.println("Detectives have chosen a player to test");
            return null;
        }

        if (user.getStatus().equals("dead")) {
            //if user is dead, then return random
            Collections.shuffle(playerList);
            for (Player i : playerList) {
                if (!new Detective(0).equals(i)) {
                    System.out.println("Detectives have chosen a player to test");
                    return i;
                }
            }
        }

        if (no_detective_alive > 0) {
            while (true) {
                try {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Choose a player to test: ");
                    int q = in.nextInt();
                    for (Player i : playerList) {
                        if (i.getNumber() == q) {
                            if (!new Detective(0).equals(i)) {
                                if (new Mafia(0).equals(i))
                                    System.out.println(i + " is a mafia.");
                                else {
                                    System.out.println(i + " is not a mafia.");
                                }
                                return i;
                            } else {
                                System.out.println("You cannot test a detective.");
                            }
                        }
                    }
                    System.out.println("Invalid selection. Choose again.");
                } catch (InputMismatchException inp) {
                    System.out.println("Wrong input. Try again.");
                }
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
}
