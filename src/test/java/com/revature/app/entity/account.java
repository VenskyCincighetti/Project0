package com.revature.app.entity;


import java.util.*;

//Notes:
//  This class is designed to add to a dictionary of accounts where the account number
//    is the key.
//  Each Account gives access to it's data via setters and getters only. 
//  Each Account can be accessed by multiple users. I'm thinking for all references to
//    this relationship should reflect this data to keep track of consistency? What
//    is more secure?

public class Account
{
	// collection of accounts sorted by their account number
    public static HashMap<String, Account> accountList = new HashMap<String, Account>();

	
	Account newProfile;

	
	private String accountNum;
	// ArrayList for multiple users. 
	private ArrayList<String> holderList;
	private int balance = 0;
	
	public Account()
	{
		//I NEED A SERVICE CLASS
	}
	
	public Account(String accountNum, ArrayList<String> holderList)
	{
		this.accountNum = accountNum;
		this.holderList = (ArrayList<String>)holderList.clone();
		this.balance = 0;
	}
	
	
	public Account createAccount( String accountNum, ArrayList<String> holderList)
	{
				
				// I need some sort of try and catch block here
				if(accountNum.equals(null) || holderList == null || accountList.containsKey(accountNum))
				{
					System.out.println("Account: One or more parameter is null.");
					return null;
				}
				else
				{
					newProfile = new Account(accountNum, holderList);
					// This is inefficient. I'm storing accountNum twice.
					accountList.put(accountNum, newProfile);
					return newProfile;
				}
	}

	public String createAccountNumber()
	{
		String candidate = "";
		Random rand = new Random();
		// if we ran out of number combinations
		if(accountList.size() == 43046721)
		{
			// then return null and communicate the issue.
			System.out.println("AccountList is full, expand account Number leniency.");
			return null;
		}
		// Otherwise, begin looking for a number that works.
		// this can definitely be more efficient, but can't be bothered.
		else 
		{
			do 
			{
				for(int i = 0; i < 8; i++)
				{
					candidate += rand.nextInt(0,10);
				}
			} while (accountList.containsKey(candidate));
			
			return candidate;
			
		}
	}
	
	// Did I do this right?
	public void printAllAccounts()
	{
		accountList.forEach((Integer, Account) -> System.out.println(Account.toString() + "\n_______"));
	}
	
	public String toString()
	{
		return ">Acc#: " + this.accountNum
				+ "\nHolder List: " + this.holderList.toString()
				+ "\nBalance: " + this.balance;
	}
	
	void printAccount(int accountNum)
	{
		System.out.println(accountList.get(accountNum).toString());
	}
	

	


	String getAccountNum() {
		return accountNum;
	}


	void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}


	public ArrayList<String> getHolderList() {
		return holderList;
	}


	void setHolderList(ArrayList<String> holderList) {
		this.holderList = (ArrayList)holderList.clone();
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}


	
}