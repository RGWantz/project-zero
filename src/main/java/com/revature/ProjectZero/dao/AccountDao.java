package com.revature.ProjectZero.dao;//do I need other methods? 

import java.util.List;

import com.revature.ProjectZero.beans.Account;
import com.revature.ProjectZero.beans.User;

public interface AccountDao {
	
	List<Account> allAccounts(); 
	
	boolean newAccount(); //removed parameter Account newOne
	
	boolean deposit(Account thisAccount, double amount); //double check that I don't want the double balance returned instead 
	
	boolean withdraw(Account thisAccount, double amount);
	
	boolean transfer(Account thisAccount, Account other, double amount); 
	
	boolean addOwner(Account thisAccount, User newUser); //Saving for eventually. 
}
