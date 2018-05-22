package com.revature.ProjectZero.dao;//do I need other methods? 

import java.util.List;

import com.revature.ProjectZero.beans.Account;
import com.revature.ProjectZero.beans.Transaction;
import com.revature.ProjectZero.beans.User;

public interface AccountDao {
	
	List<Account> allAccounts(); 
	
	boolean newAccount(Account newOne); 
	
	Account generateAccount(int acctNum); 
	
	double getBalance(Account thisAccount); 
	
	boolean deposit(Account thisAccount, double amount); 
	
	boolean withdraw(Account thisAccount, double amount);
	
	boolean transfer(Account thisAccount, Account other, double amount); 
	
	int getAcctNumber(Account thisAccount, User currentUser); 
	
	boolean addOwner(Account thisAccount, User newUser); 
	
	User getOwner(Account thisAccount, User currentUser); 
	
	//List<Transaction> viewTransactionHistory(Account thisAccount);
}
