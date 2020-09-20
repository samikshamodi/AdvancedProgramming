package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;


public class Zotato {
    Scanner in = new Scanner(System.in);
    private ArrayList<Restaurant> restaurantList;
    private ArrayList<Customer> customerList;
    public static int foodid = 1;


    public Zotato() {
        restaurantList = new ArrayList<>();
        customerList = new ArrayList<>();

        restaurantList.add(new AuthenticRestaurant("Shah", "Shahpur", "(Authentic)"));
        restaurantList.add(new Restaurant("Ravi's", "Raipur", ""));
        restaurantList.add(new AuthenticRestaurant("The Chinese", "Timarpur", "(Authentic)"));
        restaurantList.add(new FastFoodRestaurant("Wang's", "Wazirpur", "(Fast Food)"));
        restaurantList.add(new Restaurant("Paradise", "Panipur", ""));

        customerList.add(new EliteCustomer("Ram", "Raipur", "(Elite)",restaurantList));
        customerList.add(new EliteCustomer("Sam", "Shahpur", "(Elite)",restaurantList));
        customerList.add(new SpecialCustomer("Tim", "Timarpur", "(Special)",restaurantList));
        customerList.add(new Customer("Kim", "Kanpur", "",restaurantList));
        customerList.add(new Customer("Jim", "Jodhpur", "",restaurantList));

    }


    void zotatoRun() {
        while (true) {
            System.out.println("\nWelcome to Zotato:\n" +
                    "1) Enter as Restaurant Owner\n" +
                    "2) Enter as Customer\n" +
                    "3) Check User Details\n" +
                    "4) Company Account Details\n" +
                    "5) Exit");

            System.out.print("Query:");
            int op = in.nextInt();

            switch (op) {
                case 1:
                    System.out.println("\nChoose Restaurant");
                    int m = 1;
                    for (Restaurant r : restaurantList) {
                        System.out.println(m++ + " " + r.getName() + " " + r.getCategory());
                    }
                    System.out.print("Query:");
                    int on = in.nextInt();  //TODO on-1 since indexing in AL is from 0
                    restaurantList.get(on - 1).login();
                    break;
                case 2:
                    System.out.println("\nChoose Customer");
                    int i = 1;
                    for (Customer c : customerList) {
                        System.out.println(i++ + " " + c.getName() + " " + c.getCategory());
                    }
                    System.out.print("Query:");
                    int oq = in.nextInt();  //TODO oq-1 since indexing in AL is from 0
                    customerList.get(oq - 1).login();
                    break;
                case 3:
                    System.out.println("\n1) Customer List\n2) Restaurant List");
                    System.out.print("Query:");
                    int or = in.nextInt();

                    switch (or) {
                        case 1:
                            System.out.println("\nChoose Customer");
                            int j = 1;
                            for (Customer c : customerList) {
                                System.out.println(j++ + " " + c.getName());
                            }
                            System.out.print("Query:");
                            int os = in.nextInt();  //TODO os-1 becuase indexing starts from 0
                            System.out.println(customerList.get(os - 1));
                            break;

                        case 2:
                            System.out.println("\nChoose Restaurant");
                            int k = 1;
                            for (Restaurant r : restaurantList) {
                                System.out.println(k++ + " " + r.getName());
                            }
                            System.out.print("Query:");
                            int ot = in.nextInt(); //TODO ot-1 becuase indexing starts from 0
                            System.out.println(restaurantList.get(ot - 1));
                            break;
                    }
                    break;
                case 4:
                    int cb = 0;
                    int dc = 0;
                    for (Restaurant r : restaurantList) {
                        cb += r.getCompanyBalance();
                        dc += r.getDeliveryCharges();
                    }
                    System.out.println("Total Company Balance - INR" + " " + cb);
                    System.out.println("Total Delivery Charges Collected - INR" + " " + dc);

                    break;
                case 5:
                    System.out.println("Exiting App.\nGoodbye");
                    System.exit(0);
                    break;
            }
        }
    }
}

