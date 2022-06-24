package com.revature.app;

import java.util.Scanner;

public class BankingApplication {

	public static void main(String[] args) {
		
		
		////////////////////////////
		// ENTER  DB setup PROCESS//
		////////////////////////////
		// Set up account database
		while(true)
		{
			break;
		}
		
		
		boolean finished = false;
		boolean userValid, credentialValid;
		
		Scanner scan = new Scanner(System.in);
		String username, password;
		
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
					// ENTER CREDENTIAL CHECK //
					////////////////////////////
					if(true)
					{
						credentialValid= true;
					}
					else 
					{
						System.out.println("The Username and Password is not a valid combination.");
					}
				}
				// Perform registery
				else 
				{
					////////////////////////////
					// ENTER REGISTERY PROCESS//
					////////////////////////////
				}
			}
			
			// We now have a valid user. Next is to operate based on what kind of account we are working with..
			if()
			

			
			
		}

	}

}
