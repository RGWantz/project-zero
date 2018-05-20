package com.revature.ProjectZero.dao;//TODO Determine if there's a better location to save text file to. Note: All existing methods complete. 

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

public class UserSerializer implements UserDao {
	
	private final String FILE = "src/main/resources/UserInfo.txt";
	
	//It is helpful to have this as its own method because the others will call it. 
	//I don't anticipate having the option for the admin to look at this list. But making it happen should be easy. 
	@Override
	public List<User> summonUserList() {
		try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(FILE))) {
			return (List<User>) inStream.readObject(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<User>();
	}

	@Override
	public boolean addUser(String userName, String password) { //TODO what about a check to see if a certain userName is in use already? Does that go here or in menu page? 
		List<User> currentUserList  = summonUserList(); 
		if(currentUserList == null) currentUserList = new ArrayList<>(); 
		currentUserList.add(new User(userName, password)); 
		
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
			outStream.writeObject(currentUserList); 
			return true; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}


	
	@Override 
	public boolean changePassword(User currentUser, String newPass) { //This can only be accessed from within a User, so a user must already exist. 
		List<User> currentUserList  = summonUserList(); 
		for(int i = 0; i < currentUserList.size(); i++) {
			if(currentUserList.get(i).getUserName() == currentUser.getUserName()) {
				currentUserList.get(i).setPassword(newPass); 
				break; 
			}
		}
			
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
			outStream.writeObject(currentUserList); 
			return true; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override 
	public boolean addToAccountList(User currentUser, Account addlAccount) {
		List<User> currentUserList  = summonUserList(); 
		for(int i = 0; i < currentUserList.size(); i++) {
			if(currentUserList.get(i).getUserName() == currentUser.getUserName()) {
				currentUserList.get(i).addToMyAccounts(addlAccount); 
				break; 
			}
		}
		
		try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
			outStream.writeObject(currentUserList); 
			return true; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	

}
