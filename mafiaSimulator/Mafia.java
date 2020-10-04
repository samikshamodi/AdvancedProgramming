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
    public boolean equals (Object o1)
    {
        if(o1!=null && getClass()==o1.getClass())
            return true;    //returns true if Mafia

        return false;
    }

    @Override
    public void playAs(ArrayList<Player> playerList) {
        //System.out.println("mafia playing");
        boolean done =false;
        Player chosenTarget=null;
        while (!done) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Choose a target: ");
                int q = in.nextInt();
                for (Player i: playerList)
                {
                    if(i.getNumber()==q)
                    {
                        if(!(this.equals((i))))
                        {
                            done=true;
                            chosenTarget=i;

                            break;
                        }
                        else
                        {
                            System.out.println("Cannot kill another mafia.");
                        }
                    }
                }
                if(!done)
                {
                    System.out.println("Invalid selection. Choose again.");
                }
            } catch (InputMismatchException inp) {
                System.out.println("Wrong input. Try again.");
            }
        }
        System.out.println("Detectives have chosen a player to test");

        Collections.shuffle(playerList);
        playerList.get(0).heal();
        System.out.println("Healers have chosen someone to heal");
        if(chosenTarget.getHp()>0)
        {
            System.out.println("No one was killed");
        }
        else
        {
            System.out.println(chosenTarget+" died.");
            playerList.remove(chosenTarget);//remove from playerList
            chosenTarget.kill();
        }
    }

    private void decreaseMafiaHp() {
    }


}
