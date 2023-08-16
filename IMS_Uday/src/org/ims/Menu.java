package org.ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser615";
	private static String password="user615";
	
	static Scanner sc = new Scanner(System.in);
	
	public static void adminmenu() {
		System.out.println("==========================================");
		System.out.println("                Admin Menu                ");
		System.out.println("==========================================");
		System.out.println("1. Manage item");
		System.out.println("2. Display all items");
		System.out.println("3. Manage customer details");
		System.out.println("4. Display all Customers");
		System.out.println("5. Manage users");
		System.out.println("6. Display all Users");
		System.out.println("7. Logout");
		System.out.println("0. Exit!");
	}
	
	public void manage_stock() throws ClassNotFoundException, SQLException, ModuleAccessException {
		StockModule sm = new StockModule();
		System.out.println("------------------------------------------");
		System.out.println("                    Menu                  ");
		System.out.println("------------------------------------------");
		System.out.println("\t1. Manage Stock\n\t2. Display Stock\n\t3. Logout");
		System.out.print("\nChoose the no. to take the action: ");
		int u1 = sc.nextInt();
		if(u1 == 1) {
			sm.manageStock();
			manage_stock();
		}
		else if(u1 == 2) {
			sm.displayStock();
			manage_stock();
		}
		else if(u1 == 3) {
			Logout lg = new Logout();
			lg.logout();
		}
		else {
			System.out.println("Invalid Option");
			manage_stock();
		}
	}
	
	public void manage_customer() throws ClassNotFoundException, SQLException, ModuleAccessException {
		CustomerModule cm = new CustomerModule();
		System.out.println("------------------------------------------");
		System.out.println("                    Menu                  ");
		System.out.println("------------------------------------------");
		System.out.println("\t1. Manage Customer Details\n\t2. Display Customer Details\n\t3. Logout");
		System.out.print("\nChoose the no. to take the action: ");
		int u2 = sc.nextInt();
		if(u2==1) {
			cm.manageCustomer();
			manage_customer();
		}
		else if(u2==2) {
			cm.displayCustomer();
			manage_customer();
		}
		else if(u2 == 3) {
			Logout lg = new Logout();
			lg.logout();
		}
		else {
			System.out.println("Invalid Option");
			manage_customer();
		}
	}
	
	public void options() throws SQLException, ClassNotFoundException, ModuleAccessException {
		
		//Connecting to the database
		Class.forName(jdbcurl);
		Connection con = DriverManager.getConnection(connstring, username, password);	

		int opt;
		
		do {
			adminmenu();
			System.out.print("\nChoose the no. to take the action: ");
			opt = sc.nextInt();
			
			int key = 1234;
			int spkey;
			
			if(opt==1) {
				System.out.print("Enter special key to manage stock: ");
				spkey = sc.nextInt();
				try {
					if(spkey == key) {
						System.out.println();
						StockModule sm = new StockModule();
						sm.manageStock();
						System.out.println("Going back to main menu..\n");
					}
					else {
						throw new ModuleAccessException("Access Denied!");
					} 
				} catch (ModuleAccessException e) {
					System.out.println("Exception Occured: "+e);
				}
			}
				
			else if(opt==2) {
				StockModule sm1 = new StockModule();
				sm1.displayStock();		
				System.out.println("Going back to main menu..\n");
			}
				
			else if(opt==3) {
				CustomerModule sm2 = new CustomerModule();
				sm2.manageCustomer();
				System.out.println("Going back to main menu..\n");
			}
				
			else if(opt==4) {
				CustomerModule sm3 = new CustomerModule();
				sm3.displayCustomer();
				System.out.println("Going back to main menu..\n");
			}
			
			else if(opt==5) {
				UserModule user = new UserModule();
				user.manageUser();
				System.out.println("Going back to main menu..\n");
			}
			
			else if(opt==6) {
				UserModule user = new UserModule();
				user.displayUser();
				System.out.println("Going back to main menu..\n");
			}
			
			else if(opt == 7) {
				Logout lg = new Logout();
				lg.logout();
			}
			
			else if(opt==0) {
				con.close();
				System.out.println("Connection closed.");
				System.out.println("Exiting the system!");
				System.exit(-1);
			}
		} while (opt != -1);
	}
	
	public void work() throws ModuleAccessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("Connection Successful\n");
		options();
		
	}

}
