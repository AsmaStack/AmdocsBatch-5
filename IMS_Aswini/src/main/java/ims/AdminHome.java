package ims;

import java.util.Scanner;
import java.sql.Connection;
public class AdminHome {
	static Scanner sc = new Scanner(System.in);
 	static int choice;
    private Connection con;
    public AdminHome(Connection con) {
    	this.con = con;
    }
	public void menu4admin()
	{
			System.out.println("====== Home page(Admin) =======");
		System.out.println("Select from choices");
		
		
		System.out.println("1. User Information"); 
		System.out.println("2. Product Information");
		System.out.println("3. Order Information");
		System.out.println("4. Stock Tracking");
		System.out.println("5. Low Stock Report");
		
		choice = sc.nextInt();
		int ch;
		switch(choice)
		{
		case 1: 
			System.out.println("*** Welcome to User Module ***");
			System.out.println("1. Update values of User_accounts Table");
			System.out.println("2. Delete values of User_accounts Table");
			System.out.println("3. Read values of User_accounts Table");
			
			 ch = sc.nextInt();
			
			if(ch==1)
			{
				new UserInfo(con).updateUserTable();
				// function to update 
			}else if(ch==2){
				
				new UserInfo(con).deleteUserTable();
				// delete user function
			}else if(ch==3){
				
				new UserInfo(con).readUserTable();
				// delete user function
			}else {
				
				System.out.println("Invalid!!");
				
			}
			
			break;
			
		case 2: 
			
			System.out.println("*** Welcome to Product Module ***");
			System.out.println("1. Insert values in Product Table");
			System.out.println("2. Update values of Product Table");
			System.out.println("3. Delete values of Product Table");
			System.out.println("4. Read values of Product Table");
			
			 ch = sc.nextInt();
			
			if(ch==1)
			{
				// function to update 
				new ProductInfo(con).valuesInsertProduct();
				
			}else if(ch==2){
				
				new ProductInfo(con).updateProduct();
				
				// delete user function
			}else if(ch==3){
				
				new ProductInfo(con).deleteProduct();
				
				// delete user function
			}else if(ch==4){
				
				new ProductInfo(con).readProduct();
				
				// delete user function
			}else {
				System.out.println("Invalid!!");
				
			}
			
			break;
			
			
		case 3: 
			
			System.out.println("** Welcome to Order Module ***");
			System.out.println("1. Insert values in Orders Table");
			System.out.println("2. Update values of Orders Table");
			System.out.println("3. Delete values of Orders Table");
			System.out.println("4. Read values of Orders Table");
			System.out.println("    ****   ");
			System.out.println("5. Insert values in OrderItem Table");
			System.out.println("6. Update values of OrderItem Table");
			System.out.println("7. Delete values of OrderItem Table");
			System.out.println("8. Read values of OrderItem Table");
			System.out.println("    ****   ");
			System.out.println("9. Insert values in OrderHistory Table");
			System.out.println("10. Update values of OrderHistory Table");
			System.out.println("11. Delete values of OrderHistory Table");
			System.out.println("12. Read values of OrderItem Table");
			ch = sc.nextInt();
			
			switch(ch)
			{
			case 1: 
				new OrderDetails(con).valuesInsertOrders();
				break;
			case 2: 
				new OrderDetails(con).updateOrders();
				break;
			case 3: 
				new OrderDetails(con).deleteOrders();
				break;
			case 4: 
				new OrderDetails(con).readOrders();
				break;
			case 5: 
				new OrderDetails(con).valuesInsertOrderItem();
				break;
			case 6: 
				new OrderDetails(con).updateOrderItem();
				break;
			case 7: 
				new OrderDetails(con).deleteOrderItem();
				break;
			case 8: 
				new OrderDetails(con).readOrderItem();
				break;
			case 9: 
				new OrderDetails(con).valuesInsertOrderHistory();
				break;
			case 10:
				new OrderDetails(con).updateOrderHistory();
				break;
			case 11: 
				new OrderDetails(con).deleteOrderHistory();
				break;
			case 12:
				new OrderDetails(con).readOrderHistory();
				break;
			default:
				System.out.println("Invalid!!");
			
			}
							
			break;
			
			
		case 4: 
			System.out.println("*** Welcome to Stock Tracking Module ***");
			System.out.println("1. Insert values in Stock Tracking Table");
			System.out.println("2. Update values of Stock Tracking Table");
			System.out.println("3. Delete values of Stock Tracking Table");
			System.out.println("4. Read values of Stock Tracking Table");
			
			 ch = sc.nextInt();
			
			if(ch==1)
			{
				// function to update 
				new StockTracking(con).valuesInsertStockNotify();
				
			}else if(ch==2){
				
				new StockTracking(con).updateStockNotify();
				
				// delete user function
			}else if(ch==3){
				
				new StockTracking(con).deleteStocknotify();
				
				// delete user function
			}else if(ch==4){
				
				new StockTracking(con).readStockNotify();
				
				// delete user function
			}else {
				System.out.println("Invalid!!");
				
			}
			
			break;
		case 5: 
			System.out.println("*** Welcome to Report Tracking Module ***");
			System.out.println("1. Insert values in Salesreport Table");
			System.out.println("2. Update values of Salesreport Table");
			System.out.println("3. Delete values of Salesreport Table");
			System.out.println("4. Read values of Salesreport Table");
			System.out.println("5. Insert values in Stockreport Table");
			System.out.println("6. Update values of Stockreport Table");
			System.out.println("7. Delete values of Stockreport Table");
			System.out.println("8. Read values of Stockreport Table");
			
			 ch = sc.nextInt();
			
			switch(ch)
			{
			case 1: 
				new LowStockReport(con).valuesInsertSalesReport();
				break;
			case 2: 
				new LowStockReport(con).valuesUpdateSales();
				break;
			case 3: 
				new LowStockReport(con).deleteSalesreport();
				break;
			case 4: 
				new LowStockReport(con).readSalesReport();
				break;
			case 5: 
				new LowStockReport(con).valuesInsertStockReport();
				break;
			case 6: 
				new LowStockReport(con).valuesUpdateStockReport();
				break;
			case 7: 
				new LowStockReport(con).deletestockreport();
				break;
			case 8: 
				new LowStockReport(con).readStockReport();
				break;
			default: 
				System.out.println("Invalid!!");	
			}
			break;
		default:
			System.out.println("Invalid!!");
		}
		
		
	}
	
