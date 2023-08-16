package cms.amdocs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

//	private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";
	private static String connstring = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String username = "souvik";
	private static String password = "souvik";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(connstring, username, password);
	}
}
