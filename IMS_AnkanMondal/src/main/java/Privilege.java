import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Privilege {
    String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orcl.docker.internal";
    String username = "user1";
    String password = "pass";

    private int id;

    Privilege(int id) {
        this.id = id;
    }

    public void getOperations(String status) {
        Scanner scanner = new Scanner(System.in);
        switch (status) {
            case "level1" -> {
                System.out.println("You have customer access");
                System.out.println();
                System.out.println("1: View My Order");
                System.out.println("2: Exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> viewMyOrder(id);
                    case 2 -> {
                    }
                }
            }
            case "level2" -> {
                System.out.println("You have user access");
                System.out.println();
                System.out.println("1: Add Product");
                System.out.println("2: Exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> addOrder();
                    case 2 -> {
                    }
                }
            }
            case "level3" -> {
                System.out.println("You have admin access");
                System.out.println();
                System.out.println("1: View All Orders");
                System.out.println("2: View All Products");
                System.out.println("3: Update Order");
                System.out.println("4: Delete Order");
                System.out.println("5: Stock Tracking");
                System.out.println("6: Exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> viewAllOrders();
                    case 2 -> viewAllProducts();
                    case 3 -> updateOrder();
                    case 4 -> deleteOrder();
                    case 5 -> stockTracking();
                    case 6 -> {
                    }
                }
            }
        }
    }

    private void viewAllOrders() {
        String selectOrdersQuery = "SELECT * FROM ORDERS";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectOrdersQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println();
                System.out.println("Orders");
                System.out.println("ID\t\t\tProd ID\t\t\tCust ID\t\t\tOrder Date\t\t\t\t\t\tTotal Price");
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("id");
                    int prodId = resultSet.getInt("prod_id");
                    int custId = resultSet.getInt("cust_id");
                    String orderDate = resultSet.getString("order_date");
                    int totalPrice = resultSet.getInt("total_price");
                    System.out.println(orderId + "\t\t\t" + prodId + "\t\t\t\t" + custId + "\t\t\t\t" + orderDate + "\t\t\t\t" + totalPrice);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    private void viewAllProducts() {
        String selectOrdersQuery = "SELECT * FROM PRODUCT";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectOrdersQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println();
                System.out.println("Products");
                System.out.println("ID\t\t\tName\t\t\tQuantity\tPrice");
                while (resultSet.next()) {
                    int prodId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int quantity = resultSet.getInt("quantity");
                    int price = resultSet.getInt("price");
                    System.out.println(prodId + "\t\t\t" + name + "\t\t" + quantity + "\t\t\t" + price);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    private void updateOrder() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Order ID to update: ");
            int orderId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new total price: ");
            int newTotalPrice = scanner.nextInt();
            String updateQuery = "UPDATE ORDERS SET TOTAL_PRICE = ? WHERE ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, newTotalPrice);
                preparedStatement.setInt(2, orderId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Order updated successfully");
                } else {
                    System.out.println("Order not found or update failed");
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    private void viewMyOrder(int id) {
        String selectOrdersQuery = "SELECT * FROM ORDERS WHERE ID = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectOrdersQuery)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println();
                System.out.println("Orders");
                System.out.println("ID\t\t\tProd ID\t\t\tCust ID\t\t\tOrder Date\t\t\t\t\t\tTotal Price");
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("id");
                    int prodId = resultSet.getInt("prod_id");
                    int custId = resultSet.getInt("cust_id");
                    String orderDate = resultSet.getString("order_date");
                    int totalPrice = resultSet.getInt("total_price");
                    System.out.println(orderId + "\t\t\t" + prodId + "\t\t\t\t" + custId + "\t\t\t\t" + orderDate + "\t\t\t\t" + totalPrice);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    private void deleteOrder() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Order ID to delete: ");
            int orderId = scanner.nextInt();
            String deleteQuery = "DELETE FROM ORDERS WHERE ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, orderId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Order deleted successfully");
                } else {
                    System.out.println("Order not found or delete failed");
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    private void addOrder() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Order ID: ");
            int orderId = scanner.nextInt();
            System.out.print("Enter Product ID: ");
            int prodId = scanner.nextInt();
            System.out.print("Enter Customer ID: ");
            int custId = scanner.nextInt();
            java.util.Date currentDate = new java.util.Date();
            Date orderDate = new Date(currentDate.getTime());
            System.out.print("Enter Total Price: ");
            int totalPrice = scanner.nextInt();
            String insertQuery = "INSERT INTO ORDERS (ID, PROD_ID, CUST_ID, ORDER_DATE, TOTAL_PRICE) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, prodId);
                preparedStatement.setInt(3, custId);
                preparedStatement.setDate(4, orderDate);
                preparedStatement.setInt(5, totalPrice);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Order added successfully");
                } else {
                    System.out.println("Failed to add order");
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    private void stockTracking() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String trackingQuery = "SELECT C.name AS customer_name, O.prod_id, O.total_price, P.name AS product_name, P.quantity "
                    + "FROM CUSTOMER C "
                    + "JOIN ORDERS O ON C.id = O.cust_id "
                    + "JOIN PRODUCT P ON O.prod_id = P.id";
            try (PreparedStatement preparedStatement = connection.prepareStatement(trackingQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("Stock Tracking:");
                System.out.println("Customer Name\tProduct ID\tTotal Price\tProduct Name\tQuantity");
                while (resultSet.next()) {
                    String customerName = resultSet.getString("customer_name");
                    int prodId = resultSet.getInt("prod_id");
                    int totalPrice = resultSet.getInt("total_price");
                    String productName = resultSet.getString("product_name");
                    int quantity = resultSet.getInt("quantity");
                    System.out.println(customerName + "\t" + prodId + "\t" + totalPrice + "\t" + productName + "\t" + quantity);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception caught: " + e);
        }
    }
}