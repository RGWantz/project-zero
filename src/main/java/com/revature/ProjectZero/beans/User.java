package com.revature.ProjectZero.beans; //TODO When fields are established, put in .equals and hashcode methods, also toString  

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7306551928633948969L;
	
	
	private String userName;
	private String password;
	private ArrayList<Account> myAccounts; 
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.myAccounts = new ArrayList<Account>();  //addToMyAccounts will need called when a new user is created so that they get their new account. 
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//don't want userName to change  
	
	public String getUserName() {
		return userName;
	} 
	
	public ArrayList<Account> getMyAccounts() {
		return myAccounts;
	}

	public void addToMyAccounts(Account newAccount) {
		this.myAccounts.add(newAccount); 
	}
	

}
