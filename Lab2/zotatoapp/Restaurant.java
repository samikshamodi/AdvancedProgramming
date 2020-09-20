package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;

/*regular
    fast food
    authentic */
public class Restaurant {
    Scanner in = new Scanner(System.in);
    private final String name;
    private final String address;
    int ordersTaken;
    private final String category;
    int rewardPoints;
    int companyBalance;     //option 4
    int deliveryCharges;    //option 4
    int billDiscount;
    ArrayList<FoodItem> foodItemList;

    public Restaurant(String name, String address, String category) {
        this.name = name;
        this.address = address;
        this.ordersTaken = 0;
        this.category = category;
        this.rewardPoints = 0;
        this.companyBalance = 0;
        this.deliveryCharges = 0;
        this.billDiscount = 0;
        foodItemList = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    String getCategory() {
        return category;
    }

    public String toString() {
        String details = "";
        details += name + " " + address + " " + " " + ordersTaken;
        return details;
    }

    int getCompanyBalance() {
        return companyBalance;
    }

    int getDeliveryCharges() {
        return deliveryCharges;
    }

    void addItem() {
        System.out.println("\nEnter food item details");
        System.out.print("Food name:");
        String fname = in.next();
        System.out.print("Item price: ");
        int fprice = in.nextInt();
        System.out.print("Item quantity: ");
        int fquantity = in.nextInt();
        System.out.print("Item category: ");
        String fcategory = in.next();
        System.out.print("Offer: ");
        int foffer = in.nextInt();
        FoodItem f = new FoodItem(Zotato.foodid, fname, fprice, fquantity, fcategory, foffer, this);
        System.out.println(f.display());
        Zotato.foodid +=1;
    }

    void restaurantRun() {
        while (true) {
            System.out.println("\nWelcome " + name + "\n" +
                    "1) Add item\n" +
                    "2) Edit item\n" +
                    "3) Print Rewards\n" +
                    "4) Discount on bill value\n" +
                    "5) Exit");

            System.out.print("Query:");
            int op = in.nextInt();
            switch (op) {
                case 1:
                    addItem();
                    break;
                case 2:
                    //editItem();
                    break;
                case 3:
                    System.out.println("Reward Points: " + rewardPoints);
                    break;
                case 4:
                    System.out.print("Offer on bill value: ");
                    /* regular restaurant*/
                    if (category == "") {
                        System.out.println(0);
                    } else {
                        int bd = in.nextInt();
                        billDiscount = bd;
                    }
                    break;
                case 5:
                    return;
            }
        }

    }


}



