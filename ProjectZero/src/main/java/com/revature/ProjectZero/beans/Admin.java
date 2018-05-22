package com.revature.ProjectZero.beans;  

public class Admin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -65899993143824974L;
	
	private static Admin theSoleAdmin = new Admin("bankAdmin", "secureBankAdminPassword"); //Will it be a problem to have it static? 
	
	public  Admin(String userName, String password) {
		super(userName, password);
		
	}

	public static Admin getAdmin() {
		return theSoleAdmin;
	} 
	
	

}
