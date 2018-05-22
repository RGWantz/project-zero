package com.revature.ProjectZero.dao; 

import java.util.List;

import com.revature.ProjectZero.beans.Account;
import com.revature.ProjectZero.beans.User;



public interface UserDao {
	
	List<User> summonUserList(); 
	
	boolean addUser(User newOne); 
	
	boolean verifyUserLogin(int acctNum, String uName, String pass);
	
	boolean changePassword(User currentUser, String newPass);  
	
	boolean addToAccountList(User currentUser, Account addlAccount); 
	
	User generateUser(String uName, String pass);
}
