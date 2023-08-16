package org.project.login;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddUser {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";
	
public void newuser() {
		
		try {
			Class.forName(jdbcurl);
			Connection con=DriverManager.getConnection(connstring, username, password); 
			
			Scanner scan4= new Scanner(System.in); 
			System.out.println("Enter UserId :"); 
			int oid= scan4.nextInt(); 
			System.out.println("Enter Username :"); 
			String uname=scan4.next();
			System.out.println("Enter Password :"); 
			String pass =scan4.next();
			System.out.println("Enter Role :"); 
			int role =scan4.nextInt();
			

			String insertuserstmt="insert into userdetails values (?, ?, ?, ?)"; 
			PreparedStatement userinsert= con.prepareStatement (insertuserstmt); 
			
			userinsert.setInt (1, oid); 
			userinsert.setString (2, uname);
			userinsert.setString (3, pass);
			userinsert.setInt (4, role);
			

			userinsert.execute();

			System.out.println("New User Added");

}catch (ClassNotFoundException e) {
	e.printStackTrace();
		
} catch (SQLException e){
	e.printStackTrace();
}
}
	

}

