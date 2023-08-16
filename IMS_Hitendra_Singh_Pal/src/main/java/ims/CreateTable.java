package ims;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	static public void TableCreation()
	{
		System.out.println("=======Table Creation======");
		
		Connection con2 = InventoryManagementSystemprimary.conn;
		
		try {
		//	String q1 = "create table User_accounts(UserID int not null primary key,Username varchar(30) not null, password varchar(20) not null, role varchar(20))";
		//String q2 = "create table Product(ProductID int not null primary key,ProductName varchar(30) not null, Description varchar(20), Price float, Qty int)";

			
			//String q3 = "create table Orders(OrderID int not null primary key,UserID int not null, OrderDate date, TotalAmount float)";
			//String q4 = "create table OrderItem(OrderItemID int not null primary key,ProductID int not null, OrderID int not null, Subtotal float, Qty int, foreign key (ProductID) references Product(ProductID), foreign key (OrderID) references Orders(OrderID))";
			//String q5 = "create table Order_History(HistoryID int not null primary key, OrderID int not null, OrderDate date,foreign key(OrderID) references orders(OrderID))";
             // String q6 = "create table StockNotify(NotifyID int not null primary key,ProductID int not null, NotifyDate date,foreign key(productID) references product(productID))";
			//String q7 = "create table StockReport(ReportID int not null primary key,ProductID int not null,ReportDate date, Qty int,foreign key(productID) references product(productID))";
      		String q8 = "create table SalesReport(ReportID int not null primary key,ProductID int not null,ReportDate date,TotalSales int,orderid int not null,orderitemid int not null,foreign key(ProductID) references product(productid),foreign key(orderid) references orders(orderid),foreign key(orderitemid) references orderitem(orderitemid))";
//			
//			
			
			


			
			
		    Statement stmt = con2.createStatement();
		    
		    stmt.executeQuery(q8);
		    
		    System.out.println("Function finished");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
}
