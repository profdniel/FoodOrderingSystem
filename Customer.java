import java.text.*;

public class Customer{
    private String custID;
    private String custName;
    private String custPhoneNum;
    private String custEmail;
    private Restaurant restaurant;
    
     DecimalFormat df = new DecimalFormat("0.00");
    
    public Customer(String custID,String custName,String custPhoneNum, String custEmail,Restaurant restaurant) 
    {
        this.custID = custID;
        this.custName = custName ;
        this.custPhoneNum = custPhoneNum;
        this.custEmail = custEmail;
        this.restaurant = restaurant;
    }
    
    public String getCustID(){return custID;}
    public String getCustName(){return custName;}
    public String getCustPhoneNum(){return custPhoneNum;}
    public String getCustEmail(){return custEmail;}
    public Restaurant getRestaurant(){return restaurant;}
    
    public String toString() {
         
         return String.format(
            "| %-13s | %-18s | %-16s | %-30s | %-14s | %-18s | %-13s | %-10.2f | %-17s | %-17s |",
            custID, custName, custPhoneNum, custEmail, 
            restaurant.getOrderID(), restaurant.getOrderTime(), 
            restaurant.getOrderStatus(), restaurant.getFoodPrice(), 
            restaurant.getFoodName(), restaurant.getPaymentMethod()
        );
    }
 
    
    
        
}
