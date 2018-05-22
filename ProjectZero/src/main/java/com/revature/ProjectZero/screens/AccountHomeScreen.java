package com.revature.ProjectZero.screens;

//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.ProjectZero.beans.Account;
//import com.revature.ProjectZero.beans.Transaction;
import com.revature.ProjectZero.beans.User;
import com.revature.ProjectZero.dao.AccountDao;
import com.revature.ProjectZero.dao.AccountSerializer;
import com.revature.ProjectZero.dao.UserDao;
import com.revature.ProjectZero.dao.UserSerializer;

public class AccountHomeScreen implements BankScreen {
	private Account thisAcct;
	private User thisUser; 
	private AccountDao acctDao = new AccountSerializer(); 
	private UserDao userDao = new UserSerializer();
	private Scanner scan = new Scanner(System.in);  
	private Logger log = Logger.getRootLogger();
	
	
	
	public AccountHomeScreen(Account thisAcct, User thisUser) {
		super();
		this.thisAcct = thisAcct;
		this.thisUser = thisUser;
	}



	@Override
	public BankScreen run() {
		System.out.println("Welcome to the account homepage! ");
		System.out.println("Your account number is " + thisAcct.getAcctNum());
		System.out.println("Your current balance is: $" + acctDao.getBalance(thisAcct));
		
		System.out.println("  ");
		System.out.println("What action would you like to perform? ");
		
		System.out.println("Enter 1 to deposit into your account. ");
		System.out.println("Enter 2 to withdraw from your account. ");
		System.out.println("Enter 3 to transfer money from your account to another account. ");
		System.out.println("Enter 4 to change your account password. ");
		//System.out.println("Enter 5 to add another account to your user profile. ");
		//System.out.println("Enter 6 to view your account transaction history.");
		System.out.println("Enter 0 to sign out of your account. ");
		
		try {
			
			int go = Integer.parseInt(scan.nextLine());
			
			switch (go) {
			case 1:
				System.out.println("How much would you like to deposit? ");
				System.out.print("$");
				double amount = Double.parseDouble(scan.nextLine());  
				if(acctDao.deposit(thisAcct, amount)) {
					System.out.println("Deposit complete. ");
					log.info("Deposit made to Account " + thisAcct.getAcctNum());
//					List<Transaction> updatedTList = thisAcct.getTransactions();
//					updatedTList.add(new Transaction(thisAcct.getAcctNum(), LocalDateTime.now(), "Deposit", amount));
//					thisAcct.setTransactions(updatedTList);
				} else {
					log.info("Deposit to Account " + thisAcct.getAcctNum() + " failed");
					System.out.println("Deposit was not possible. Please try again. ");
				}
				
				
				break; 
			case 2:
				System.out.println("How much would you like to withdraw? ");
				System.out.print("$");
				double amount2 = Double.parseDouble(scan.nextLine());  
				
				if(acctDao.withdraw(thisAcct, amount2)) {
					log.info("Withdrawal from Account " + thisAcct.getAcctNum());
					System.out.println("Withdrawal complete. ");
//					List<Transaction> updatedTList = thisAcct.getTransactions();
//					updatedTList.add(new Transaction(thisAcct.getAcctNum(), LocalDateTime.now(), "Withdrawal", amount2));
//					thisAcct.setTransactions(updatedTList);
				} else {
					log.info("Withdrawal from Account " + thisAcct.getAcctNum() + " failed");
					System.out.println("Withdrawal was not possible. Please try again. ");
				}
				break;
			case 3:
				System.out.println("How much would you like to transfer? ");
				System.out.print("$");
				double amount3 = Double.parseDouble(scan.nextLine());  
				System.out.println("Enter the number of the account to which you want the money to go: ");
				int other = Integer.parseInt(scan.nextLine());
				
				acctDao.transfer(thisAcct, acctDao.generateAccount(other), amount3);
					System.out.println("Transfer successfully sent! ");
					log.info("Transfer from Account " + thisAcct.getAcctNum() + " to Account " + other);
//					List<Transaction> updatedTList = thisAcct.getTransactions();
//					updatedTList.add(new Transaction(thisAcct.getAcctNum(), LocalDateTime.now(), "Transfer Out", amount3));
//					thisAcct.setTransactions(updatedTList);
//					
//					List<Transaction> updatedOtherList = acctDao.generateAccount(other).getTransactions();
//					updatedOtherList.add(new Transaction(other, LocalDateTime.now(), "Transfer In", amount3));
//					thisAcct.setTransactions(updatedOtherList);
				//} else System.out.println("Transfer was unable to be sent. Please try again. ");
				break; 
			case 4:
				System.out.println("Enter your new password: ");
				String newPass = scan.nextLine();
				if(userDao.changePassword(thisUser, newPass)) {
					log.info("Password to Account " + thisAcct.getAcctNum() + " changed");
					System.out.println("Password successfully changed! ");
				} else System.out.println("Password was unable to be changed. Please try again. ");
				break;
//			case 5:
//				System.out.println("Are you sure you want to add another account? ");
//				System.out.println("Enter 1 for Yes and 0 for No.");
//				int keepgo = Integer.parseInt(scan.nextLine());
//				
//				switch (keepgo) {
//				case 0:
//					break;
//				case 1:
//					Account newOne = new Account(0, thisUser);
//					if (acctDao.newAccount(newOne) && (userDao.addToAccountList(thisUser, newOne))) {
//						System.out.println("New Account successfully created! ");
//						System.out.println("Your new Account number is: " + acctDao.getAcctNumber(newOne, thisUser));
//					} else System.out.println("Sorry, new account unable to be created. Please try again."); 
//					break; 
//				default:
//					System.out.println("Invalid Input. Try Again. ");
//					break;
//				}
//			case 6:
//				System.out.println("Here is the account history for account " + thisAcct.getAcctNum());
//				for(int i = 0; i < acctDao.viewTransactionHistory(thisAcct).size(); i++) {
//				System.out.println(acctDao.viewTransactionHistory(thisAcct).get(i).toString());
//				}
//				break; 
			case 0: 
				System.out.println("You are now signed out of your account. ");
				log.info("Log out from Account " + thisAcct.getAcctNum());
				return new StartupScreen(); 
			default:
				System.out.println("Invalid Input. Try Again. ");
				break;
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid Input. Try Again. ");
			return this; 
		}
		
		return this;
	}

}
