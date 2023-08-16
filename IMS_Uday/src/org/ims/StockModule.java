package org.ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StockModule {
	
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser615";
	private static String password="user615";
	
	static int uid;
	static String user, pwd, role, module;
	
	public void manageStock() throws ClassNotFoundException, SQLException, ModuleAccessException {
		
		Class.forName(jdbcurl);
		Connection con = DriverManager.getConnection(connstring, username, password);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------------------------------------");
		System.out.println("             Select the operation          ");
		System.out.println("-------------------------------------------");
		System.out.println("\t1. Add Item\n\t2. Update Item\n\t3. Delete Item\n\t0. Go Back");
		System.out.print("\nChoose the no. to take the action: ");
		int t1 = sc.nextInt();
		if(t1==1) {
			StockDetails add = new StockDetails();
			add.addItems();
			manageStock();
		}
		else if(t1==2) {
			StockDetails update = new StockDetails();
			update.updateItems();
			manageStock();
		}
		else if(t1==3) {
			StockDetails delete = new StockDetails();
			delete.deleteItems();
			manageStock();
		}
		else if(t1==0) {
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
				if(u.getRole().contains("admin")) {
					Menu admin = new Menu();
					admin.work();
				}
				else if(u.getModule().contains("stock")) {
					Menu user1 = new Menu();
					user1.manage_stock();
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
	
	public void displayStock() throws ClassNotFoundException, SQLException {
		StockDetails display = new StockDetails();
		display.displayItems();
	}
}
