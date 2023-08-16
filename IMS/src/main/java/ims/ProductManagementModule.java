package ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManagementModule {
	
	static Scanner sc = new Scanner(System.in);

	public static void getAllProducts() {
		List<Product> products = new ArrayList<>();

		try (Connection connection = DatabaseConnection.getConnection()) {
			String query = "SELECT * FROM products";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						int productId = resultSet.getInt("product_id");
						String productName = resultSet.getString("product_name");
						String category = resultSet.getString("category");
						double unitPrice = resultSet.getDouble("unit_price");

						Product product = new Product(productId, productName, category, unitPrice);
						products.add(product);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!products.isEmpty()) {
			System.out.println("\nAll Products:\n");
			for (Product product : products) {
				System.out.println(product);
			}
		} else {
			System.out.println("\nNo products found.");
		}
	}
	
	public static void getProductById() {
        Product product = null;
        
        System.out.print("\nEnter Product ID to see details: ");
        int productId = sc.nextInt();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM products WHERE product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, productId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                    	int id = resultSet.getInt("product_id");
                        String productName = resultSet.getString("product_name");                        
                        String category = resultSet.getString("category");
                        double price = resultSet.getDouble("unit_price");
                        product = new Product(productId, productName, category, price);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (product != null) {
            System.out.println("\nRetrieved Product: " + product);
        } else {
            System.out.println("\nProduct not found.");
        }
    }

	public static void addProduct() {
		
		System.out.println("\nEnter product details:\n");
    	System.out.print("Product Name: ");
    	String product_name = sc.next();
    	System.out.print("Category: ");
    	String category = sc.next();
    	System.out.print("Unit Price: Rs. ");
    	double unit_price = sc.nextDouble();
    	
		try (Connection connection = DatabaseConnection.getConnection()) {
			String query = "INSERT INTO products (product_id, product_name, category, unit_price) VALUES (pid_seq.nextval, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, product_name);
				preparedStatement.setString(2, category);
				preparedStatement.setDouble(3, unit_price);

				int rowsInserted = preparedStatement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("\nProduct added successfully.\n");
				} else {
					System.out.println("\nFailed to add product.\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateProduct() {
		
		System.out.println("\nEnter product details to update product:\n");
		System.out.print("Enter Product ID: ");
		int product_id = sc.nextInt();
    	System.out.print("Product Name: ");
    	String product_name = sc.next();
    	System.out.print("Category: ");
    	String category = sc.next();
    	System.out.print("Unit Price: Rs. ");
    	double unit_price = sc.nextDouble();
    
    	
		try (Connection connection = DatabaseConnection.getConnection()) {
			String query = "UPDATE products SET product_name = ?, category = ?, unit_price = ? WHERE product_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, product_name);
				preparedStatement.setString(2, category);
				preparedStatement.setDouble(3, unit_price);
				preparedStatement.setInt(4, product_id);

				int rowsUpdated = preparedStatement.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("\nProduct updated successfully.\n");
				} else {
					System.out.println("\nFailed to update product.\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteProduct() {
		
		System.out.print("\nEnter Product ID to delete product: ");
		int product_id = sc.nextInt();
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			String query = "DELETE FROM products WHERE product_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setInt(1, product_id);

				int rowsDeleted = preparedStatement.executeUpdate();
				if (rowsDeleted > 0) {
					System.out.println("\nProduct deleted successfully.\n");
				} else {
					System.out.println("\nFailed to delete product.\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
