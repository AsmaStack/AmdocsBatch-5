package IMS;
import java.util.Scanner;
import java.sql.Statement;
public class TestConnection {
	
	static Statement stmt;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
        System.out.println("-".repeat(100));
		System.out.println("Welcome to Inventory Management System");
		System.out.println("Please enter '1' for Admin Operations and '2' for Customer Operation and '3' Sales Report Operations");
		System.out.println("-".repeat(100));
 		
		int user_input = scanner.nextInt();	
		if (user_input == 1) {
			System.out.println("-".repeat(100));
			System.out.println("Enter '1' for creating a new user");
			System.out.println("Enter '2' for Login");
			System.out.println("-".repeat(100));
			int registration=scanner.nextInt();
			switch(registration) {
			case 1:
				userCreation newUser= new userCreation();
				newUser.useradd();
				break;
			case 2:
				adminInfo admin_login= new adminInfo();
				admin_login.userLogin();
				if (admin_login.login==1) {
			        System.out.println("-".repeat(100));
					System.out.println("You Have Successfully Logged Ln");
					System.out.println("Enter '1' to Create a new Product");
					System.out.println("Enter '2' to Update a existing product");
					System.out.println("Enter '3' to Delete a existing Product");
					System.out.println("Enter '4' to Create a new Order");
					System.out.println("Enter '5' to Update a existing Order");
					System.out.println("Enter '6'to Delete a existing Order");
					System.out.println("Enter '7' for Stock Details");
					System.out.println("Enter '8'for Order History");
					System.out.println("Enter '9'for Low Stock Notification");
					System.out.println("Enter '0' to LogOut");
			        System.out.println("-".repeat(100));
			        
					int admin_choice = scanner.nextInt();
					switch (admin_choice) {
					case 1:
						stockManagement create_Product=new stockManagement();
						create_Product.createProduct();		
						break;
					case 2:
						stockManagement update_Product=new stockManagement();
						update_Product.updateProduct();	
						break;
					case 3:
						stockManagement delete_product=new stockManagement();
						delete_product.deleteProduct();	
						break;
					case 4:
						lowstockManagement LSN1=new lowstockManagement();
						LSN1.Notify();
						orderTracking create_order = new orderTracking();
						create_order.createOrder();
						break;
					case 5:
						orderTracking update_order = new orderTracking();
						update_order.updateOrder();
						break;
					case 6:
						orderTracking delete_order = new orderTracking();
						delete_order.deleteOrder();
						break;
					case 7:
						stockManagement stock_Details=new stockManagement();
						stock_Details.stockDetails();		
						break;				
					case 8:
						orderTracking order_History = new orderTracking();
						order_History.orderHistory();
						break;
					case 9:
						lowstockManagement LSN=new lowstockManagement();
						LSN.Notify();					
					case 0:
						System.out.println("You Have Succesfully Logged Out");
						break;					
					}
				}
				else  {
					System.out.println("Maximum login attempts reached. Exiting...");
				}
				
				break;
			default :
				System.out.println("Invalid Option!Please select either 1 or 2 only");
				break;
			}
			
		} 
		
		else if (user_input == 2) {
			System.out.println("Enter '1' to Create a new Order");
			System.out.println("Enter '2' for update order Details");
			System.out.println("Enter '3' to delete a order");
			int customer_choice = scanner.nextInt();
			
			switch(customer_choice) {
			case 1:
				orderTracking create_order2 = new orderTracking();
				create_order2.createOrder();
				break;
			case 2:
				orderTracking update_order2 = new orderTracking();
				update_order2.updateOrder();
				break;
			case 3:
				orderTracking delete_order2 = new orderTracking();
				delete_order2.deleteOrder();
				break;
			}									
		}
		else if(user_input ==3) {
			adminInfo admin_login1= new adminInfo();
			admin_login1.userLogin();
			if(admin_login1.login==1) {
				System.out.println("You have succesfully loggined");
				System.out.println("enter 1 for Product Report and enter 2 for Customer Report and 3 for Low Sock Report");
				int sales=scanner.nextInt();
				switch(sales) {
				
				case 1:
					
					stockManagement stock_Details3=new stockManagement();
					stock_Details3.stockDetails();
					break;	
				case 2:
					orderTracking order_History3 = new orderTracking();
					order_History3.orderHistory();
					
					break;
				case 3:
					lowstockManagement LSN3=new lowstockManagement();
					LSN3.Notify();
					break;
				}				
			}
			
			
		}
		else {
			System.out.println("Invaid Option!Please select either 1 or 2 only");
		}
		
		
	}
	
}