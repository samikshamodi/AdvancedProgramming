package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;

/*regular
    fast food
    authentic */
public class Restaurant implements Login {
    Scanner in = new Scanner(System.in);
    private final String name;
    private final String address;
    int ordersTaken;
    private final String category;
    private int rewardPoints;
    private int companyBalance;     //option 4
    private int deliveryCharges;    //option 4
    private int billDiscount;
    private ArrayList<FoodItem> foodItemList;

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

    void displayFoodItems()
    {
        for(FoodItem f:foodItemList)
        {
            System.out.println(f);
        }
    }

    FoodItem getFoodItem(int foodid)
    {
        for(FoodItem f:foodItemList)
        {
            if (f.getId()==foodid)
            {
                return f;
            }
        }
        return null;
    }


    int getCompanyBalance() {
        return companyBalance;
    }

    int getDeliveryCharges() {
        return deliveryCharges;
    }

    int getBillDiscount()
    {
        return  billDiscount;
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
        foodItemList.add(f);
        System.out.println(f.display());
        Zotato.foodid += 1;
    }

    void editItem() {
        System.out.println("Choose item by code");
        for (FoodItem f : foodItemList) {
            System.out.println(f);
        }

        System.out.print("Query:");
        int op = in.nextInt();

        FoodItem foodItemToBeUpdated = null;
        for (FoodItem f : foodItemList) {
            if (f.getId() == op)
            {
                foodItemToBeUpdated = f;
                break;
            }
        }

        System.out.println("\nChoose an attribute to edit: " + "\n" +
                "1) Name\n" +
                "2) Price\n" +
                "3) Quantity\n" +
                "4) Category\n" +
                "5) Offer");
        int oq = in.nextInt();
        switch (oq) {
            case 1:
                System.out.print("Enter the new name:");
                String s1 = in.next();
                assert foodItemToBeUpdated != null;
                foodItemToBeUpdated.setName(s1);
                System.out.println(foodItemToBeUpdated);
                break;
            case 2:
                System.out.print("Enter the new price:");
                int s2 = in.nextInt();
                assert foodItemToBeUpdated != null;
                foodItemToBeUpdated.setPrice(s2);
                System.out.println(foodItemToBeUpdated);
                break;
            case 3:
                System.out.print("Enter the new Quantity");
                int s3 = in.nextInt();
                assert foodItemToBeUpdated != null;
                foodItemToBeUpdated.setQuantity(s3);
                System.out.println(foodItemToBeUpdated);
                break;
            case 4:
                System.out.print("Enter the new Category:");
                String s4 = in.next();
                assert foodItemToBeUpdated != null;
                foodItemToBeUpdated.setName(s4);
                System.out.println(foodItemToBeUpdated);
                break;
            case 5:
                System.out.print("Enter the new Offer:");
                int s5 = in.nextInt();
                assert foodItemToBeUpdated != null;
                foodItemToBeUpdated.setDiscount(s5);
                System.out.println(foodItemToBeUpdated);
                break;
        }

    }


    @Override
    public void login() {
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
                    editItem();
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



