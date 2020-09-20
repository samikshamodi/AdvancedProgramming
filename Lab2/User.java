package zotatoapp;

import java.util.ArrayList;

public class User {
    ArrayList<Restaurant> restaurantList;
    ArrayList<Customer> customerList;

    public User()
    {
        restaurantList = new ArrayList<>();
        customerList = new ArrayList<>();

        customerList.add(new Elite("Ram", "Raipur","(Elite)"));
        customerList.add(new Elite("Sam", "Shahpur","(Elite)"));
        customerList.add(new Special("Tim", "Timarpur","(Special)"));
        customerList.add(new Customer("Kim", "Kanpur",""));
        customerList.add(new Customer("Jim", "Jodhpur",""));

        restaurantList.add(new Authentic("Shah", "Shahpur","(Authentic)"));
        restaurantList.add(new Restaurant("Ravi's", "Raipur",""));
        restaurantList.add(new Authentic("The Chinese", "Timarpur","(Authentic)"));
        restaurantList.add(new FastFood("Wang's", "Wazirpur","(Fast Food)"));
        restaurantList.add(new Restaurant("Paradise", "Panipur",""));
    }

    void listRestaurant()
    {
        
    }
}
