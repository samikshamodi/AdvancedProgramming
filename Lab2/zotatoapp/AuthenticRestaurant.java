package zotatoapp;

public class AuthenticRestaurant extends Restaurant{
    public AuthenticRestaurant(String name, String address, String category) {
        super(name, address,category);
    }

    @Override
    public int calculateRewardPointRestaurant(double bill) {
        int p=((int) (bill / 200)) * 25;
        rewardPoints += p;
        //System.out.println("Authentic ka Reward POint update"); //TODO remove
        return p;
    }
}
