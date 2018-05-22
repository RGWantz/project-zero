package com.revature.ProjectZero.dao;

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
	
	private final String FILE = "target/UserInfo.txt"; 
	
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
	public boolean addUser(User newOne) {  
		List<User> currentUserList  = summonUserList(); 
		if(currentUserList == null) currentUserList = new ArrayList<>(); 
		currentUserList.add(newOne); 
		
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
	public boolean verifyUserLogin(int acctNum, String uName, String pass) {
		List<User> currentUserList  = summonUserList(); 
		for(int i = 0; i < currentUserList.size(); i++) {
			if((currentUserList.get(i).getUserName().equals(uName)) && (currentUserList.get(i).getPassword().equals(pass))) return true; 
		}
		return false;
	}

	@Override
	public User generateUser(String uName, String pass) {
		List<User> currentUserList  = summonUserList(); 
		for(int i = 0; i < currentUserList.size(); i++) {
			if((currentUserList.get(i).getUserName().equals(uName)) && (currentUserList.get(i).getPassword().equals(pass))) {
				return currentUserList.get(i); 
			}
				
		} return null;
	}

	@Override 
	public boolean changePassword(User currentUser, String newPass) {  
		List<User> currentUserList  = summonUserList(); 
		for(int i = 0; i < currentUserList.size(); i++) {
			if(currentUserList.get(i).getUserName().equals(currentUser.getUserName())) {
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

	//Never accessed
	@Override 
	public boolean addToAccountList(User currentUser, Account addlAccount) {
		List<User> currentUserList  = summonUserList(); 
		for(int i = 0; i < currentUserList.size(); i++) {
			if(currentUserList.get(i).getUserName().equals(currentUser.getUserName())) {
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
