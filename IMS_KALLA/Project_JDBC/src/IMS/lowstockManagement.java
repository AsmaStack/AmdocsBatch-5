package IMS;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class lowstockManagement {
	    private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";
	    private static String connstring = "jdbc:oracle:thin:@localhost:1521:XE";
	    private static String username = "amdocsuser1";
	    private static String password = "naru";
	    static Statement stmt;
	    public void Notify() {
	    	try {
				Class.forName(jdbcurl);
				 Connection con = DriverManager.getConnection(connstring, username, password);
				 String selectLowStockProductsQuery = "SELECT prodid, prodname, prodquant FROM product WHERE prodquant < 150";
		            PreparedStatement selectLowStockProductsStatement = con.prepareStatement(selectLowStockProductsQuery);

		            ResultSet resultSet = selectLowStockProductsStatement.executeQuery();
		            while (resultSet.next()) {
		                int productId = resultSet.getInt("prodid");
		                String productName = resultSet.getString("prodname");
		                int productQuantity = resultSet.getInt("prodquant");
		                System.out.println("+" + "-".repeat(30) + "+");
		                System.out.println("Product ID: " + productId);
		                System.out.println("Product Name: " + productName);
		                System.out.println("Product Quantity: " + productQuantity);
		                System.out.println("Low Stock Notification: Product quantity is less than 150");
		                System.out.println("+" + "-".repeat(30) + "+");

				 
		            }
	    	}
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
