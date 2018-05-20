package com.revature.ProjectZero.beans; //TODO get owner into methods, verify that transfer is going to a real account, when fields are established, put in .equals and hashcode methods

import java.io.Serializable;

public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1531876802932559492L;
	
	private int acctNum;
	private double balance;
	private User owner; //This may eventually be an ArrayList of them. 
	
	public Account() {
		super();
		this.acctNum = generateAcctNum();
		this.balance = 0.0; 
		this.owner = null; 
	}

	public Account(double balance) { //currently unused 
		super();
		this.acctNum = generateAcctNum();
		this.balance = balance;
		this.owner = null; 
	}

	public int generateAcctNum() { //I would make this private, but from where I may need to access it that may not work.
		int a = (int) Math.random() * 10000; //for now, account numbers will be 5 digits. 
		
		return a;
		
	}
	
	public double deposit(double amount) { //if this is eventually outsourced, remember to make setBalance public
		
		return balance; 
	} 
	
	public double withdraw(double amount) { //if this is eventually outsourced, remember to make setBalance public
		
		return balance; 
	}
	
	public double transfer(Account other, double amount) {
		//make sure that the other account exists by checking the list of Accounts and returning a message if impossible...
		if(amount <= this.getBalance()) {
			setBalance(this.getBalance() - amount);
			other.setBalance(other.getBalance() + amount);
		}
		else System.out.println("Sorry, you do not have sufficient funds to transfer $" + amount); 
		return balance; 
	}

	
	public int getAcctNum() {
		return acctNum;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Account [Account Number=" + acctNum + ", balance= $" + balance + ", owner=" +"]";
	} 
	
	

}
