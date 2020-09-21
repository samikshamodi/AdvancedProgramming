package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Cart {
    Scanner in = new Scanner(System.in);
    private ArrayList<FoodItem> itemList;


    public Cart() {
        itemList = new ArrayList<>();
    }

    void addToCart(FoodItem f) {
        itemList.add(f);
    }

    void displayCart() {
        System.out.println("Items in cart - ");
        for (FoodItem f : itemList) {
            System.out.println(f);
        }
    }

    boolean restaurantValid(Restaurant selectedRestaurant) {
        if (itemList.size() > 0)
            return itemList.get(0).getRestaurantName() == selectedRestaurant;
        return true;
    }

    double calculateCartValue() {
        double cartValue = 0;
        for (FoodItem f : itemList) {
            cartValue += f.getPrice() * f.getQuantity();
        }
        return cartValue;

    }


    public double calculateFoodItemOffer() {
        double d1 = 0;
        for (FoodItem f : itemList) {
            d1 = ((f.getDiscount() * f.getPrice() * f.getQuantity()) / 100.0);
        }
        return d1;

    }

    public Restaurant getRestaurant() {
        if (itemList.size() > 0)
            return itemList.get(0).getRestaurantName();

        return null;
    }

    public int getCartSize() {
        return itemList.size();
    }

    public void deleteItems() {
        System.out.println("Items in cart - ");
        int i=1;
        for (FoodItem f : itemList) {
            System.out.print("Sno: "+i+++"   ");
            System.out.println(f);
        }

        System.out.print("SNo to delete : ");
        int op=in.nextInt();
        itemList.remove(op-1);  //op-1 since AL index starts from 0
    }

    public void displayLastOrder(int dc)
    {
        for (FoodItem f : itemList)
        {
            f.displayLastOrder(dc);
        }
    }
}
