package IMS;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class userCreation {
	private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";
    private static String connstring = "jdbc:oracle:thin:@localhost:1521:XE";
    private static String username = "amdocsuser1";
    private static String password = "naru";
    static Statement stmt;
    
    public void useradd(){
    	try {
			Class.forName(jdbcurl);
			 Connection con = DriverManager.getConnection(connstring, username, password);
	    	 Scanner pd= new Scanner(System.in);
			 System.out.println("Enter user id ");
			 int userId= pd.nextInt();
			 System.out.println("Enter user name");
			 String userNAME=pd.next();			 
			 System.out.println("Enter user password");
			 String userPassword =pd.next();
			 System.out.println("Enter user email");
			 String email =pd.next();
			 System.out.println("Enter role Id");
			 int roleId =pd.nextInt();
			 String insertstmt="insert into userdata values (?, ?, ?, ?, ?)";
			 PreparedStatement udinsert= con.prepareStatement (insertstmt);
			 udinsert.setInt (1, userId);
		     udinsert.setString (2, userNAME);
			 udinsert.setString (3, userPassword);	
			 udinsert.setString (4, email);
			 udinsert.setInt (5, roleId);
			 udinsert.execute();
		     System.out.println("Record Added");
		     
	    	}
	    	catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	
    	
    }

}
