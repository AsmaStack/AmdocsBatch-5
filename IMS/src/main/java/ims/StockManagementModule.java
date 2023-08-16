package ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockManagementModule {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void getAllStock() {
        List<Stock> stockList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM stock";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int productId = resultSet.getInt("product_id");
                        int quantity = resultSet.getInt("quantity");
                        Stock stock = new Stock(productId, quantity);
                        stockList.add(stock);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!stockList.isEmpty()) {
        System.out.println("\nStock Details:\n");
        for(Stock stock : stockList) {
        	System.out.println(stock);
        }
        }
        else {
            System.out.println("\nNo stock items found.\n");
        }
    }
	
    public static void getStockByProduct() {
        Stock stock = null;
        
        System.out.print("\nEnter Product ID to get Stock details: ");
        int productId = sc.nextInt();
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM stock WHERE product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, productId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int stockQuantity = resultSet.getInt("quantity");
                        stock = new Stock(productId, stockQuantity);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (stock != null) {
            System.out.println("Current Stock: " + stock.getQuantity());

    }
    }

    public static void updateStock() {
    	
    	System.out.print("\nEnter Product ID to update stock: ");
    	int productId = sc.nextInt();
    	System.out.print("\nEnter new quantity for product "+productId+": ");
    	int newQuantity = sc.nextInt();
    	
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE stock SET quantity = ? WHERE product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, newQuantity);
                preparedStatement.setInt(2, productId);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                	System.out.println("\nStock updated successfully.\n");
                } else {
                    System.out.println("\nFailed to update stock.\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
