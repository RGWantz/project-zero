package com.revature.ProjectZero.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.ProjectZero.beans.Admin;
import com.revature.ProjectZero.dao.AccountDao;
import com.revature.ProjectZero.dao.AccountSerializer;
import com.revature.ProjectZero.dao.UserDao;
import com.revature.ProjectZero.dao.UserSerializer;

public class StartupScreen implements BankScreen {

	private UserDao userDao = new UserSerializer(); 
	private AccountDao acctDao = new AccountSerializer();
	private Scanner scan  = new Scanner(System.in); 
	private Logger log = Logger.getRootLogger();
	
	@Override
	public BankScreen run() {
		try {
			//userDao.addUser(Admin.getAdmin()); 
			System.out.println("Welcome to the banking application. ");
			log.info("Application initiated");
			System.out.println("Enter 1 to Log in to your account. ");
			System.out.println("Enter 2 to create a new User Profile. "); 
			System.out.println("Enter 3 to log in as the Admin. "); 
			int go = Integer.parseInt(scan.nextLine());
			
			switch (go) {
			case 1:
				log.info("Login Attempt made");
				return new LoginScreen(); 
			case 2:
				log.info("New User Account being created");
				return new NewUserScreen();
			case 3: 
				log.info("Admin Login Attempt made");
				return new AdminScreen(); 
			default:
				System.out.println("Invalid Input. Try Again. ");
				break;
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid Input. Try Again. ");
			return this; //This says, replay the screen. 
		}
		return this;
	}

}
