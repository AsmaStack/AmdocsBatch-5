package com.amdocs.ims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class JDBCConnection {
    static Connection connection;
    public static Connection getConnection() {
        if(connection!=null) return connection;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            System.out.println(e1.getMessage());
        }

        //step2 create  the connection object
        try {
            connection=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","ims","1234");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
}
