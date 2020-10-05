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

        //if option 5 chosen, then assigning an integer from 1 to 4 randomly
        if (op == 5) {
            Random ran = new Random();
            op = ran.nextInt(3) + 1;
        }

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
        System.out.println(assignPlayers);

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


        //assigning roles to all players
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


        //Starting game
        System.out.println();
        int round = 1;
        Player mafiaTarget, detectiveTarget, healerTarget, votingTarget;
        while (!endOfGame()) {
            mafiaTarget = null;
            detectiveTarget = null;
            healerTarget = null;
            votingTarget = null;
            System.out.println("\nRound " + round++);
            System.out.print(playerList.size() + " players are remaining: ");
            for (Player i : playerList) {
                System.out.print(i + ", ");
            }
            System.out.println("are alive.");

            //TODO add check, do an action only if mafia detective and healer are alive
            mafiaTarget = user.action1(playerList, mafiaList, user);
            detectiveTarget = user.action2(playerList, detectiveList, user,no_detective_alive());
            healerTarget = user.action3(playerList, user,no_healer_alive());

            System.out.println("----- " + mafiaTarget + detectiveTarget + healerTarget + "-----");    //TODO remove

            //assert mafiaTarget!=null;
            //did someone die. reduce hp of mafia and target then print
            //heal only if healers are alive
            int X = mafiaTarget.getHp();
            mafiaTargetTakeDamage(mafiaTarget);
            mafiaTakeDamage(X);
            if (healerTarget != null) {
                healerTarget.heal();
            }

            if (mafiaTarget.getHp() > 0) {
                System.out.println("No one has died");
            } else {
                mafiaTarget.kill();
                playerList.remove(mafiaTarget);
                System.out.println(mafiaTarget + " has died.");
            }
            //after killing check if game has ended
            if (endOfGame())
                break;


            //detective tested and it was mafia
            if (no_detective_alive() > 0 && detectiveTarget != null && mafiaList.contains(detectiveTarget)) {
                System.out.println(detectiveTarget + " has been voted out");
                detectiveTarget.kill(); //IMPORTANT!!! whenever removing from playeerList always kill them
                playerList.remove(detectiveTarget); //remove from playerList aka list of players still in game
                continue;
            }

            votingTarget = playerList.get(0);//TODO REMOVE abhi !!!!!!!!!!!!!!!!!!

            //else if detective did not test a mafia then voting and we remove the person. KILL HIMMM
            if (user.getStatus() == "alive") {
                done = false;
                while (!done) {
                    try {
                        Scanner in = new Scanner(System.in);
                        System.out.print("Select a person to vote out: ");
                        int q = in.nextInt();
                        for (Player i : playerList) {
                            if (i.getNumber() == q) {
                                done = true;
                                votingTarget = i;
                                break;
                            }
                        }
                        if (!done) {
                            System.out.println("Invalid selection. Choose again.");
                        }

                    } catch (InputMismatchException inp) {
                        System.out.println("Wrong input. Try again.");
                    }
                }
            }
            Collections.shuffle(playerList);    //random vote
            //votingTarget = playerList.get(0);//TODO REMOVE abhi
            System.out.println(votingTarget+" has been voted out.");
            votingTarget.kill();
            playerList.remove(votingTarget);


            /*//if the user is no longer alive, then end the game; //LOGICAL ERROR. INPUT BELOW
            if (user.getStatus()=="dead") {
                System.out.println("The Mafias have lost");
                break;
            }*/
        }

      /*  //TODO remove
        System.out.print(playerList.size() + " players are remaining: ");
        for (Player i : playerList) {
            System.out.print(i + ", ");
        }
        System.out.println("are alive.");*/

        //Game has ended
        System.out.println(roles);
    }

    private static void mafiaTakeDamage(int X) {
        int Y = 0;
        for (Mafia i : mafiaList) {
            if (i.getStatus() == "alive" && i.getHp() > 0) {
                Y++;
            }
        }
        if(Y==0)
        {
            return;
        }

        mafiaList.sort(new hpSorter());
        System.out.println("%%%%BEFORE"+mafiaList);//TODO remove
        for(Mafia i:mafiaList)
        {
            if(i.getStatus()=="alive" && i.getHp()>0)
            {
                if(i.getHp()<X/Y)
                {
                    X-=i.getHp();
                    i.setHp(0);
                    Y--;
                    if(Y==0)
                    {
                        return;
                    }
                }
                else
                {
                    i.setHp((i.getHp()-(X/Y)));
                }
            }
        }
        System.out.println("%%%%AFTER"+mafiaList);//TODO remove

    }

    private static void mafiaTargetTakeDamage(Player mafiaTarget) {
        int mafiaHp = 0;
        for (Mafia i : mafiaList) {
            if (i.getStatus() == "alive") {
                mafiaHp += i.getHp();
            }
        }
        mafiaTarget.setHp(Math.max(0, mafiaTarget.getHp() - mafiaHp));
    }

    private static int no_mafia_alive() {
        int mcnt = 0;
        for (Mafia i : mafiaList) {
            if (i.getStatus() == "alive")
                mcnt++;
        }
        return mcnt;
    }

    private static int no_detective_alive() {
        int dcnt = 0;
        for (Detective i : detectiveList) {
            if (i.getStatus() == "alive")
                dcnt++;
        }
        return dcnt;
    }

    private static int no_healer_alive() {
        int hcnt = 0;
        for (Healer i : healerList) {
            if (i.getStatus() == "alive")
                hcnt++;
        }
        return hcnt;
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
        //System.out.println("---mcnt " + mcnt + "        " + (dcnt + hcnt + ccnt) + "------");
        //if end of game
        if (mcnt == 0) {
            System.out.println("\nGame Over\nThe Mafias have lost.");
            return true;
        } else if (mcnt == (dcnt + hcnt + ccnt)) {
            System.out.println("\nGame Over\nThe Mafias have won.");
            return true;
        } else {
            return false;
        }
    }

    public static class hpSorter implements Comparator<Mafia>
    {
        @Override
        public int compare(Mafia o1, Mafia o2) {
            return o1.getHp()-o2.getHp();
        }
    }
}

