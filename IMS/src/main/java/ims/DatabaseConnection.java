package ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public static Connection getConnection() throws SQLException {

		String jdbcurl = "oracle.jdbc.driver.OracleDriver";
		String connstring="jdbc:oracle:thin:@localhost:1521:XE";
		String username = "admin";
	    String password = "pass";
	    
	    try {
			Class.forName(jdbcurl);
			DriverManager.getConnection(connstring, username, password);
//			System.out.println("Connection Successful");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return DriverManager.getConnection(connstring, username, password);
	}

}
