package ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Deletion_Product {
	
	public static void deleteProduct() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter productID to delete: ");
		int temp = sc.nextInt();
		String q = "delete from product where productID = ?";
		Connection con1 = InventoryManagementSystemprimary.conn;
		
		try {
			PreparedStatement stmt = con1.prepareStatement(q);
			stmt.setInt(1, temp);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	      
	}
	}
