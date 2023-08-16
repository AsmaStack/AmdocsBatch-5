package org.ims;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerDetails {
	
	private String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private String username="amdocsuser615";
	private String password="user615";
	
	Scanner sc = new Scanner(System.in);
	
	int customerId;
	String customerName, mobileno, order_id, order_date;
	Date date;
	
	Connection con;
	
	public void addCustomers() throws  SQLException, ClassNotFoundException {
		try {
			Class.forName(jdbcurl);
			con = DriverManager.getConnection(connstring, username, password);
			
			System.out.print("\nEnter Customer Id: ");
			customerId = sc.nextInt();
			System.out.print("Enter Customer Name: ");
			customerName = sc.next();
			System.out.print("Enter Customer Mobile No: ");
			mobileno = sc.next();
			System.out.print("Enter Order Id: ");
			order_id = sc.next();
			System.out.print("Enter Order Date: ");
			order_date = sc.next();
			date = Date.valueOf(order_date);
			
			// inserting into database
//			/*
			String insert_statement="insert into customer_details values(?,?,?,?,?)";
			PreparedStatement insert = con.prepareStatement(insert_statement);
			insert.setInt(1, customerId);
			insert.setString(2, customerName);
			insert.setString(3, mobileno);
			insert.setString(4, order_id);
			insert.setDate(5, date);
			insert.executeUpdate();
			
			System.out.println("Insert Success");
//			*/
		}
		finally {
			System.out.println();
		}
	}
	
	public void updateCustomers() throws SQLException, ClassNotFoundException{
		try {
			Class.forName(jdbcurl);
			con = DriverManager.getConnection(connstring, username, password);
			
			System.out.println("1.Update Customer Name");
			System.out.println("2.Update Mobile no.");
			System.out.println("3.Update Order ID");
			System.out.println("4.Update Order Date");
			System.out.println("Select what needs to be updated: ");
			int nupdate = sc.nextInt();
			if(nupdate==1) {
				System.out.print("\nEnter Customer ID: ");
				customerId = sc.nextInt();
				System.out.print("Enter new Customer Name: ");
				customerName = sc.next();
				
				// updating Order ID
//				/*
				String update_statement="update customer_details set cname=? where cid=?";
				PreparedStatement update = con.prepareStatement(update_statement);
				update.setInt(2, customerId);
				update.setString(1, customerName);
				
				update.execute();
				System.out.println("Customer Name Updated Successfully");
//				*/
			}
			else if(nupdate==2) {
				System.out.print("\nEnter Customer ID: ");
				customerId = sc.nextInt();
				System.out.print("Enter new Mobile no: ");
				mobileno = sc.next();
				
				// updating Order ID
//				/*
				String update_statement="update customer_details set mobile=? where cid=?";
				PreparedStatement update = con.prepareStatement(update_statement);
				update.setInt(2, customerId);
				update.setString(1, mobileno);
				
				update.execute();
				System.out.println("Mobile no Updated Successfully");
//				*/
			}
			else if(nupdate==3) {
				System.out.print("\nEnter Customer Id: ");
				customerId = sc.nextInt();
				System.out.print("Enter new Order ID: ");
				order_id = sc.next();
				
				// // updating Order Date
//				/*
				String update_statement="update customer_details set orderId=? where cid=?";
				PreparedStatement update = con.prepareStatement(update_statement);
				update.setInt(2, customerId);
				update.setString(1, order_id);
				
				update.execute();
				System.out.println("Order ID Updated Successfully");
//				*/
			}
			else if(nupdate==4) {
				System.out.print("\nEnter Customer Id: ");
				customerId = sc.nextInt();
				System.out.print("Enter new Order Date: ");
				order_date = sc.next();
				date = Date.valueOf(order_date);
				
				// // updating Order Date
//				/*
				String update_statement="update customer_details set orderDate=? where cid=?";
				PreparedStatement update = con.prepareStatement(update_statement);
				update.setInt(2, customerId);
				update.setDate(1, date);
				
				update.execute();
				System.out.println("Order Date Updated Successfully");
//				*/
			}
		}
		finally {
			System.out.println();
		}
	}
	
	public void deleteCustomers() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(jdbcurl);
			con = DriverManager.getConnection(connstring, username, password);
		
			// deleting the row
	//		/*
			System.out.print("\nEnter Customer Id which needs to be deleted: ");
			customerId = sc.nextInt();
			String delete_statement="delete from customer_details where cid=?";
			PreparedStatement delete = con.prepareStatement(delete_statement);
			delete.setInt(1, customerId);
			
			delete.execute();
			System.out.println("Successfully Deleted!!");
	//		*/
		}
		finally {
			System.out.println();
		}
	}
	
	public void displayCustomers() throws SQLException, ClassNotFoundException{
		try {
			Class.forName(jdbcurl);
			con = DriverManager.getConnection(connstring, username, password);
	//		/*
			String select_statement = "select * from customer_details";
			PreparedStatement select = con.prepareStatement(select_statement);
			
			ResultSet rs = select.executeQuery();
			System.out.println("------------------------------------------------------------------------------------------");
			System.out.println("Customer ID \tCustomer Name \t\tMobile No \t\tOrder ID \tOrder Date");
			System.out.println("------------------------------------------------------------------------------------------");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getDate(5));
			}
			System.out.println("------------------------------------------------------------------------------------------\n");
			System.out.println("\t\t	===== Printed Successfully =====");
			System.out.println();
	//		*/
		}
		finally {
			System.out.println();
		}
	}
}
