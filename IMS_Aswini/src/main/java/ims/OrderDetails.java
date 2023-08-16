package ims;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class OrderDetails {
	private Connection con;
	public OrderDetails(Connection con) {
		this.con = con;
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
	

	public void readOrders() {
		
		System.out.println("Showing Orders Table ");
		
		String q = "select * from Orders";
		
		 //Connection conn = IMS.con;
	    	
		 try {
			 
			 Statement stmt = con.createStatement();
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


	public void readOrderItem() {
		
		System.out.println("Showing OrderItem Table ");
		
		String q = "select * from OrderItem";
		
		 //Connection conn = IMS.con;
	    	
		 try {
			 
			 Statement stmt = con.createStatement();
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

	public void readOrderHistory() {
		
		System.out.println("Showing OrderHistory Table ");
		
		String q = "select * from OrderHistory";
		
		 //Connection conn = IMS.con;
	    	
		 try {
			 
			 Statement stmt = con.createStatement();
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

	
	public void updateOrders()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating Orders values in table =======");
		
		System.out.print("OrderID - ");
		int orderID = sc.nextInt();
		
		System.out.println("What to update: \n1.UserID \n2.OrderDate \n3.Total_Amount ");
		int choice = sc.nextInt();
	
		   
		
		     if(choice == 1 ) {
		    	 System.out.println("enter new UserID:");
		    	 int temp = sc.nextInt();
		    	 String q = "update orders set userID = ? where OrderID = ? ";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
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
		    	 
		    	 String q = "update Orders set orderdate = ? where orderID = ?";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
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
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
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

	public void updateOrderItem() {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("========= Updating OrderItem values in table =======");
		
		System.out.print("OrderItemID - ");
		int orderItemID = sc.nextInt();
		
		System.out.println("What to update: \n1.ProductID \n2.OrderID \n3.Subtotal\n4.Qty ");
		int choice = sc.nextInt();
	
		   
		
		     if(choice == 1 ) {
		    	 System.out.println("enter new ProductID:");
		    	 int temp = sc.nextInt();
		    	 String q = "update orderItem set ProductID = ? where OrderItemID = ? ";
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
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
		   
		    	 
		    	 String q = "update OrderItem set orderID = ? where orderItemID = ?";
		    	 Connection conn = IMS.con;
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
		    	 Connection conn = IMS.con;
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
			   
			    	 
			    	 String q = "update OrderItem set Qty = ? where orderItemID = ?";
			    	 Connection conn = IMS.con;
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
	
	public void updateOrderHistory() {
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
		    	 //Connection conn = IMS.con;
		    	 try {
		    		 PreparedStatement stmt  = con.prepareStatement(q);
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
		    	 
		    	 
		    	 String q = "update Order_History set orderdate = ? where HistoryID = ?";
		    	 Connection conn = IMS.con;
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
			   
			    	 
			    	 String q = "update OrderItem set Qty = ? where orderItemID = ?";
			    	 //Connection conn = IMS.con;
			    	 try {
			    		 PreparedStatement stmt  = con.prepareStatement(q);
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
	
	public void deleteOrders() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter OrderID to delete: ");
		int temp = sc.nextInt();
		String q = "delete from Orders where orderID = ?";
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q);
			stmt.setInt(1, temp);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	
    public void deleteOrderItem() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter OrderItemID to delete: ");
		int temp = sc.nextInt();
		String q = "delete from OrderItem where orderID = ?";
		//Connection con1 = IMS.con;
		
		try {
			PreparedStatement stmt = con.prepareStatement(q);
			stmt.setInt(1, temp);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}


    public void deleteOrderHistory() {
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter HistoryID to delete: ");
	int temp = sc.nextInt();
	String q = "delete from Order_History where historyID = ?";
	//Connection con1 = IMS.con;
	
	try {
		PreparedStatement stmt = con.prepareStatement(q);
		stmt.setInt(1, temp);
		stmt.execute();
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	
}
	
	
	
}