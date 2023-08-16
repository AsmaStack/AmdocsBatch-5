package ims;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertingValues {
    private Connection con;
	
	public InsertingValues(Connection con) {
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
	
	
	public void valuesInsertOrders()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Inserting Order values in table =======");
		System.out.println("Enter Order values:");
		
		System.out.print("OrderID - ");
		int orderID = sc.nextInt();
		
		System.out.println("UserID - ");
		int userID = sc.nextInt();
		
		
		System.out.println("Order Date - (yyyy-MM-dd)");
		String OrderDt = sc.next();
		Date OrderDate = Date.valueOf(OrderDt);
		
		System.out.println("Total Amount - ");
		Float TotalAmount = sc.nextFloat();
		
		
		String q1 = "insert into Orders(OrderID,UserID,OrderDate,TotalAmount) values(?,?,?,?)";
		
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q1);
			
			stmt.setInt(1, orderID);
			stmt.setInt(2, userID);
			stmt.setDate(3, OrderDate);
			stmt.setFloat(4, TotalAmount);
			
			
			stmt.execute();
			
			System.out.println("Successful Values Insertion!!");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	public void valuesInsertOrderItem()
    {
        Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Inserting Order Items values in table =======");
		System.out.println("Enter Order Item values:");
		
		System.out.print("OrderItemID - ");
		int orderItemID = sc.nextInt();
		
		System.out.println("ProductID - ");
		int productID = sc.nextInt();
		
		System.out.println("OrderID - ");
		int OrderID = sc.nextInt();
		
		System.out.println("Qty - ");
		int Qty = sc.nextInt();


		
		System.out.println("Sub Total Amount - ");
		Float SubTotalAmount = sc.nextFloat();
		
		
		String q1 = "insert into OrderItem(OrderItemID,ProductID,OrderID,Qty,SubTotalAmount) values(?,?,?,?,?)";
		
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q1);
			
			stmt.setInt(1, orderItemID);
			stmt.setInt(2, productID);
			stmt.setInt(3, OrderID);
			stmt.setInt(4, Qty);
			stmt.setFloat(4, SubTotalAmount);
			
			
			stmt.execute();
			
			System.out.println("Successful Values Insertion!!");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void valuesInsertOrderHistory()
	{
        Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Inserting order history in table =======");
		System.out.println("Enter Order history values:");
		
		System.out.print("HistoryID - ");
		int HistoryID = sc.nextInt();
		
		System.out.println("OrderID - ");
		int OrderID = sc.nextInt();
		
		System.out.println("Order Date - (yyyy-MM-dd)");
		String OrderDt = sc.next();
		Date OrderDate = Date.valueOf(OrderDt);		
		
		String q1 = "insert into Order_History(OrderItemID,ProductID,OrderID,Qty,SubTotalAmount) values(?,?,?,?,?)";
		
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q1);
			
			stmt.setInt(1, HistoryID);
			stmt.setInt(2, OrderID);
			stmt.setDate(3, OrderDate);
	
			
			stmt.execute();
			
			System.out.println("Successful Values Insertion!!");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
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
	
	
	
	
	
	
	
}

