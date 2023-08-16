package IMS;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class adminInfo {
	private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";
	private static String connstring = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String username = "amdocsuser1";
	private static String password = "naru";
	static Statement stmt;
	//private boolean loggedIn = false; // Store login status
    
    // Other methods
    
//    public boolean isLoggedIn() {
//        return loggedIn;
//    }
	int login=0;
	public void userLogin() {
		
		    Scanner scanner = new Scanner(System.in);
			int maxLoginAttempts = 4;
			
			boolean loggedIn = false;

			while (maxLoginAttempts > 0 && !loggedIn) {
				try {
					Class.forName(jdbcurl);
					Connection con = DriverManager.getConnection(connstring, username, password);

					String query = "SELECT * FROM UserData WHERE USERID = ? AND Password = ?";
					PreparedStatement preparedStatement1 = con.prepareStatement(query);
					System.out.println("Enter  Userid");
					int userId = scanner.nextInt();
					System.out.print("Enter  Password: ");
					String Password = scanner.next();
					if (userId<2004) {
						preparedStatement1.setInt(1, userId);
						preparedStatement1.setString(2, Password);
						ResultSet resultSet = preparedStatement1.executeQuery();
						if (resultSet.next()) {
							//loggedIn = true;
							 login=1;
							break;
						} else {
							maxLoginAttempts--;
							System.out.println("Wrong admin userid and Password.Re_enter admin useid and password again " + maxLoginAttempts + " attempts left.");						}
						
					}
					else {
						System.out.println("You are not allowed to access the data");
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
	}
}

			

		
	




			

		
	


