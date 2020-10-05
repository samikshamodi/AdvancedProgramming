package mafiaSimulator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Detective extends Player {
    public Detective(int number) {
        super(number, 800);
    }


    @Override
    public Player action(ArrayList<Player> playerList) {
        //no of detective >0
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Choose a player to test: ");
                int q = in.nextInt();
                for (Player i : playerList) {
                    if (i.getNumber() == q) {
                        if (!(i.getClass() == Detective.class)) {
                            if (i.getClass() == Mafia.class)
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

}