package com.Naman_CMS.custsys;

import java.sql.*;

public class CustomerOb implements CustomerInt 
{
	Connection conn;

	@Override
	public void createCust(Customer c) 
	{
		conn=dbConnect.createConn();
	    String query="insert into customer values(?,?,?,?)";
	    try
	    {
	           PreparedStatement pstm=conn.prepareStatement(query);
	           pstm.setInt(1,c.getId());
	           pstm.setString(2,c.getName());
	           pstm.setInt(3,c.getAge());
	           pstm.setString(4,c.getMob());
	           int v=pstm.executeUpdate();
	           if(v!=0)
	        	   System.out.println("\n\u001B[32mCUSTOMER INSERTED SUCCESSFULLY ....\u001B[0m\n");
	              
	    }

	    catch (Exception ex)
	    {
	           ex.printStackTrace();
	    }
	       
		
	}

	@Override
	public void showAllCust() 
	{
		conn=dbConnect.createConn();
        String query="select * from customer";
        System.out.println("\nCUSTOMER DETAILS : ");
        System.out.println("---------------------------------------------"
        		+ "-----------------------------");

        System.out.format("%s\t\t%s\t\t\t\t%s\t\t%s\n","ID","NAME","AGE","MOBILE NO.");
        System.out.println("---------------------------------------------"
        		+ "-----------------------------");

        try
        {
            Statement stmt=conn.createStatement();
            ResultSet result= stmt.executeQuery(query);
            while (result.next())
            {
                System.out.format("%d\t\t%s\t%d\t\t%s\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getString(4));
                

            }
            System.out.println("---------------------------------------------"
            		+ "-----------------------------\n\n");

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
		
	}

	@Override
	public void showCustByID(int id) 
	{
		conn=dbConnect.createConn();
        String query="select * from customer where id = "+id;
        System.out.println("\nCUSTOMER DETAILS :");
        System.out.println("---------------------------------------------"
        		+ "-----------------------------");

        System.out.format("%s\t\t%s\t\t\t\t%s\t\t%s\n","ID","NAME","AGE","MOBILE NO.");
        System.out.println("---------------------------------------------"
        		+ "-----------------------------");
        
        try
        {
            Statement stmt=conn.createStatement();
            ResultSet result= stmt.executeQuery(query);
            boolean found=false;
            while (result.next())
            {
            	found=true;
            	System.out.format("%d\t\t%s\t%d\t\t%s\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getString(4));

            }
            
            if (!found) 
            {
                // No records found for the given ID
                System.out.println("\n\u001B[31mERROR >> NO RECORDS FOUND FOR ID : "+id+"\u001B[0m");
            }
            System.out.println("\n");

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        
	}

	@Override
	public void updateCust(int id, String name) 
	{
		conn=dbConnect.createConn();
        String query="update customer set name=? where id=?";
        try
        {
            PreparedStatement pstm=conn.prepareStatement(query);
            boolean found=true;
            pstm.setString(1,name);
            pstm.setInt(2,id);
            
            int v=pstm.executeUpdate();
            if(v!=0)
                System.out.println("\n\u001B[32mCUSTOMER DETAILS UPDATED SUCCESSFULLY ....\u001B[0m\n");
            else
            	System.out.println("\n\u001B[31mERROR >> NO RECORDS FOUND FOR ID : "+id+"\u001B[0m\n");
            	 
                
                    
               

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
		
	}

	@Override
	public void deleteCust(int id) 
	{
		conn=dbConnect.createConn();
        String query="delete from customer where id=?";
        try{
            PreparedStatement pstm=conn.prepareStatement(query);
            pstm.setInt(1,id);
            int v= pstm.executeUpdate();
            if(v!=0)
               System.out.println("\n\u001B[32mCUSTOMER DETAILS WITH ID = "+"'"+id+"'"+" DELETED SUCCESSFULLY ....\u001B[0m\n");
            else
            	System.out.println("\n\u001B[31mERROR >> NO RECORDS FOUND FOR ID : "+id+"\u001B[0m\n");

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
		
	}

	
}
