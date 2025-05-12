import java.text.*;
public class Restaurant{
    
    private String orderID;
    private String orderTime;
    private String orderStatus;
    private String foodName;
    private double foodPrice;
    private String paymentMethod;
    
    public Restaurant(String orderID,String orderTime,String orderStatus,String foodName,double foodPrice,String paymentMethod){
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.paymentMethod = paymentMethod;
    }
    
    public String getOrderID(){return orderID;}
    public String getOrderTime(){return orderTime;}
    public String getOrderStatus(){return orderStatus;}
    public String getFoodName(){return foodName;}
    public double getFoodPrice(){return foodPrice;}
    public String getPaymentMethod(){return paymentMethod;}
    
    public void setStatusOrder(String newOrderStatus){
        orderStatus = newOrderStatus;
    }
    
    public void setPaymentMethod(String newPaymentMethod){
        paymentMethod = newPaymentMethod;
    }
    
    
    public String toString(){
         return String.format(
             "| %-14s | %-16.2f | %-14s | $%-9.2f | %-17s | %-17s |\n" ,
            orderID, orderTime, orderStatus,foodName, foodPrice,  paymentMethod);
    }
}
