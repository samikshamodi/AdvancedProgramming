package zotatoapp;

public class FoodItem {
    private int id;
    private String name;
    private int price;
    private int quantity;
    String category;
    private int discount;
    Restaurant restaurantName;

    public FoodItem(int id, String name, int price, int quantity, String category,int discount,Restaurant restaurantName)
    {
        this.id=id;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.category=category;
        this.discount=discount;
        this.restaurantName=restaurantName;
    }

    public String toString() {
        String details = "";
        details += id + " " + restaurantName.getName() + " - " + name + " " + price +" " + quantity + " "+discount+"%off "+category;
        return details;
    }

    public String display() {
        String details = "";
        details += id + " " + name + " " + price +" " + quantity + " "+discount+"%off "+category;
        return details;
    }

    public int getId()
    {
        return id;
    }

    public int getQuantity()
    {
        return quantity;
    }


    public void setName(String s)
    {
        name=s;
    }

    public void setPrice(int p)
    {
        price=p;
    }

    public void setQuantity(int q)
    {
        quantity=q;
    }

    public void setCategory(String s)
    {
        category=s;
    }

    public void setDiscount(int d)
    {
        discount=d;
    }

    public void reduceQuantity(int q)
    {
        quantity-=q;
    }



}
