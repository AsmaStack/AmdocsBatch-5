package org.amdocs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class AgeException extends Exception {
    public AgeException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}

public class JDBC_Connection {
	
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="system";
	private static String password="pass";

	public static void main(String[] args) {
		
		try {
			
			Class.forName(jdbcurl);
			Connection con = DriverManager.getConnection(connstring, username, password);
			System.out.println("Successfully Connected ");
//			Statement smt = con.createStatement();
//			smt.executeUpdate("create table Customer(cust_id int, cust_name varchar(30))");
//			System.out.println("Table Created Successfully...");
//			con.close();
			
			try {
				
				System.out.println("Customer Management System");
				Scanner scan=new Scanner(System.in);
				
				System.out.println("Login or Register");
                System.out.println("1. Register New User");
                System.out.println("2. Login Existing User");
                System.out.println("3. Delete User");
                													
                int operat = scan.nextInt();

                switch (operat) {
                    case 1:
                        registerNewUser(con, scan);
                        break;
                        
                    case 2:
                    	loginExisting(con, scan);
                        break;
                        
                    case 3:
                    	System.out.println("Enter Customer id: ");
                        int deleteUserId = scan.nextInt();
                        deleteUser(con, deleteUserId);
                        break;
                        
                    default:
                        System.out.println("Invalid operation.");
                        break;
                }
                
				System.out.println("Choose an operation:");
                System.out.println("1. Search customer");
                System.out.println("2. Add customer");
                System.out.println("3. Update customer");
                System.out.println("4. Delete customer");
                
                int operation1 = scan.nextInt();
                switch (operation1) {
                    case 1:
                         //Search customer
                        System.out.println("Enter Customer id: ");
                        int searchCustomerId = scan.nextInt();
                        searchCustomer(con, searchCustomerId);
                        break;
                    case 2:
                        // Add customer
                    	addCustomer(con, scan);
                        break;
                    case 3:
                        // Update customer
                        System.out.println("Enter Customer id: ");
                        int updateCustomerId = scan.nextInt();
                        updateCustomer(con, scan, updateCustomerId);
                        break;
                        
                    case 4:
                        // Delete customer
                        System.out.println("Enter Customer id: ");
                        int deleteCustomerId = scan.nextInt();
                        deleteCustomer(con, deleteCustomerId);
                        break;
                        
                    default:
                        System.out.println("Invalid operation.");
                        break;
                        
                	}
                
                boolean exit = false;
                
                while (!exit) {
                        System.out.println("Login or Register");
                        System.out.println("1. Register New User");
                        System.out.println("2. Login Existing User");
                        System.out.println("3. Exit");
                        
                        int operation2 = scan.nextInt();
                        
                        switch (operation2) {
                            case 1:
                                registerNewUser(con, scan);
                                break;
                            
                            case 2:
                                loginExisting(con, scan);
                                break;
                            
                            case 3:
                                exit = true;
                                System.out.println("Exiting...");
                                con.close();
                                System.exit(0);
                                break;
                            
                            default:
                                System.out.println("Invalid operation.");
                                break;
                        }
                    }
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		}catch(Exception e){
			
			System.out.println(e);
			
		}
		
		

	}
	
	
	//Register New User
	private static void registerNewUser(Connection con, Scanner scan) throws SQLException {
		
		System.out.println("Enter user_id ");
		String user_id = scan.next();
		
		System.out.println("Enter user_name ");
		String user_name=scan.next();
		
		System.out.println("Enter user_password ");
		String user_password=scan.next();

				
		String regstmt="insert into user_cred(user_id, user_name,user_password) values(?,?,?)";
		
        try (PreparedStatement psRegister = con.prepareStatement(regstmt)) {
        	psRegister.setString(1, user_id);
        	psRegister.setString(2, user_name);
        	psRegister.setString(3, user_password);

        	psRegister.execute();
			System.out.println("User Registered: ");
        }
    }
	
	
	
	//login Existing User
	private static void loginExisting(Connection con, Scanner scan) throws SQLException{
		
		System.out.println("Enter user_name ");
		String user_name=scan.next();
		
		System.out.println("Enter user_password ");
		String user_password=scan.next();
		
		String userLogin ="select user_name  from  user_cred where user_name =? and user_password=?";
		
		try(PreparedStatement psLogin = con.prepareStatement(userLogin)){
			psLogin.setString(1, user_name);
			psLogin.setString(2, user_password);
			ResultSet result= psLogin.executeQuery();
	
		if(result.next())
			System.out.println("Login Successful");
		else
			System.out.println("User doesn't exist");
	}
}
	
	//Delete User 
	private static void deleteUser(Connection con, int user_id) throws SQLException {
		String deleteStmt = "DELETE FROM user_cred WHERE user_id=?";
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
	
	//search Customer
	private static void searchCustomer(Connection con, int customerId) throws SQLException {
        String searchStmt = "select * from cust_table where cust_id = ?";
        try (PreparedStatement psSearch = con.prepareStatement(searchStmt)) {
            psSearch.setInt(1, customerId);
            ResultSet rs = psSearch.executeQuery();
            if (rs.next()) {
                System.out.println("Customer ID: " + rs.getInt("cust_id"));
                System.out.println("Name: " + rs.getString("cust_name"));
                System.out.println("Email: " + rs.getString("cust_email"));
                System.out.println("Contact: " + rs.getInt("cust_number"));
                System.out.println("Age: " + rs.getString("cust_age"));
            } else {
                System.out.println("Customer not found.");
            }
        }
    }
	
	
	// add customer
	private static void addCustomer(Connection con, Scanner scan) throws SQLException {
		
        System.out.println("Enter customer name: ");
        String cust_name = scan.next();

        System.out.println("Enter customer email: ");
        String cust_email = scan.next();

        System.out.println("Enter customer contact: ");
        int cust_number = scan.nextInt();

        System.out.println("Enter customer age: ");
        int cust_age = scan.nextInt();
        
        System.out.println("Enter cust_id ");
		int cust_id=scan.nextInt(); 
		

        String insertstmt="insert into cust_table(cust_id, cust_name, cust_email, cust_number, cust_age) values(?,?,?,?,?)";
        try (PreparedStatement psinsert = con.prepareStatement(insertstmt)) {
			psinsert.setInt(1, cust_id);				
			psinsert.setString(2, cust_name);
			psinsert.setString(3, cust_email);
			psinsert.setInt(4, cust_number);
			psinsert.setInt(5, cust_age);
			psinsert.execute();
			System.out.println("Data inserted: ");
        }
    }
	
	//update customer 
	private static void updateCustomer(Connection con, Scanner scan, int customerId) throws SQLException {
		System.out.println("Enter Customer id ");
		int cust_id = scan.nextInt();
		
		System.out.println("Enter customer name: ");
        String cust_name = scan.next();

        System.out.println("Enter customer email: ");
        String cust_email = scan.next();

        System.out.println("Enter customer contact: ");
        int cust_number = scan.nextInt();

        System.out.println("Enter customer age: ");
        int cust_age = scan.nextInt();
        
        try {
            if (cust_age>100)
            throw new AgeException("Input age should be less than 100");
        }
        catch (AgeException ex) {
            System.out.println("Caught");
 
            // Print the message from MyException object
            System.out.println(ex.getMessage());
        }

        String updateStmt = "update cust_table set cust_name=?, cust_email=?, cust_number=?, cust_age=? where cust_id=?";
        try (PreparedStatement psUpdate = con.prepareStatement(updateStmt)) {
			psUpdate.setString(1, cust_name);
			psUpdate.setString(2, cust_email);
			psUpdate.setInt(3, cust_number);
			psUpdate.setInt(4, cust_age);
			psUpdate.setInt(5, cust_id);				
			psUpdate.executeUpdate();
			System.out.println("Data updated: ");
        }
    }
	
	//delete customer 
	private static void deleteCustomer(Connection con, int customerId) throws SQLException {
		
		String deleteStmt = "DELETE FROM cust_table WHERE cust_id=?";
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

}
