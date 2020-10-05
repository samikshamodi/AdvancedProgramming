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
    public Player action(ArrayList<Player> playerList, Player user) {
        //System.out.println("mafia playing");
        if (user.getStatus().equals("dead")) {
            //if user is dead, then return random
            Collections.shuffle(playerList);
            for (Player i : playerList) {
                if (!(i.getClass()==Mafia.class)) {
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
                        if (!(i.getClass()==Mafia.class)) {
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

}