package org.project.login;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class UpdateProduct {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";
	
	public void updateprdt() {
		
		try {
			Class.forName(jdbcurl);
			Connection con=DriverManager.getConnection(connstring, username, password); 
			
			Scanner scan2= new Scanner(System.in); 
			System.out.println("Enter Product ID to Update Product. "); 
			int pid1= scan2.nextInt(); 
			System.out.println("Enter Product Name"); 
			String pname1=scan2.next();
			System.out.println("Enter Product Price"); 
			int pprice1 =scan2.nextInt();
			System.out.println("Enter Product Quantity"); 
			int pqty1 =scan2.nextInt();

			String updatestmt="update products set PRODUCTNAME=?, PRICE=?, QUANTITY=? where PRODUCTID=?"; 
			PreparedStatement psupdate= con.prepareStatement (updatestmt); 
			
			psupdate.setString (1, pname1); 
			psupdate.setInt (2, pprice1);
         	psupdate.setInt (3, pqty1);
         	psupdate.setInt (4, pid1);


			psupdate.execute();

			System.out.println("Product Details Updated");
			
			
			
			
			
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
			
	} catch (SQLException e){
		e.printStackTrace();
	}	
	
	

}
}
