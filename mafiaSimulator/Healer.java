package mafiaSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Healer extends Player {
    public Healer(int number) {
        super(number, 800);
    }


    @Override
    public Player action(ArrayList<Player> playerList,Player user) {

        if (user.getStatus().equals("dead")) {
            //if user is dead, then return random
            Collections.shuffle(playerList);
            return playerList.get(0);
        }


        //no of healer>0
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Choose a player to heal: ");
                int q = in.nextInt();
                for (Player i : playerList) {
                    if (i.getNumber() == q) {
                        return i;
                    }
                }
                System.out.println("Invalid selection. Choose again.");
            } catch (InputMismatchException inp) {
                System.out.println("Wrong input. Try again.");
            }
        }
    }
}