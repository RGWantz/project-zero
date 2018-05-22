package com.revature.ProjectZero;

import java.util.Scanner;

import com.revature.ProjectZero.screens.BankScreen;
import com.revature.ProjectZero.screens.StartupScreen; 

public class BankLauncher {
	public static void main(String[] args) {
		BankScreen currentScreen = new StartupScreen();
			//currentScreen.run(); 
			while(true) {
				currentScreen = currentScreen.run(); 
			}		
	}
}
