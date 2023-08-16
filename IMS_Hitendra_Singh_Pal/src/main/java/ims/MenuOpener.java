package ims;

import java.util.Scanner;

public class MenuOpener {

	 	static Scanner sc = new Scanner(System.in);
	 	static int choice;
	
		public static void menu4admin()
		{
 			System.out.println("====== Menu (Admin) =======");
			System.out.println("Select from choices");
			
			
			System.out.println("1. User Module");
			System.out.println("2. Product Module");
			System.out.println("3. Order Module");
			System.out.println("4. Stock Tracking Module");
			System.out.println("5. Report Module");
			
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
					User_Module.updateUserTable();
					// function to update 
				}else if(ch==2){
					
					User_Module.deleteUserTable();
					// delete user function
				}else if(ch==3){
					
					User_Module.readUserTable();
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
					Product_Module.valuesInsertProduct();
					
				}else if(ch==2){
					
					Product_Module.updateProduct();
					
					// delete user function
				}else if(ch==3){
					
					Product_Module.deleteProduct();
					
					// delete user function
				}else if(ch==4){
					
					Product_Module.readProduct();
					
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
					Orders_Module.valuesInsertOrders();
					break;
				case 2: 
					Orders_Module.updateOrders();
					break;
				case 3: 
					Orders_Module.deleteOrders();
					break;
				case 4: 
					Orders_Module.readOrders();
					break;
				case 5: 
					Orders_Module.valuesInsertOrderItem();
					break;
				case 6: 
					Orders_Module.updateOrderItem();
					break;
				case 7: 
					Orders_Module.deleteOrderItem();
					break;
				case 8: 
					Orders_Module.readOrderItem();
					break;
				case 9: 
					Orders_Module.valuesInsertOrderHistory();
					break;
				case 10:
					Orders_Module.updateOrderHistory();
					break;
				case 11: 
					Orders_Module.deleteOrderHistory();
					break;
				case 12:
					Orders_Module.readOrderHistory();
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
					Stock_Tracking_Module.valuesInsertStockNotify();
					
				}else if(ch==2){
					
					Stock_Tracking_Module.updateStockNotify();
					
					// delete user function
				}else if(ch==3){
					
					Stock_Tracking_Module.deleteStocknotify();
					
					// delete user function
				}else if(ch==4){
					
					Stock_Tracking_Module.readStockNotify();
					
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
					Report_Module.valuesInsertSalesReport();
					break;
				case 2: 
					Report_Module.valuesUpdateSales();
					break;
				case 3: 
					Report_Module.deleteSalesreport();
					break;
				case 4: 
					Report_Module.readSalesReport();
					break;
				case 5: 
					Report_Module.valuesInsertStockReport();
					break;
				case 6: 
					Report_Module.valuesUpdateStockReport();
					break;
				case 7: 
					Report_Module.deletestockreport();
					break;
				case 8: 
					Report_Module.readStockReport();
					break;
				default: 
					System.out.println("Invalid!!");	
				}
				break;
			default:
				System.out.println("Invalid!!");
			}
			
			
		}
		
		public static void menu4Supplier()
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
				Product_Module.valuesInsertProduct();
				
			}else if(ch==2){
				
				Product_Module.updateProduct();
				
				// delete user function
			}else if(ch==3){
				
				Product_Module.deleteProduct();
				
				// delete user function
			}if(ch==4){
				
				Product_Module.readProduct();
				
				// delete user function
			}else {
				System.out.println("Invalid!!");
				
			}
			
			
			
			
		}
		
		public static void menu4User()
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
			case 1: Product_Module.readProduct();
				break;
			case 2: Orders_Module.readOrders();
				break;
			case 3: Orders_Module.readOrderItem();
				break;
			case 4: Orders_Module.readOrderHistory();
				break;
			
			default: System.out.println("Invalid!!");		
					
			}
		}
	
	
}
