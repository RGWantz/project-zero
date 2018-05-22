package com.revature.ProjectZero.screens;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.ProjectZero.dao.AccountDao;
import com.revature.ProjectZero.dao.AccountSerializer;
import com.revature.ProjectZero.dao.UserDao;
import com.revature.ProjectZero.dao.UserSerializer;

public class LoginScreen implements BankScreen {

	private UserDao userDao = new UserSerializer(); 
	private AccountDao acctDao = new AccountSerializer();
	private Scanner scan  = new Scanner(System.in); 
	private Logger log = Logger.getRootLogger(); 
	
	
	@Override
	public BankScreen run() {
//		try {
			System.out.println("Log in to your Account ");
			System.out.println("Please enter your username: ");
			String uName = scan.nextLine();
			System.out.println("Please enter your password: "); 
			String pass = scan.nextLine();
			System.out.println("Please enter your account number: "); 
			int acctNum = Integer.parseInt(scan.nextLine());
			
						
			if(userDao.verifyUserLogin(acctNum, uName, pass)) {
				log.info("Login Attempt successful to Account " + acctNum);
				return new AccountHomeScreen(acctDao.generateAccount(acctNum), userDao.generateUser(uName, pass));  
			}
			else {
				System.out.println("Sorry, those entries do not match. Please re-enter them. ");
				log.info("Login Attempt Failed");
				return new LoginScreen(); 
			}
			
	}

}
