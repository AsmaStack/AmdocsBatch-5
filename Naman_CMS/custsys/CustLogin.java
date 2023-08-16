package com.naman.custsys;

import java.io.*;

public class CustLogin extends Customer 
{
	private static final String user="naman";
    private static final String pass="pass";
    
    public CustLogin(String name, int id, int age, int mob) 
    {
        super(name,id,age,mob);
    }
    
    public static boolean login() throws IOException
    {
    	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ENTER USERNAME : ");
        String username=in.readLine();
        System.out.print("ENTER PASSWORD : ");
        String password=in.readLine();

        return (user.equals(username) && pass.equals(password));
    }

    
}
