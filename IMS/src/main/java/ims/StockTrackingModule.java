package ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StockTrackingModule {
	
	final static int threshold = 3;
	
    public static void getLowStockProducts() {
        HashMap<Product, Stock> lowStockProducts = new HashMap<Product, Stock>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT products.*, stock.quantity FROM products " +
                           "INNER JOIN stock ON products.product_id = stock.product_id " +
                           "WHERE stock.quantity < ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, threshold);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int productId = resultSet.getInt("product_id");
                        String productName = resultSet.getString("product_name");
                        String category = resultSet.getString("category");
                        double unitPrice = resultSet.getDouble("unit_price");
                        int stockQuantity = resultSet.getInt("quantity");

                        Product product = new Product(productId, productName, category, unitPrice);
                        Stock stock = new Stock(productId, stockQuantity);
                        stock.setQuantity(stockQuantity);
                        lowStockProducts.put(product, stock);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!lowStockProducts.isEmpty()) {
            StockTrackingModule.lowStockNotifications(lowStockProducts);
        } else {
            System.out.println("No low stock products found.");
        }
    }

    public static void lowStockNotifications(Map<Product, Stock> lowStockProducts) {
        for (Map.Entry<Product, Stock> product : lowStockProducts.entrySet()) {
            System.out.println("Low stock notification: Product - " + product.getKey().getProductName() +
                               ", Stock Quantity - " + product.getValue().getQuantity());
        }
    }
}

    
