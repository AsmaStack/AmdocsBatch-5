package org.ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class ModuleAccessException extends Exception {
	public ModuleAccessException(String str) {
		super(str);
	}
}

class UserAccessException extends Exception {
	public UserAccessException(String str){
		super(str);
	}
}

class InvalidUserPasswordException extends Exception {
	public InvalidUserPasswordException(String str){
		super(str);
	}
}

public class Login {
	
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser615";
	private static String password="user615";
	
	static Scanner sc = new Scanner(System.in);
	
	static int uid;
	static String user, pwd, role, module;
	
	public static void login() throws SQLException, ClassNotFoundException, ModuleAccessException {
		
		Class.forName(jdbcurl);
		Connection con = DriverManager.getConnection(connstring, username, password);
		
		System.out.print("Enter Username: ");
		user = sc.next();
		System.out.print("Enter Password: ");
		pwd = sc.next();

		System.out.println();
		String s = "select * from login_details where username ='"+user+"' and pwd ='"+pwd+"'";
		PreparedStatement pscheck = con.prepareStatement(s);
			
		ResultSet rs = pscheck.executeQuery();
		
		// check the role and provide further access to IMS
		if(rs.next()) {			
			Users u = new Users();
			u.setUid(rs.getInt(1));
			u.setUser(rs.getString(2));
			u.setPwd(rs.getString(3));
			u.setRole(rs.getString(4));
			u.setModule(rs.getString(5));
			
			System.out.println("Login Successful. User ID: "+u.getUid()+" Role: "+u.getRole());
			System.out.println();
			
			if(u.getRole().contains("admin")) {
				Menu admin = new Menu();
				admin.work();
			}
			else if(u.getModule().contains("stock")) {
				Menu user1 = new Menu();
				user1.manage_stock();
			}
			else if(u.getModule().contains("customer")) {
				Menu user2 = new Menu();
				user2.manage_customer();
			}
		}
		else {
			try {
				throw new InvalidUserPasswordException("Login Failed. Username/Password Invalid!!!");
			}
			catch (InvalidUserPasswordException e) {
				System.out.println("Exception Occurred: "+e);
			}
			finally {
				System.out.print("Want to login/signup(y/n) again: ");
				String ask = sc.next();
				switch (ask) {
					case "y":
						System.out.println();
						welcome();
						break;
					case "n":
						con.close();
						System.out.println("\nConnection closed");
						System.exit(0);
						break;
					default:
						break;
				}
			}
		}
	}

	public static void new_User() throws ClassNotFoundException, SQLException, ModuleAccessException {
		Class.forName(jdbcurl);
		Connection con = DriverManager.getConnection(connstring, username, password);
		
		int spkey ,key = 1234;
		
		System.out.print("Enter new User ID: ");
		uid = sc.nextInt();
		System.out.print("Enter new Username: ");
		user = sc.next();
		System.out.print("Enter new Password: ");
		pwd = sc.next();
		System.out.print("Enter new Role(admin/user): ");
		role = sc.next();
		switch (role) {
			case "user":
				try {
					System.out.print("Enter special key from admin to give access to users: ");
					spkey = sc.nextInt();
					if(spkey==key) {
						System.out.print("\tSelect a new Module(stock/customer): ");
						module = sc.next();
					}
					else {
						throw new ModuleAccessException("Access Denied!");
					}
				} catch (ModuleAccessException e) {
					System.out.println("Exception Occured: "+e);
				}
				break;
				
			case "admin":
				module = "both";
				break;
			default:
				try {
					throw new UserAccessException("Unauthorized User!");
				} catch (UserAccessException e){
					System.out.println("Exception Occurred: "+e);				
				}
				break;
		}
		
		String newlogin = "insert into login_details values(?,?,?,?,?)";
		PreparedStatement insertlogin = con.prepareStatement(newlogin);
		insertlogin.setInt(1,uid);
		insertlogin.setString(2, user);
		insertlogin.setString(3, pwd);
		insertlogin.setString(4, role);
		insertlogin.setString(5, module);
		
		insertlogin.execute();
		
		System.out.println("New User Added!\n");
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("                Login Page                ");
		System.out.println("------------------------------------------");
		login();
	}
	
	public static void welcome() throws ClassNotFoundException, SQLException, ModuleAccessException{
		// TODO Auto-generated method stub
		System.out.println("------------------------------------------");
		System.out.println("              Welcome to IMS              ");
		System.out.println("------------------------------------------");
		System.out.println("1. Signup/Register \n2. Login\n3. Exit");
		System.out.print("Choose your option: ");
		int n = sc.nextInt();
		
		if(n == 1) {
			System.out.println("Establishing Connection.....");
			System.out.println("------------------------------------------");
			System.out.println("               SignUp Page                ");
			System.out.println("------------------------------------------");
			new_User();
		}
		else if(n == 2) {
			System.out.println("Establishing Connection.....");
			System.out.println("------------------------------------------");
			System.out.println("                Login Page                ");
			System.out.println("------------------------------------------");
			login();
		}
		else if(n == 3) {
			System.out.println("Exiting the system!");
			System.exit(0);
		}
	}
}
