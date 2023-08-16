package cms.amdocs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerOperation {
	private Connection conn;

	public CustomerOperation() throws SQLException {
		this.conn = DatabaseManager.getConnection();
	}

	// Insert
	public void addCustomer(Customer cust) throws SQLException {
		String query = "INSERT INTO cms_customers (cust_id, cust_name, cust_email, cust_address, cust_contactNumber) VALUES (cust_id_seq.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, cust.getName());
		statement.setString(2, cust.getEmail());
		statement.setString(3, cust.getAddress());
		statement.setString(4, cust.getContactNumber());
		statement.executeUpdate();
	}

	// View
	public void viewAllCustomers() throws SQLException {
		String query = "SELECT * FROM cms_customers";
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		System.out.println("\n********** Customer Records **********");
		if(result.next()) {
			System.out.println("\nCust_ID\tCust_Name\tCust_Email\tCust_Address\tCust_ContactNumber");
			System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t\t" + result.getString(3) + "\t" + result.getString(4) + "\t\t" + result.getString(5));
			while (result.next()) {
				System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t\t" + result.getString(3) + "\t" + result.getString(4) + "\t\t" + result.getString(5));
			}
		}
		else {
			System.out.println("No Customer Records Available !!!");
		}
	}

	// Update
	public void updateCustomer(Customer cust) throws SQLException {
		String query = "UPDATE cms_customers SET cust_name = ?, cust_email = ?, cust_address = ?, cust_contactNumber = ? WHERE cust_id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, cust.getName());
		statement.setString(2, cust.getEmail());
		statement.setString(3, cust.getAddress());
		statement.setString(4, cust.getContactNumber());
		statement.setInt(5, cust.getCustID());
		statement.executeUpdate();
	}

	// Delete
	public void deleteCustomer(Customer cust) throws SQLException {
		String query = "DELETE FROM cms_customers WHERE cust_id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, cust.getCustID());
		statement.executeUpdate();
	}
	
	// Whether the particular Customer Record is available or not
	public Customer getCustomerByCustId(Customer cust) throws SQLException {
		String query = "SELECT * FROM cms_customers WHERE cust_id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, cust.getCustID());
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			Customer customer = new Customer();
			customer.setCustID(result.getInt("cust_id"));
			customer.setName(result.getString("cust_name"));
			customer.setEmail(result.getString("cust_email"));
			customer.setAddress(result.getString("cust_address"));
			customer.setContactNumber(result.getString("cust_contactNumber"));
			return customer;
		}

		return null;
	}
	
}
