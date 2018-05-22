package com.revature.ProjectZero.beans; 

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7306551928633948969L;
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myAccounts == null) ? 0 : myAccounts.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (myAccounts == null) {
			if (other.myAccounts != null)
				return false;
		} else if (!myAccounts.equals(other.myAccounts))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	private String userName;
	private String password;
	private ArrayList<Account> myAccounts; 
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.myAccounts = new ArrayList<Account>(); 
		//addToMyAccounts will need called when a new user is created so that they get their new account. 
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

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}
	
	

}
