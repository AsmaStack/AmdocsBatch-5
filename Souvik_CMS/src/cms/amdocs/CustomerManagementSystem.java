package cms.amdocs;

//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerManagementSystem {

	private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";

	public static void main(String[] args) throws SQLException {

		try {
			Class.forName(jdbcurl);
			try {
				UserOperation userOperation = new UserOperation();
				CustomerOperation custOperation = new CustomerOperation();
				User loggedInUser = null;

				System.out.println("Welcome to Customer Management System");
				while (true) {
					int flag = 0, input = 0;
					Scanner sc = new Scanner(System.in);
					if (loggedInUser == null) {
						System.out.println("Select an Option \n 1. New User SignUp/Register \n 2. Login Existing User");
						System.out.print("\nEnter Your Choice: ");
						try {
							input = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("\nNumeric Input Only !!");
						}
						sc.nextLine();

						switch (input) {

						// Register
						case 1:
							boolean uniqueUsername = false;
							String newUsername = null;
							String password;
							String role;

							// check whether the username is already there or not.
							while (!uniqueUsername) {
								System.out.print("Enter Username: ");
								newUsername = sc.nextLine();
								if (userOperation.isUsernameTaken(newUsername)) {
									System.out.println("Username already taken. Choose a different username.");
								} else {
									uniqueUsername = true;
								}
							}

							System.out.print("Enter Password: ");
							password = sc.nextLine();
							System.out.print("Enter Role: ");
							role = sc.nextLine();

							User newUser = new User();
							newUser.setUsername(newUsername);
							newUser.setPassword(password);
							newUser.setRole(role); // Default role

							userOperation.addUser(newUser);
							System.out.println("User registered successfully.");
							loggedInUser = userOperation.getUserByUsernameAndPassword(newUsername, password);
							System.out.println("\nLogged in as " + loggedInUser.getUsername());
							break;

						// Login
						case 2:
							System.out.print("Enter Your Username: ");
							String username = sc.nextLine();
							System.out.print("Enter Your Password: ");
							password = sc.nextLine();

							loggedInUser = userOperation.getUserByUsernameAndPassword(username, password);
							if (loggedInUser != null) {
								System.out.println("\nLogged in as " + loggedInUser.getUsername());
							} else {
//								System.out.println(loggedInUser);
								System.out.println("\nInvalid credentials. Please try again.");
							}
							break;

						// Exit
//						case 3:
//							flag = 1;
//							System.out.println("Exited Successful");
//							break;

						default:
							System.out.println("\nInvalid Input !!! \nSelect a Valid Option\n");
							break;
						}

					} else {
//						System.out.println(loggedInUser.getUserID());
						System.out.println("\n*****  Customer Operations *****");
						System.out.println(
								"Select an Option \n 1. Insert Customer Record \n 2. View All Customers\n 3. Update Customer Details\n 4. Delete Customer Record\n 5. Logout");
						System.out.print("\nEnter Input: ");
						try {
							input = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("Enter a valid input !!");
						}
						sc.nextLine();

						switch (input) {

						// Insert a new Customer
						case 1:
							System.out.print("Enter Customer Name: ");
							String cust_name = sc.nextLine();
							System.out.print("Enter Customer Email: ");
							String cust_email = sc.nextLine();
							System.out.print("Enter Customer Address: ");
							String cust_address = sc.nextLine();
							System.out.print("Enter Customer Contact Number: ");
							String cust_contactNumber = sc.nextLine();

							Customer newCustomer = new Customer();
							newCustomer.setName(cust_name);
							newCustomer.setEmail(cust_email);
							newCustomer.setAddress(cust_address);
							newCustomer.setContactNumber(cust_contactNumber);

							custOperation.addCustomer(newCustomer);
							System.out.println("\nCustomer record inserted successfully.");
							break;

						// View all customers
						case 2:
							custOperation.viewAllCustomers();
							break;

						// Update customer details
						case 3:
							if(loggedInUser.getRole().equalsIgnoreCase("admin")) {
								boolean isCustomerIdPresent = false;
								while (!isCustomerIdPresent) {
									System.out.print("\nEnter the Customer ID to update: ");
									int cust_id = sc.nextInt();
									sc.nextLine();
									Customer inputCustomer = new Customer();
									inputCustomer.setCustID(cust_id);
									if (custOperation.getCustomerByCustId(inputCustomer) == null) {
										System.out.println("\n!! Invalid Customer ID. Enter a valid Customer ID !!");
									} else {
										System.out.print("Enter Updated Customer Name: ");
										String up_cust_name = sc.nextLine();
										System.out.print("Enter Updated Customer Email: ");
										String up_cust_email = sc.nextLine();
										System.out.print("Enter Updated Customer Address: ");
										String up_cust_address = sc.nextLine();
										System.out.print("Enter Updated Customer Contact Number: ");
										String up_cust_contactNumber = sc.nextLine();
										inputCustomer.setName(up_cust_name);
										inputCustomer.setEmail(up_cust_email);
										inputCustomer.setAddress(up_cust_address);
										inputCustomer.setContactNumber(up_cust_contactNumber);
										custOperation.updateCustomer(inputCustomer);
										System.out.println("\nCustomer record updated successfully");
										isCustomerIdPresent = true;
									}
								}
							}
							else {
								System.out.println("You do not have permission to update Customer records");
							}
							break;

						// Delete a customer record
						case 4:
							if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
								boolean isCustomerIdPresent = false;
								while (!isCustomerIdPresent) {
									System.out.println("\nEnter the Customer ID to delete: ");
									int cust_id = sc.nextInt();
									Customer inputCustomer = new Customer();
									inputCustomer.setCustID(cust_id);
									if (custOperation.getCustomerByCustId(inputCustomer) == null) {
										System.out.println("\n!! Invalid Customer ID. Enter a valid Customer ID !!");
									} else {
										custOperation.deleteCustomer(inputCustomer);
										System.out.println("\nCustomer record deleted successfully");
										isCustomerIdPresent = true;
									}
								}
							}
							else {
								System.out.println("You do not have permission to delete Customer records");
							}
							break;

						// Logout
						case 5:
							flag = 1;
							System.out.println("Logout Successful");
							break;

						default:
							break;
						}

					}

					if (flag == 1) {
						break;
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
