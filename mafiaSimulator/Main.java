package mafiaSimulator;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int N = 0;
        boolean done;
        System.out.println("\nWelcome to Mafia");
        done = false;
        while (!done) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Enter number of Players:");
                N = in.nextInt();
                if (N < 6) {
                    System.out.println("Minimum 6 players required to play.");
                } else {
                    done = true;
                }

            } catch (InputMismatchException inp) {
                System.out.println("Wrong input. Try again.");
            }
        }
        //System.out.println(N);
        System.out.println("Choose a Character\n1)Mafia\n2)Detective\n3)Healer\n4)Commoner\n5)Assign randomly");
        int op = 0;
        done = false;
        while (!done) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Option:");
                op = in.nextInt();
                if (op < 0 || op > 5) {
                    System.out.println("Invalid option. Select again.");
                } else {
                    done = true;
                }

            } catch (InputMismatchException inp) {
                System.out.println("Wrong input. Try again.");
            }
        }
        //System.out.println(op);
        //if option 5 chosen, then assigning an integer from 1 to 4 randomly
        if (op == 5) {
            Random ran = new Random();
            op = ran.nextInt(3) + 1;
        }
        //System.out.println(op);

        int no_mafia = N / 5;
        int no_detective = N / 5;
        int no_healer = Math.max(1, N / 10);
        int no_commoner = N - no_mafia - no_detective - no_healer;
        System.out.println(no_mafia + " " + no_detective + " " + no_healer + " " + no_commoner); //TODO remove

        ArrayList<Mafia> mafiaList = new ArrayList<>();
        ArrayList<Detective> detectiveList = new ArrayList<>();
        ArrayList<Healer> healerList = new ArrayList<>();
        ArrayList<Commoner> commonerList = new ArrayList<>();
        ArrayList<Player>playerList=new ArrayList<>();

        //result string stores all the players role
        String result = "";

        ArrayList<Integer> assignPlayers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            assignPlayers.add(i + 1);
        }

        Collections.shuffle(assignPlayers);
        //TODO remove
        for (Integer i:assignPlayers)
        System.out.print(i+ " " );
        System.out.println();


        int cnt = 0;
        for (int i = 0; i < no_mafia; i++) {
            mafiaList.add(new Mafia(assignPlayers.get(cnt++)));
            result += mafiaList.get(i) + ", ";
            playerList.add(mafiaList.get(i));
        }
        result += "were mafia.\n";
        for (int i = 0; i < no_detective; i++) {
            detectiveList.add(new Detective(assignPlayers.get(cnt++)));
            result += detectiveList.get(i) + ", ";
            playerList.add(detectiveList.get(i));
        }
        result += "were detective.\n";
        for (int i = 0; i < no_healer; i++) {
            healerList.add(new Healer(assignPlayers.get(cnt++)));
            result += healerList.get(i) + ", ";
            playerList.add(healerList.get(i));
        }
        result += "were healer.\n";
        for (int i = 0; i < no_commoner; i++) {
            commonerList.add(new Commoner(assignPlayers.get(cnt++)));
            result += commonerList.get(i) + ", ";
            playerList.add(commonerList.get(i));
        }
        result += "were commoner.\n";


       /*//TODO remove
        for (int i = 0; i < no_mafia; i++) {
            System.out.println(mafiaList.get(i));
        }
        for (int i = 0; i < no_detective; i++) {
            System.out.println(detectiveList.get(i));
        }
        for (int i = 0; i < no_healer; i++) {
            System.out.println(healerList.get(i));
        }
        for (int i = 0; i < no_commoner; i++) {
            System.out.println(commonerList.get(i));
        }*/

        if (op == 1) {
            System.out.println("You are " + mafiaList.get(0));
            System.out.print("You are a mafia. Other mafias are: ");
            for (int i = 1; i < no_mafia; i++) {
                System.out.println(mafiaList.get(i) + ", ");
            }
        } else if (op == 2) {
            System.out.println("You are " + detectiveList.get(0));
            System.out.print("You are a detective. Other detectives are: ");
            for (int i = 1; i < no_detective; i++) {
                System.out.print(detectiveList.get(i) + ", ");
            }

        } else if (op == 3) {
            System.out.println("You are " + healerList.get(0));
            System.out.print("You are a healer. Other healers are: ");
            for (int i = 1; i < no_healer; i++) {
                System.out.println(healerList.get(i) + ",");
            }

        } else if (op == 4) {
            System.out.println("You are " + commonerList.get(0));
            System.out.println("You are a commoner.");
        }

        System.out.println("\n"+result);


    }

}
