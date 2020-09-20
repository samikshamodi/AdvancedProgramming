package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    Scanner in = new Scanner(System.in);

    public User() {

    }

    void listRestaurant(ArrayList<Restaurant> restaurantList) {
        System.out.println("\nChoose Restaurant");
        int i = 1;
        for (Restaurant r : restaurantList) {
            System.out.println(i++ + " " + r.getName() + " " + r.getCategory());
        }
        System.out.print("Query:");
        int op = in.nextInt();  //TODO op-1 since indexing in AL is from 0
        restaurantList.get(op-1).restaurantRun();
    }

    void listCustomer(ArrayList<Customer> customerList) {
        System.out.println("\nChoose Customer");
        int i = 1;
        for (Customer c : customerList) {
            System.out.println(i++ + " " + c.getName() + " " + c.getCategory());
        }
        System.out.print("Query:");
        int op = in.nextInt();
    }

    void checkUserDetails(ArrayList<Restaurant> restaurantList, ArrayList<Customer> customerList) {
        System.out.println("\n1) Customer List\n2) Restaurant List");
        System.out.print("Query:");
        int op = in.nextInt();

        switch (op) {
            case 1:
                System.out.println("\nChoose Customer");
                int i = 1;
                for (Customer c : customerList) {
                    System.out.println(i++ + " " + c.getName());
                }
                System.out.print("Query:");
                int oq = in.nextInt();  //TODO oq-1 becuase indexing starts from 0
                System.out.println(customerList.get(oq-1));
                break;

            case 2:
                System.out.println("\nChoose Restaurant");
                int j = 1;
                for (Restaurant r : restaurantList) {
                    System.out.println(j++ + " " + r.getName());
                }
                System.out.print("Query:");
                int os = in.nextInt(); //TODO os-1 becuase indexing starts from 0
                System.out.println(restaurantList.get(os-1));
                break;
        }
    }


}
