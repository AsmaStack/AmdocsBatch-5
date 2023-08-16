package ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserAuthentication{
	
	private static String username;
	private static String password;
	
    public static boolean authenticateUser() throws InvalidUserException{
    	
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.print("Enter username: ");
	    username = sc.next();
		System.out.print("Enter password: ");
		password = sc.next();
		
        boolean isAuthenticated = false;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        isAuthenticated = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isAuthenticated) {
            System.out.println("\nAuthentication successful.");
        } else {
        	
            throw new InvalidUserException("\nAuthentication failed. Invalid Username or password.");            
        }
        
        return isAuthenticated;       
    }
    
    public static String getUserRole() {
    	
        String role = null;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT role FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        role = resultSet.getString("role");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

}