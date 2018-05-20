package com.revature.ProjectZero.dao; //TODO Do I need other methods? 

import java.util.List;

import com.revature.ProjectZero.beans.Account;
import com.revature.ProjectZero.beans.User;



public interface UserDao {
	
	List<User> summonUserList(); 
	
	boolean addUser(String userName, String password); 
	
	boolean changePassword(User currentUser, String newPass); //determine if I actually want it to return the String so I can print it. 
	
	boolean addToAccountList(User currentUser, Account addlAccount); 
	
	
}
