package com.naman.custsys;

import java.sql.*;

public class dbConnect 
{
	static Connection conn;
	
	public static Connection createConn()
	{
		try
		{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=custDB;encrypt=true;trustServerCertificate=true;";
        String user="myuser";
        String pass="myuser";
        conn= DriverManager.getConnection(jdbcUrl,user,pass);
        
        System.out.println("\u001B[32mCONNECTED TO SQL SERVER SUCCESSFULLY ....\u001B[0m");
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return conn;
		
	}

}
