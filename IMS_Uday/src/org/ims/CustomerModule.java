package org.ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerModule {
	
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser615";
	private static String password="user615";
	
	static int uid;
	static String user, pwd, role, module;
	
	public void manageCustomer() throws ClassNotFoundException, SQLException, ModuleAccessException {
		
		Class.forName(jdbcurl);
		Connection con = DriverManager.getConnection(connstring, username, password);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------------------------------------");
		System.out.println("             Select the operation          ");
		System.out.println("-------------------------------------------");
		System.out.println("\t1. Add Customer Details\n\t2. Update Customer Details\n\t3. Delete Customer Details\n\t0. Go Back");
		System.out.print("\nChoose the no. to take the action: ");
		int t2 = sc.nextInt();
		if(t2==1) {
			CustomerDetails add = new CustomerDetails();
			add.addCustomers();
			manageCustomer();
		}
		else if(t2==2) {
			CustomerDetails update = new CustomerDetails();
			update.updateCustomers();
			manageCustomer();
		}
		else if(t2==3) {
			CustomerDetails delete = new CustomerDetails();
			delete.deleteCustomers();
			manageCustomer();
		}
		else if(t2==0) {
			System.out.print("Enter User ID: ");
			uid = sc.nextInt();
			String s = "select * from login_details where userid ='"+uid+"'";
			PreparedStatement pscheck = con.prepareStatement(s);
				
			ResultSet rs = pscheck.executeQuery();
			
			if(rs.next()) {
				Users u = new Users();
				u.setUid(rs.getInt(1));
				u.setRole(rs.getString(4));
				u.setModule(rs.getString(5));
				
				if(uid == u.getUid()) {
					if(u.getRole().contains("admin")) {
						Menu admin = new Menu();
						admin.work();
					}
					else if(u.getModule().contains("customer")) {
						Menu user1 = new Menu();
						user1.manage_customer();
					}
				}
				else {
					try {
						throw new UserAccessException("Wrong User ID!");
					} catch (UserAccessException e){
						System.out.println("Exception Occurred: "+e);				
					}
				}
			}
		}
		else {
			System.out.println("Invalid Option! Logging out...");
			Logout l = new Logout();
			l.logout();
		}
		
		sc.close();
	}
	
	public void displayCustomer() throws ClassNotFoundException, SQLException {
		CustomerDetails display = new CustomerDetails();
		display.displayCustomers();
	}
}
