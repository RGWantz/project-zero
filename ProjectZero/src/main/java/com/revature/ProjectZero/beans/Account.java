package com.revature.ProjectZero.beans; 

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1531876802932559492L;
	
	
	
	private int acctNum;
	private double balance;
	private User owner; 
	//private List<Transaction> transactions; 
	
	
	

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
	
	public Account(double balance, User owner) { //currently used 
		super();
		this.acctNum = generateAcctNum();
		this.balance = balance;
		this.owner = owner; 
		//this.transactions = new ArrayList<Transaction>(); 
	}

	private int generateAcctNum() { 
		int a = (int) (Math.random() * 100000); //for now, account numbers will be no more than 5 digits. 
		return a;
		
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
		//this.transactions.add(new Transaction(acctNum, LocalDateTime.now(), "Change of Account Owner", 0)); 
	}
	
	
	
//	public List<Transaction> getTransactions() {
//		return transactions;
//	}
//	
//
//	public void addToListOfTransactions(Transaction transaction) {
//		this.transactions.add(transaction);
//	}
//	
//	public void setTransactions(List<Transaction> transactions) {
//		this.transactions = transactions;
//	}
//	
	

	@Override
	public String toString() {
		return "Account [Account Number=" + acctNum + ", balance= $" + balance + ", owner=" +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acctNum;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (acctNum != other.acctNum)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	} 
	
	

}
