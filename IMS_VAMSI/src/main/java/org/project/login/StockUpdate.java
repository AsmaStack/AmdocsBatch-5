package org.project.login;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StockUpdate {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";
	
public void stockupdt() {
		
		try {
			Class.forName(jdbcurl);
			Connection con=DriverManager.getConnection(connstring, username, password); 
			
        	System.out.println("***********************STOCK UPDATE***********************");
			String stock="SELECT Productid, productname, quantity from products"; 
			PreparedStatement psstock= con.prepareStatement (stock); 

			ResultSet resultSet1 = psstock.executeQuery(stock);       
			System.out.println("----------------------------------------------");
            System.out.printf("| %-15s | %-10s | %-8s |\n", "Product ID", "Product Name", "Quantity");
            System.out.println("----------------------------------------------");
			
			while (resultSet1.next()) {
                int productId = resultSet1.getInt("Productid");
                String productName = resultSet1.getString("productname");
                int quantity = resultSet1.getInt("quantity");
                
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
