import java.util.*;
import java.io.*;
import java.text.*;
import java.text.DecimalFormat;

public class TestRestaurantQ
{
    public static void main(String[]args) throws IOException
    {
        FileReader fr = new FileReader("Cust.txt");
        BufferedReader br = new BufferedReader(fr);
        DecimalFormat df = new DecimalFormat("#.##");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        
        Queue <Customer> CustomerQ = new Queue();
        Queue<Customer> tempQ = new Queue();
        
        // Create Queue For Custom
        Queue <Customer> custQ = new Queue();
        Queue <Customer> creditQ = new Queue();
        Queue <Customer> onlineQ = new Queue();
        Queue <Customer> cashQ = new Queue();
        
       String custID, custName, custPhoneNum, custEmail;

        String orderID;
        String orderTime;
        String orderStatus;
        double foodPrice;
        String foodName;
        String paymentMethod;
        int NameFood;
        
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
             
             Restaurant restaurant = new Restaurant (orderID, orderTime, orderStatus, foodName, foodPrice, paymentMethod);

             Customer customer = new Customer(custID, custName, custPhoneNum, custEmail,restaurant);
             
             
             CustomerQ.enqueue(customer);
          
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
           
        System.out.println("\nDisplay Customer Order Information");
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
        System.out.println("| Customer ID   | Customer Name      | Phone Number     | Email                          | Order ID       | Order Time         | Order Status  | Food Price | Food Name         | Payment Method    |");
        System.out.println("+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
        
        Customer obj;
        while(!CustomerQ.isEmpty())
        {
               obj = CustomerQ.dequeue();
               System.out.println(obj.toString());
               tempQ.enqueue(obj);
        }
        
        while(!tempQ.isEmpty())
        {
                CustomerQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
        }
        
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
        
       
         // 2. count and display number of customer payment method
         System.out.println("\nCount and Display number of customer payment method");
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

        System.out.print("\nEnter the payment method to search for(cash | online | credit): ");
        String searchPaymentMethod = scanner.nextLine().trim();
        
        int cashCount = 0;
        int onlineCount = 0;
        int creditCount = 0;
            
     
        while(!CustomerQ.isEmpty()){
            
            obj = CustomerQ.dequeue();
            if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Cash")){
                    cashCount++;
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Online Transfer")){
                    onlineCount++;
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Credit Card")){
                    creditCount++;
                }
            tempQ.enqueue(obj);
        }
        
        
        
        if(searchPaymentMethod.equalsIgnoreCase("Cash")){
                    System.out.printf("Number of customers who pay with '%s': %d%n", searchPaymentMethod, cashCount);
            }
                else if(searchPaymentMethod.equalsIgnoreCase("Online Transfer")){
                     System.out.printf("Number of customers who pay with '%s': %d%n", searchPaymentMethod, onlineCount);
            }
                else if(searchPaymentMethod.equalsIgnoreCase("Credit Card")){
                     System.out.printf("Number of customers who pay with '%s': %d%n", searchPaymentMethod, creditCount);
            }
                
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
            
           
         // Restore custQ from tempQ
        while (!tempQ.isEmpty()) {
            CustomerQ.enqueue(tempQ.dequeue());
        }  
        
        // 3. search customer ID and update order status
        System.out.println("\nSearch customer id and Update Status");
        System.out.println("\n\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

        System.out.print("\nEnter the Customer ID to search for and update order status: ");
        String searchCustomerID = scanner.nextLine();
        boolean customerFound = false;
            
        while (!CustomerQ.isEmpty()) 
        {
                obj = CustomerQ.dequeue();
                
                if (obj.getCustID().equalsIgnoreCase(searchCustomerID)) 
                {
                    
                    
                    customerFound = true;
                    System.out.println("\nCustomer found:");
                    System.out.println(obj.toString()); // Display current customer details
                    System.out.println();
                    System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
                    System.out.println("\nUpdate Status");
                    System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

                    System.out.print("\nEnter the new order status: ");   
                    String newOrderStatus = scanner.nextLine().trim();
                    obj.getRestaurant().setStatusOrder(newOrderStatus); // Update order status
                    

                    
                    System.out.println("\nOrder status updated successfully!");
                    System.out.println();
                    System.out.println(obj.toString()); // Display updated customer details
                    System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

                }
                tempQ.enqueue(obj); // Move to the next customer
        }
        

            
        System.out.println("\n+---------------+--------------------+------------------+----------------Update Customer Information---------------------------+---------------+------------+-------------------+-------------------+");
        
        // Restore custQ from tempQ
        while (!tempQ.isEmpty()) 
        {
            CustomerQ.enqueue(tempQ.dequeue());
        }  
        
        

            
                       
            
        while( !CustomerQ.isEmpty())
        {
                obj = CustomerQ.dequeue();
               System.out.println(obj.toString());
               tempQ.enqueue(obj);
        }
            
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

        // Restore custQ from tempQ
        while (!tempQ.isEmpty()) 
        {
            CustomerQ.enqueue(tempQ.dequeue());
        }  
        
         // 4 - Search Customer Name and Update Payment Method 
         System.out.println("\nSearch Customer Name and Update Payment Method");
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

        System.out.print("\nEnter the Customer Name to search for and update payment method: ");
        String searchCustomerName = scanner.nextLine();
        boolean nameFound = false;
            
        while ( !CustomerQ.isEmpty())
        {
                obj = CustomerQ.dequeue();
                
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
                tempQ.enqueue(obj);
        }
            
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
        
         // Restore custQ from tempQ
        while (!tempQ.isEmpty()) 
        {
            CustomerQ.enqueue(tempQ.dequeue());
        }  
                       
            
        while( !CustomerQ.isEmpty())
            {
               obj = CustomerQ.dequeue();
               System.out.println(obj.toString());
                tempQ.enqueue(obj);
            }
            
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
        
         // Restore custQ from tempQ
        while (!tempQ.isEmpty()) 
        {
            CustomerQ.enqueue(tempQ.dequeue());
        }  
        
        //5 -remove all the data in cust list into custQ,creditQ,onlineQ 
            
        // Transfer all data from CustomerList
        System.out.println("\nSort Customer Payment Method");
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

        while (!CustomerQ.isEmpty()) 
        {
                obj = CustomerQ.dequeue(); // Remove from CustomerList
            
    
                // Check payment method and add to appropriate lists
                if (obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Credit Card")) {
                    creditQ.enqueue(obj);
                } else if (obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Online Transfer")) {
                    onlineQ.enqueue(obj);
                } else if (obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Cash")){
                    cashQ.enqueue(obj);
                }
                tempQ.enqueue(obj);  
        }
    
        // Restore custQ from tempQ
        while (!tempQ.isEmpty()) 
        {
                CustomerQ.enqueue(tempQ.dequeue());
        }
        
        System.out.println("\nAll customers (custQ):");
            
           
        while (!CustomerQ.isEmpty()) 
        {
                obj = CustomerQ.dequeue();
                System.out.println(obj.toString());
                tempQ.enqueue(obj);
        }
            
        System.out.println("\nCustomers who paid with Credit Card (credit):");
            
        while (!creditQ.isEmpty()) 
        {
                obj = creditQ.dequeue();
                System.out.println(obj.toString());
        }
            
        System.out.println("\nCustomers who paid with Online Transfer (online):");
        while (!onlineQ.isEmpty()) 
        {
                obj = onlineQ.dequeue();
                System.out.println(obj.toString());
        }
        
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

        System.out.println("\nCustomers who paid with Cash Payment (cash):");
        while (!cashQ.isEmpty()) 
        {
                obj = cashQ.dequeue();
                System.out.println(obj.toString());
        }

        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

        
        
        // Restore custQ from tempQ
        while (!tempQ.isEmpty()) 
        {
                CustomerQ.enqueue(tempQ.dequeue());
        }
        
        //6. Display information of customer where the foodprice < 10.00
        System.out.println("\nDisplay Customer Information where the food price below than RM 10.00");
        System.out.println("\n+---------------+--------------------+------------------+----------------Display Customer Food Price less than RM10-------------+----------------+--------------------+---------------+------------+-");
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
        System.out.println("| Customer ID   | Customer Name      | Phone Number     | Email                          | Order ID       | Order Time         | Order Status  | Food Price | Food Name         | Payment Method    |");
        System.out.println("+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
        
           
        while(!CustomerQ.isEmpty())
        {
                obj = CustomerQ.dequeue();   
               if(obj.getRestaurant().getFoodPrice() < 10.00)
               {
                   System.out.println(obj.toString());
               }
               tempQ.enqueue(obj);
        }
        
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

        
         while(!tempQ.isEmpty())
        {
                CustomerQ.enqueue(tempQ.dequeue());//yang buang tadi akan masuk balik
        }
        
        //7. Find Highest And Lowest price of customer order 
        
        Customer highestObj = null;
        Customer lowestObj = null;


        
        // Initialize highestObj and lowestObj with the first customer
        if (!CustomerQ.isEmpty()) 
        {
                highestObj = CustomerQ.dequeue();
                lowestObj = highestObj; // Both start as the same first customer
                tempQ.enqueue(highestObj); // Enqueue back into tempQ for further processing
        }

        // Compare remaining customers to find the highest and lowest food prices
        while (!CustomerQ.isEmpty()) 
        {
                obj = CustomerQ.dequeue();
                if (obj.getRestaurant().getFoodPrice() > highestObj.getRestaurant().getFoodPrice()) 
                {
                highestObj = obj;
                }
                if (obj.getRestaurant().getFoodPrice() < lowestObj.getRestaurant().getFoodPrice()) 
                {
                lowestObj = obj;
                }
                tempQ.enqueue(obj);
        }

        // Restore the original queue
        while (!tempQ.isEmpty()) 
        {
                CustomerQ.enqueue(tempQ.dequeue());
        }

        
        System.out.println("\n+---------------+--------------------+------------------+----------------Display Customer Highest and Lowest Price-------------+----------------+--------------------+---------------+-----------+-");
           
        System.out.println("\nCustomer with the Highest Food Price:");
        System.out.println(highestObj);

        System.out.println("\nCustomer with the Lowest Food Price:");
        System.out.println(lowestObj);
           
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
                  
        
         // 8. Calculate And Display the Average food Price in Customer List
        
        double totalFoodPrice = 0;
        int customerCount = 0;
        
        System.out.println("\n+---------------+--------------------+------------------+----------------Display average food price among all customer---------+---------------+------------+-------------------+-------------------+");

            
        while (!CustomerQ.isEmpty()) 
        {
                obj = CustomerQ.dequeue();
                
                totalFoodPrice += obj.getRestaurant().getFoodPrice(); // Accumulate food prices
                customerCount++; // Count each customer
                
                tempQ.enqueue(obj); // Move to the next customer
        }
            
        if (customerCount > 0) 
        {
                double averageFoodPrice = totalFoodPrice / customerCount;
                System.out.printf("\nThe average food price among all customers is: RM%.2f%n", averageFoodPrice);
        } else 
        {
                System.out.println("\nNo customers available to calculate the average food price.");
        }
        
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");

            
        // Restore custQ from tempQ
        while (!tempQ.isEmpty()) 
        {
            CustomerQ.enqueue(tempQ.dequeue());
        }  
        
         // 9 - Calculate total Price of Payment method
        System.out.print("\nEnter the payment method to calculate total price for(cash | online | credit): ");
        String searchPayment = scanner.nextLine().trim();
        double totalPriceC = 0.00; 
        double totalPriceOn9 = 0.00 ;
        double totalPriceCard = 0.00;
            
            
            
        while(!CustomerQ.isEmpty())
        {
            obj = CustomerQ.dequeue();
                if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Cash"))
                {
                    totalPriceC += obj.getRestaurant().getFoodPrice();
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Online Transfer"))
                {
                    totalPriceOn9 += obj.getRestaurant().getFoodPrice();
                }
                else if(obj.getRestaurant().getPaymentMethod().equalsIgnoreCase("Credit Card"))
                {
                    totalPriceCard += obj.getRestaurant().getFoodPrice();
                }
                tempQ.enqueue(obj);
        }
        
        // Restore custQ from tempQ
        while (!tempQ.isEmpty()) 
        {
            CustomerQ.enqueue(tempQ.dequeue());
        }  
                
        if(searchPayment.equalsIgnoreCase("Cash")){
                    System.out.println("Total price customer who pay with cash : " + df.format(totalPriceC));
                }
                else if(searchPayment.equalsIgnoreCase("Online Transfer")){
                     System.out.printf("Total price customer who pay with online payment : " + df.format(totalPriceOn9));
                }
                else if(searchPayment.equalsIgnoreCase("Credit")){
                     System.out.printf("Total price customer who pay with : " + df.format(totalPriceCard));
    
                }
    
        
                
        System.out.println("\n+---------------+--------------------+------------------+--------------------------------+----------------+--------------------+---------------+------------+-------------------+-------------------+");
        
        // 10. Generate a report for food names and the number of customers
        System.out.println("\nReport for food names and the number of the customers");
        System.out.println("\nFood Report:");
        System.out.println("+-------------------+------------------+");
        System.out.println("| Food Name         | Total Customers |");
        System.out.println("+-------------------+------------------+");

        // Outer loop to traverse through CustomerQ
        while (!CustomerQ.isEmpty()) {
            obj = CustomerQ.dequeue(); // Dequeue a customer
            String currentFoodName = obj.getRestaurant().getFoodName(); // Get the food name
            boolean alreadyCounted = false;

            // Check if this food name has already been processed
            Queue<Customer> checkTempQ = new Queue<>(); // Temporary queue for iteration
            while (!tempQ.isEmpty()) {
                Customer tempCustomer = tempQ.dequeue();
                if (tempCustomer.getRestaurant().getFoodName().equals(currentFoodName)) {
                    alreadyCounted = true;
                }
                checkTempQ.enqueue(tempCustomer); // Restore tempQ
            }

            // Restore tempQ after checking
            while (!checkTempQ.isEmpty()) {
                tempQ.enqueue(checkTempQ.dequeue());
            }

            if (!alreadyCounted) {
                int foodCount = 1; // Start with the current customer

                //Inner loop to count occurrences of the current food name
                Queue<Customer> innerTempQ = new Queue<>();
                while (!CustomerQ.isEmpty()) 
                {
                    Customer tempObj = CustomerQ.dequeue();
                    if (tempObj.getRestaurant().getFoodName().equals(currentFoodName)) {
                        foodCount++;
                    }
                    innerTempQ.enqueue(tempObj); // Restore the original queue
                }

                // Restore CustomerQ from innerTempQ
                while (!innerTempQ.isEmpty()) {
                    CustomerQ.enqueue(innerTempQ.dequeue());
                }   

                // Display the result for the current food name
                System.out.printf("| %-17s | %-16d |\n", currentFoodName, foodCount);
    
            }

            tempQ.enqueue(obj); // Add the current customer to tempQ
        }

        // Restore CustomerQ from tempQ
        while (!tempQ.isEmpty()) {
        CustomerQ.enqueue(tempQ.dequeue());
        }

        System.out.println("+-------------------+------------------+");

        
        
        
    
        
        
        
        
        
        
                
           
        
        
    }
}
