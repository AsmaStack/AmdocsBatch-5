package com.naman.custsys;

public class Customer 
{
	private String name;
	private int id;
	private int age;
	private int mob;
	
	public Customer()
	{
		
	}
	
	public Customer(String name, int id, int age, int mob) 
	{
		this.name = name;
		this.id = id;
		this.age = age;
		this.mob = mob;
	}
	

	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public int getAge() 
	{
		return age;
	}
	
	public void setAge(int age) 
	{
		this.age = age;
	}
	
	public int getMob() 
	{
		return mob;
	}
	
	public void setMob(int mob) 
	{
		this.mob = mob;
	}

	@Override
	public String toString() 
	{
		return "Customer [name=" + name + ", id=" + id + ", age=" + age + ", mob=" + mob + "]";
	}
	
	
	
	
	


	
	
	
	
	
	
	

}
