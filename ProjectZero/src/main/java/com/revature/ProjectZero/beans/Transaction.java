package com.revature.ProjectZero.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5069713161433777872L;
	private int accountNumber;
	private LocalDateTime timeOfTransaction;
	private String type;
	private double amount;
	
	
	public Transaction(int accountNumber, LocalDateTime timeOfTransaction, String type, double amount) {
		super();
		this.accountNumber = accountNumber;
		this.timeOfTransaction = LocalDateTime.now(); 
		this.type = type;
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", timeOfTransaction=" + timeOfTransaction + ", type="
				+ type + ", amount=" + amount + "]";
	}
	
	
	
}
