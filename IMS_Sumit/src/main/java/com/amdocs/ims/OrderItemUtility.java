package com.amdocs.ims;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderItemUtility {
    public static void orderItem(int user_id,int product_id,int quantity) {
        Connection connection=JDBCConnection.getConnection();
        String query="insert into order_list values (order_sequence.nextval,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user_id);
            stmt.setInt(2, product_id);
            stmt.setInt(3, quantity);
            int num=stmt.executeUpdate();
            if(num>0) {
                System.out.println("Order Placed Successfully\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void seeOrder(int user_id) {
        Connection connection=JDBCConnection.getConnection();
        String query="select * from order_list where user_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();
            boolean found=false;
            while(rs.next()) {
                found=true;
                OrderList orderList =new OrderList(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                System.out.println(orderList);
            }
            if(!found) {
                System.out.println("No Order Placed with id "+user_id);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    public static OrderList findOrder(int order_id) {
        Connection connection=JDBCConnection.getConnection();
        String query = "select * from order_list where order_id=?";
        try {
            PreparedStatement stmt=connection.prepareStatement(query);
            stmt.setInt(1, order_id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                OrderList orderList =new OrderList(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                return orderList;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void cancelOrder(int order_id,int user_id) {
        OrderList orderList =findOrder(order_id);
        System.out.println(orderList);
        if(orderList ==null) {
            System.out.println("No Order Exist with Given id");
            return ;
        }
        Connection connection=JDBCConnection.getConnection();
        String query="delete from order_list where order_id=? and user_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, order_id);
            stmt.setInt(2, user_id);
            int num=stmt.executeUpdate();
            if(num==0) {
                System.out.println("You have not placed any order with id "+order_id);
            }
            else {
                UserUtility.setquantity(orderList.getProd_id(), ProductUtility.getQuantity(orderList.getProd_id())+ orderList.getPurchased_quantity());
                System.out.println("Deleted Successfully");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
}
