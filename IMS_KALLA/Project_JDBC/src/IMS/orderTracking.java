package IMS;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class orderTracking {
	 	private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";
	    private static String connstring = "jdbc:oracle:thin:@localhost:1521:XE";
	    private static String username = "amdocsuser1";
	    private static String password = "naru";
	    static Statement stmt;
	    
	    public static void createOrder() {
	    	    	
	        Scanner scan3 = new Scanner(System.in);	 
	        System.out.println("enter orderId ID");
	    	int orderId = scan3.nextInt(); 
	        System.out.println("enter Product ID");
	    	int productId = scan3.nextInt(); 
	    	System.out.println("Order Quantity ");
	        int orderQuantity = scan3.nextInt();
	        System.out.println("customer Id");
	        String customerid = scan3.next(); // Example customer name
	        System.out.println("Enter Order Date in YYYY-MM-DD format");
	        String odate=scan3.next();;
	        	        
	        try {
				Class.forName(jdbcurl);
				 Connection con = DriverManager.getConnection(connstring, username, password);
				// Insert the order into the OrderTable
		            String insertOrderQuery = "INSERT INTO ordertable (orderid, opropid, orderdate, orderquant, orderprice, customerid) " +
		                    "VALUES (?, ?, ?, ?, ?, ?)";
		            PreparedStatement insertOrderStatement = con.prepareStatement(insertOrderQuery);
		            // Provide a unique order ID (you'll need to manage this manually)
		            insertOrderStatement.setInt(1, orderId); // Example order ID
		            insertOrderStatement.setInt(2, productId);
		            insertOrderStatement.setString(3, odate);
		            insertOrderStatement.setInt(4, orderQuantity);
		            // You would calculate the order price based on the product price and order quantity
		            insertOrderStatement.setInt(5, calculateOrderPrice(productId, orderQuantity));
		            insertOrderStatement.setString(6, customerid);
		            insertOrderStatement.executeUpdate();

		            // Update the product quantity in the Product table
		            String updateQuantityQuery = "UPDATE product SET prodquant = prodquant - ? WHERE prodid = ?";
		            PreparedStatement updateQuantityStatement = con.prepareStatement(updateQuantityQuery);
		            updateQuantityStatement.setInt(1, orderQuantity);
		            updateQuantityStatement.setInt(2, productId);
		         
		            updateQuantityStatement.executeUpdate();

		            System.out.println("Order placed and product quantity updated successfully.");						 						 						 						 						 						 			 
	        }
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	    }
	    public static void orderHistory() {
	    	try {
				Class.forName(jdbcurl);
				 Connection con = DriverManager.getConnection(connstring, username, password);
				 String query = "SELECT ORDERID, OPROPID, ORDERDATE,ORDERQUANT,ORDERPRICE,CUSTOMERid FROM ordertable";
		            PreparedStatement preparedStatement = con.prepareStatement(query);

		            ResultSet resultSet = preparedStatement.executeQuery();
		            System.out.println("----------------------------------------------------------------------------------------------");
			        System.out.printf("| %-15s | %-10s | %-8s | %-12s | %-10s | %-15s |\n", "ORDER_ID", "ORDER_DATE", "PRODUCT_ID", "ORDER_QUANTITY", "ORDER_PRICE", "CUSTOMER_ID");
			        System.out.println("----------------------------------------------------------------------------------------------");


		            while (resultSet.next()) {
		                int orderId = resultSet.getInt("ORDERID");
		                int productId = resultSet.getInt("OPROPID");
		                String orderDate = resultSet.getString("ORDERDATE");
		                int orderQuant = resultSet.getInt("ORDERQUANT");
		                int orderPrice=resultSet.getInt("ORDERPRICE");
		                String customerid = resultSet.getString("CUSTOMERid");

		                //System.out.println("+" + "-".repeat(30) + "+");
		                //System.out.println("| Customer id: " + customerid + " ".repeat(3) + "|");
		                //System.out.println("| Order ID: " + orderId + " ".repeat(14 - String.valueOf(orderId).length()) + "|");
		                //System.out.println("| Product ID: " + productId + " ".repeat(14 - String.valueOf(productId).length()) + "|");
		                //System.out.println("| Order Date: " + orderDate + " ".repeat(3) + "|");
		                //System.out.println("| Order Quantity: " + orderQuant + " ".repeat(14 - String.valueOf(orderQuant).length()) + "|");
		                //System.out.println("| Order Price: " + orderPrice + " ".repeat(14 - String.valueOf(orderPrice).length()) + "|");
		                //System.out.println("| Customer id: " + customerid + " ".repeat(3) + "|");
		                //System.out.println("+" + "-".repeat(30) + "+");
		                printTableRow(orderId ,orderDate, productId, orderQuant, orderPrice, customerid);

		                
		            }
	    	}
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
        public static void deleteOrder() {
        	
        	try {
				Class.forName(jdbcurl);
			    Connection con1 = DriverManager.getConnection(connstring, username, password);
			    Scanner scan2 = new Scanner(System.in);
			    String query = "SELECT * FROM ordertable WHERE orderId = ?";
	            PreparedStatement preparedStatement = con1.prepareStatement(query);
	            System.out.println("ENTER ORDER ID");
	            int orderId =scan2.nextInt();
				preparedStatement.setInt(1, orderId);			            
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                System.out.println("Order found in the database:");
	                System.out.println("Order ID: " + resultSet.getInt("orderID"));			                			                			                			               			                			                
	                // Add more columns as needed			             			     			                
	                String deleteQuery = "DELETE FROM ordertable WHERE orderID = ?";
	                PreparedStatement deleteStatement = con1.prepareStatement(deleteQuery);
	                deleteStatement.setInt(1, orderId);

	                int rowsDeleted = deleteStatement.executeUpdate();

	                if (rowsDeleted > 0) {
	                    System.out.println("Order with ID " + orderId + " deleted successfully.");
	                } else {
	                    System.out.println("Order deletion failed. Order with ID " + orderId + " not found.");
	                }
	                
	                
	            } else {
	                System.out.println(" Order with ID "+orderId+" s not found in the database.");
	            }			    				    			    				    	
	    	}
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	       	        	        	
        }
        public static void updateOrder() {
        	try {
				Class.forName(jdbcurl);
			    Connection con1 = DriverManager.getConnection(connstring, username, password);
			    Scanner scan2 = new Scanner(System.in);
			    String query = "SELECT * FROM ordertable WHERE orderId = ?";
	            PreparedStatement preparedStatement = con1.prepareStatement(query);
	            System.out.println("enter order id");
	            int orderId =scan2.nextInt();
				preparedStatement.setInt(1, orderId);			            
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                System.out.println("Order found in the database:");
	                System.out.println("Order ID: " + resultSet.getInt("orderID"));
	                
	                Scanner scan=new Scanner(System.in);	                
	                System.out.println("enter the new product ID to update");
	                int OPROPID1=scan.nextInt();
	                System.out.println("enter the new order Date to update in YYYY-MM-DD");
	                String ORDERDATE=scan.next();	                
	                System.out.println("enter the new order quantity to update ");
	                int ORDERQUANT = scan.nextInt();
	                System.out.println(orderId);
	                System.out.println("The product is caluculated using above information ");
	                //int ORDERPRICE=scan.nextInt();
	                
	                String updatestmt="update ordertable set OPROPID=?,ORDERDATE=?,ORDERQUANT=?,ORDERPRICE=? where orderID=?";		                			                			                 
	                PreparedStatement psupdate = con1.prepareStatement(updatestmt);

	                //psupdate.setInt(1,prodId);
	                psupdate.setInt(1,OPROPID1);
	                psupdate.setString(2,ORDERDATE);	              
	                psupdate.setInt(3,ORDERQUANT);
	                psupdate.setInt(4,calculateOrderPrice(OPROPID1, ORDERQUANT));
	                psupdate.setInt(5,orderId);
	                psupdate.execute();
	                System.out.println("Recoded updated");			                			                
	                // Add more columns as needed
	                String updateQuantityQuery = "UPDATE product SET prodquant = prodquant - ? WHERE prodid = ?";
		            PreparedStatement updateQuantityStatement1 = con1.prepareStatement(updateQuantityQuery);
		            updateQuantityStatement1.setInt(1, ORDERQUANT);
		            updateQuantityStatement1.setInt(2, OPROPID1);
		            updateQuantityStatement1.executeUpdate();
	            } else {
	                System.out.println("Order with ID "+orderId+" s not found in the database.");
	            }			    				    			    				    	
	    	}
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
	    private static int calculateOrderPrice(int productId, int orderQuantity) {
	        // You would fetch the product price from the database based on productId
	        int productPrice = getProductPriceFromDatabase(productId);
	        return productPrice * orderQuantity;
	    }

	    // This method simulates fetching product price from the database
	    private static int getProductPriceFromDatabase(int productId) {
	        // Replace this with actual code to fetch product price from the database
	        // For simplicity, returning a fixed value here
	    	int productPrice = 0;
	    	 try {
					Class.forName(jdbcurl);
					 Connection con = DriverManager.getConnection(connstring, username, password);
					 
					 
					 String selectPriceQuery = "SELECT prodprice FROM product WHERE prodid = ?";
				        PreparedStatement selectPriceStatement = con.prepareStatement(selectPriceQuery);
				        selectPriceStatement.setInt(1, productId);

				        ResultSet resultSet = selectPriceStatement.executeQuery();
				        if (resultSet.next()) {
				            productPrice = resultSet.getInt("prodprice");
				           
				        } else {
				            System.out.println("Product not found.");
				        }
	    	 }
		    	catch (ClassNotFoundException e) {
					e.printStackTrace();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	 return productPrice;
	    }
	    private static void printTableRow(int ORDERID,String ORDERDATE,int PRODUCTID, int orderquantity, int price, String customerid) {
	    	// TODO Auto-generated method stub
	    System.out.printf("| %-15s | %-10s | %-8s | %-12s | %-10s | %-15s |\n", ORDERID, ORDERDATE, PRODUCTID, orderquantity, price, customerid);

	    }

	    

}
