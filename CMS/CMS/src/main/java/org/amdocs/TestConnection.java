package org.amdocs;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
public class TestConnection {
	
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";
	static Statement stmt;
static Connection con;


	public static void main(String[] args) {
		try {
		Class.forName(jdbcurl);
		 try {
			con= DriverManager.getConnection(connstring, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection Successful");

		
//		String createstmt="create table Customer(custid int, custname varchar(20))";
//		stmt=con.createStatement();
//		stmt.executeQuery(createstmt);
//		System.out.println("table created");1
		System.out.print("Select the rights you want: "
				+ "1: Only viewing"
				+ "2. Viewing and inserting"
				+ "3. Viewing, Inserting and Updating"
				+ "4. Admin Rights");
		
		
		}
		
		 catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

}



}