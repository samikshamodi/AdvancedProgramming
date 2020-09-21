package zotatoapp;

import java.util.ArrayList;
import java.util.Scanner;

/*regular
    elite
    special*/
public class Customer implements Login {
    Scanner in = new Scanner(System.in);
    private String name;
    private String address;
    int wallet;
    private String category;
    int rewardPoints;
    protected Cart myCart;
    private ArrayList<Restaurant> restaurantList;
    private ArrayList<Cart> recentOrders;
    private int deliveryCost;


    public Customer(String name, String address, String category, ArrayList<Restaurant> restaurantList,int deliveryCost) {
        this.name = name;
        this.address = address;
        this.wallet = 1000;
        this.category = category;
        this.rewardPoints = 0;
        myCart = null;
        this.restaurantList = restaurantList;
        this.recentOrders = new ArrayList<>();
        this.deliveryCost=deliveryCost;
    }

    String getName() {
        return name;
    }

    String getCategory() {
        return category;
    }

    public String toString() {
        String details = "";
        details += name + " " + category + " " + address + " " + " " + wallet;
        return details;
    }

    void deleteItemInCart() {
        System.out.println("Insufficient balance. Please remove items from cart.");
        myCart.deleteItems();
        checkoutCart();
    }

     void updateCustomerWallet(double bill)
    {
        if(bill>rewardPoints)
        {
            bill-=rewardPoints;
            rewardPoints=0;
            wallet-=bill;
        }
        else if(rewardPoints>bill)
        {
            rewardPoints-=bill;
        }
    }

    void updateDeliveryChargesRestaurant(Restaurant r)
    {
        r.setDeliveryCharges(40);
    }

    void proceedToCheckout(double bill) {
        System.out.println("\t1) Proceed to checkout");
        System.out.print("Query:");
        int op = in.nextInt();

        if (op == 1) {
            if (bill == 0) {
                System.out.println(myCart.getCartSize() + " items successfully bought for INR " + bill);
                return;
            }

            //check if sufficient amount in wallet+reward points
            if (bill > wallet + rewardPoints)
                deleteItemInCart();
            else
            {
                System.out.println(myCart.getCartSize() + " items successfully bought for INR " + bill);
                updateCustomerWallet(bill);
                rewardPoints+= myCart.getRestaurant().calculateRewardPointRestaurant(bill); //update reward points of customer
                myCart.getRestaurant().calculateZotatoIncome(bill);    //company balance per restaurant
                updateDeliveryChargesRestaurant(myCart.getRestaurant());
                recentOrders.add(myCart);       //add the cart to recent order
                myCart=new Cart();              //reset cart as new
            }

        }

        //TODO
        //handle delivery charge
        //option to delete if insufficient
        //calculate reward points
        //update in customerwallet (first in reward points then from wallet)
        //update in restuaurant
        //calculate transaction fee
        //set a new cart
        //updtaed delivery charges collected add attribute in customer instead of restaurant
    }


    void checkoutCart() {
        System.out.println("Delivery Charge - " + deliveryCost );
        double bill = myCart.calculateCartValue();

        if (bill == 0) {
            System.out.println("Total order value - INR 0");
            //pass delivery charges in proceed to checkout. simple.
            proceedToCheckout(bill);
            return;
        }

        //applying all discounts

        //individual food item discount
        double d1 = myCart.calculateFoodItemOffer();
        bill -= d1;

        //overall restaurant bill discounts
        double d2=myCart.getRestaurant().applyRestaurantDiscount(bill);
        bill-=d2;

        //customer discount
        double d3=applyCustomerDiscount(bill);
        bill-=d3;

        //add delivery Cost
        bill+=deliveryCost;

        System.out.println("Total order value - " + bill);
        proceedToCheckout(bill);
    }

    double applyCustomerDiscount(double bill) {
        return 0;
    }


    @Override
    public void login() {
        myCart = new Cart();    //on login you get a new cart

        while (true) {
            System.out.println("\nWelcome " + name + "\nCustomer Menu\n" +
                    "1) Select Restaurant\n" +
                    "2) Checkout Cart\n" +
                    "3) Reward Won\n" +
                    "4) Print the recent order\n" +
                    "5) Exit");

            System.out.print("Query:");
            int ou = in.nextInt();
            switch (ou) {
                case 1:
                    System.out.println("\nChoose Restaurant");
                    int i = 1;
                    for (Restaurant r : restaurantList) {
                        System.out.println(i++ + " " + r.getName() + " " + r.getCategory());
                    }
                    System.out.print("Query:");
                    int ov = in.nextInt();  //ov-1 since indexing in AL is from 0
                    Restaurant selectedRestaurant = restaurantList.get(ov - 1);

                    if (myCart.restaurantValid(selectedRestaurant)) {
                        System.out.println("Choose item by code");
                        selectedRestaurant.displayFoodItems();
                        System.out.print("Query:");
                        int ow = in.nextInt();  //ow-1 since indexing in AL is from 0
                        FoodItem selectedFoodItem = selectedRestaurant.getFoodItem(ow);
                        System.out.print("Enter item quantity - ");
                        int q = in.nextInt();
                        //Incase you selected a restaurant which has no items
                        if (selectedFoodItem==null)
                        {
                            break;
                        }
                        if (selectedFoodItem.getQuantity() == 0) {
                            System.out.println("Restaurant ran out of this item");
                        } else if (q > selectedFoodItem.getQuantity()) {
                            System.out.println("Order not accepted. The restaurant only has only " + selectedFoodItem.getQuantity() + " quantity of this item");
                        } else {
                            //TODO add items to cart;
                            //create a new object of selected food item with the bought quantity and add it to cart
                            FoodItem cartItem = new FoodItem((selectedFoodItem.getId()), selectedFoodItem.getName(), selectedFoodItem.getPrice(), q, selectedFoodItem.getCategory(), selectedFoodItem.getDiscount(), selectedFoodItem.getRestaurantName());
                            myCart.addToCart(cartItem);
                            selectedFoodItem.reduceQuantity(q);
                            System.out.println("Items added to cart");
                            //myCart.displayCart();
                        }
                    } else {
                        System.out.println("Select items from the previously selected restaurant.");
                    }
                    break;
                case 2:
                    myCart.displayCart();
                    checkoutCart();
                    break;
                case 3:
                    System.out.println("Reward won - "+rewardPoints);
                    break;
                case 4:
                    int n=Math.min(recentOrders.size(),10);
                    for (int j = recentOrders.size()-1,k=n-1; k>=0  ; j--,k--) {
                        System.out.println("Order");
                        Cart o=recentOrders.get(j);
                        o.displayLastOrder(deliveryCost);

                    }
                   /* for(Cart o:recentOrders)
                    {
                        System.out.println("Order");
                        o.displayLastOrder(deliveryCost);
                    }*/
                    break;
                case 5:
                    return;
            }
        }

    }
}
