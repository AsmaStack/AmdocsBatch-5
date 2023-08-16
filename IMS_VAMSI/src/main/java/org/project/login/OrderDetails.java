package org.project.login;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OrderDetails {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";
	
public void orderdtls() {
		
		try {
			Class.forName(jdbcurl);
			Connection con=DriverManager.getConnection(connstring, username, password); 
			
			System.out.println("ORDER DETAILS");
			String orderdetails="SELECT * from orderdetails"; 
			PreparedStatement psorder= con.prepareStatement (orderdetails); 

			ResultSet resultSet2 = psorder.executeQuery(orderdetails);    
			System.out.println("----------------------------------------------------------------------------------------------");
	        System.out.printf("| %-15s | %-10s | %-8s | %-12s | %-10s | %-15s |\n", "ORDER_ID", "ORDER_DATE", "PRODUCT_ID", "ORDER_QUANTITY", "ORDER_PRICE", "CUSTOMER_ID");
	        System.out.println("----------------------------------------------------------------------------------------------");

			
			while (resultSet2.next()) {
                int orderId = resultSet2.getInt("ORDERID");
                String orderdate = resultSet2.getString("ORDERDATE");
                int productid = resultSet2.getInt("PRODUCTID");
                int orderquantity = resultSet2.getInt("ORDERQUANTITY");
                int orderprice = resultSet2.getInt("ORDERPRICE");
                int customerid = resultSet2.getInt("CUSTOMERID");
                
                printTableRow(orderId ,orderdate, productid, orderquantity, orderprice, customerid);
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
}
private static void printTableRow(int ORDERID,String ORDERDATE,int PRODUCTID, int orderquantity, int price, int customerid) {
	// TODO Auto-generated method stub
System.out.printf("| %-15s | %-10s | %-8s | %-12s | %-10s | %-15s |\n", ORDERID, ORDERDATE, PRODUCTID, orderquantity, price, customerid);

}
}
