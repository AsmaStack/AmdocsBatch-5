package org.project.login;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Userpage {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName(jdbcurl);
			Connection con=DriverManager.getConnection(connstring, username, password); 
			//System.out.println("Connection Successful.");
			
	        Scanner scanner1 = new Scanner(System.in);

	        // Prompt the user for input
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
	        System.out.println("                                                           LOGIN PAGE                                                                                                  ");
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
	        System.out.println(" ");
	        System.out.println("***************CHOOSE LOGIN TYPE***************");
	        System.out.println("1. ADMIN.");
	        System.out.println("2. SALES PERSON.");
	        System.out.println("3. CUSTOMER");
	        int choice1 = scanner1.nextInt();

            switch (choice1) {
                case 1:
        	        Scanner scanner = new Scanner(System.in);

                	System.out.print("Enter username: ");
        	        String enteredUsername = scanner.nextLine();

        	        System.out.print("Enter password: ");
        	        String enteredPassword = scanner.nextLine();

        	        String sql = "SELECT * FROM userdetails WHERE username = ? AND password = ?";
        	        PreparedStatement pscheck= con.prepareStatement (sql);
        	        pscheck.setString(1, enteredUsername);
                    pscheck.setString(2, enteredPassword);

                    // Execute the query
                    ResultSet resultSet = pscheck.executeQuery();

                    // Check if the query returned a matching user
                    if (resultSet.next()) {
                    	
                        System.out.println("Login successful. ");
                        System.out.println(" ");
                        System.out.println("Welcome!! " + enteredUsername + ":-)");
                        
                        System.out.println(" ");
                        System.out.println("********************Choose an operation******************");
                        System.out.println(" ");
                        System.out.println("1. Insert Product.");
                        System.out.println("2. Update Product.");
                        System.out.println("3. Delete Product.");
                        System.out.println("4. Stock Tracking.");
                        System.out.println("5. Order Details.");
                        System.out.println("6. Add Order Details.");
                        System.out.println("7. Delete Order Details.");
                        System.out.println("8. Low Stock Details");
                        System.out.println("9. Add New User");




                        int choice = scanner.nextInt();

                        switch (choice) {
                            case 1:

                            	Task1 e=new Task1();
                            	e.TaskA();
                            	
                                break;
                                
                            case 2:
                                        	
                            	UpdateProduct e1=new UpdateProduct();
                            	e1.updateprdt();
                            	
                                break;
                                
                            case 3:
                            	
                            	DeleteProduct e2=new DeleteProduct();
                            	e2.deleteprdt();
                            	
                                break;
                                
                            case 4:
                            	
                            	StockUpdate e3=new StockUpdate();
                            	e3.stockupdt();
                            	
                    			break;
                            case 5:

                            	OrderDetails e4=new OrderDetails();
                            	e4.orderdtls();
                            	
                    			break;
                    			
                            case 6:
                            	
                            	InsertOrderDetails e5=new InsertOrderDetails();
                            	e5.insertOrderdtls();
                            	
                                break;
                              
                            case 7:
                            	
                            	DeleteOrders e6=new DeleteOrders();
                            	e6.deleteorders();
                            	
                                break;  
                                
                            case 8:
                            	
                            	LowStock e7=new LowStock();
                            	e7.lowstock();
                            	
                                break;  
                                
                            case 9:
                            	
                            	AddUser e8=new AddUser();
                            	e8.newuser();
                            	
                                break;  
                            	

                            default:
                                System.out.println("Invalid choice.");
                        }
                    
                    } else {
                        System.out.println("Login Failed :( ");
                        System.out.println("Invalid username or password.");
                    }
                    break;
                    
                case 2:
                	Scanner scanner3 = new Scanner(System.in);

                	System.out.print("Enter username: ");
        	        String enteredUsername3 = scanner3.nextLine();

        	        System.out.print("Enter password: ");
        	        String enteredPassword3 = scanner3.nextLine();

        	        String sql2 = "SELECT * FROM userdetails WHERE username = ? AND password = ?";
        	        PreparedStatement pscheck2= con.prepareStatement (sql2);
        	        pscheck2.setString(1, enteredUsername3);
                    pscheck2.setString(2, enteredPassword3);

                    // Execute the query
                    ResultSet resultSet2 = pscheck2.executeQuery();

                    // Check if the query returned a matching user
                    if (resultSet2.next()) {
                    	
                        System.out.println("Login successful. ");
                        System.out.println(" ");
                        System.out.println("Welcome!! " + enteredUsername3 + ":-)");
                        
                        System.out.println(" ");
                        System.out.println("********************Choose an operation******************");
                        System.out.println(" ");
                        System.out.println("1. Customer Order Details.");
                        System.out.println("2. Stock Details");
                        System.out.println("3. Low Stock Details.");
                        
                        int choice = scanner3.nextInt();

                        switch (choice) {
                            case 1:

                            	OrderDetails e=new OrderDetails();
                            	e.orderdtls();
                            	
                            	
                                break;
                                
                            case 2:
                                        	
                            	StockUpdate e1=new StockUpdate();
                            	e1.stockupdt();
                            	
                                break;
                                
                            case 3:
                            	
                            	LowStock e2=new LowStock();
                            	e2.lowstock();
                            	
                                break;
                        
            }
		}    
            
                    
                case 3:
                	Scanner scanner2 = new Scanner(System.in);

                	System.out.print("Enter username: ");
        	        String enteredUsername1 = scanner2.nextLine();

        	        System.out.print("Enter password: ");
        	        String enteredPassword1 = scanner2.nextLine();

        	        String sql1 = "SELECT * FROM customerlogin WHERE CUSTUSERNAME = ? AND CUSTPASSWORD = ?";
        	        PreparedStatement pscheck1= con.prepareStatement (sql1);
        	        pscheck1.setString(1, enteredUsername1);
                    pscheck1.setString(2, enteredPassword1);

                    // Execute the query
                    ResultSet resultSet1 = pscheck1.executeQuery();

                    // Check if the query returned a matching user
                    if (resultSet1.next()) {
                        System.out.println("Login successful. Welcome, " + enteredUsername1 + "!");
                        
                        System.out.println(" ");
                        System.out.println("********************Choose an operation******************");
                        System.out.println(" ");
                        System.out.println("1. Avaliable Products");
                        
                        int choice = scanner2.nextInt();

                        switch (choice) {
//                            case 1:
//
//                            	OrderDetails a=new OrderDetails();
//                                a.orderdtls();
//                            	
//                                break;
                                
                            case 1:
                                        	
                            	StockUpdate e1=new StockUpdate();
                            	e1.stockupdt();
                            	
                                break;
//                        
//                        System.out.println(" ");
//                        System.out.println("***************CUSTOMER PORTAL***************");
//                        System.out.println("Track Orders");
//                        
//                        OrderDetails a=new OrderDetails();
//                        a.orderdtls();
//                        
//                	break;
                        }
            }
                    else {
                        System.out.println("Login Failed :( ");
                        System.out.println("Invalid username or password.");
                    }
                    break;
                    
                
            }
		}
                            	


catch (ClassNotFoundException e) {
	e.printStackTrace();
		
} catch (SQLException e){
	e.printStackTrace();
}
	}
}
		
	



