package ims;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManagementModule {
	
	static Scanner sc = new Scanner(System.in);
	
    public static void createOrder() {
    	System.out.println("\nEnter order details:\n");
    	System.out.print("Customer ID: ");
    	int cust_id = sc.nextInt();
    	System.out.print("Order Date(YYYY-MM-DD): ");
    	String date = sc.next();
    	Date order_date = Date.valueOf(date);
    	System.out.print("Total amount: Rs. ");
    	double amount = sc.nextDouble();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO orders (order_id, cust_id, order_date, total_amount) VALUES (oid_seq.nextval, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cust_id);
                preparedStatement.setDate(2, order_date);
                preparedStatement.setDouble(3, amount);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("\nOrder created successfully.");
                } else {
                    System.out.println("\nFailed to create order.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getOrderById() {
    	
    	System.out.print("\nEnter Order ID to get details: ");
    	int orderId = sc.nextInt();
        Order order = null;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM orders WHERE order_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, orderId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int custId = resultSet.getInt("cust_id");
                        java.util.Date orderDate = new java.util.Date(resultSet.getTimestamp("order_date").getTime());
                        double totalAmount = resultSet.getDouble("total_amount");
                        order = new Order(orderId, custId, orderDate, totalAmount);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (order != null) {
            System.out.println("\nRetrieved Order: " + order);
        } else {
            System.out.println("\nOrder not found.");
        }
    }

    public static void updateOrder() {
    	
    	System.out.println("\nEnter order details to update:\n");
    	System.out.print("Order ID: ");
    	int order_id = sc.nextInt();
    	System.out.print("Customer ID: ");
    	int cust_id = sc.nextInt();
    	System.out.print("Order Date(YYYY-MM-DD): ");
    	String date = sc.next();
    	Date order_date = Date.valueOf(date);
    	System.out.print("Total amount: Rs. ");
    	double amount = sc.nextDouble();
    	    	
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE orders SET cust_id = ?, order_date = ?, total_amount = ? WHERE order_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, cust_id);
                preparedStatement.setDate(2, order_date);
                preparedStatement.setDouble(3, amount);
                preparedStatement.setInt(4, order_id);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("\nOrder updated successfully.");
                } else {
                    System.out.println("\nFailed to update order.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOrder() {
    	System.out.print("\nEnter Order ID to delete: ");
    	int orderId = sc.nextInt();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM orders WHERE order_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, orderId);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("\nOrder deleted successfully.");
                } else {
                    System.out.println("\nFailed to delete order.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }
    }
    
    public static void getAllOrders() {
        List<Order> allOrders = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM orders";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderId = resultSet.getInt("order_id");
                        int custId = resultSet.getInt("cust_id");
                        Date orderDate = new Date(resultSet.getTimestamp("order_date").getTime());
                        double totalAmount = resultSet.getDouble("total_amount");
                                                
                        Order order = new Order(orderId, custId, orderDate, totalAmount);
                        allOrders.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (!allOrders.isEmpty()) {
            System.out.println("\nAll Orders:\n");
            for (Order order : allOrders) {
                System.out.println(order);
            }
        } else {
            System.out.println("\nNo orders found.");
        }
    }

}