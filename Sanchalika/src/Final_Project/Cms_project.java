package Final_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Cms_project {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="sanch";
	private static String password="pass";
	
	public static void main(String[] args) {
		
		try {
			Class.forName(jdbcurl);
			try {
				Connection con = DriverManager.getConnection(connstring, username, password);
				System.out.println("Customer Management System");
				
				Scanner scan=new Scanner(System.in);
				
				System.out.println("Login or Register");
                System.out.println("1. Register New User");
                System.out.println("2. Login Existing User");
                System.out.println("3. Delete User");
                
                int operation = scan.nextInt();

                switch (operation) {
                    case 1:
                        registerNewUser(con, scan);
                        break;
                        
                    case 2:
                    	loginExisting1(con, scan);
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
                
                loginExisting1(con,scan);
                
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
                                loginExisting1(con, scan);
                                break;
                            
                            case 3:
                                exit = true;
                                System.out.println("Exiting...");
                                break;
                            
                            default:
                                System.out.println("Invalid operation.");
                                break;
                        }
                    }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//------------------------------------------Perform Customer Operations-------------------------------------------------------------
	
	private static void performCustomerOperations(Connection con, Scanner scan, String username) throws SQLException {
        if (!isAdmin(con, username)) {
            System.out.println("Only admin users are allowed to perform customer operations.");
            return;
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
        	}
	//------------------------------------------Login Existing User--------------------------------------------------------------
	
	private static void loginExisting1(Connection con, Scanner scan) throws SQLException {
	    System.out.println("Enter existing user_name ");
	    String user_name = scan.next();

	    System.out.println("Enter user_password ");
	    String user_password = scan.next();

	    String userLogin = "select username from usercredentials where username = ? and user_password = ?";

	    try (PreparedStatement psLogin = con.prepareStatement(userLogin)) {
	        psLogin.setString(1, user_name);
	        psLogin.setString(2, user_password);
	        ResultSet result = psLogin.executeQuery();

	        if (result.next()) {
	            System.out.println("Login Successful");
	            String username = user_name; 
	            performCustomerOperations(con, scan, username); 
	        } else {
	            System.out.println("User doesn't exist");
	        }
	    }
	}
	
	//-------------------------------------------------Search customers------------------------------------------------------------------------
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
                System.out.println("Address: " + rs.getString("customer_address"));
            } else {
                System.out.println("Customer not found.");
            }
        }
    }
	//----------------------------------add customers-----------------------------------------------------------------------------
	private static void addCustomer(Connection con, Scanner scan) throws SQLException {
		
        System.out.println("Enter customer name: ");
        String customer_name = scan.next();

        System.out.println("Enter customer email: ");
        String customer_email = scan.next();

        System.out.println("Enter customer contact: ");
        int customer_contact = scan.nextInt();

        System.out.println("Enter customer address: ");
        String customer_address = scan.next();
        
        System.out.println("Enter user_id ");
		int user_id=scan.nextInt();

        String insertstmt="insert into customer_table(customer_id, customer_name,customer_email,customer_contact, customer_address, user_id) values(customer_id_seq.NEXTVAL,?,?,?,?,?)";
        try (PreparedStatement psinsert = con.prepareStatement(insertstmt)) {

			psinsert.setString(1, customer_name);
			psinsert.setString(2, customer_email);
			psinsert.setInt(3, customer_contact);
			psinsert.setString(4, customer_address);
			psinsert.setInt(5, user_id);				
			psinsert.execute();
			System.out.println("Data inserted: ");
        }
    }
	
	
	//------------------------------------------update customer----------------------------------------------------------------
	
	
	private static void updateCustomer(Connection con, Scanner scan, int customerId) throws SQLException {
		System.out.println("Enter Customer id ");
		int customer_id = scan.nextInt();
		
		System.out.println("Enter customer name: ");
        String customer_name = scan.next();

        System.out.println("Enter customer email: ");
        String customer_email = scan.next();

        System.out.println("Enter customer contact: ");
        int customer_contact = scan.nextInt();

        System.out.println("Enter customer address: ");
        String customer_address = scan.next();

        String updateStmt = "update customer_table set customer_name=?, customer_email=?, customer_contact=?, customer_address=? where customer_id=?";
        try (PreparedStatement psUpdate = con.prepareStatement(updateStmt)) {
			psUpdate.setString(1, customer_name);
			psUpdate.setString(2, customer_email);
			psUpdate.setInt(3, customer_contact);
			psUpdate.setString(4, customer_address);
			psUpdate.setInt(5, customer_id);				
			psUpdate.executeUpdate();
			System.out.println("Data updated: ");
        }
    }
	
	
	//----------------------------------------------delete customer----------------------------------------------------------------------
	
	
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
	
	
	//------------------------------------------------register new user----------------------------------------------------------------------
	
	
	private static void registerNewUser(Connection con, Scanner scan) throws SQLException {
		
		System.out.println("Enter user_name ");
		String user_name=scan.next();
		
		System.out.println("Enter user_password ");
		String user_password=scan.next();
		
		System.out.println("Enter user_role ");
		String user_role=scan.next();
				
		String regstmt="insert into usercredentials(user_id, username,user_password,user_role) values(user_id_seq.nextval,?,?,?)";
		
        try (PreparedStatement psRegister = con.prepareStatement(regstmt)) {

        	psRegister.setString(1, user_name);
        	psRegister.setString(2, user_password);
        	psRegister.setString(3, user_role);				
        	psRegister.execute();
			System.out.println("User Registered: ");
        }
    }
	//--------------------------------------------delete user---------------------------------------------------------------------------
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
	//-----------------------------------Check if user is admin or normal user ------------------------------------------------------------------------------------
	private static boolean isAdmin(Connection con, String username) throws SQLException {
		
        String roleQuery = "SELECT user_role FROM usercredentials WHERE username = ?";
        try (PreparedStatement psRole = con.prepareStatement(roleQuery)) {
            psRole.setString(1, username);
            ResultSet rs = psRole.executeQuery();
            if (rs.next()) {
                String userRole = rs.getString("user_role");
                return "admin".equals(userRole);
            }
        }
        return false;
    }
	//----------------------------------------------------------------------------------------------------------------------
}
