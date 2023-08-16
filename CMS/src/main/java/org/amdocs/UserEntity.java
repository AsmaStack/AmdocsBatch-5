package org.amdocs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.lang.Exception;

class AgeException extends Exception {
    public AgeException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}

public class UserEntity {
		private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
		private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
		private static String username="amdocsuser1";
		private static String password="pass";
		
		public static void main(String[] args) {
			
			try {
				Class.forName(jdbcurl);
				try {
					Connection con = DriverManager.getConnection(connstring, username, password);
					System.out.println("Customer Management System");
					Scanner scan=new Scanner(System.in);
					
	                boolean exit = false;
	                int flag = 0;		

	                while (!exit) {
					if (flag==0){
						System.out.println("Login or Register");
		                System.out.println("1. Register New User");
		                System.out.println("2. Login Existing User");
		                System.out.println("3. Delete User");
	                    System.out.println("4. Exit");
	
		                int operat = scan.nextInt();
	
		                switch (operat) {
		                    case 1:
		                        registerNewUser(con, scan);
		                        break;
		                        
		                    case 2:
		                    	flag = loginExisting(con, scan);
		                    	if (flag==1) {
			                    	System.out.println("Admin false");
		                    	}
		                    	else if (flag==2) {
		                    	System.out.println("Admin true");
		                    	}
		                        break;
		                        
		                    case 3:
		                    	System.out.println("Enter Customer id: ");
		                        int deleteUserId = scan.nextInt();
		                        deleteUser(con, deleteUserId);
		                        break;
		                        
		                    case 4:
	                            exit = true;
	                            System.out.println("Exiting...");
	                            break;
		                        
		                    default:
		                        System.out.println("Invalid operation.");
		                        break;
		                }
					}
	                else {
	                	exit = repeatingSteps(con, scan, flag);
	                }
	                }
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		private static boolean repeatingSteps(Connection con, Scanner scan, int flag) throws SQLException {
			boolean exit = false;
			if (flag==2){
				System.out.println("Choose an operation:");
                System.out.println("1. View all customer");
                System.out.println("2. Search customer");
                System.out.println("3. Add customer");
                System.out.println("4. Update customer");
                System.out.println("5. Delete customer");
                System.out.println("6. Exit");

                
                int operation1 = scan.nextInt();
                switch (operation1) {
                	case 1:
                		String searchStmt = "select * from customer_table";
            	        try (PreparedStatement psSearch = con.prepareStatement(searchStmt)) {
            	            ResultSet rs = psSearch.executeQuery();
            	            while (rs.next()) {
	                	    	System.out.println("Customer ID: " + rs.getInt("customer_id") + " Name: " + rs.getString("customer_name") + " Email: " + rs.getString("customer_email") + " Contact: " + rs.getInt("customer_contact") + " Age: " + rs.getString("customer_age"));
	                	    }}
	            	        break;
            	        
                    case 2:
                         //Search customer
                        System.out.println("Enter Customer id: ");
                        int searchCustomerId = scan.nextInt();
                        searchCustomer(con, searchCustomerId);
                        break;
                    case 3:
                        // Add customer
                    	addCustomer(con, scan);
                        break;
                    case 4:
                        // Update customer
                        System.out.println("Enter Customer id: ");
                        int updateCustomerId = scan.nextInt();
                        updateCustomer(con, scan, updateCustomerId);
                        break;
                        
                    case 5:
                        // Delete customer
                        System.out.println("Enter Customer id: ");
                        int deleteCustomerId = scan.nextInt();
                        deleteCustomer(con, deleteCustomerId);
                        break;
                        
                    case 6:
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                        
                    default:
                        System.out.println("Invalid operation.");
                        break;
                        
                	}
                }
			else if(flag==1) {
                	System.out.println("Choose an operation:");
                    System.out.println("1. View all customer");
	                System.out.println("1. Search customer");
	                System.out.println("2. Add customer");
	                System.out.println("3. Exit");

	                
	                int operation1 = scan.nextInt();
	                switch (operation1) {
		                case 1:
	                		String searchStmt = "select * from customer_table";
	            	        try (PreparedStatement psSearch = con.prepareStatement(searchStmt)) {
	            	            ResultSet rs = psSearch.executeQuery();
	                	    while (rs.next()) {
	                	    	System.out.println("Customer ID: " + rs.getInt("customer_id") + " Name: " + rs.getString("customer_name") + " Email: " + rs.getString("customer_email") + " Contact: " + rs.getInt("customer_contact") + " Age: " + rs.getString("customer_age"));
	                	    }}
	            	        break;
	                    case 2:
	                         //Search customer
	                        System.out.println("Enter Customer id: ");
	                        int searchCustomerId = scan.nextInt();
	                        searchCustomer(con, searchCustomerId);
	                        break;
	                    case 3:
	                        // Add customer
	                    	addCustomer(con, scan);
	                        break;
	                    case 4:
	                        exit = true;
	                        System.out.println("Exiting...");
	                        break;
	                    default:
	                        System.out.println("Invalid operation.");
	                        break;
	                }
                }
			return exit;
	    }
		
		private static void searchCustomer(Connection con, int customerId) throws SQLException {
	        String searchStmt = "select * from customer_table where customer_id = ?";
	        try (PreparedStatement psSearch = con.prepareStatement(searchStmt)) {
	            psSearch.setInt(1, customerId);
	            ResultSet rs = psSearch.executeQuery();
	            if (rs.next()) {
	                System.out.println("Customer ID: " + rs.getInt("customer_id"));
	                System.out.println("Name: " + rs.getString("customer_name"));
	                System.out.println("Email: " + rs.getString("customer_email"));
	                System.out.println("Contact: " + rs.getInt("customer_contact"));
	                System.out.println("Age: " + rs.getString("customer_age"));
	            } else {
	                System.out.println("Customer not found.");
	            }
	        }
	    }
		
		private static void addCustomer(Connection con, Scanner scan) throws SQLException {
			
	        System.out.println("Enter customer name: ");
	        String customer_name = scan.next();

	        System.out.println("Enter customer email: ");
	        String customer_email = scan.next();

	        System.out.println("Enter customer contact: ");
	        int customer_contact = scan.nextInt();

	        System.out.println("Enter customer age: ");
	        int customer_age = scan.nextInt();
	        
	        System.out.println("Enter customer_id ");
			int customer_id=scan.nextInt(); 
	        
			
	        String insertstmt="insert into customer_table(customer_id, customer_name, customer_email, customer_contact, customer_age) values(?,?,?,?,?)";
	        try (PreparedStatement psinsert = con.prepareStatement(insertstmt)) {
	        	if (customer_age>100)
		            throw new AgeException("Input age should be less than 100");
				psinsert.setInt(1, customer_id);				
				psinsert.setString(2, customer_name);
				psinsert.setString(3, customer_email);
				psinsert.setInt(4, customer_contact);
				psinsert.setInt(5, customer_age);
				psinsert.execute();
				System.out.println("Data inserted: ");
	        }
	        catch (AgeException ex) {
	            System.out.println("Caught");
	 
	            // Print the message from MyException object
	            System.out.println(ex.getMessage());
	        }
	    }
		
		private static void updateCustomer(Connection con, Scanner scan, int customerId) throws SQLException {
			System.out.println("Enter Customer id ");
			int customer_id = scan.nextInt();
			
			System.out.println("Enter customer name: ");
	        String customer_name = scan.next();

	        System.out.println("Enter customer email: ");
	        String customer_email = scan.next();

	        System.out.println("Enter customer contact: ");
	        int customer_contact = scan.nextInt();

	        System.out.println("Enter customer age: ");
	        int customer_age = scan.nextInt();
	        

	        String updateStmt = "update customer_table set customer_name=?, customer_email=?, customer_contact=?, customer_age=? where customer_id=?";
	        try (PreparedStatement psUpdate = con.prepareStatement(updateStmt)) {
	        	if (customer_age>100)
		            throw new AgeException("Input age should be less than 100");
				psUpdate.setString(1, customer_name);
				psUpdate.setString(2, customer_email);
				psUpdate.setInt(3, customer_contact);
				psUpdate.setInt(4, customer_age);
				psUpdate.setInt(5, customer_id);				
				psUpdate.executeUpdate();
				System.out.println("Data updated: ");
	        }
	        catch (AgeException ex) {
	            System.out.println("Caught");
	 
	            // Print the message from MyException object
	            System.out.println(ex.getMessage());
	        }
	    }

		
		private static void deleteCustomer(Connection con, int customerId) throws SQLException {
			
			String deleteStmt = "DELETE FROM customer_table WHERE customer_id=?";
		    try (PreparedStatement psDelete = con.prepareStatement(deleteStmt)) {
		        psDelete.setInt(1, customerId);
		        int rowsAffected = psDelete.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Customer deleted successfully.");
		        } else {
		            System.out.println("Customer not found.");
		        }
		    }
		}
		
		private static void registerNewUser(Connection con, Scanner scan) throws SQLException {
			
			System.out.println("Enter user_id ");
			String user_id = scan.next();
			
			System.out.println("Enter user_name ");
			String user_name=scan.next();
			
			System.out.println("Enter user_password ");
			String user_password=scan.next();

					
			String regstmt="insert into usercredentials(user_id, user_name,user_password) values(?,?,?)";
			
	        try (PreparedStatement psRegister = con.prepareStatement(regstmt)) {
	        	psRegister.setString(1, user_id);
	        	psRegister.setString(2, user_name);
	        	psRegister.setString(3, user_password);

	        	psRegister.execute();
				System.out.println("User Registered: ");
	        }
	    }
		
		private static int loginExisting(Connection con, Scanner scan) throws SQLException{
			
			System.out.println("Enter user_name ");
			String user_name=scan.next();
			
			System.out.println("Enter user_password ");
			String user_password=scan.next();
			
			String userLogin ="select user_name  from  usercredentials where user_name =? and user_password=?";
			
			try(PreparedStatement psLogin = con.prepareStatement(userLogin)){
				psLogin.setString(1, user_name);
				psLogin.setString(2, user_password);
				ResultSet result= psLogin.executeQuery();
		
			if(result.next()) {
				System.out.println("Login Successful");
				if (user_name.equals("lakshit")){
					return 2;
				}
				else {
					return 1;
				}
			}
				
			else {
				System.out.println("User doesn't exist");
				return 0;
			}

		}
	}
		
		private static void deleteUser(Connection con, int user_id) throws SQLException {
			String deleteStmt = "DELETE FROM usercredentials WHERE user_id=?";
		    try (PreparedStatement psDelete = con.prepareStatement(deleteStmt)) {
		        psDelete.setInt(1, user_id);
		        int rowsAffected = psDelete.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("User deleted successfully.");
		        } else {
		            System.out.println("User not found.");
		        }
		    }
		}
		
	}