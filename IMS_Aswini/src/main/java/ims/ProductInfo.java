package ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProductInfo {
	private Connection con;
	public ProductInfo(Connection con) {
		this.con = con;
	}
	public void updateProduct()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating Product values in table =======");
		
		System.out.print("ProductID - ");
		int productID = sc.nextInt();
		
		System.out.println("What to update: \n1.ProductName \n2.Desc \n3.Price \n4.Qty");
		int choice = sc.nextInt();
	
		
		if(choice == 1 || choice == 2 ) {
		     String temp="";
		     if(choice == 1 ) {
		    	 System.out.println("enter new product name:");
		    	 temp = sc.next();
		    	 String q = "update product set productname = ? where productID = ? ";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setString(1,temp);
		    		 stmt.setInt(2, productID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else {
		    	 System.out.println("enter desc :");
		    	 temp = sc.next();
		    	 String q = "update product set description = ? where productID = ?";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setString(1, temp);
		    		 stmt.setInt(2, productID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }}else if(choice == 3 ) {
			     Float temp;
			     
		    	 System.out.println("enter new price:");
		    	 temp = sc.nextFloat();
		    	 
		    	 String q = "update product set price=? where productID = ?";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setFloat(1, temp);
		    		 stmt.setInt(2, productID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     
		    	
		     }else {
		    	 int temp;
		    	 System.out.println("enter new Qty:");
		    	 temp = sc.nextInt();
		    	 String q = "update product set Qty=? where productID = ?";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setInt(1, temp);
		    		 stmt.setInt(2, productID);
		    		 stmt.execute(); 
			    	 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		    	 
		    	 
		     }
		     
		
		
		
	}
	
	
	public void valuesInsertProduct()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Inserting Product values in table =======");
		System.out.println("Enter product values:");
		
		System.out.print("ProductID - ");
		int productID = sc.nextInt();
		
		System.out.println("ProductName - ");
		String prodName = sc.next();
		
		
		System.out.println("Desc - ");
		String desc = sc.next();
		sc.nextLine();
		
		System.out.print("Price - ");
		float price = sc.nextFloat();
		
		System.out.print("Quantity - ");
		int Qty = sc.nextInt();
		
		
		String q1 = "insert into PRODUCT(ProductID,ProductName,Description,Price,Qty) values(?,?,?,?,?)";
		
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q1);
			
			stmt.setInt(1, productID);
			stmt.setString(2, prodName);
			stmt.setString(3, desc);
			stmt.setFloat(4, price);
			stmt.setInt(5, Qty);
			
			stmt.execute();
			
			System.out.println("Successful Values Insertion!!");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void deleteProduct() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter productID to delete: ");
		int temp = sc.nextInt();
		String q = "delete from product where productID = ?";
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q);
			stmt.setInt(1, temp);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	      
	}
	
	
	public void readProduct() {
		
		System.out.println("Showing Product Table ");
		
		String q = "select * from product";
		
		 //Connection conn = IMS.con;
	    	
		 try {
			 
			 Statement stmt = con.createStatement();
			 ResultSet resultSet = stmt.executeQuery(q); 
			 
			 while (resultSet.next()) {
				 int column1Value = resultSet.getInt("Productid");
				 String column2Value = resultSet.getString("ProductName"); 
				 String column3Value = resultSet.getString("Description");
				 Float column4Value = resultSet.getFloat("Price");
				 int column5Value = resultSet.getInt("Qty");
		         System.out.println("Productid: " + column1Value + ", ProductName: " + column2Value +", Description: " + column3Value+", Price: " + column4Value+", Qty: " + column5Value);
		  
				 }
			 
			 
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		
		
	}
	
	
	

}
