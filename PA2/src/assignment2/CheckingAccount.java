package assignment2;

import exceptions.InsufficientFundsException;

/**
 * Checking Account
 * Bank Account with no minimum and no interest
 * OverDraw functionality
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953 
 * @author Ronald Macmaster
 * @version 1.01 2/12/2016
 */

class CheckingAccount extends BankAccount
{
  // fine levied for overdraws
  private static final double overdrawFee = 20.0;
	
 /**
  * Checking Account Constructor with names
  * @param acct   			account number for Bank Account
  * @param owner	 			owner's name
  * @param initBalance 		initial balance in the account  */
 public CheckingAccount(int acct, String owner, double initBalance){
	 super(acct, owner, initBalance);
 }
 
 /**
  * Checking Account Constructor with only initial balance
  * @param initBalance 		initial balance in the account  */
 public CheckingAccount(double initBalance){
	 super(initBalance);
 }

 /** overDraw
  * 
  * If the account is overDrawn,
  * attempt to transfer funds from the savings account
  * pay a fine
  * 
  * @param savings  The source of savings funds
  * @param amount   Requested transaction amount								
 * @throws InsufficientFundsException */
 public void overDraw(SavingsAccount savings, double amount) throws InsufficientFundsException{  
	 double overdraw = amount-balance; // dollars overdrawn
	 if(overdraw + overdrawFee > savings.getBalance()){
		 throw new InsufficientFundsException("Error: Insufficient Savings Funds! (Checking Account OverDraw)");
	 }
	 else{ //transfer funds and pay a fine
		 System.err.println("Warning: Checking Account Over Drawn!");
		 savings.withdraw(overdraw);
		 savings.fine(overdrawFee);
		 this.deposit(overdraw);
	 }
 }
 
}