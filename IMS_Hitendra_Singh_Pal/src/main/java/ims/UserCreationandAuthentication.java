package ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserCreationandAuthentication {

	public static void Userlogin()
	{
		
		System.out.println("======= Enter Login Details ======");
		System.out.println("1. New User SignUp\n2. Existing User Login");
		
		Scanner sc = new Scanner(System.in);
		
		int temp = sc.nextInt();
		String Username;
		String password;
		
		if(temp == 2)
		{
			System.out.println("Enter Username: ");
			Username = sc.next();
			System.out.println("Enter Password: ");
			password = sc.next();
			
			
			Connection con2 = InventoryManagementSystemprimary.conn;
			
			 try {
		           
		            String sql = "SELECT userid, role FROM user_accounts WHERE username = ? AND password = ?";
		            
		            PreparedStatement preparedStatement = con2.prepareStatement(sql);
		            preparedStatement.setString(1, Username);
		            preparedStatement.setString(2, password);
		            
		            ResultSet resultSet = preparedStatement.executeQuery();
		            
		            if (resultSet.next()) {
		                int userId = resultSet.getInt("userid");
		                String roles = resultSet.getString("role");
		                
		                System.out.println("User authenticated. User ID: " + userId + ", Roles: " + roles);
		               
		                //call menu function
		                
		                if(roles.equals("Admin"))
		                {
		                	MenuOpener.menu4admin();
		                }else if(roles.equals("Supplier"))
		                {
		                	MenuOpener.menu4Supplier();
		                }else if(roles.equals("User"))
		                {
		                	MenuOpener.menu4User();
		                }else {
		                	System.out.println("Invalid roles type!");
		                }
		                
		                
		            
		            } else {
		            	
		                System.out.println("Authentication failed.");
		                
		                Userlogin();
		                
		            }
		            
		            
		           
		            
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			
			
		}else if(temp == 1)
		{
			 System.out.println("Choose from following: ");
            

			    System.out.println("Enter New UserID: ");
				int UserID = sc.nextInt(); 
			    System.out.println("Enter New Username: ");
				Username = sc.next();
				System.out.println("Enter New Password: ");
				password = sc.next();
				System.out.println("Enter Role: ");
				String roles = sc.next();
				
				
				Connection con2 = InventoryManagementSystemprimary.conn;
				
				
				try {
					
					String q2 = "insert into User_accounts(UserID, Username,password,role) values(?,?,?,?)";
					
					PreparedStatement stmt = con2.prepareStatement(q2);
					
					stmt.setInt(1, UserID);
					stmt.setString(2,Username);
					stmt.setString(3, password);
					stmt.setString(4, roles);
					
					stmt.execute();
					
					System.out.println("User Creation Success.");
					
					Userlogin();
					
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
				
			
		}else {
			System.out.println("Invalid Input!!");
		}
		
		
	}
	
	
}
