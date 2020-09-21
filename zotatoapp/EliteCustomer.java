package zotatoapp;

import java.util.ArrayList;

public class EliteCustomer extends Customer {
    public EliteCustomer(String name, String address, String category, ArrayList<Restaurant> restaurantList,int deliveryCost) {
        super(name, address, category, restaurantList,deliveryCost);
    }

    @Override
    double applyCustomerDiscount(double bill) {
        if (bill > 200) {
            bill -= 50;
            return 50;
        }
        return 0;
    }


    @Override
    void updateDeliveryChargesRestaurant(Restaurant r) {
        r.setDeliveryCharges(0);
    }
}
