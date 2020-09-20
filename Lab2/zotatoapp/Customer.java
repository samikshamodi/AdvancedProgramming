package zotatoapp;

/*regular
    elite
    special*/
public class Customer {
    private String name;
    private String address;
    int wallet;
    private String category;
    int rewardPoints;

    public Customer(String name, String address, String category) {
        this.name=name;
        this.address=address;
        this.wallet=1000;
        this.category=category;
        this.rewardPoints=0;
    }

    String getName()
    {
        return name;
    }

    String getCategory()
    {
        return category;
    }

    public String toString()
    {
        String details="";
        details+=name+" "+category+" "+address+" "+" "+wallet;
        return details;
    }
}
