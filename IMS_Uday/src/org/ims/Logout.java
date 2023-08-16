package org.ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Logout {
	private static String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private static String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private static String username="amdocsuser615";
	private static String password="user615";
	
	static Scanner sc = new Scanner(System.in);
	
	public void logout() throws SQLException, ClassNotFoundException, ModuleAccessException {
		try {
			Class.forName(jdbcurl);
			Connection con = DriverManager.getConnection(connstring, username, password);
			
			con.close();
			System.out.println("======================================================================\n\n");
			
			// Going back to Welcome page
			Login newlogin = new Login();
			newlogin.welcome();
		}
		finally {
			System.out.println();
		}
	}
}
