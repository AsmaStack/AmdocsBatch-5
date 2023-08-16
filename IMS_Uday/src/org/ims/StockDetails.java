package org.ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StockDetails {

	private String jdbcurl="oracle.jdbc.driver.OracleDriver";
	private String connstring="jdbc:oracle:thin:@localhost:1521:XE";
	private String username="amdocsuser615";
	private String password="user615";
	
	Scanner sc = new Scanner(System.in);
	
	int iid,icprice,isprice,iqty;
	String iname,itype;
	
	Connection con;
	
	public void addItems() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(jdbcurl);
			con = DriverManager.getConnection(connstring, username, password);
			
			System.out.print("\nEnter Item Id: ");
			iid = sc.nextInt();
			System.out.print("Enter Item Name: ");
			iname = sc.next();
			System.out.print("Enter Item Type: ");
			itype = sc.next();
			System.out.print("Enter Item Cost Price: ");
			icprice = sc.nextInt();
			System.out.print("Enter Item Sale Price: ");
			isprice = sc.nextInt();
			System.out.print("Enter the Quantity: ");
			iqty = sc.nextInt();
			System.out.println();
			
			// inserting into database
//			/*
			String insertstmt="insert into product values(?,?,?,?,?,?)";
			PreparedStatement pstinsert = con.prepareStatement(insertstmt);
			pstinsert.setInt(1, iid);
			pstinsert.setString(2, iname);
			pstinsert.setString(3, itype);
			pstinsert.setInt(4, icprice);
			pstinsert.setInt(5, isprice);
			pstinsert.setInt(6, iqty);
			
			pstinsert.execute();
			System.out.println("Inserted Successfully");
			
		}
		finally {
			System.out.println();
		}
	}
	
	public void updateItems() throws SQLException, ClassNotFoundException{
		try {
			Class.forName(jdbcurl);
			con = DriverManager.getConnection(connstring, username, password);
			
			System.out.println("1.Update Cost Price");
			System.out.println("2.Update Sale Price");
			System.out.println("3.Update Quantity");
			System.out.println("Select what needs to be updated: ");
			int nupdate = sc.nextInt();
			if(nupdate==1) {
				System.out.print("\nEnter Item Id: ");
				iid = sc.nextInt();
				System.out.print("Enter new Item Cost Price: ");
				icprice = sc.nextInt();
				
				// updating cost price
//				/*
				String updatestmt="update product set p_price=? where pid=?";
				PreparedStatement psupdate = con.prepareStatement(updatestmt);
				psupdate.setInt(2, iid);
				psupdate.setInt(1, icprice);
				psupdate.execute();
				System.out.println("Cost Price Updated Successfully");
//				*/
			}
			else if(nupdate==2) {
				System.out.print("\nEnter Item Id: ");
				iid = sc.nextInt();
				System.out.print("Enter new Item Sale Price: ");
				isprice = sc.nextInt();
				
				// updating sale price
//				/*
				String updatestmt="update product set s_price=? where pid=?";
				PreparedStatement psupdate = con.prepareStatement(updatestmt);
				psupdate.setInt(2, iid);
				psupdate.setInt(1, isprice);
				psupdate.execute();
				System.out.println();
				System.out.println("Sale Price Updated Successfully");
//				*/
			}
			else if(nupdate==3) {
				System.out.print("\nEnter Item Id: ");
				iid = sc.nextInt();
				System.out.print("Enter new Quantity: ");
				iqty = sc.nextInt();
				
				// updating quantity
//				/*
				String updatestmt1="update product set pqty=? where pid=?";
				PreparedStatement psupdate = con.prepareStatement(updatestmt1);
				psupdate.setInt(2, iid);
				psupdate.setInt(1, iqty);
				psupdate.execute();
				System.out.println();
				System.out.println("Quantity Updated Successfully");
//				*/
			}
		}
		finally {
			System.out.println();
		}
	}
	
	public void deleteItems() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(jdbcurl);
			con = DriverManager.getConnection(connstring, username, password);
		
			// deleting the row
	//		/*
			System.out.print("\nEnter Item Id which needs to be deleted: ");
			iid = sc.nextInt();
			String updatestmt="delete from product where pid=?";
			PreparedStatement psupdate = con.prepareStatement(updatestmt);
			psupdate.setInt(1, iid);
			psupdate.execute();
			System.out.println();
			System.out.println("Successfully Deleted!!");
	//		*/
		}
		finally {
			System.out.println();
		}
	}
	
	public void displayItems() throws SQLException, ClassNotFoundException{
		try {
			Class.forName(jdbcurl);
			con = DriverManager.getConnection(connstring, username, password);
	//		/*
			String selectstmt = "select * from product";
			PreparedStatement psselect = con.prepareStatement(selectstmt);
			
			ResultSet rs = psselect.executeQuery();
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("ID\t\tCost Price\tSale Price\tQuantity\tName\t\tType");
			System.out.println("-------------------------------------------------------------------------------------------");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t\t"+rs.getInt(4)+"\t\t"+rs.getInt(5)+"\t\t"+rs.getInt(6)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
			}
			System.out.println("-------------------------------------------------------------------------------------------\n");
			System.out.println("\t\t\t	===== Printed Successfully =====");
			System.out.println();
	//		*/
		}
		finally {
			System.out.println();
		}
	}
}
