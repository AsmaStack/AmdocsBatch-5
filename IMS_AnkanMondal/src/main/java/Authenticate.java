import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticate {
    private final String jdbcUrl;
    private final String username;
    private final String password;

    Authenticate(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public String authenticate(int id, String pass) {
        String status = "level0";
        String selectQuery = "SELECT * FROM CUSTOMER WHERE id = ? AND pass = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, pass);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getString("status");
                    System.out.println();
                    System.out.println("Authentication successful");
                    System.out.println("You have privilege level: " + status);
                    return status;
                } else {
                    System.out.println("Authentication failed");
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception caught: " + e);
        }
        return status;
    }
}