	public void menu4Supplier()
	{
		
		System.out.println("*** Welcome to Product Module ***");
		System.out.println("1. Insert values in Product Table");
		System.out.println("2. Update values of Product Table");
		System.out.println("3. Delete values of Product Table");
		System.out.println("4. Read values of Product Table");
		
		 int ch = sc.nextInt();
		
		if(ch==1)
		{
			// function to update 
			new ProductInfo(con).valuesInsertProduct();
			
		}else if(ch==2){
			
			new ProductInfo(con).updateProduct();
			
			// delete user function
		}else if(ch==3){
			
			new ProductInfo(con).deleteProduct();
			
			// delete user function
		}if(ch==4){
			
			new ProductInfo(con).readProduct();
			
			// delete user function
		}else {
			System.out.println("Invalid!!");
			
		}
		
		
		
		
	}
	
	public void menu4User()
	{
		System.out.println("====== Menu (user) =======");
		
		//System.out.println("** Welcome to Orders Module ***");
		System.out.println("1. List values of Product Table");
		System.out.println("2. List values of Order Table");
		System.out.println("3. List values of OrderItem Table");
		System.out.println("4. List values of OrderHistory Table");
		
		int ch;
		ch = sc.nextInt();
		
		
		switch(ch)
		{
		case 1: new ProductInfo(con).readProduct();
			break;
		case 2: new OrderDetails(con).readOrders();
			break;
		case 3: new OrderDetails(con).readOrderItem();
			break;
		case 4: new OrderDetails(con).readOrderHistory();
			break;
		
		default: System.out.println("Invalid!!");		
				
		}
}



}
