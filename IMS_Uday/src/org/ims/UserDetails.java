package org.ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDetails {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser615";
	private static String password="user615";
	
	public void updateUsers() throws SQLException, ClassNotFoundException{
		try {
			Class.forName(jdbcurl);
			Connection con = DriverManager.getConnection(connstring, username, password);
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("1.Update User Name");
			System.out.println("2.Update Role");
			System.out.println("3.Update Module");
			System.out.println("Select what needs to be updated: ");
			int nupdate = sc.nextInt();
			if(nupdate==1) {
				System.out.print("\nEnter User ID: ");
				int userId = sc.nextInt();
				System.out.print("Enter new UserName: ");
				String userName = sc.next();
				
				// updating User ID
	//			/*
				String update_statement="update login_details set username=? where userid=?";
				PreparedStatement update = con.prepareStatement(update_statement);
				update.setInt(2, userId);
				update.setString(1, userName);
				
				update.execute();
				System.out.println("UserName Updated Successfully");
	//			*/
			}
			else if(nupdate==2) {
				System.out.print("\nEnter User ID: ");
				int userId = sc.nextInt();
				System.out.print("Assign new Role: ");
				String role = sc.next();
				
				// updating User Role
	//			/*
				String update_statement="update login_details set user_role=? where userid=?";
				PreparedStatement update = con.prepareStatement(update_statement);
				update.setInt(2, userId);
				update.setString(1, role);
				
				update.execute();
				System.out.println("New Role Assigned Successfully");
	//			*/
			}
			else if(nupdate==3) {
				System.out.print("\nEnter User ID: ");
				int userId = sc.nextInt();
				System.out.print("Assign New Module: ");
				String module = sc.next();
				
				// updating Order ID
	//			/*
				String update_statement="update login_details set module_type=? where userid=?";
				PreparedStatement update = con.prepareStatement(update_statement);
				update.setInt(2, userId);
				update.setString(1, module);
				
				update.execute();
				System.out.println("New Module Assigned Successfully");
	//			*/
			}
			sc.close();
		}
		finally {
			System.out.println();
		}
		
	}
	
	public void deleteUsers() throws SQLException, ClassNotFoundException{
		try {
			Class.forName(jdbcurl);
			Connection con = DriverManager.getConnection(connstring, username, password);
			
			Scanner sc = new Scanner(System.in);
			
			// deleting the row
	//		/*
			System.out.print("\nEnter User Id which needs to be deleted: ");
			int userId = sc.nextInt();
			String delete_statement="delete from login_details where userid=?";
			PreparedStatement delete = con.prepareStatement(delete_statement);
			delete.setInt(1, userId);
			
			delete.execute();
			System.out.println("Successfully Deleted!!");
	//		*/
		}
		finally {
			System.out.println();
		}
	}
	
	public void displayUsers() throws SQLException, ClassNotFoundException{
		try {
			Class.forName(jdbcurl);
			Connection con = DriverManager.getConnection(connstring, username, password);
			
			String authorizedusers = "select * from login_details";
			PreparedStatement select = con.prepareStatement(authorizedusers);
			
			ResultSet rs = select.executeQuery();
			System.out.println("---------------------------------------------------------");
			System.out.println("User ID \tUserName \tRole \tModule Authorized");
			System.out.println("---------------------------------------------------------");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(4)+"\t"+rs.getString(5));
			}
			System.out.println("---------------------------------------------------------\n");
			System.out.println("\t	===== Printed Successfully =====");
			System.out.println();
		}
		finally {
			System.out.println();
		}
	}
}
