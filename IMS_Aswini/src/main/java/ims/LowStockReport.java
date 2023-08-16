package ims;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class LowStockReport {
	private Connection con;
	public LowStockReport(Connection con) {
		this.con = con;
	}
	public void valuesInsertStockReport()
	{
        Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Inserting stock report in table =======");
		System.out.println("Enter stock report values:");
		
		System.out.print("ReportID - ");
		int ReportID = sc.nextInt();
		
		System.out.println("ProductID - ");
		int ProductID = sc.nextInt();
		
		System.out.println("Order Date - (yyyy-MM-dd)");
		String ReportDt = sc.next();
		Date ReportDate = Date.valueOf(ReportDt);	
		
		System.out.println("Qty - ");
		int Qty = sc.nextInt();
		
		String q1 = "insert into StockReport(ReportID,ReportDate,ProductID,Qty) values(?,?,?,?)";
		
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q1);
			
			stmt.setInt(1, ReportID);
			stmt.setDate(2, ReportDate);
			stmt.setInt(3, ProductID);
			stmt.setInt(4, Qty);
			
			stmt.execute();
			
			System.out.println("Successful Values Insertion!!");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	

	public void valuesInsertSalesReport()
	{
        Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Inserting sales report in table =======");
		System.out.println("Enter sales report values:");
		
		System.out.print("ReportID - ");
		int ReportID = sc.nextInt();
		
		System.out.println("ProductID - ");
		int ProductID = sc.nextInt();
		
		System.out.println("Order Date - (yyyy-MM-dd)");
		String ReportDt = sc.next();
		Date ReportDate = Date.valueOf(ReportDt);	
		
		System.out.println("TotalSales - ");
		int TotalSales = sc.nextInt();
		
		System.out.println("OrderID - ");
		int OrderID = sc.nextInt();
		
		System.out.println("OrderItemID - ");
		int OrderItemID = sc.nextInt();


		String q1 = "insert into SalesReport(ReportID,ProductID,ReportDate,TotalSales,OrderID,OrderItemID) values(?,?,?,?,?,?)";
		
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q1);
			
			stmt.setInt(1, ReportID);
			stmt.setInt(2, ProductID);
			stmt.setDate(3, ReportDate);
			stmt.setInt(4, TotalSales);
			stmt.setInt(5, OrderID);
			stmt.setInt(6, OrderItemID);
			
			
			stmt.execute();
			
			System.out.println("Successful Values Insertion!!");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	////////////////////////
	
	
	public void valuesUpdateStockReport() {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating Stock Report values in table =======");
		
		System.out.print("ReportID - ");
		int ReportID = sc.nextInt();
		
		System.out.println("What to update: \n1.ProductID \n2.ReportDate \n3.Qty ");
		int choice = sc.nextInt();
	
		   
		
		     if(choice == 1 ) {
		    	 System.out.println("enter new ProductID:");
		    	 int temp = sc.nextInt();
		    	 String q = "update stockreport set ProductID = ? where ReportID = ? ";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setInt(1,temp);
		    		 stmt.setInt(2, ReportID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 2){
		    	 System.out.println("enter New ReportDate : (yyyy-mm-dd)");
		    	 String temp = sc.next();
		    	 Date dt =  Date.valueOf(temp);
		    	 
		    	 String q = "update stockreport set Reportdate = ? where ReportID = ?";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setDate(1, dt);
		    		 stmt.setInt(2, ReportID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 3 ) {
			     int  temp;
			     
		    	 System.out.println("enter new Quantity:");
		    	 temp = sc.nextInt();
		    	 
		    	 String q = "update Stockreport set qty=? where ReportID = ?";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setInt(1, temp);
		    		 stmt.setInt(2, ReportID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     
		    	
		     }else {
		    	 System.out.println("Invalid!!");
		    	 
		     }
		     
		 
		
	}
	
    public void valuesUpdateSales() {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating Sales Report values in table =======");
		
		System.out.print("ReportID - ");
		int ReportID = sc.nextInt();
		
		System.out.println("What to update: \n1.ProductID \n2.ReportDate \n3.Totalsales \n4.OrderID \n5.OrderItemID ");
		int choice = sc.nextInt();
	
		   
		
		     if(choice == 1 ) {
		    	 System.out.println("enter new ProductID:");
		    	 int temp = sc.nextInt();
		    	 String q = "update salesreport set ProductID = ? where ReportID = ? ";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setInt(1,temp);
		    		 stmt.setInt(2, ReportID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 2){
		    	 System.out.println("enter New ReportDate : (yyyy-mm-dd)");
		    	 String temp = sc.next();
		    	 Date dt =  Date.valueOf(temp);
		    	 
		    	 String q = "update salesreport set Reportdate = ? where ReportID = ?";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setDate(1, dt);
		    		 stmt.setInt(2, ReportID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 3 ) {
			     int  temp;
			     
		    	 System.out.println("enter new totalsales:");
		    	 temp = sc.nextInt();
		    	 
		    	 String q = "update Salesreport set totalsales=? where ReportID = ?";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setInt(1, temp);
		    		 stmt.setInt(2, ReportID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }}

		     if(choice == 4 ) {
		    	 System.out.println("enter new OrderID:");
		    	 int temp = sc.nextInt();
		    	 String q = "update salesreport set ORDERID = ? where ReportID = ? ";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
		    		 stmt.setInt(1,temp);
		    		 stmt.setInt(2, ReportID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		    	
		     } else if(choice == 5 ) {
			    	 System.out.println("enter new OrderItemID:");
			    	 int temp = sc.nextInt();
			    	 String q = "update salesreport set ORDERitemID = ? where ReportID = ? ";
			    	 //Connection conn = IMS.con;
			    	 try {
			    		 PreparedStatement stmt  = con.prepareStatement(q);
			    		 stmt.setInt(1,temp);
			    		 stmt.setInt(2, ReportID);
				    	 stmt.execute(); 
			    	 }
			    	 catch(SQLException e){
			    		 e.printStackTrace();
			    	 }
			    	 
		     }else {
		    	 System.out.println("Invalid!!");
		    	 
		     }
		     
		 
	}
    
    
    
    public void deleteSalesreport() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ReportID to delete: ");
		int temp = sc.nextInt();
		String q = "delete from salesreport where ReportID = ?";
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q);
			stmt.setInt(1, temp);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	

    public void deletestockreport() {
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter reportID to delete: ");
	int temp = sc.nextInt();
	String q = "delete from stockreport where reportID = ?";
	//Connection con1 = IMS.con;
	
	try {
		PreparedStatement stmt = con.prepareStatement(q);
		stmt.setInt(1, temp);
		stmt.execute();
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	
}

	
    
    
    public void readStockReport()
    {
	System.out.println("Showing Stock Report Table ");
		
		String q = "select * from stockreport";
		
		 //Connection conn = IMS.con;
	    	
		 try {
			 
			 Statement stmt = con.createStatement();
			 ResultSet resultSet = stmt.executeQuery(q);
			 
			 
			 while (resultSet.next()) {
				 int column1Value = resultSet.getInt("ReportID");
				 int column2Value = resultSet.getInt("ProductID");
				 
				 Date column3Value = resultSet.getDate("ReportDate");
			        
		         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 String formattedDate = dateFormat.format(column3Value);

				 
				 
				 
		         int column4Value = resultSet.getInt("Qty");

		         System.out.println("ReportID: " + column1Value + ", ProductID: " + column2Value +", ReportDate: " + formattedDate +", Qty: " + column4Value);
		  
				 }
			 
			 
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		
	
    }
	
    public void readSalesReport()
    {
	System.out.println("Showing salesreport Table ");
		
		String q = "select * from salesreport";
		
		 //Connection conn = IMS.con;
	    	
		 try {
			 
			 Statement stmt = con.createStatement(); 
			 ResultSet resultSet = stmt.executeQuery(q);
			 
			 
			 while (resultSet.next()) {
				 int column1Value = resultSet.getInt("ReportID");
		         int column2Value = resultSet.getInt("ProductID");
		         
		         Date column3Value = resultSet.getDate("ReportDate");
			        
		         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 String formattedDate = dateFormat.format(column3Value);

		         
		         int column4Value = resultSet.getInt("TotalSales");
		         int column5Value = resultSet.getInt("OrderID");
		         int column6Value = resultSet.getInt("OrderItemID");

		         System.out.println("ReportID: " + column1Value + ", ProductID: " + column2Value +", ReportDate: " + formattedDate +", TotalSales: " + column4Value+", OrderID: " + column5Value+", OrderItemID: " + column6Value);
		  
				 }
			 
			 
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		
	
    }
}

