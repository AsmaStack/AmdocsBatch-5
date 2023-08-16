package ims;

public class InvalidUserException extends Exception{
	
	public InvalidUserException(String msg) {
		super("\n"+msg);
	}

}
