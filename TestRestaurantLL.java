import java.util.*;
import java.io.*;
import java.text.*;
import java.text.DecimalFormat;


public class TestRestaurantLL{
    public static void main(String[]args) throws IOException
    {
    
           FileReader fr = new FileReader("Cust.txt");
           BufferedReader br = new BufferedReader(fr);
           DecimalFormat df = new DecimalFormat("0.00");
           Scanner scanner = new Scanner(System.in);
           scanner.useDelimiter("\n");
           
           LinkedList <Customer> CustomerList = new LinkedList();
           
           //5 Create LinkedLists for custom
            LinkedList<Customer> custLL = new LinkedList();
            LinkedList<Customer> creditLL = new LinkedList();
            LinkedList<Customer> onlineLL = new LinkedList();
            LinkedList<Customer> cashLL = new LinkedList();
           
           String custID;
           String custName;
           String custPhoneNum;
           String custEmail;
           
            String orderID;
            String orderTime;
            String orderStatus;
            double foodPrice;
            String foodName;
            String paymentMethod;
           
           String inData = null;
           while((inData = br.readLine()) != null)
           {
             StringTokenizer st = new StringTokenizer(inData, ",");
             
             custID = st.nextToken();
             custName = st.nextToken();
             custPhoneNum = st.nextToken();
             custEmail = st.nextToken();
             
             orderID = st.nextToken();
             orderTime = st.nextToken();
             orderStatus = st.nextToken();
             foodName = st.nextToken();
             foodPrice = Double.parseDouble(st.nextToken());
             paymentMethod = st.nextToken();
             
             Restaurant restaurant = new Restaurant(orderID, orderTime, orderStatus,foodName, foodPrice, paymentMethod);
             Customer customer = new Customer(custID, custName, custPhoneNum, custEmail,restaurant);
             
             
             CustomerList.addFirst(customer);
          
           }
           br.close();
           fr.close();
           
            System.out.printf("%-20s %-10s\n", "Food Item", "Price (RM)");
            System.out.println("-----------------------------------");
            System.out.printf("%-20s %-10.2f\n", "Nasi Lemak", 4.00);
            System.out.printf("%-20s %-10.2f\n", "Char Kway Teow", 6.00);
            System.out.printf("%-20s %-10.2f\n", "Roti Canai", 2.00);
            System.out.printf("%-20s %-10.2f\n", "Nasi Dagang", 6.50);
            System.out.printf("%-20s %-10.2f\n", "Nasi Kerabu", 7.00);
            
           //1. Display customer order
           
           System.out.println("\n+---------------+--------------------+------------------+-------------Display Customer Order Information-------------------+----------------+--------------------+---------------+------------+------");
           System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
           System.out.println("| Customer ID   | Customer Name      | Phone Number     | Email                          | Order ID       | Order Time         | Order Status  | Food Price | Food Name         | Payment Method    |");
           System.out.println("+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
        
           Customer obj;
           obj  = CustomerList.getFirst();
           while( obj != null){
               System.out.println(obj.toString());
               obj = CustomerList.getNext();
           }
           
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

           // 2. Count and display number of customers that buy a specific payment method
            System.out.println("\nCount and Display number of customer payment method");
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

            System.out.print("\nEnter the payment method to search for(cash | online | credit ): ");
            String searchPaymentMethod = scanner.nextLine().trim();
            int cashCount = 0;
            int onlineCount = 0;
            int creditCount = 0;
            
            obj = CustomerList.getFirst();   
            while( obj != null){
                if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Cash")){
                    cashCount++;
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Online Transfer")){
                    onlineCount++;
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Credit Card")){
                    creditCount++;
                }
                obj = CustomerList.getNext();
                }
                
            if(searchPaymentMethod.equalsIgnoreCase("Cash")){
                    System.out.printf("Number of customers who pay with '%s': %d%n", searchPaymentMethod, cashCount);
                }
                else if(searchPaymentMethod.equalsIgnoreCase("Online")){
                     System.out.printf("Number of customers who pay with '%s': %d%n", searchPaymentMethod, onlineCount);
                }
                else if(searchPaymentMethod.equalsIgnoreCase("Credit")){
                     System.out.printf("Number of customers who pay with '%s': %d%n", searchPaymentMethod, creditCount);
                }
                
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
            
            // 3. Search customer ID in customer list to update orderStatus
            System.out.println("\nSearch customer id and Update Status");
            System.out.println("\n\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

            System.out.print("\nEnter the Customer ID to search for and update order status: ");
            String searchCustomerID = scanner.nextLine();
            boolean customerFound = false;
            
            obj = CustomerList.getFirst(); // Start from the first customer in the linked list
            while (obj != null) {
                if (obj.getCustID().equalsIgnoreCase(searchCustomerID)) {
                    customerFound = true;
                    System.out.println("\nCustomer found:");
                    System.out.println(obj.toString()); // Display current customer details
                    System.out.println();
                    System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
                    System.out.println("\nUpdate Status");
                    System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

                    
                    System.out.print("Enter the new order status: ");   
                    String newOrderStatus = scanner.nextLine().trim();
                    obj.getRestaurant().setStatusOrder(newOrderStatus); // Update order status
                    
                    System.out.println("\nOrder status updated successfully!");
                    System.out.println();
                    System.out.println(obj.toString()); // Display updated customer details
                    System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

                }
                obj = CustomerList.getNext(); // Move to the next customer
            }
            

            
            System.out.println("\n+---------------+--------------------+------------------+----------------Update Customer Information---------------------------+---------------+------------+-------------------+-------------------+");
                       
            obj  = CustomerList.getFirst();
            while( obj != null){
               System.out.println(obj.toString());
               obj = CustomerList.getNext();
            }
            
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
            
            //4
            System.out.println("\nSearch Customer Name and Update Payment Method");
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");


            System.out.print("\nEnter the Customer Name to search for and update payment method: ");
            String searchCustomerName = scanner.nextLine();
            boolean nameFound = false;
            
            obj = CustomerList.getFirst(); // Start from the first customer in the linked list
            while (obj != null) {
                if (obj.getCustName().equalsIgnoreCase(searchCustomerName)) {
                    nameFound = true;
                    System.out.println("\nCustomer found:");
                    System.out.println(obj.toString()); // Display current customer details
                    System.out.println();
                    System.out.println("+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

                    System.out.println("\n\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

                    System.out.print("Enter the new payment method: ");   
                    String newPaymentMethod = scanner.nextLine().trim();
                    obj.getRestaurant().setPaymentMethod(newPaymentMethod); // Update order status
                    
                    System.out.println("\nCustomer payment method updated successfully!");
                    System.out.println();
                    System.out.println(obj.toString()); // Display updated customer details
                    System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

                }
                obj = CustomerList.getNext(); // Move to the next customer
            }
            
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

            
                       
            obj  = CustomerList.getFirst();
            
            while( obj != null){
               System.out.println(obj.toString());
               obj = CustomerList.getNext();
            }
            
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
            
            System.out.print("\nEnter the payment method to search for(cash | online | credit): ");
            String searchPayment = scanner.nextLine().trim();
            double totalPriceC = 0.00; 
            double totalPriceOn9 = 0.00 ;
            double totalPriceCard = 0.00;
            
            
            obj = CustomerList.getFirst();   
            while( obj != null){
                if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Cash")){
                    totalPriceC += obj.getRestaurant().getFoodPrice();
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Online Transfer")){
                    totalPriceOn9 += obj.getRestaurant().getFoodPrice();
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Credit Card")){
                    totalPriceCard += obj.getRestaurant().getFoodPrice();
                }
                obj = CustomerList.getNext();
                }
                
            if(searchPayment.equalsIgnoreCase("Cash")){
                    System.out.println("Total price cutomer who pay with cash : " +df.format(totalPriceC));
                }
                else if(searchPayment.equalsIgnoreCase("Online")){
                     System.out.printf("Total price cutomer who pay with online payment : " +df.format(totalPriceOn9));
                }
                else if(searchPayment.equalsIgnoreCase("Credit")){
                     System.out.printf("Total price cutomer who pay with : " +df.format(totalPriceCard));
                }
                
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
            
             //5 remove all the data in cust list into custLL,creditLL,onlineLL 
            // Transfer all data from CustomerList
            System.out.println("\nSort Customer Payment Method");
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

            while (!CustomerList.isEmpty()) {
                Customer customer = CustomerList.removeFirst(); // Remove from CustomerList
                custLL.addFirst(customer);  // Add to custLL
    
                // Check payment method and add to appropriate lists
                if (customer.getRestaurant().getPaymentMethod().equalsIgnoreCase("Credit Card")) {
                    creditLL.addFirst(customer);
                } else if (customer.getRestaurant().getPaymentMethod().equalsIgnoreCase("Online Transfer")) {
                    onlineLL.addFirst(customer);
                } else if (customer.getRestaurant().getPaymentMethod().equalsIgnoreCase("Cash")){
                    cashLL.addFirst(customer);
                }
            }
    
            System.out.println("\nAll customers (custLL):");
            Customer current = custLL.getFirst();
            while (current != null) {
                System.out.println(current.toString());
                current = custLL.getNext();
            }
            
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

            System.out.println("\nCustomers who paid with Credit Card (creditLL):");
            current = creditLL.getFirst();
            while (current != null) {
                System.out.println(current.toString());
                current = creditLL.getNext();
            }
            
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");


            System.out.println("\nCustomers who paid with Online Transfer (onlineLL):");
            current = onlineLL.getFirst();
            while (current != null) {
                System.out.println(current.toString());
                current = onlineLL.getNext();
            }
            
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

            System.out.println("\nCustomers who paid with Cash Payment (cashLL):");
            current = cashLL.getFirst();
            while (current != null) {
                System.out.println(current.toString());
                current = cashLL.getNext();
            }

            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

            
             //6. display information of customer where the foodprice < 10.00


           System.out.println("\n+---------------+--------------------+------------------+----------------Display Customer Food Price less than RM10-------------+----------------+--------------------+---------------+------------+-");
           System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
           System.out.println("| Customer ID   | Customer Name      | Phone Number     | Email                          | Order ID       | Order Time         | Order Status  | Food Price | Food Name         | Payment Method    |");
           System.out.println("+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
           obj = custLL.getFirst();   
           while( obj != null){
               if(obj.getRestaurant().getFoodPrice() < 10.00){
                   System.out.println(obj.toString());
               }
               obj = custLL.getNext();
           }
           
           System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

           //7. find highest and lowest price of a customer 
           
             
           Customer highestObj = custLL.getFirst();
           Customer lowestObj = custLL.getFirst();
           obj = custLL.getFirst();
           
           while(obj != null){
            
            if(obj.getRestaurant().getFoodPrice() > highestObj.getRestaurant().getFoodPrice()){
                highestObj = obj ;
            }
            else if(obj.getRestaurant().getFoodPrice() < lowestObj.getRestaurant().getFoodPrice()){
                lowestObj = obj;
            }
            obj = custLL.getNext();
            
            
            }
            
            System.out.println("\n+---------------+--------------------+------------------+----------------Display Customer Highset and Lowest Price-------------+----------------+--------------------+---------------+-----------+-");
           
            System.out.println("\nCustomer with the Highest Food Price:");
            System.out.println(highestObj);

            System.out.println("\nCustomer with the Lowest Food Price:");
            System.out.println(lowestObj);
           
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
                  
           
            // 8. Calculate and display the average food price in customer list
            double totalFoodPrice = 0;
            int customerCount = 0;
            
            System.out.println("\n+---------------+--------------------+------------------+----------------Display average food price among all customer---------+---------------+------------+-------------------+-------------------+");
           
            obj = custLL.getFirst(); // Start from the first customer in the linked list
            while (obj != null) {
                totalFoodPrice += obj.getRestaurant().getFoodPrice(); // Accumulate food prices
                customerCount++; // Count each customer
                obj = custLL.getNext(); // Move to the next customer
            }
            
            if (customerCount > 0) {
                double averageFoodPrice = totalFoodPrice / customerCount;

            System.out.printf("\nThe average food price among all customers is: RM%.2f%n", averageFoodPrice);
            } else {
                System.out.println("\nNo customers available to calculate the average food price.");
            }
            
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

           
           //9
            System.out.print("\nEnter the payment method to search for(cash | online | credit): ");
            String searchPaymentLL = scanner.nextLine().trim();
            double totalPriceCLL = 0.00; 
            double totalPriceOn9LL = 0.00 ;
            double totalPriceCardLL = 0.00;
            
            
            obj = custLL.getFirst();   
            while( obj != null){
                if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Cash")){
                    totalPriceCLL += obj.getRestaurant().getFoodPrice();
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Online Transfer")){
                    totalPriceOn9LL += obj.getRestaurant().getFoodPrice();
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Credit Card")){
                    totalPriceCardLL += obj.getRestaurant().getFoodPrice();
                }
                obj = custLL.getNext();
                }
                
            if(searchPayment.equalsIgnoreCase("Cash")){
                    System.out.println("Total price cutomer who pay with cash : " +df.format(totalPriceCLL));
                }
                else if(searchPayment.equalsIgnoreCase("Online")){
                     System.out.printf("Total price cutomer who pay with online payment : " +df.format(totalPriceOn9LL));
                }
                else if(searchPayment.equalsIgnoreCase("Credit")){
                     System.out.printf("Total price cutomer who pay with : " +df.format(totalPriceCardLL));
                }
                
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

                
            //10 - Generate Report
                
            System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
            System.out.println("\nReport for food names and the number of the customers");
            System.out.println("\nFood Report:");
            System.out.println("+-------------------+------------------+");
            System.out.println("| Food Name         | Total Customers |");
            System.out.println("+-------------------+------------------+");
            
            int nlemakC = 0;
            int CKTc = 0;
            int rotiCanaiC=0;
            int KLekorC=0;
            int satayC=0;
            
            obj = custLL.getFirst();
            
            while(obj != null)
            {
                if(obj.getRestaurant().getFoodName().equalsIgnoreCase("Nasi Lemak"))
                {
                    nlemakC++;
                 }
                else if ( obj.getRestaurant().getFoodName().equalsIgnoreCase("Char Kway Teow"))
                {
                    CKTc++;
                }
                else if(obj.getRestaurant().getFoodName().equalsIgnoreCase("Roti Canai"))
                {
                    rotiCanaiC++;
                }
                else if(obj.getRestaurant().getFoodName().equalsIgnoreCase("Keropok Lekor"))
                {
                    KLekorC++;                    
                }
                else if(obj.getRestaurant().getFoodName().equalsIgnoreCase("Satay")){
                    satayC++;    
                }
        
                obj = custLL.getNext();
      
            }
            System.out.printf("| %-17s | %-16d |\n", "Nasi Lemak", nlemakC);
            System.out.printf("| %-17s | %-16d |\n", "Char Kway Tiaw", CKTc);
            System.out.printf("| %-17s | %-16d |\n", "Roti Canai", rotiCanaiC);
            System.out.printf("| %-17s | %-16d |\n", "Keropok Lekor", KLekorC);
            System.out.printf("| %-17s | %-16d |\n", "Satay", satayC);
                
            System.out.println("+-------------------+------------------+");
            
        
}
}
