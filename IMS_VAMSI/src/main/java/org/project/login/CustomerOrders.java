package org.project.login;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class CustomerOrders {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";
	
public void cusorders() {
		
		try {
			Class.forName(jdbcurl);
			Connection con=DriverManager.getConnection(connstring, username, password); 
			String query = "SELECT OrderID, ORDERPRICE, ORDERDATE FROM orderdetails WHERE CUSTOMERID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, customerID);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int orderID = resultSet.getInt("OrderID");
                double totalAmount = resultSet.getDouble("TotalAmount");
                String orderDate = resultSet.getString("OrderDate");
                
                System.out.println("Order ID: " + orderID + ", Total Amount: " + totalAmount + ", Order Date: " + orderDate);
	
	            
	            
            }
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
}
		
		
		
	        
	    }
	
            
          

