package org.project.login;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Task1 {private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
private static String username="amdocsuser1";
private static String password="pass";
	
	public void TaskA() {
		
		try {
			Class.forName(jdbcurl);
			Connection con=DriverManager.getConnection(connstring, username, password); 
		
		Scanner scan1= new Scanner(System.in); 
		System.out.println("Enter Product ID "); 
		int pid= scan1.nextInt(); 
		System.out.println("Enter Product Name"); 
		String pname=scan1.next();
		System.out.println("Enter Product Price"); 
		int pprice =scan1.nextInt();
		System.out.println("Enter Product Quantity"); 
		int pqty =scan1.nextInt();

		String insertstmt="insert into products values (?, ?, ?, ?)"; 
		PreparedStatement psinsert= con.prepareStatement (insertstmt); 
		
		psinsert.setInt (1, pid); 
		psinsert.setString (2, pname);
     	psinsert.setInt (3, pprice);
     	psinsert.setInt (4, pqty);
     	
     	psinsert.execute();
     	System.out.println("New Product Added");
	
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
			
	} catch (SQLException e){
		e.printStackTrace();
	}	

	}
	}
