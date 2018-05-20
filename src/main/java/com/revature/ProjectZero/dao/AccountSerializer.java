package com.revature.ProjectZero.dao; //TODO Determine if there's a better location to save text file to. Note: All existing methods complete for initial effort. 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.ProjectZero.beans.Account;
import com.revature.ProjectZero.beans.User;

public class AccountSerializer implements AccountDao {
	private final String FILE = "src/main/resources/AllAccounts.txt";
	
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
	public boolean newAccount() {
		List<Account> currentAccountList = allAccounts();
		if(currentAccountList == null) {
			currentAccountList = new ArrayList<>(); 
		}
		currentAccountList.add(new Account());
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
			return true; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deposit(Account thisAccount, double amount) {
		List<Account> currentAccountList = allAccounts();
		for(int i = 0; i < currentAccountList.size(); i++) {
			if(currentAccountList.get(i).getAcctNum() == thisAccount.getAcctNum()) {
				currentAccountList.get(i).setBalance(thisAccount.getBalance() + amount); 
				break;
			}
			 
		}
		
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
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
				if(amount <= thisAccount.getBalance()) { 
					currentAccountList.get(i).setBalance(thisAccount.getBalance() - amount);
				}
				else System.out.println("Sorry, you do not have sufficient funds to withdraw $" + amount);  
				break; 
			}
		}
		
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
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
					thisAccount.withdraw(amount); //setBalance(thisAccount.getBalance() - amount);
					other.deposit(amount); 
				}
				else System.out.println("Sorry, you do not have sufficient funds to transfer $" + amount);  
				break; 
			}
		}
		//I believe this is unnecessary with the deposit and withdraw as above. 
//		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
//			return true; 
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		return true; //false if other part is reinstated 
	}

	@Override
	public boolean addOwner(Account thisAccount, User newUser) {
		// TODO Saved for eventually 
		return false;
	}

	

	

}
