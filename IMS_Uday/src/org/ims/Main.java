package org.ims;

import java.sql.SQLException;

public class Main {
	//Welcome Page
	public static void main(String[] args) throws ModuleAccessException {
		// TODO Auto-generated method stub
		try {
			Login users = new Login();
			users.welcome();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

}
