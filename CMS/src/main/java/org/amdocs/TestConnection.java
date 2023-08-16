package org.amdocs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

class ContactException extends Exception {
	public ContactException(String S) {
		super(S);
	}
}
public class TestConnection {

	private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";
	private static String connstring = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String username = "amdocsuser1";
	private static String password = "pass";

	public static void main(String[] args) {

		try {
			Class.forName(jdbcurl);
			Connection con = DriverManager.getConnection(connstring, username, password);
			System.out.println("Connection Successful");
			
			Statement smt = con.createStatement();
//			smt.executeUpdate("create table usercredentials(user_id varchar(100), username varchar(100), user_password varchar(100), user_role varchar(100) )");
//			
//			smt.executeUpdate("create table customer_table(customer_id varchar(100), customer_name varchar(100), customer_email varchar(100), customer_contact integer, customer_address varchar(100))" );
//			
			System.out.println("Customer Management System");

			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);

			System.out.println("Login or Sign up");
			System.out.println("1. Sign Up New User");
			System.out.println("2. Login User");
			System.out.println("3. Remove User");

			int s = sc.nextInt();

			switch (s) {
			case 1:
				signUpNewUser(con);
				break;

			case 2:
				login(con);
				break;

			case 3:
				System.out.println("Enter Customer id: ");
				int id = sc.nextInt();
				removeUser(con, id);
				break;

			default:
				System.out.println("Invalid.");
				break;
			}

//			CRUD OPERATIONS
			System.out.println("Choose an operation:");
			System.out.println("1. Search customer");
			System.out.println("2. Create customer");
			System.out.println("3. Update customer");
			System.out.println("4. Delete customer");

			int s1 = sc.nextInt();
			switch (s1) {
			case 1:
				// Search customer
				System.out.println("Enter Customer id: ");
				int search_Id = sc.nextInt();
				searchCustomer(con, search_Id);
				break;
			case 2:
				// Add customer
				addCustomer(con);
				break;
			case 3:
				// Update customer
				System.out.println("Enter Customer id: ");
				String update_Id = sc.next();
				updateCustomer(con, update_Id);
				break;

			case 4:
				// Delete customer
				System.out.println("Enter Customer id: ");
				int delete_Id = sc.nextInt();
				deleteCustomer(con, delete_Id);
				break;

			default:
				System.out.println("Invalid.");
				break;

			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
// try catch ended
//	ALL USER DEFINED FUNCTIONS DEFINATION.
	
//	FUNCTIONS ON USER TABLE -- SIGNUP, lOGIN, REMOVE.
	private static void signUpNewUser(Connection con) throws SQLException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter user_id");
		String user_id = scan.next();
		
		System.out.println("Enter user_name ");
		String user_name = scan.next();

		System.out.println("Enter user_password ");
		String user_password = scan.next();

		System.out.println("Enter user_role ");
		String user_role = scan.next();

		String regstmt = "insert into usercredentials(user_id, username,user_password,user_role) values(?,?,?,?)";

		try (PreparedStatement stmt_signUp = con.prepareStatement(regstmt)) {
			stmt_signUp.setString(1, user_id);
			stmt_signUp.setString(2, user_name);
			stmt_signUp.setString(3, user_password);
			stmt_signUp.setString(4, user_role);
			stmt_signUp.execute();
			System.out.println("welcome aboard. You are registered: ");
		}
	}

	private static void login(Connection con) throws SQLException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter user_id");
		String user_id = scan.next();
		
		System.out.println("Enter user_name ");
		String user_name = scan.next();

		System.out.println("Enter user_password ");
		String user_password = scan.next();

		String userLogin = "select username  from  usercredentials where username =? and user_password=?";

		try (PreparedStatement stmt_login = con.prepareStatement(userLogin)) {
			stmt_login.setString(1, user_name);
			stmt_login.setString(2, user_password);
			ResultSet rs1 = stmt_login.executeQuery();

			if (rs1.next())
				System.out.println("Login Successful");
			else
				System.out.println("User doesn't exist");
		}
	}

	private static void removeUser(Connection con, int user_id) throws SQLException {
		String stmt_delete = "DELETE FROM usercredentials WHERE user_id=?";
		try (PreparedStatement stmt_remove = con.prepareStatement(stmt_delete)) {
			stmt_remove.setInt(1, user_id);
			int row_exist = stmt_remove.executeUpdate();
			if (row_exist > 0) {
				System.out.println("User deleted successfully.");
			} else {
				System.out.println("User not found.");
			}
		}
	}

//	FUNCTIONS ON CUSTOMER TABLE -- CRUD OPERATIONS
	private static void searchCustomer(Connection con, int customerId) throws SQLException {
		String stmt = "select * from customer_table where customer_id = ?";
		try (PreparedStatement stmt_search = con.prepareStatement(stmt)) {
			stmt_search.setInt(1, customerId);
			ResultSet rs = stmt_search.executeQuery();
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

	private static void addCustomer(Connection con ) throws SQLException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter customer id: ");
		String customer_id = scan.next();

		
		System.out.println("Enter customer name: ");
		String customer_name = scan.next();

		System.out.println("Enter customer email: ");
		String customer_email = scan.next();

		System.out.println("Enter customer contact: ");
		String customer_contact = scan.next();
		
		try {
			if (customer_contact.length() != 10) {
				throw new ContactException("PLEASE ! ENTER 10 DIGIT NUMBER");
 			}
		}
		catch(ContactException CE) {
			System.out.println(CE.getMessage());
		}
		System.out.println("Enter customer address: ");
		String customer_address = scan.next();

		
		String insertstmt = "insert into customer_table(customer_id, customer_name,customer_email,customer_contact, customer_address) values(?,?,?,?,?)";
		try (PreparedStatement psinsert = con.prepareStatement(insertstmt)) {
			psinsert.setString(1, customer_id);
			psinsert.setString(2, customer_name);
			psinsert.setString(3, customer_email);
			psinsert.setString(4, customer_contact);
			psinsert.setString(5, customer_address);
			
			psinsert.execute();
			System.out.println("Data inserted: ");
		}
	}
//  we are takinf input 
	private static void updateCustomer(Connection con, String customerId) throws SQLException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter customer name: ");
		String customer_name = scan.next();

		System.out.println("Enter customer contact: ");
		String customer_contact = scan.next();

		try {
			if (customer_contact.length() != 10) {
				throw new ContactException("PLEASE ! ENTER 10 DIGIT NUMBER");
 			}
		}
		catch(ContactException CE) {
			System.out.println(CE.getMessage());
		}

		System.out.println("Enter customer address: ");
		String customer_address = scan.next();

		String updateStmt = "update customer_table set customer_name=?, customer_contact=?, customer_address=? where customer_id=" + customerId ;
		try (PreparedStatement psUpdate = con.prepareStatement(updateStmt)) {
			psUpdate.setString(1, customer_name);
			
			psUpdate.setString(2, customer_contact);
			psUpdate.setString(3, customer_address);
			psUpdate.executeUpdate();
			System.out.println("Data updated. ");
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

}
