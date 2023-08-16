package com.naman.custsys;

public interface CustomerInt 
{
	
    public void createCust(Customer c);
    
    public void showAllCust();
    
    public void showCustByID(int id);
    
    public void updateCust(int id,String name);
    
    public void deleteCust(int id);

}
