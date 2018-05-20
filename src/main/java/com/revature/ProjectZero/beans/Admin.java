package com.revature.ProjectZero.beans; //eventually change password. 

//import java.lang.Object.User;

public class Admin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -65899993143824974L;
	
	private Admin theSoleAdmin = new Admin("bankAdmin", "secureBankAdminPassword"); 
	
	private Admin(String userName, String password) {
		super(userName, password);
		
	}

	public Admin getAdmin() {
		return theSoleAdmin;
	} 
	
	

}
