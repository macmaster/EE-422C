package assignment2;

import exceptions.AccountTypeException;
import exceptions.AmountException;
import exceptions.CustomerIDException;
import exceptions.TransactionTypeException;

/**
 * Transaction class for managing Transaction data
 * Input Format: Customer-ID#  transaction-type  [ amount]	account-type	[account-type2]
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953 
 * @author Ronald Macmaster
 * @version 1.01 2/12/2016
 */

class Transaction {
	private String[] fields; 		// transaction fields
	private int customerID; 		// Unique ID number for a customer
	private char action;    		// (D, W, I, T, G) = (deposit, withdraw, interest, transfer, get balance)
	private double amount;	 		// transaction amount
	private char accountType1; 	// source account type
	private char accountType2; 	// destination account type

	public Transaction(String transaction)
	throws CustomerIDException, AmountException, AccountTypeException, TransactionTypeException{
		// Parse Transaction String for ID, action, amount, and account types
		fields = transaction.trim().split("\\s+", 5);
		customerID = Integer.parseInt(fields[0]);
		action = fields[1].toUpperCase().charAt(0);
		
		if(customerID <= 0){throw new CustomerIDException();} //check illegal customer ID
		switch(action){// parse amount and account type
			case 'T':	//transfer
				accountType2 = fields[4].toUpperCase().charAt(0);
				if(!validAccountType(accountType2)){throw new AccountTypeException();} // check valid account type
			case 'D': 	//deposit
			case 'W':	//withdraw
				amount = Double.parseDouble(fields[2]);
				if(amount < 0){throw new AmountException();} // check negative amount
				accountType1 = fields[3].toUpperCase().charAt(0);
				if(!validAccountType(accountType1)){throw new AccountTypeException();} // check valid account type
				break;
			case 'I':	//interest
			case 'G':	//get balance
				accountType1 = fields[2].toUpperCase().charAt(0);
				if(!validAccountType(accountType1)){throw new AccountTypeException();} // check valid account type
				break;
			default:
				throw new TransactionTypeException(); // invalid transaction type!
		}

	}
	
	/**
	 * @return The Account String*/
	public static String getAccountString(char accountChar){
		switch(accountChar){ //decode account type
			case 'C': 
		 		return "checking account";
		 	case 'S': 
		 		return "savings account";
		 	case 'L': 
		 		return "student loan account";
		 	case 'A': 
		 		return "auto loan account";
		 	default:
		 		return "";
		}
	}
	
	// Get Methods
	/**
	 * @return The customerID	*/
	public int getCustomerID(){
	    return customerID;
	}
	    
	/**
	 * @return The Transaction Type	*/
	public char getTransactionType(){
	    return action;
	}
	
	/**
	 * @return The Transaction Amount	*/
	public double getTransactionAmount(){
		if(action != 'I' && action != 'G'){
			 return amount;
		}
		else{
			 return 0;
		}
	}
	    
	/**
	 * @return The First Account Type	*/
	public char getAccountType1(){
		return accountType1;
	}
	
	/**
	 * @return The Second Account Type	*/
	public char getAccountType2(){
		 if(action == 'T'){
			 return accountType2;
		 }
		 else{
			 return '\0';
		 }
	}
	
	/**
	 * @return The Transaction Log String*/
	public String toString(){
		 String tstring = "";
		 char taction = getTransactionType();
		 char taccount1 = getAccountType1();
		 char taccount2 = getAccountType2();
		 
		 // build transaction string.
		 tstring += "For Customer " + getCustomerID() + "'s ";
		 tstring += getAccountString(taccount1) + ", ";
		 
		 switch(taction){ //append transaction verb
		 	case 'D':
		 		tstring += "deposit " + getTransactionAmount() + " dollars.";
		 		break;
		 	case 'W':
		 		tstring += "withdraw " + getTransactionAmount() + " dollars.";
		 		break;
		 	case 'T':
		 		tstring += "transfer " + getTransactionAmount() + " dollars into their ";
		 		tstring += getAccountString(taccount2) + ".";
		 		break;
		 	case 'G':
		 		tstring += "the balance was returned.";
		 		break;
		 	case 'I':
		 		tstring += "the interest was computed and added to the balance.";
		 		break;
		 }
		 return tstring; 
	}
	
	/* Valid account types: 'C', 'S', 'A', 'L' */
	boolean validAccountType(char accountType){
		if(accountType == 'C' || accountType == 'S' || accountType == 'A' || accountType == 'L'){
			return true;
		}
		else{
			return false;
		}
	}
	
}
	