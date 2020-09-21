package zotatoapp;

public class FastFoodRestaurant extends Restaurant {
    public FastFoodRestaurant(String name, String address, String category) {
        super(name, address, category);
    }


    @Override
    public int calculateRewardPointRestaurant(double bill) {
        int p = ((int) (bill / 150)) * 10;
        rewardPoints += p;
        //System.out.println("fast food ka Reward POint update");
        return p;
    }

    @Override
    public double applyRestaurantDiscount(double bill) {
        return (bill * billDiscount) / 100;
    }
}
