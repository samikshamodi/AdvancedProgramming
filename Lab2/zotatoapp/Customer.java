package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;

/*regular
    elite
    special*/
public class Customer implements Login {
    Scanner in=new Scanner(System.in);
    private String name;
    private String address;
    int wallet;
    private String category;
    int rewardPoints;
    private Cart myCart;
    ArrayList<Restaurant> restaurantList;


    public Customer(String name, String address, String category, ArrayList<Restaurant> restaurantList) {
        this.name = name;
        this.address = address;
        this.wallet = 1000;
        this.category = category;
        this.rewardPoints = 0;
        myCart = null;
        this.restaurantList = restaurantList;

    }

    String getName() {
        return name;
    }

    String getCategory() {
        return category;
    }

    public String toString() {
        String details = "";
        details += name + " " + category + " " + address + " " + " " + wallet;
        return details;
    }

    void shop(Restaurant selectedRestaurant)
    {

    }

    @Override
    public void login() {
        System.out.println("\nWelcome " + name + "\nCustomer Menu\n" +
                "1) Select Restaurant\n" +
                "2) Checkout Cart\n" +
                "3) Reward Won\n" +
                "4) Print the recent order\n" +
                "5) Exit");

        myCart = new Cart();
        int ou = in.nextInt();
        switch (ou) {
            case 1:
                System.out.println("\nChoose Restaurant");
                int i = 1;
                for (Restaurant r : restaurantList) {
                    System.out.println(i++ + " " + r.getName() + " " + r.getCategory());
                }
                System.out.print("Query:");
                int ov = in.nextInt();  //ov-1 since indexing in AL is from 0
                Restaurant selectedRestaurant = restaurantList.get(ov - 1);

                System.out.println("Choose item by code");
                selectedRestaurant.displayFoodItems();
                System.out.print("Query:");
                int ow = in.nextInt();  //ow-1 since indexing in AL is from 0
                FoodItem selectedFoodItem=selectedRestaurant.getFoodItem(ow);
                System.out.print("Enter item quantity - ");
                int q=in.nextInt();
                if(selectedFoodItem.getQuantity()==1)
                {
                    System.out.println("Restaurant ran out of this item");
                }
                else if(q>selectedFoodItem.getQuantity())
                {
                    System.out.println("The restaurant only has only " + selectedFoodItem.getQuantity()+" quantity of this item");
                }
                else
                {
                    //TODO add items to cart;
                    myCart.addToCart(selectedFoodItem,q);
                    selectedFoodItem.reduceQuantity(q);
                    System.out.println("Items added to cart");
                }
                shop(selectedRestaurant);
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

    }
}
