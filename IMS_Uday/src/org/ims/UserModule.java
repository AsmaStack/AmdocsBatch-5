package org.ims;

import java.sql.SQLException;
import java.util.Scanner;

public class UserModule {
	
	public void manageUser() throws ClassNotFoundException, SQLException, ModuleAccessException {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------------------------------------");
		System.out.println("             Select the operation          ");
		System.out.println("-------------------------------------------");
		System.out.println("\t1. Update User Details\n\t2. Delete User Details\n\t0. Go Back");
		System.out.print("\nChoose the no. to take the action: ");
		int t3 = sc.nextInt();
		if(t3==1) {
			UserDetails update = new UserDetails();
			update.updateUsers();
			manageUser();
		}
		else if(t3==2) {
			UserDetails delete = new UserDetails();
			delete.deleteUsers();
			manageUser();
		}
		else if(t3==0) {
			Menu m = new Menu();
			m.options();
		}
		else {
			System.out.println("Invalid Option. Please Retry!");
			manageUser();
		}
		
		sc.close();
	}
	
	public void displayUser() throws ClassNotFoundException, SQLException {
		UserDetails display = new UserDetails();
		display.displayUsers();
	}
}
