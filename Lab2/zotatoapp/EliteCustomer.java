package zotatoapp;

import java.util.ArrayList;

public class EliteCustomer extends Customer {
    public EliteCustomer(String name, String address, String category, ArrayList<Restaurant> restaurantList) {
        super(name, address, category, restaurantList);
    }

    @Override
    void  calculateRewardPointCustomer(double bill)
    {

    }

    @Override
    void checkoutCart() {
        System.out.println("Delivery Charge - " + 0);
        double bill = myCart.calculateCartValue();

        if (bill == 0) {
            System.out.println("Total order value - INR 0");
            super.proceedToCheckout(bill);
            return;
        }

        //applying all discounts

        //individual food item discount
        double d1 = myCart.calculateFoodItemOffer();
        bill -= d1;


        //overall restaurant bill discounts
        //overall bill regular restaurant discount=0

        //overall bill fast food restaurant disocunt
        Restaurant r = myCart.getRestaurant();

        if (r.getCategory() == ("Fast Food")) {
            double d2 = (bill * r.getBillDiscount()) / 100;
            bill -= d2;
        }

        //overall bill authentic restaurant discount  //if after that still bill value is greater 100 then rs 50 off
        if (r.getCategory() == "(Authentic)") {
            double d3 = (bill * r.getBillDiscount()) / 100;
            bill -= d3;

            if (bill > 100) {
                bill -= 50;
            }
        }

        //customer discount
        //elite customer discount=0;
        if(bill>200)
        {
            bill-=50;
        }

        //special customer discount =0;

        //add delivery amount
        bill += 0;
        System.out.println("Total order value - " + bill);
        super.proceedToCheckout(bill);
    }
}
