package com.revature.ProjectZero.dao; 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.ProjectZero.beans.Account;
import com.revature.ProjectZero.beans.Transaction;
import com.revature.ProjectZero.beans.User;

public class AccountSerializer implements AccountDao {
	private final String FILE = "target/AllAccounts.txt"; 
	
	@Override
	public List<Account> allAccounts(){
		try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(FILE));) {
			return (List<Account>) inStream.readObject(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return new ArrayList<>(); 
	}
	
	@Override
	public boolean newAccount(Account newOne) {
		List<Account> currentAccountList = allAccounts();
		if(currentAccountList == null) {
			currentAccountList = new ArrayList<>(); 
		}
		currentAccountList.add(newOne);
		
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
			outStream.writeObject(currentAccountList);
			return true; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Account generateAccount(int acctNum) {
		List<Account> currentAccountList = allAccounts();
		for(int i = 0; i < currentAccountList.size(); i++) {
			if(currentAccountList.get(i).getAcctNum() == acctNum) {
				return currentAccountList.get(i); 
			}
		}
		return null;
	}
	
	
	
	@Override
	public boolean deposit(Account thisAccount, double amount) {
		List<Account> currentAccountList = allAccounts();
		for(int i = 0; i < currentAccountList.size(); i++) {
			if(currentAccountList.get(i).getAcctNum() == thisAccount.getAcctNum()) {
				currentAccountList.get(i).setBalance(getBalance(thisAccount) + amount); 
				//currentAccountList.get(i).addToListOfTransactions(new Transaction(thisAccount.getAcctNum(), LocalDateTime.now(), "Deposit via Serializer", amount));
				break;
			}
		}
		
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
			outStream.writeObject(currentAccountList); 
			return true; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean withdraw(Account thisAccount, double amount) {
		List<Account> currentAccountList = allAccounts();
		for(int i = 0; i < currentAccountList.size(); i++) {
			if(currentAccountList.get(i).getAcctNum() == thisAccount.getAcctNum()) {
				if(amount <= getBalance(thisAccount)) { 
					currentAccountList.get(i).setBalance(getBalance(thisAccount) - amount);
					//currentAccountList.get(i).addToListOfTransactions(new Transaction(thisAccount.getAcctNum(), LocalDateTime.now(), "Withdrawal via Serializer", amount));
				}
				else System.out.println("Sorry, you do not have sufficient funds to withdraw $" + amount);  
				break; 
			}
		}
		
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
			outStream.writeObject(currentAccountList);
			return true; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean transfer(Account thisAccount, Account other, double amount) {
		List<Account> currentAccountList = allAccounts();
		for(int i = 0; i < currentAccountList.size(); i++) {
			if(currentAccountList.get(i).getAcctNum() == thisAccount.getAcctNum()) {
				if(amount <= thisAccount.getBalance()) { 
					withdraw(thisAccount, amount);
					deposit(other, amount); 
					
				}
				else System.out.println("Sorry, you do not have sufficient funds to transfer $" + amount);  
				return false;  
			}
		}
		
		return true; 
	}

	@Override
	public boolean addOwner(Account thisAccount, User newUser) { 
		List<Account> currentAccountList = allAccounts();
		for(int i = 0; i < currentAccountList.size(); i++) {
			if(currentAccountList.get(i).getAcctNum() == thisAccount.getAcctNum()) {
				currentAccountList.get(i).setOwner(newUser); 
				break;
			}
			 
		}
		
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
			outStream.writeObject(currentAccountList); 
			return true; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public int getAcctNumber(Account thisAccount, User currentUser) {
		List<Account> currentAccountList = allAccounts();
		for(int i = 0; i < currentAccountList.size(); i++) {
			if(currentAccountList.get(i).getOwner().equals(thisAccount.getOwner())) {
				return currentAccountList.get(i).getAcctNum(); 
			}
			 
		}
		return 0;
	}

	@Override
	public User getOwner(Account thisAccount, User currentUser) {
		List<Account> currentAccountList = allAccounts();
		for(int i = 0; i < currentAccountList.size(); i++) {
			if(currentAccountList.get(i).getAcctNum() == thisAccount.getAcctNum()) {
				return currentAccountList.get(i).getOwner(); 
			}
			 
		}
		return null;
	}

	
	@Override
	public double getBalance(Account thisAccount) {
		List<Account> currentAccountList = allAccounts();
		for(int i = 0; i < currentAccountList.size(); i++) {
			if(currentAccountList.get(i).getAcctNum() == thisAccount.getAcctNum()) {
				return currentAccountList.get(i).getBalance(); 
			}
		 
		}
		return 0;
	}

//	@Override
//	public List<Transaction> viewTransactionHistory(Account thisAccount) {
//		List<Account> currentAccountList = allAccounts();
//		for(int i = 0; i < currentAccountList.size(); i++) {
//			if(currentAccountList.get(i).getAcctNum() == thisAccount.getAcctNum()) {
//				return currentAccountList.get(i).getTransactions(); 
//			}
//		 
//		}
//		return null;
//	}

}
