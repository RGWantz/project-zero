package com.revature.ProjectZero.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.ProjectZero.beans.Account;
import com.revature.ProjectZero.beans.User;
import com.revature.ProjectZero.dao.AccountDao;
import com.revature.ProjectZero.dao.AccountSerializer;
import com.revature.ProjectZero.dao.UserDao;
import com.revature.ProjectZero.dao.UserSerializer;

public class NewUserScreen implements BankScreen {

	private UserDao userDao = new UserSerializer(); 
	private AccountDao acctDao = new AccountSerializer();
	private Scanner scan  = new Scanner(System.in);
	private Logger log = Logger.getRootLogger(); 
	
	
	@Override
	public BankScreen run() {
		
			System.out.println("Create a new Account ");
			System.out.println("Please enter a username: ");
			String uName = scan.nextLine();
			System.out.println("Please enter a password: "); 
			String pass = scan.nextLine();
			
			User myUser = new User(uName, pass); 
			userDao.addUser(myUser); 
			
			
			Account thisAcct = new Account(0, myUser); 
			acctDao.newAccount(thisAcct); 
			myUser.addToMyAccounts(thisAcct);
			
			System.out.println("Your new User Profile has been created. ");
			System.out.println("Your new Account number is: " + thisAcct.getAcctNum());
			System.out.println("Now you can log in to your new account. ");
			log.info("New Account created to number " + thisAcct.getAcctNum());
			
			return new LoginScreen(); 
			
	}

}
