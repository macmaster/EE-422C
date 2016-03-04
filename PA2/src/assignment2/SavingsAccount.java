package assignment2;

import exceptions.InsufficientFundsException;

/**
 * Savings Account
 * Pays fines and supports interest
 * Able to bail out a checking account if 
 * funds are sufficient.
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953 
 * @author Ronald Macmaster
 * @version 1.01 2/12/2016
 */

class SavingsAccount extends BankAccount
{
	//current interest rate (multiply by 100 for %)
	private static final double interestRate = 0.04; 
	//minimum balance at which interest takes effect
	private static final double interestMinimum = 1000.00; 
	
 /**
  * Savings Account Constructor with names
  * @param acct   			account number for Bank Account
  * @param owner	 			owner's name
  * @param initBalance 		initial balance in the account  */
 public SavingsAccount(int acct, String owner, double initBalance){
	 super(acct, owner, initBalance);
 }
 
 /**
  * Savings Account Constructor with only initial balance
  * @param initBalance 		initial balance in the account  */
 public SavingsAccount(double initBalance){
	 super(initBalance);
 }

 /** addInterest
  * 
  * Reward the Savings account with granted interest
  * Calculate the interest off of current interest rate							*/
 public void addInterest(){  
	 if(balance >= interestMinimum){
		 double interest = balance;
		 interest *= interestRate;
		 balance += interest;
	 }
 }
 
 /** fine 
  * Pay a hefty fine							
 * @throws InsufficientFundsException */
 public void fine(double fineAmount) throws InsufficientFundsException{  
	 if(balance >= fineAmount){
		 balance -= fineAmount;
	 }
	 else{
		 throw new InsufficientFundsException("Error: Insufficient Savings Funds!(Could not pay fine)");
	 }
 }
 
}