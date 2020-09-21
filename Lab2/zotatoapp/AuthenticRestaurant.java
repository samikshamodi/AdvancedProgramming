package zotatoapp;

public class AuthenticRestaurant extends Restaurant {
    public AuthenticRestaurant(String name, String address, String category) {
        super(name, address, category);
    }

    @Override
    public int calculateRewardPointRestaurant(double bill) {
        int p = ((int) (bill / 200)) * 25;
        rewardPoints += p;
        //System.out.println("Authentic ka Reward POint update");
        return p;
    }

    @Override
    public double applyRestaurantDiscount(double bill) {
        //overall bill authentic restaurant discount
        // if after that still bill value is greater 100 then rs 50 off
        double d3 = (bill * billDiscount) / 100;
        bill-=d3;
        if (bill > 100) {
            bill -= 50;
            return d3+50;
        }
        return d3;
    }
}

