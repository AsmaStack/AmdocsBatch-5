package ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SalesReport {
	
	static Scanner sc = new Scanner(System.in);
   
    public static void generateSalesReportByCustomer() {
        List<Order> sales = new ArrayList<>();
        
        System.out.print("\nEnter customer ID to generate report: ");
        int customerId = sc.nextInt();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM orders WHERE cust_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, customerId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderId = resultSet.getInt("order_id");
                        Date orderDate = new Date(resultSet.getTimestamp("order_date").getTime());
                        double totalAmount = resultSet.getDouble("total_amount");
                        Order order = new Order(orderId, customerId, orderDate, totalAmount);
                        sales.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!sales.isEmpty()) {
            System.out.println("\nSales Report for Customer " + customerId + ":\n");
            for (Order order : sales) {
                System.out.println(order);
            }
        } else {
            System.out.println("\nNo sales report found for Customer " + customerId);
        }
    }


    public static void generateSalesReportByProduct() {
        List<ProductSalesReport> salesReport = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT p.product_id, p.product_name, SUM(oi.quantity) AS total_quantity, SUM(oi.subtotal) AS total_revenue " +
                           "FROM products p " +
                           "INNER JOIN order_item oi ON p.product_id = oi.product_id " +
                           "GROUP BY p.product_id, p.product_name";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int productId = resultSet.getInt("product_id");
                        String productName = resultSet.getString("product_name");
                        int totalQuantity = resultSet.getInt("total_quantity");
                        double totalRevenue = resultSet.getDouble("total_revenue");
                        ProductSalesReport reportEntry = new ProductSalesReport(productId, productName, totalQuantity, totalRevenue);
                        salesReport.add(reportEntry);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nSales Report by Product:\n");
        for (ProductSalesReport reportEntry : salesReport) {
            System.out.println(reportEntry);
        }
    }

}

    
