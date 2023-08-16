package org.project.login;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertOrderDetails {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";
	
public void insertOrderdtls() {
		
		try {
			Class.forName(jdbcurl);
			Connection con=DriverManager.getConnection(connstring, username, password); 
			
			Scanner scan4= new Scanner(System.in); 
			System.out.println("Enter Order ID "); 
			int oid= scan4.nextInt(); 
			System.out.println("Enter order Date"); 
			String date1=scan4.next();
			System.out.println("Enter Product ID"); 
			int pid3 =scan4.nextInt();
			System.out.println("Enter order quantity :"); 
			int oqty =scan4.nextInt();
			System.out.println("Enter order customer id :"); 
			int cusid =scan4.nextInt();

			String insertorderstmt="insert into orderdetails values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement orderinsert= con.prepareStatement (insertorderstmt); 
			
			orderinsert.setInt (1, oid); 
			orderinsert.setString (2, date1);
			orderinsert.setInt (3, pid3);
			orderinsert.setInt (4, oqty);
			orderinsert.setInt(5, calculateOrderPrice(pid3, oqty));            			
			orderinsert.setInt (6, cusid);

         	orderinsert.execute();
         	
         	String updateQuantityQuery = "UPDATE products SET QUANTITY = QUANTITY - ? WHERE PRODUCTID = ?";
            PreparedStatement updateQuantityStatement1 = con.prepareStatement(updateQuantityQuery);
            updateQuantityStatement1.setInt(1, oqty);
            updateQuantityStatement1.setInt(2, pid3);
            updateQuantityStatement1.executeUpdate();

			System.out.println("New Order Added");

}catch (ClassNotFoundException e) {
	e.printStackTrace();
		
} catch (SQLException e){
	e.printStackTrace();
}
}
	private static int calculateOrderPrice(int productId, int orderQuantity) {
	        // You would fetch the product price from the database based on productId
		int productPrice = getProductPriceFromDatabase(productId);
	    return productPrice * orderQuantity;
	    }
	private static int getProductPriceFromDatabase(int productId) {
        // Replace this with actual code to fetch product price from the database
        // For simplicity, returning a fixed value here
    	int productPrice = 0;
    	 try {
				Class.forName(jdbcurl);
				 Connection con = DriverManager.getConnection(connstring, username, password);
				 
				 
				 String selectPriceQuery = "SELECT price FROM products WHERE productid = ?";
			        PreparedStatement selectPriceStatement = con.prepareStatement(selectPriceQuery);
			        selectPriceStatement.setInt(1, productId);

			        ResultSet resultSet = selectPriceStatement.executeQuery();
			        if (resultSet.next()) {
			            productPrice = resultSet.getInt("price");
			           
			        } else {
			            System.out.println("Product not found.");
			        }
    	 }
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 return productPrice;
}
}

