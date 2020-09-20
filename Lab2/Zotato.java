package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Zotato {
    Scanner in = new Scanner(System.in);
    User user;

    public Zotato() {
        user=new User();
    }

    void run() {
        while (true) {
            System.out.println("Welcome to Zotato:\n" +
                    "1) Enter as Restaurant Owner\n" +
                    "2) Enter as Customer\n" +
                    "3) Check User Details\n" +
                    "4) Company Account Details\n" +
                    "5) Exit");

            int op = in.nextInt();
            switch (op) {
                case 1:
                    user.listRestaurant();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Exiting App.\nGoodbye");
                    System.exit(0);
                    break;
            }
        }
    }
}

