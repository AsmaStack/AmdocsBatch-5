package ims;

import java.util.Scanner;

public class Dashboard {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("\tWelcome to Inventory Management System");
		System.out.println("------------------------------------------------------");
	
		// User Authentication
		boolean authenticateUser = false;
		try {
			authenticateUser = UserAuthentication.authenticateUser();
		} catch (InvalidUserException msg) {
			System.out.println(msg);
		}
		
		if(authenticateUser) {
			// Check user role for role-based access
			String access = UserAuthentication.getUserRole();
			// System.out.println(access);
			if(access.equals("admin")) {
				System.out.println("\nSigned in as Admin");
				int logout = Integer.MIN_VALUE;
				do{
				System.out.println("\nPlease select module:\n");
				System.out.println("1. User Module");
				System.out.println("2. Product Module");
				System.out.println("3. Stock Module");
				System.out.println("4. Order Module");
				System.out.println("5. Sales Report");
				System.out.print("\nEnter choice(1-5): ");
				int choice = sc.nextInt();
				switch(choice) {
				case 1: {
					System.out.println("\nPlease select task:\n");
					System.out.println("1. Add new user");
					System.out.println("2. View details of one user");
					System.out.println("3. View details of all users");
					System.out.println("4. Update user details");
					System.out.println("5. Delete user");
					System.out.print("\nEnter choice(1-5): ");
					int task = sc.nextInt();
					switch(task){
						case 1: UserManagementModule.addUser();
						break;
						case 2: UserManagementModule.getUser();
						break;
						case 3: UserManagementModule.getAllUsers();
						break;
						case 4: UserManagementModule.updateUser();
						break;
						case 5: UserManagementModule.deleteUser();
						break;
						default: System.err.println("Invalid option selected");
						break;	
					}
					break;
				}			
					
				case 2:{
					System.out.println("\nPlease select task:\n");
					System.out.println("1. Add new product");
					System.out.println("2. View details of one product");
					System.out.println("3. View details of all products");
					System.out.println("4. Update product details");
					System.out.println("5. Delete product");
					System.out.print("\nEnter choice(1-5): ");
					int task = sc.nextInt();
					switch(task){
						case 1: ProductManagementModule.addProduct();
						break;
						case 2: ProductManagementModule.getProductById();
						break;
						case 3: ProductManagementModule.getAllProducts();
						break;
						case 4: ProductManagementModule.updateProduct();
						break;
						case 5: ProductManagementModule.deleteProduct();
						break;
						default: System.err.println("Invalid option selected");
						break;	
					}
					break;
				}		
				
				case 3:{
					System.out.println("\nPlease select task:\n");
					System.out.println("1. View stock details of one product");
					System.out.println("2. View all stock details");
					System.out.println("3. Update stock details");
					System.out.println("4. View products with low stock");
					System.out.print("\nEnter choice(1-4): ");
					int task = sc.nextInt();
					switch(task){
						case 1: StockManagementModule.getStockByProduct();
						break;
						case 2: StockManagementModule.getAllStock();
						break;
						case 3: StockManagementModule.updateStock();
						break;
						case 4: StockTrackingModule.getLowStockProducts();
						break;
						default: System.err.println("Invalid option selected");
						break;	
					}
					break;
				}		
				
				case 4:{
					System.out.println("\nPlease select task:\n");
					System.out.println("1. Add new order");
					System.out.println("2. View details of one order");
					System.out.println("3. View details of all orders");
					System.out.println("4. Update order details");
					System.out.println("5. Delete order");
					System.out.print("\nEnter choice(1-5): ");
					int task = sc.nextInt();
					switch(task){
						case 1: OrderManagementModule.createOrder();
						break;
						case 2: OrderManagementModule.getOrderById();
						break;
						case 3: OrderManagementModule.getAllOrders();
						break;
						case 4: OrderManagementModule.updateOrder();
						break;
						case 5: OrderManagementModule.deleteOrder();
						break;
						default: System.err.println("Invalid option selected");
						break;	
					}
					break;
				}		
				
				case 5:{
					System.out.println("\nPlease select task:\n");
					System.out.println("1. Generate Sales Report by Customer");
					System.out.println("2. Generate Sales Report by Product");
					System.out.print("\nEnter choice(1-2): ");
					int task = sc.nextInt();
					switch(task){
						case 1: SalesReport.generateSalesReportByCustomer();
						break;
						case 2: SalesReport.generateSalesReportByProduct();
						break;
						default: System.err.println("Invalid option selected");
						break;	
					}
					break;
				}		
				default: System.err.println("Invalid option selected");
				break;	
				}
					
				System.out.println("\nPress 0 to logout\n");
				logout = sc.nextInt();				
						
		}while(logout != 0);
			}
			
			else if(access.equals("stock_manager")){
				System.out.println("\nSigned in as Stock Manager\n");
				int logout = Integer.MIN_VALUE;
				do{
					System.out.println("\nPlease select task:\n");
					System.out.println("1. View details of one product");
					System.out.println("2. View details of all products");
					System.out.println("3. View stock details of one product");
					System.out.println("4. View all stock details");
					System.out.println("5. Update stock details");
					System.out.println("6. View products with low stock");
					System.out.print("\nEnter choice(1-6): ");
					int task = sc.nextInt();
					switch(task){
						case 1: ProductManagementModule.getProductById();
						break;
						case 2: ProductManagementModule.getAllProducts();
						break;
						case 3: StockManagementModule.getStockByProduct();
						break;
						case 4: StockManagementModule.getAllStock();
						break;
						case 5: StockManagementModule.updateStock();
						break;
						case 6: StockTrackingModule.getLowStockProducts();
						break;
						default: System.err.println("Invalid option selected");
						break;	
					}
					
					
				System.out.println("\nPress 0 to logout\n");
				logout = sc.next().charAt(0);				
			}	while(logout != 0);		
		}		
			else if(access.equals("sales_manager")){
				System.out.println("\nSigned in as Sales Manager\n");
				int logout = Integer.MIN_VALUE;
				do{
					System.out.println("\nPlease select task:\n");
					System.out.println("1. Generate Sales Report by Customer");
					System.out.println("2. Generate Sales Report by Product");
					System.out.print("\nEnter choice(1-2): ");
					int task = sc.nextInt();
					switch(task){
						case 1: SalesReport.generateSalesReportByCustomer();
						break;
						case 2: SalesReport.generateSalesReportByProduct();
						break;
						default: System.err.println("Invalid option selected");
						break;	
					}				
					
				System.out.println("Press 0 to logout");
				logout = sc.next().charAt(0);				
			}	while(logout != 0);		
		}		
			else if (access.equals("employee")) {
				System.out.println("\nSigned in as Employee\n");
				int logout = Integer.MIN_VALUE;
				do{
					System.out.println("\nPlease select task:\n");
					System.out.println("1. View details of one product");
					System.out.println("2. View details of all products");
					System.out.println("3. View stock details of one product");
					System.out.println("4. View all stock details");
					System.out.println("5. View products with low stock");
					System.out.print("\nEnter choice(1-5): ");
					int task = sc.nextInt();
					switch(task){
						case 1: ProductManagementModule.getProductById();
						break;
						case 2: ProductManagementModule.getAllProducts();
						break;
						case 3: StockManagementModule.getStockByProduct();
						break;
						case 4: StockManagementModule.getAllStock();
						break;
						case 5: StockTrackingModule.getLowStockProducts();
						break;
						default: System.err.println("Invalid option selected");
						break;	
					}				
					
				System.out.println("\nPress 0 to logout\n");
				logout = sc.next().charAt(0);				
			}	while(logout != 0);	
			}
		}
			
		else {
			System.out.println("\nPlease contact admin to add new user.\n");
		}
		
		sc.close();

	
	}
}

