package ims;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Orders_Module {

	public static void valuesInsertOrders()
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
		
		
		String q1 = "insert into IMS_Orders(OrderID,UserID,OrderDate,TotalAmount) values(?,?,?,?)";
		
		Connection con1 = InventoryManagementSystemprimary.conn;
		
		try {
			PreparedStatement stmt = con1.prepareStatement(q1);
			
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
	

	public static void valuesInsertOrderItem()
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
		
		
		String q1 = "insert into IMS_OrderItem(OrderItemID,ProductID,OrderID,Qty,SubTotalAmount) values(?,?,?,?,?)";
		
		Connection con1 = InventoryManagementSystemprimary.conn;
		
		try {
			PreparedStatement stmt = con1.prepareStatement(q1);
			
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
	
	
	public static void valuesInsertOrderHistory()
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
		
		String q1 = "insert into IMS_Order_History(OrderItemID,ProductID,OrderID,Qty,SubTotalAmount) values(?,?,?,?,?)";
		
		Connection con1 = InventoryManagementSystemprimary.conn;
		
		try {
			PreparedStatement stmt = con1.prepareStatement(q1);
			
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
	
//////////////////////
	public static void readOrders() {
		
		System.out.println("Showing IMS_Orders Table ");
		
		String q = "select * from IMS_Orders";
		
		 Connection conn = InventoryManagementSystemprimary.conn;
	    	
		 try {
			 
			 Statement stmt = conn.createStatement();
			 ResultSet resultSet  = stmt.executeQuery(q);
			 
			 while (resultSet.next()) {
				 int column1Value = resultSet.getInt("OrderID");
		         int column2Value = resultSet.getInt("UserID");
		         
		         Date column3Value = resultSet.getDate("OrderDate");
			        
		         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 String formattedDate = dateFormat.format(column3Value);

		         
		        
		         Float column4Value = resultSet.getFloat("TotalAmount");
				 

		         System.out.println("Orderid: " + column1Value + ", UserID: " + column2Value +", OrderDate: " + formattedDate +", TotalAmount: " + column4Value);
		  
				 }
			 
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		
		
	}


	public static void readOrderItem() {
		
		System.out.println("Showing IMS_OrderItem Table ");
		
		String q = "select * from IMS_OrderItem";
		
		 Connection conn = InventoryManagementSystemprimary.conn;
	    	
		 try {
			 
			 Statement stmt = conn.createStatement();
			 ResultSet resultSet = stmt.executeQuery(q);
			 
			 while (resultSet.next()) {
				 int column1Value = resultSet.getInt("OrderItemid");
		         int column2Value = resultSet.getInt("ProductID");
		         int column3Value = resultSet.getInt("OrderID");
		         Float column4Value = resultSet.getFloat("SubTotal");
		         int column5Value = resultSet.getInt("Qty");
				 

		         System.out.println("OrderItemid: " + column1Value + ", ProductID: " + column2Value +", OrderID: " + column3Value +", SubTotal: " + column4Value+", Qty: " + column5Value);
		  
				 }
			 
			 
			 
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		
		
	}

	public static void readOrderHistory() {
		
		System.out.println("Showing OrderHistory Table ");
		
		String q = "select * from IMS_Order_History";
		
		 Connection conn = InventoryManagementSystemprimary.conn;
	    	
		 try {
			 
			 Statement stmt = conn.createStatement();
			 ResultSet resultSet = stmt.executeQuery(q);
			 
			 
			 while (resultSet.next()) {
				 int column1Value = resultSet.getInt("Historyid");
		         int column2Value = resultSet.getInt("OrderID");
		        
		         Date column3Value = resultSet.getDate("OrderDate");
			        
		         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 String formattedDate = dateFormat.format(column3Value);
        
		         System.out.println("Historyid: " + column1Value + ", OrderID: " + column2Value +", OrderDate: " + formattedDate);
		  
				 }
			 
			 
			 
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		
		
	}

	////////////////////
	public static void updateOrders()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating IMS_Orders values in table =======");
		
		System.out.print("OrderID - ");
		int orderID = sc.nextInt();
		
		System.out.println("What to update: \n1.UserID \n2.OrderDate \n3.Total_Amount ");
		int choice = sc.nextInt();
	
		   
		
		     if(choice == 1 ) {
		    	 System.out.println("enter new UserID:");
		    	 int temp = sc.nextInt();
		    	 String q = "update orders set userID = ? where OrderID = ? ";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setInt(1,temp);
		    		 stmt.setInt(2, orderID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 2){
		    	 System.out.println("enter New Date : (yyyy-mm-dd)");
		    	 String temp = sc.next();
		    	 Date dt =  Date.valueOf(temp);
		    	 
		    	 String q = "update IMS_Orders set orderdate = ? where orderID = ?";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setDate(1, dt);
		    		 stmt.setInt(2, orderID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 3 ) {
			     Float temp;
			     
		    	 System.out.println("enter new TotalAmount:");
		    	 temp = sc.nextFloat();
		    	 
		    	 String q = "update orders set totalamount=? where orderID = ?";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setFloat(1, temp);
		    		 stmt.setInt(2, orderID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     
		    	
		     }else {
		    	 System.out.println("Invalid!!");
		    	 
		     }
		     
		
		
		
	}

	public static void updateOrderItem() {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating IMS_OrderItem values in table =======");
		
		System.out.print("OrderItemID - ");
		int orderItemID = sc.nextInt();
		
		System.out.println("What to update: \n1.ProductID \n2.OrderID \n3.Subtotal\n4.Qty ");
		int choice = sc.nextInt();
	
		   
		
		     if(choice == 1 ) {
		    	 System.out.println("enter new ProductID:");
		    	 int temp = sc.nextInt();
		    	 String q = "update orderItem set ProductID = ? where OrderItemID = ? ";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setInt(1,temp);
		    		 stmt.setInt(2, orderItemID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 2){
		    	 System.out.println("enter new OrderID:");
		    	 int temp = sc.nextInt();
		   
		    	 
		    	 String q = "update IMS_OrderItem set orderID = ? where orderItemID = ?";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setInt(1, temp);
		    		 stmt.setInt(2, orderItemID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 3 ) {
			     Float temp;
			     
		    	 System.out.println("enter new SubTotal:");
		    	 temp = sc.nextFloat();
		    	 
		    	 String q = "update orderItem set subtotal=? where orderItemID = ?";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setFloat(1, temp);
		    		 stmt.setInt(2, orderItemID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }}
		    	 else if(choice == 4) {
		    		 System.out.println("enter new Qty:");
			    	 int temp = sc.nextInt();
			   
			    	 
			    	 String q = "update IMS_OrderItem set Qty = ? where orderItemID = ?";
			    	 Connection conn = InventoryManagementSystemprimary.conn;
			    	 try {
			    		 PreparedStatement stmt  = conn.prepareStatement(q);
			    		 stmt.setInt(1, temp);
			    		 stmt.setInt(2, orderItemID);
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
	
	public static void updateOrderHistory() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating OrderHistory values in table =======");
		
		System.out.print("HistoryID - ");
		int HistoryID = sc.nextInt();
		
		System.out.println("What to update: 1.OrderID \n2.Orderdate");
		int choice = sc.nextInt();
	
		   
		
		     if(choice == 1 ) {
		    	 System.out.println("enter new OrderID:");
		    	 int temp = sc.nextInt();
		    	 String q = "update order_history set OrderID = ? where HistoryID = ? ";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setInt(1,temp);
		    		 stmt.setInt(2, HistoryID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }else if(choice == 2){
		    	 System.out.println("enter new OrderDate:");
		    	 String temp = sc.next();
		    	 Date dt = Date.valueOf(temp);
		    	 
		    	 
		    	 String q = "update IMS_Order_History set orderdate = ? where HistoryID = ?";
		    	 Connection conn = InventoryManagementSystemprimary.conn;
		    	 try {
		    		 PreparedStatement stmt  = conn.prepareStatement(q);
		    		 stmt.setDate(1, dt);
		    		 stmt.setInt(2, HistoryID);
			    	 stmt.execute(); 
		    	 }
		    	 catch(SQLException e){
		    		 e.printStackTrace();
		    	 }
		     }
		    	 else if(choice == 4) {
		    		 System.out.println("enter new Qty:");
			    	 int temp = sc.nextInt();
			   
			    	 
			    	 String q = "update IMS_OrderItem set Qty = ? where orderItemID = ?";
			    	 Connection conn = InventoryManagementSystemprimary.conn;
			    	 try {
			    		 PreparedStatement stmt  = conn.prepareStatement(q);
			    		 stmt.setInt(1, temp);
			    		 stmt.setInt(2, HistoryID);
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
	
	
	/////////////////////
	
	public static void deleteOrders() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter OrderID to delete: ");
		int temp = sc.nextInt();
		String q = "delete from IMS_Orders where orderID = ?";
		Connection con1 = InventoryManagementSystemprimary.conn;
		
		try {
			PreparedStatement stmt = con1.prepareStatement(q);
			stmt.setInt(1, temp);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	
    public static void deleteOrderItem() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter OrderItemID to delete: ");
		int temp = sc.nextInt();
		String q = "delete from IMS_OrderItem where orderID = ?";
		Connection con1 = InventoryManagementSystemprimary.conn;
		
		try {
			PreparedStatement stmt = con1.prepareStatement(q);
			stmt.setInt(1, temp);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}


    public static void deleteOrderHistory() {
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter HistoryID to delete: ");
	int temp = sc.nextInt();
	String q = "delete from IMS_Order_History where historyID = ?";
	Connection con1 = InventoryManagementSystemprimary.conn;
	
	try {
		PreparedStatement stmt = con1.prepareStatement(q);
		stmt.setInt(1, temp);
		stmt.execute();
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	
}
	
	
	
}
