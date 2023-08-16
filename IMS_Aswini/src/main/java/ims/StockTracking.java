package ims;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class StockTracking {
	private Connection con;
	public StockTracking(Connection con) {
		this.con = con;
	}
	

	public void valuesInsertStockNotify()
	{
        Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Inserting stock notify in table =======");
		System.out.println("Enter stock notify values:");
		
		System.out.print("NotifyID - ");
		int NotifyID = sc.nextInt();
		
		System.out.println("ProductID - ");
		int ProductID = sc.nextInt();
		
		System.out.println("Order Date - (yyyy-MM-dd)");
		String NotifyDt = sc.next();
		Date NotifyDate = Date.valueOf(NotifyDt);	
		
		String q1 = "insert into StockNotify(NotifyID,ProductID,NotifyDate) values(?,?,?)";
		
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q1);
			
			stmt.setInt(1, NotifyID);
			stmt.setInt(2, ProductID);
			stmt.setDate(3, NotifyDate);
			
			stmt.execute();
			
			System.out.println("Successful Values Insertion!!");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
    public void deleteStocknotify() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter notifyID to delete: ");
		int temp = sc.nextInt();
		String q = "delete from stocknotify where notifyID = ?";
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q);
			stmt.setInt(1, temp);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	

	public void updateStockNotify() {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating Stocknotify values in table =======");
		
		System.out.print("NotifyID - ");
		int NotifyID = sc.nextInt();
		
		System.out.println("What to update: \n1.ProductID \n2.NotifyDate ");
		int choice = sc.nextInt();
	
		   
		
		     if(choice == 1 ) {
		    	 System.out.println("enter new ProductID:");
		    	 int temp = sc.nextInt();
		    	 String q = "update stocknotify set ProductID = ? where NotifyID = ? ";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setInt(1,temp);
		    		 stmt.setInt(2, NotifyID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 2){
		    	 System.out.println("enter New NotifyDate : (yyyy-mm-dd)");
		    	 String temp = sc.next();
		    	 Date dt =  Date.valueOf(temp);
		    	 
		    	 String q = "update stocknotify set Notifydate = ? where NotifyID = ?";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setDate(1, dt);
		    		 stmt.setInt(2, NotifyID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }
		     else {
		    	 System.out.println("Invalid!!");
		    	 
		     }
		     
		 
	}
	
	
	public void readStockNotify() {
		System.out.println("Showing StockNotify Table ");
		
		String q = "select * from StockNotify";
		
		 //Connection conn = IMS.con;
	    	
		 try {
			 
			 Statement stmt = con.createStatement();
			 ResultSet resultSet = stmt.executeQuery(q);
			 
			  
			 while (resultSet.next()) {
				 int column1Value = resultSet.getInt("Notifyid");
				 int column2Value = resultSet.getInt("ProductID"); 
				
		         Date column3Value = resultSet.getDate("NotifyDate");
		        
		         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 String formattedDate = dateFormat.format(column3Value);


		         System.out.println("Notifyid: " + column1Value + ", ProductID: " + column2Value +", NotifyDate: " + formattedDate);
		  
				 }
			 
			 
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		
		
	}
	
	
}

