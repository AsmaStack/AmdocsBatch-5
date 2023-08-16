package org.project.login;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteOrders {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser1";
	private static String password="pass";
	
public void deleteorders() {
		
		try {
			Class.forName(jdbcurl);
			Connection con=DriverManager.getConnection(connstring, username, password); 
			Scanner scan3= new Scanner(System.in); 
			System.out.println("Enter Order ID to Delete Order. "); 
			int pid2= scan3.nextInt(); 
			String deletestmt="DELETE FROM orderdetails WHERE ORDERID = ?"; 
			PreparedStatement psdelete= con.prepareStatement (deletestmt); 
            psdelete.setInt (1, pid2);

			psdelete.execute();

			System.out.println("Order Deleted Successfully.");

}catch (ClassNotFoundException e) {
	e.printStackTrace();
	
} catch (SQLException e){
	e.printStackTrace();
}	
}
}
