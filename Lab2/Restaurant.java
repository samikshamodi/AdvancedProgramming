package zotatoapp;

/*regular
    fast food
    authentic */
public class Restaurant {
    private String name;
    private String address;
    int ordersTaken;
    private String category;
    int rewardPoints;
    int companyBalance;     //option 4
    int deliveryCharges;    //option 4

    public Restaurant(String name, String address, String category) {
        this.name=name;
        this.address=address;
        this.ordersTaken=0;
        this.category=category;
        this.rewardPoints=0;
        this.companyBalance=0;
        this.deliveryCharges=0;
    }
}

