package com.amdocs.ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class ProductUtility {
    public static boolean isExist(int prod_id){
        String query = "select * from products where prod_id=?";
        Connection connection=JDBCConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, prod_id);
            int num=stmt.executeUpdate();
            if(num>0) return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static int getQuantity(int prod_id) {
        String query = "select quantity from products where prod_id=?";
        Connection connection=JDBCConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, prod_id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public static void showProduct() {
        Connection connection=JDBCConnection.getConnection();
        String queryString="select * from Products";
        try {
            Statement stmt= connection.createStatement();
            ResultSet rs=stmt.executeQuery(queryString);
            boolean found=false;
            while(rs.next()) {
                found=true;
                Product p1=new Product(rs.getInt(1),rs.getString(2),rs.getInt(3));
                System.out.println(p1);
            }
            if(!found) {
                System.out.println("Currently No Product is available for sale");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }
    public static void addProduct(String prod_name,int quantity) {
        Connection connection=JDBCConnection.getConnection();
        String queryString="insert into products values (product_sequence.nextval,?,?)";
        try {
            PreparedStatement stmt=connection.prepareStatement(queryString);
            stmt=connection.prepareStatement(queryString);
            stmt.setString(1, prod_name);
            stmt.setInt(2, quantity);
            int num=stmt.executeUpdate();
            if(num>0) {
                System.out.println("Added Successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
