package org.project.login;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class LowStock {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";
	
public void lowstock() {
	try {
		Class.forName(jdbcurl);
		Connection con=DriverManager.getConnection(connstring, username, password); 
		
		 String query = "SELECT * FROM products WHERE QUANTITY < 100";
		 PreparedStatement lowstock= con.prepareStatement (query);
		 ResultSet resultSet = lowstock.executeQuery(query);
		 
		 System.out.println("----------------------------------------------");
         System.out.printf("| %-15s | %-10s | %-8s |\n", "Product ID", "Product Name", "Quantity");
         System.out.println("----------------------------------------------");
         // Display the products
         while (resultSet.next()) {
             int productId = resultSet.getInt("productid");
             String productName = resultSet.getString("productname");
             int quantity = resultSet.getInt("quantity");
             
             printTableRow(productId ,productName, quantity);
         }			

}catch (ClassNotFoundException e) {
	e.printStackTrace();
	
} catch (SQLException e){
	e.printStackTrace();
}
}

	private static void printTableRow(int Productid,String productname,int quantity) {
	// TODO Auto-generated method stub
		System.out.printf("| %-15s | %-10s | %-8s |\n", Productid , productname, quantity);
}
}

