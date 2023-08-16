package ims;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User_Module {
    
	
	public static void deleteUserTable()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter UserID to delete: ");
		int temp = sc.nextInt();
		String q = "delete from User_accounts where UserID = ?";
		Connection con1 = InventoryManagementSystemprimary.conn;
		
		try {
			PreparedStatement stmt = con1.prepareStatement(q);
			stmt.setInt(1, temp);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	
	public static void updateUserTable()
	{
	
Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating User_accounts values in table =======");
		
		System.out.print("UserID - ");
		int UserID = sc.nextInt();
		
		System.out.println("What to update: \n1.Username \n2.password \n3.Roles ");
		int choice = sc.nextInt();
	
		   
		
		     if(choice == 1 ) {
		    	 System.out.println("enter new Username:");
		    	 String temp = sc.next();
		    	 String q = "update User_accounts set username = ? where userID = ? ";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setString(1,temp);
		    		 stmt.setInt(2,UserID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 2){
		    	 System.out.println("enter new Password:");
		    	 String temp = sc.next();
		    	 String q = "update User_accounts set password = ? where userID = ? ";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setString(1,temp);
		    		 stmt.setInt(2,UserID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }
		     else if(choice == 3){
		    	 System.out.println("enter new Roles:");
		    	 String temp = sc.next();
		    	 String q = "update User_accounts set role = ? where userID = ? ";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setString(1,temp);
		    		 stmt.setInt(2,UserID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		    	 
		     }else {
		    	 System.out.println("Invalid!!");
		    	 
		     }
		     
		 
	}
	
	public static void readUserTable() {
	System.out.println("Showing User Table ");
	
	String q = "select * from user_accounts";
	
	 Connection conn = InventoryManagementSystemprimary.conn;
    	
	 try {
		 
		 Statement stmt = conn.createStatement();
		 ResultSet resultSet = stmt.executeQuery(q);
		 
		 while (resultSet.next()) {
		 int column1Value = resultSet.getInt("userid");
         String column2Value = resultSet.getString("Username");
         String column3Value = resultSet.getString("Password");
         String column4Value = resultSet.getString("Role");
		 

         System.out.println("userid: " + column1Value + ", Username: " + column2Value +", Password: " + column3Value +", Role: " + column4Value);
  
		 }
		 
		 
		 
	 }catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
	}
	
	
}
