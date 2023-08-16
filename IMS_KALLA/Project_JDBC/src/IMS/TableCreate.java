package IMS;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class TableCreate {
	private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";
    private static String connstring = "jdbc:oracle:thin:@localhost:1521:XE";
    private static String username = "amdocsuser1";
    private static String password = "naru";
    static Statement stmt;
	static public void TableCreation()
	{
		try {
			 Class.forName(jdbcurl);
			 Connection con = DriverManager.getConnection(connstring, username, password);
		    //	String q1 = "create table UserData(UserID int not null primary key,Username varchar(30) not null, password varchar(50) not null, email varchar2(50),roleId int)";
		    //String q2 = "create table Product(ProdID int not null primary key,ProdName varchar(50) not null, prodDesc varchar(50), prodPrice int, prodQuant int)";			
			//String q3 = "create table ordertable(OrderID int not null primary key,opropID int not null, OrderDate Varchar2(50), orderquant int,orderprice int,customername varchar2(55)";
			//String q4 = "create table OrderItem(OrderItemID int not null primary key,ProductID int not null, OrderID int not null, Subtotal float, Qty int, foreign key (ProductID) references Product(ProductID), foreign key (OrderID) references Orders(OrderID))";
			//String q5 = "create table Order_History(HistoryID int not null primary key, OrderID int not null, OrderDate date,foreign key(OrderID) references orders(OrderID))";
            // String q6 = "create table StockNotify(NotifyID int not null primary key,ProductID int not null, NotifyDate date,foreign key(productID) references product(productID))";
			//String q7 = "create table StockReport(ReportID int not null primary key,ProductID int not null,ReportDate date, Qty int,foreign key(productID) references product(productID))";
      		//String q8 = "create table SalesReport(ReportID int not null primary key,ProductID int not null,ReportDate date,TotalSales int,orderid int not null,orderitemid int not null,foreign key(ProductID) references product(productid),foreign key(orderid) references orders(orderid),foreign key(orderitemid) references orderitem(orderitemid))";
					
		    //Statement stmt = con.createStatement();		    
		    //stmt.executeQuery(q8);		    
		}
    	catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
