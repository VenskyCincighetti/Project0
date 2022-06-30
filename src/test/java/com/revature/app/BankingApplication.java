package com.revature.app;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.revature.app.entity.Account;
import com.revature.app.entity.User;

public class BankingApplication {

	public static boolean validateStringAsIntInString(String str)
	{
		
		// Lets only see the numbers.
		String[] inputFilter = str.split("[^0-9]");
		
		
		// Are there any numbers to begin with?
		if(0<inputFilter.length)
		{
			// do the found numbers represent the entirety of our input?
			if(inputFilter[0].equals(str))
			{
				return true;
			}
		}
		
		return false;

	}
	
	public static void main(String[] args) {
		
		// THIS NEEDS TO BE A SERVICE CLASS
		User userService = new User();
		Account accountService = new Account();
		Random rand = new Random();
		
		////////////////////////////
		// ENTER  DB setup PROCESS//
		////////////////////////////
		// Set up account database
		while(true)
		{
			break;
		}
		
		final String line = "============";
		
		User currentUser;
		Account currentAccount;
		Account transferAccount;
		
		boolean finished = false;
		boolean userValid, credentialValid, interfaceResolved, userInputValid, finishedWithAccount;
		
		Scanner scan = new Scanner(System.in);
		String username = "";
		String password = "";
		
		int uType;
		String accountNumber = "";
		String userInput;
		
		String[] inputValidation;
		
		// Begin receiving input.
		while(!finished)
		{
			// Begin checking for an approved user and password combination
			credentialValid = false;
			while(!credentialValid)
			{
				// ask for username
				System.out.println("Please enter a username or r to register: ");
				// receive username
				username = scan.next();
				
				// Check if the input is a username
				if(!username.equals("r"))
				{
					// ask for password
					System.out.println("\nPlease enter a password: ");
					// receive password
					password = scan.next();
					
					
					////////////////////////////
					///// CREDENTIAL CHECK  ////
					////////////////////////////
					
					// if username exists in user list
					if(User.userList.containsKey(username))
					{
						// and if the password for that username patches the provided one..
						if(User.userList.get(username).getUserPassword().equals(password))
						{	
							// then this is a valid username and password combination
							credentialValid= true;
						}
						else 
						{
							System.out.println("The Username and Password is not a valid combination.");
						}
					}
					else 
					{
						System.out.println("The Username and Password is not a valid combination.");
					}
					System.out.println(line);
				}
				// Perform registery
				else 
				{
					userValid = false;
					while(!userValid)
					{
						// ask for and receive a username request
						System.out.println("Please input a username you would like to use: ");
						username = scan.next();
						
						// If the username already exists
						if(User.userList.containsKey(username))
						{
							// then we will ask for another username.
							System.out.println("That username is taken.");
						}
						// Otherwise, the username doesn't exist,
						else 
						{
							// and we will continue registering the account.
							System.out.println("Please input a password you would like to use: ");
							password = scan.next();
							System.out.println("What kind of account?\n0:Basic\n1:Employee\n2:Admin");
							uType = scan.nextInt();
							
							///////////////////////////////////////////
							///// Resolve by adding service class  ////
							///////////////////////////////////////////
							
							//This is a list of users for the account constructor
							ArrayList<String> uList = new ArrayList<String>();
							uList.add(username);
							
							//This is a list of accounts for the user constructor
							ArrayList<String> aList= new ArrayList<String>();
							accountNumber = accountService.createAccountNumber();
							aList.add(accountNumber);
							
							// If the database is too full to get more accounts,
							if(accountNumber == null)
							{
								// then communicate it and exit registry.
								System.out.println("Database is full, try registering another time");
								break;
							}
							// Otherwise, we will create an account and user.
							else 
							{
								System.out.println(line);
								System.out.println("Confirmation: ");
								System.out.println("Username:" + username);
								System.out.println("Password:" + password);
								accountService.createAccount(accountNumber, uList);
								userService.createUser(username, password, aList, uType);
								userValid = true;
								System.out.println(line);

							}
							
							//I need to make an account Number;
							//for sake of simplicity
							
								
						}	
					}
				}
				// At this point, a username has been made. If a login happened,
				//   we would exit by changing the credential boolean.
			}
			
			
			currentUser = User.userList.get(username);
			uType = currentUser.getUserType();
			
			//Basic functions
			if(uType==0)
			{
				interfaceResolved = false;
				while(!interfaceResolved)
				{
					//  First, we identify if they want to add, create, or access an account
					//    for this user.
					System.out.println("0: Access Paired Account"
							+ "\n1: Apply for New Account"
							+ "\n2: Connect Existing Account"
							+ "\n3: Back");
					userInput = scan.next();
					System.out.println(line);

					// Have user access account by account number:
					if(userInput.equals("0"))
					{
						userInputValid = false;
						while (!userInputValid)
						{
							System.out.println("Please Choose an Account:");
							currentUser.printPairedAccountNumbers();
							
							userInput = scan.next();
							
							
							
							if(validateStringAsIntInString(userInput))
							{		
								// is the input within range?
								if(0 <= Integer.valueOf(userInput) && Integer.valueOf(userInput)<currentUser.getAccountNumberList().size())
								{
									userInputValid = true;
								}
							}
							

							
							if(!userInputValid)
							{
								System.out.println("Invalid Input, Please Choose From The List.");
							}
							System.out.println(line);

						}
						
						// We now have a valid index to pull the account from.
						//    It is in range and is an int that shouldn't cause any issues.
						currentAccount = accountService.accountList.get(currentUser.getAccountNumberList().get(Integer.valueOf(userInput)));
						
						System.out.println("Selected Account:\n" + currentAccount.toString());
						System.out.println(line);
						
						finishedWithAccount = false;
						while(!finishedWithAccount)
						{
							System.out.println("What would you like to do?\nBalance: " + currentAccount.getBalance());
							System.out.println("0: Withdraw \n"
											 + "1: Deposit \n"
											 + "2: Transfer\n"
											 + "3: Back");
							
							userInput = scan.next();
							
							// if we are withdrawing
							if(userInput.equals("0"))
							{
								System.out.println("Please enter money to Withdraw");
								userInput = scan.next();
								
								// Is a valid number
								if(validateStringAsIntInString(userInput))
								{
									if(0 < Integer.valueOf(userInput) && Integer.valueOf(userInput) <= currentAccount.getBalance()) 
									{
										currentAccount.setBalance(currentAccount.getBalance() - Integer.valueOf(userInput));
									}
									else 
									{
										System.out.println("Invalid Withrdawl Amount.");
									}
								}
								else 
								{	
										System.out.println("Invalid Withdrawl Input.")
		
								}
								
								
							}
							else if(userInput.equals("1"))
							{
								System.out.println("Please Enter Money to Deposit");
								userInput = scan.next();
								
								// Is a valid number
								if(validateStringAsIntInString(userInput))
								{
									if(0 < Integer.valueOf(userInput) && Integer.valueOf(userInput) <= Integer.MAX_VALUE) 
									{
										currentAccount.setBalance(currentAccount.getBalance() + Integer.valueOf(userInput));
									}
								}
							}
							else if(userInput.equals("2"))
							{
								System.out.println("Please Enter an Account's Account Number to Transfer to:");
								userInput = scan.next();
								
								// Is a valid number
								if(accountService.accountList.containsKey(userInput))
								{
									transferAccount = accountService.accountList.get(userInput);
									System.out.println("Enter an ammount to transfer");
									userInput = scan.next();
									if(validateStringAsIntInString(userInput))
									{
										if(0 < Integer.valueOf(userInput) && Integer.valueOf(userInput) <= currentAccount.getBalance())
										{
											transferAccount.setBalance(transferAccount.getBalance() + Integer.valueOf(userInput));
											currentAccount.setBalance(currentAccount.getBalance() - Integer.valueOf(userInput));
										}
									}
								}
							}
							else if(userInput.equals("3"))
							{
								finishedWithAccount = true;
							}
							else 
							{
								System.out.println("Please enter a valid option.");
							}
							System.out.println("Current balance: " + currentAccount.getBalance());
							System.out.println(line);
						}
					}
					// If they want to create a new account:
					else if(userInput.equals("1"))
					{
						
					}
					// If they want to connect an existing account:
					else if(userInput.equals("2"))
					{
						
					}
					else if(userInput.equals("3"))
					{
						interfaceResolved = true;
					}
					else 
					{
						System.out.println("Invalid Input");
					}
				}

				
			}
			//Employee functions
			else if(uType==1)
			{
				
			}
			//Admin functions
			else if(uType==2)
			{
				
			}
			//Invalid User
			else 
			{
				System.out.println("Invalid User-Type.");
			}
			
		}

	}

}
