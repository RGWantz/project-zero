package com.revature.ProjectZero.screens;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.ProjectZero.beans.Admin;

public class AdminScreen implements BankScreen {
	private Scanner scan = new Scanner(System.in); 
	private Logger log = Logger.getRootLogger(); 
	
	@Override
	public BankScreen run() {
		System.out.println("Log in to Administrator's Account ");
		System.out.println("Please enter admin username: ");
		String uName = scan.nextLine();
		System.out.println("Please enter admin password: "); 
		String pass = scan.nextLine();
		
		if((uName.equals(Admin.getAdmin().getUserName())) && (pass.equals(Admin.getAdmin().getPassword()))) {
			log.info("Admin account accessed");
			System.out.println("Welcome to the Administrator's Secret Page");
			System.out.println("Enter 1 to view the activity log. ");
			System.out.println("Enter 0 to log out. ");
			int key = Integer.parseInt(scan.nextLine());
			switch (key) {
			case 1:
				try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
					String s; 
					do {
						s = br.readLine(); 
						System.out.println(s);
					} while (s != null); 
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				break;
			case 0:
				return new StartupScreen(); 
			default:
				System.out.println("Invalid Input. Try Again. ");
				break;
			}
		}
		else {
			System.out.println("Sorry, those entries do not match. Please sign in as a User. ");
			log.info("Admin login failed");
			return new StartupScreen(); 
		}
		return new AdminScreen();
	}

}
