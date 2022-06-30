package com.revature.app.entity;

import java.util.*;

//Notes:
// This class is designed to add to a dictionary of users, where the username 
//   is the key.
// Each user gives access to it's data via setters and getters only. 
// Each user can access multiple accounts, especially bank admin users.
//    Users will be paired up with certain Accounts.

public class User 
{
	public static HashMap<String, User> userList = new HashMap<String, User>();

	private User newUser;

	private String userName;
	private String userPassword;
	private ArrayList<String> accountNumberList;
	
	// 0 is basic user, 1 is a bank employee, 2 is bank admin
	private int userType;

	public User()
	{
		// THIS NEEDS TO BE REMOVED. I NEED A SERVICE CLASS

	};
	
	public User(String userName, String userPassword, ArrayList<String> accountNumberList, int userType) 
	{
		this.userName = userName;
		this.userPassword = userPassword;
		this.accountNumberList = (ArrayList<String>) accountNumberList.clone();
		this.userType = userType;
	}


	public int createUser(String userName, String userPassword, ArrayList<String> accountNumberList, int userType) 
	{

		// I need some sort of try and catch block here
		if (userList.get(userName) != null || accountNumberList.isEmpty() || userType < 0 || 2 < userType ) 
		{
			System.out.println("User: One or more parameter is invalid.");
			return 0;
		} 
		else 
		{
			newUser = new User(userName, userPassword, accountNumberList, userType);
			// This is inefficient. I'm storing accountNum twice.
			this.userList.put(userName, newUser);
			return 1;
		}
	}
	
	// Did I do this right?
	public void printAllUsers()
	{
		userList.forEach((Integer, User) -> System.out.println(User.toString() + "\n========"));
	}
	
	public String toString()
	{
		return ">UserName: " + this.userName
				+ "Password: " + this.userPassword
				+ "\nHolder List: " + this.accountNumberList.toString();
	}
	
	public void printPairedAccountNumbers()
	{
		for(int i = 0; i < accountNumberList.size(); i++)
		{
			System.out.println(i +": " + accountNumberList.get(i));
		}
	}
	
	void printUser(int userName)
	{
		System.out.println(userList.get(userName).toString());
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public ArrayList<String> getAccountNumberList() {
		return this.accountNumberList;
	}

	public void setAccountNumberList(ArrayList<String> accountNumberList) 
	{
		this.accountNumberList = (ArrayList)accountNumberList.clone();
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	

}
