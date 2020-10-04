package mafiaSimulator;

import java.util.*;

public class Main {
    private static ArrayList<Mafia> mafiaList = new ArrayList<>();
    private static ArrayList<Detective> detectiveList = new ArrayList<>();
    private static ArrayList<Healer> healerList = new ArrayList<>();
    private static ArrayList<Commoner> commonerList = new ArrayList<>();
    private static ArrayList<Player> playerList = new ArrayList<>();

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


        //result string stores all the players role
        String roles = "";

        ArrayList<Integer> assignPlayers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            assignPlayers.add(i + 1);
        }

        Collections.shuffle(assignPlayers);
        //TODO remove
        for (Integer i : assignPlayers)
            System.out.print(i + " ");
        System.out.println();


        int cnt = 0;
        for (int i = 0; i < no_mafia; i++) {
            mafiaList.add(new Mafia(assignPlayers.get(cnt++)));
            roles += mafiaList.get(i) + ", ";
            playerList.add(mafiaList.get(i));
        }
        roles += "were mafia.\n";
        for (int i = 0; i < no_detective; i++) {
            detectiveList.add(new Detective(assignPlayers.get(cnt++)));
            roles += detectiveList.get(i) + ", ";
            playerList.add(detectiveList.get(i));
        }
        roles += "were detective.\n";
        for (int i = 0; i < no_healer; i++) {
            healerList.add(new Healer(assignPlayers.get(cnt++)));
            roles += healerList.get(i) + ", ";
            playerList.add(healerList.get(i));
        }
        roles += "were healer.\n";
        for (int i = 0; i < no_commoner; i++) {
            commonerList.add(new Commoner(assignPlayers.get(cnt++)));
            roles += commonerList.get(i) + ", ";
            playerList.add(commonerList.get(i));
        }
        roles += "were commoner.\n";


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
        Player user;
        if (op == 1) {
            System.out.println("You are " + mafiaList.get(0));
            System.out.print("You are a mafia. Other mafias are: ");
            for (int i = 1; i < no_mafia; i++) {
                System.out.print(mafiaList.get(i) + ", ");
            }
            user = mafiaList.get(0);
        } else if (op == 2) {
            System.out.println("You are " + detectiveList.get(0));
            System.out.print("You are a detective. Other detectives are: ");
            for (int i = 1; i < no_detective; i++) {
                System.out.print(detectiveList.get(i) + ", ");
            }
            user = detectiveList.get(0);

        } else if (op == 3) {
            System.out.println("You are " + healerList.get(0));
            System.out.print("You are a healer. Other healers are: ");
            for (int i = 1; i < no_healer; i++) {
                System.out.print(healerList.get(i) + ",");
            }
            user = healerList.get(0);

        } else {
            System.out.println("You are " + commonerList.get(0));
            System.out.print("You are a commoner.");
            user = commonerList.get(0);
        }
        System.out.println();
        int round=1;
        while (!endOfGame()) {
            System.out.println("Round "+round++);
            System.out.print(playerList.size()+" players are remaining: ");
            for (Player i:playerList)
            {
                System.out.print(i+", ");
            }
            System.out.println("are alive.");
            user.playAs(playerList);
        }

        System.out.println("\n" + roles);
    }

    private static boolean endOfGame() {
        int mcnt = 0;
        int dcnt = 0;
        int hcnt = 0;
        int ccnt = 0;
        for (Mafia i : mafiaList) {
            if (i.getStatus() == "alive") {
                mcnt++;
            }
        }

        for (Detective i : detectiveList) {
            if (i.getStatus() == "alive") {
                dcnt++;
            }
        }
        for (Healer i : healerList) {
            if (i.getStatus() == "alive") {
                hcnt++;
            }
        }

        for (Commoner i : commonerList) {
            if (i.getStatus() == "alive") {
                ccnt++;
            }
        }

        //if end of game
        if (mcnt == 0) {
            System.out.println("The Mafias have lost.");
            return true;
        } else if (mcnt == dcnt + hcnt + ccnt) {
            System.out.println("The Mafias have won.");
            return true;
        } else {
            return false;
        }
    }

}
