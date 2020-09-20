package zotatoapp;

/*regular
    elite
    special*/
public class Customer {
    private String name;
    private String address;
    int Wallet;
    private String category;
    int rewardPoints;

    public Customer(String name, String address, String category) {
        this.name=name;
        this.address=address;
        this.Wallet=1000;
        this.category=category;
        this.rewardPoints=0;
    }
}
