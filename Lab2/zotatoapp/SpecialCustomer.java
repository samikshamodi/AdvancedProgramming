package zotatoapp;

import java.util.ArrayList;

public class SpecialCustomer extends Customer {

    public SpecialCustomer(String name, String address, String category, ArrayList<Restaurant>restaurantList,int deliveryCost) {
        super(name, address, category,restaurantList,deliveryCost);
    }

    @Override
    double applyCustomerDiscount(double bill) {
        if(bill>200)
        {
            bill-=25;
            return 25;
        }
        return 0;
    }

    @Override
    void updateDeliveryChargesRestaurant(Restaurant r)
    {
        r.setDeliveryCharges(20);
    }
}
