package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    Scanner in = new Scanner(System.in);
    private ArrayList<Restaurant> restaurantList;
    private ArrayList<Customer> customerList;
    public User(ArrayList<Restaurant> restaurantList, ArrayList<Customer> customerList){
        this.restaurantList=restaurantList;
        this.customerList=customerList;
    }

    void displayRestaurant() {
        System.out.println("\nChoose Restaurant");
        int i = 1;
        for (Restaurant r : restaurantList) {
            System.out.println(i++ + " " + r.getName() + " " + r.getCategory());
        }
    }

    void listRestaurant()
    {
        displayRestaurant();
        System.out.print("Query:");
        int op = in.nextInt();  //TODO op-1 since indexing in AL is from 0
        restaurantList.get(op - 1).restaurantRun();
    }


    void listCustomer() {
        System.out.println("\nChoose Customer");
        int i = 1;
        for (Customer c : customerList) {
            System.out.println(i++ + " " + c.getName() + " " + c.getCategory());
        }
        System.out.print("Query:");
        int op = in.nextInt();  //TODO op-1 since indexing in AL is from 0
        Customer selectedCustomer=customerList.get(op-1);
        System.out.println("\nWelcome " + selectedCustomer.getName() + "\nCustomer Menu\n" +
                "1) Select Restaurant\n" +
                "2) Checkout Cart\n" +
                "3) Reward Won\n" +
                "4) Print the recent order\n" +
                "5) Exit");

      /*  selectedCustomer.setCart(); //the selected customer gets a new cart
        int oq = in.nextInt();
        switch (oq) {
            case 1:
                displayRestaurant();
                System.out.print("Query:");
                int or = in.nextInt();  //or-1 since indexing in AL is from 0
                Restaurant selectedRestaurant=restaurantList.get(or-1);
                selectedCustomer.shop(selectedRestaurant);

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                return;
        }
*/
    }

    void checkUserDetails() {
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
                System.out.println(customerList.get(oq - 1));
                break;

            case 2:
                System.out.println("\nChoose Restaurant");
                int j = 1;
                for (Restaurant r : restaurantList) {
                    System.out.println(j++ + " " + r.getName());
                }
                System.out.print("Query:");
                int os = in.nextInt(); //TODO os-1 becuase indexing starts from 0
                System.out.println(restaurantList.get(os - 1));
                break;
        }
    }


}
