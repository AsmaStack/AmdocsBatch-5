package com.Naman_CMS.custsys;

import java.io.*;

public class Main 
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\n                            WELCOME TO CUSTOMER MANAGEMENT SYSTEM \n"
				+ "                                   \033[3m- [ C M S PROJECT 2023 ]\033[0m\n "
				+ "                                       \033[3m- [ by NAMAN ]\033[0m\n");
	        if (CustLogin.login()) 
	        {
	            System.out.println("\n\u001B[32mLOGIN SUCCESSFULL ...\u001B[0m");
	            startMenu();
	        } 
	        else 
	        {
	            System.out.println("\n\u001B[31mERROR >> LOGIN FALIED . EXITING ....\u001B[0m");
	        }
	    }
		
	
	public static void startMenu() throws IOException
	{
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		int id;
		String name;
        
		CustomerInt obj=new CustomerOb();
		
		System.out.println("\n\n                            WELCOME TO CUSTOMER MANAGEMENT SYSTEM \n"
				+ "                                   \033[3m- [ C M S PROJECT 2023 ]\033[0m\n "
				+ "                                       \033[3m- [ by NAMAN ]\033[0m\n");
		
		do
		{
            System.out.println("1. ADD CUSTOMER\n" +
                    "2. SHOW ALL CUSTOMER\n" +
                    "3. SHOW / SEARCH CUSTOMER BY ID \n" +
                    "4. UPDATE / EDIT CUSTOMER\n" +
                    "5. DELETE CUSTOMER\n" +
                    "6. EXIT\n");

            System.out.print("PLEASE ENTER YOUR CHOICE : ");
            
            int ch;
            ch=Integer.parseInt(in.readLine());
            System.out.println();
            
            switch (ch)
            {
                case 1:
                    Customer c=new Customer();
                    
                    System.out.print("ENTER ID : ");
                    id=Integer.parseInt(in.readLine());
                    
          
                    
                    System.out.print("ENTER NAME : ");
                    name=in.readLine();
                    System.out.print("ENTER AGE : ");
                    int age=Integer.parseInt(in.readLine());
                    System.out.print("ENTER MOBILE NUMBER : ");
                    String mob=in.readLine();
                    System.out.println();
                    c.setId(id);
                    c.setName(name);
                    c.setAge(age);
                    c.setMob(mob);
                    obj.createCust(c);
                    break;
                    
                case 2:
                    obj.showAllCust();
                    break;
                    
                case 3:
                    System.out.print("ENTER ID : ");
                    int cid=Integer.parseInt(in.readLine());
                    System.out.println();
                    obj.showCustByID(cid);
                    break;
                    
                case 4:
                    System.out.print("ENTER ID TO UPDATE DETAILS : ");
                    int cid1=Integer.parseInt(in.readLine());
                
                    System.out.print("\nENTER NEW NAME : ");
                    name=in.readLine();
                    System.out.println();
                    obj.updateCust(cid1,name);
                    break;
                    
                case 5:
                    System.out.print("ENTER ID TO DELETE DETAILS : ");
                    id=Integer.parseInt(in.readLine());
                    System.out.println();
                    obj.deleteCust(id);
                    break;

                case 6:
                    System.out.println("\u001B[32mTHANK YOU FOR USING THE APPLICATION . HAVE A NICE DAY ...\u001B[0m");
                    System.exit(0);
                    
                default:
                    System.out.println("\u001B[31mENTER A VALID CHOICE !!!!\u001B[0m");
                    break;


            }

        }
		while (true);
		
	}

}
