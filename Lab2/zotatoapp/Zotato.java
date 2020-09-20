package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;


public class Zotato {
    Scanner in = new Scanner(System.in);
    private ArrayList<Restaurant> restaurantList;
    private ArrayList<Customer> customerList;
    User user;
    public static int foodid=1;


    public Zotato() {
        restaurantList = new ArrayList<>();
        customerList = new ArrayList<>();

        customerList.add(new EliteCustomer("Ram", "Raipur", "(Elite)"));
        customerList.add(new EliteCustomer("Sam", "Shahpur", "(Elite)"));
        customerList.add(new SpecialCustomer("Tim", "Timarpur", "(Special)"));
        customerList.add(new Customer("Kim", "Kanpur", ""));
        customerList.add(new Customer("Jim", "Jodhpur", ""));

        restaurantList.add(new AuthenticRestaurant("Shah", "Shahpur", "(Authentic)"));
        restaurantList.add(new Restaurant("Ravi's", "Raipur", ""));
        restaurantList.add(new AuthenticRestaurant("The Chinese", "Timarpur", "(Authentic)"));
        restaurantList.add(new FastFoodRestaurant("Wang's", "Wazirpur", "(Fast Food)"));
        restaurantList.add(new Restaurant("Paradise", "Panipur", ""));

        user=new User(restaurantList,customerList);
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
                    user.listRestaurant();
                    break;
                case 2:
                    user.listCustomer();
                    break;
                case 3:
                    user.checkUserDetails();
                    break;
                case 4:
                    int cb=0;
                    int dc=0;
                    for(Restaurant r:restaurantList)
                    {
                        cb+=r.getCompanyBalance();
                        dc+=r.getDeliveryCharges();
                    }
                    System.out.println("Total Company Balance - INR"+" "+cb);
                    System.out.println("Total Delivery Charges Collected - INR"+" "+dc);

                    break;
                case 5:
                    System.out.println("Exiting App.\nGoodbye");
                    System.exit(0);
                    break;
            }
        }
    }
}

