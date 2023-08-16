package ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManagementModule {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void addUser() {
		System.out.println("\nEnter details to add user:");
		System.out.print("Enter username: ");
		String username = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
		System.out.print("Enter role: ");
		String role = sc.next();
		try (Connection connection = DatabaseConnection.getConnection()) {
			String query = "INSERT INTO users (user_id, username, password, role) VALUES (uid_seq.nextval, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, role);

				int rowsInserted = preparedStatement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("\nUser added successfully.\n");
				} else {
					System.out.println("\nFailed to add user.\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getUser() {
		User user = null;

		System.out.print("\nEnter User ID to view details: ");
		int userId = sc.nextInt();
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			String query = "SELECT * FROM users WHERE user_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setInt(1, userId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						String username = resultSet.getString("username");
						String password = resultSet.getString("password");
						String role = resultSet.getString("role");
						user = new User(userId, username, password, role);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (user != null) {
			System.out.println("\nRetrieved User: " + user);
		} else {
			System.out.println("\nUser not found.\n");
		}
	}

	public static void updateUser() {
		
		System.out.println("\nEnter details to update user:");
		System.out.print("Enter user ID: ");
		int userId = sc.nextInt();
		System.out.print("Enter username: ");
		String username = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
		System.out.print("Enter role: ");
		String role = sc.next();
		try (Connection connection = DatabaseConnection.getConnection()) {
			String query = "UPDATE users SET username = ?, password = ?, role = ? WHERE user_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, role);
				preparedStatement.setInt(4, userId);

				int rowsUpdated = preparedStatement.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("\nUser updated successfully.\n");
				} else {
					System.out.println("\nFailed to update user.\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteUser() {
		
		System.out.print("\nEnter user ID to delete user: ");
		int userId = sc.nextInt();
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			String query = "DELETE FROM users WHERE user_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setInt(1, userId);

				int rowsDeleted = preparedStatement.executeUpdate();
				if (rowsDeleted > 0) {
					System.out.println("\nUser deleted successfully.\n");
				} else {
					System.out.println("\nFailed to delete user.\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getAllUsers() {
		List<User> users = new ArrayList<>();

		try (Connection connection = DatabaseConnection.getConnection()) {
			String query = "SELECT * FROM users";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						int userId = resultSet.getInt("user_id");
						String username = resultSet.getString("username");
						String password = resultSet.getString("password");
						String role = resultSet.getString("role");
						User user = new User(userId, username, password, role);
						users.add(user);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!users.isEmpty()) {
			System.out.println("\nAll Users:\n");
			for (User user : users) {
				System.out.println(user);
			}
		} else {
			System.out.println("\nNo users found.\n");
		}
	}

}
