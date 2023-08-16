package ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class IMS {
	private static String jdbcurl = "oracle.jdbc.driver.OracleDriver" ;
	private static String connString = "jdbc:oracle:thin:@localhost:1521:XE";
     private static String username = "Aswini";
     private static String password = "pass";
     
     static Connection con;
     
	public static void main(String[] args) {
    	 try {
    		 Class.forName(jdbcurl);
    		 try {
    			 Connection con = DriverManager.getConnection(connString, username, password);
    			 
    			 if(con == null) {
    				 System.out.println("null");
    			 }

 				System.out.println("Connection Successful:");
 				
 				
 				//new tableCreation(con).creatingTable();
 				//new InsertingValues(con).valuesInsertProduct();
 				//new InsertingValues(con).updateProduct();
 				//ProductInfo.deleteProduct();
 				//new InsertingValues(con).valuesInsertOrders();
 				
 				new UserAuthentication(con).Userlogin();
 				
 				System.out.println("Success!!");
 				
 				con.close();
 				
 				System.out.println("Connection closed!!");
    		    
           
 				
                
                
 				
 			} catch (SQLException e) {

 				// TODO Auto-generated catch block

 				e.printStackTrace();

 			}

 		} catch (ClassNotFoundException e) {

 			// TODO Auto-generated catch block

 			e.printStackTrace();
    	}
    }
}



