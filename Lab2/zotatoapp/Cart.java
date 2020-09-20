package zotatoapp;

import java.util.ArrayList;

public class Cart {
    ArrayList<FoodItem> itemList;
    ArrayList<Integer> itemQuantityList;

    public Cart()
    {
        itemList=new ArrayList<>();
        itemQuantityList=new ArrayList<>();
    }

    void addToCart(FoodItem f,int q)
    {
        itemList.add(f);
        itemQuantityList.add(q);
    }


}
