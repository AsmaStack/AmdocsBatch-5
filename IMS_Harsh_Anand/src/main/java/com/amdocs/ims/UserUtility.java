package com.amdocs.ims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class UserUtility {
    static int loginUser(String input_email,String input_pass) {
        String query = "select user_id from users where email =? and pass=?";
        Connection con = JDBCConnection.getConnection();
        int return_id=-1;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, input_email);
            stmt.setString(2, input_pass);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                return_id=rs.getInt(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        return return_id;

    }
    static int registerUser(String name,String email, String pass, String role){
        Connection con = JDBCConnection.getConnection();
        String query = "insert into users values (user_sequence.nextval,?,?,?,?)";
        String getid = "select user_sequence.currval from dual";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, pass);
            stmt.setString(4, role);
            int num=stmt.executeUpdate();
            if(num>0) {
                System.out.println("Registered Successfully");
            }
            Statement stmt1=con.createStatement();
            ResultSet rs=stmt1.executeQuery(getid);
            while(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        return -1;
    }
    public static void seeProfile(int id) {
        Connection con = JDBCConnection.getConnection();
        String query = "select * from users where user_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                User user =new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void setquantity(int prod_id,int quantity) {
        Connection connection=JDBCConnection.getConnection();
        String queryString="update products set quantity=? where prod_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setInt(1, quantity);
            stmt.setInt(2, prod_id);
            int num=stmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    public static void orderProduct(int user_id,int product_id,int quantity) {
        Connection connection=JDBCConnection.getConnection();
        try {
            String query="select quantity from products where prod_id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, product_id);
            int available_quantity=-1;
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                available_quantity=rs.getInt(1);
            }
            if(available_quantity==-1) {
                System.out.println("No Product Exist with Id "+product_id);
                return ;
            }
            else if(available_quantity<quantity) {
                System.out.println("only "+available_quantity+" left in stock");
                return ;
            }
            OrderItemUtility.orderItem(user_id, product_id, quantity);
            setquantity(product_id, available_quantity-quantity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static boolean authenticateAdmin(int user_id) {
        Connection connection=JDBCConnection.getConnection();
        String queryString="select role from users where user_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();
            String role="user";
            while(rs.next()) {
                role=rs.getString(1);
            }
            if(role.equals("admin")) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void setRole(int user_id,String role) {
        String queryString="update users set role=? where user_id=?";
        Connection connection=JDBCConnection.getConnection();
        try {
            PreparedStatement stmt= connection.prepareStatement(queryString);
            stmt.setString(1, role);
            stmt.setInt(2, user_id);
            int num=stmt.executeUpdate();
            if(num>0) {
                System.out.println("Role changed successfully");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void changeRole(int user_id) {
        Connection connection=JDBCConnection.getConnection();
        String queryString="select role from users where user_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setInt(1, user_id);
            String role =null;
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                role=rs.getString(1);
            }
            if(role!=null && role.equals("user")) role="admin";
            else role="user";
            setRole(user_id, role);
        }
        catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    public static void seeAllUser() {
        Connection connection=JDBCConnection.getConnection();
        String queryString="select * from users";
        try {
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(queryString);
            while(rs.next()) {
                User user =new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
                System.out.println(user);			}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
