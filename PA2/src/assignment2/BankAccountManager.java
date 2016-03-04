package assignment2;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import exceptions.AccountTypeException;
import exceptions.AmountException;
import exceptions.CustomerIDException;
import exceptions.InsufficientFundsException;
import exceptions.TransactionTypeException;

/**
 * Bank Account Manager
 * Test Driver to Bank Account Classes
 * 1) Create Bank Accounts List
 * 2) Process Transactions
 * 3) Return Account Statement
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953 
 * @author Ronald Macmaster
 * @version 1.01 2/12/2016
 */

public class BankAccountManager 
{
	public static void main(String[] args){
	// 1) Create Bank Accounts List
		Set<Integer> customerSet = new HashSet<Integer>();
		Map<Integer, Customer> customerMap = new HashMap<Integer, Customer>();
		
	// 2) Process Transactions
		Transaction transaction; 
		int resume = 0; // 1 = no, 2 = cancel
		while(resume == 0){
			try{ // prompt teller
				String command = JOptionPane.showInputDialog("Please enter a bank account transation: ");
				transaction = new Transaction(command);
				// Log transaction
				System.out.println(transaction);
			}catch(CustomerIDException e){
				System.err.println("Error: Invalid Customer ID!");
				continue;
			}catch(TransactionTypeException e){
				System.err.println("Error: Invalid Transaction Type!");
				continue;
			}catch(AmountException e){
				System.err.println("Error: Invalid Transaction Amount!");
				continue;
			}catch(AccountTypeException e){
				System.err.println("Error: Invalid Account Type!");
				continue;
			}catch(Exception e){
				System.err.println("Error: Invalid Transaction Format!");
				continue;
			}
			
			// Check for new customer
			int customerID = transaction.getCustomerID();
			if(!customerMap.containsKey(customerID)){
				customerSet.add(customerID);
				customerMap.put(customerID, new Customer(customerID));
			}
			
			// Perform transaction on user account
			Customer user = customerMap.get(customerID);
			
			try{ 
				ServiceCustomerAccount(user, transaction);
			}catch(InsufficientFundsException ife){
				System.err.println(ife.msg);
			}
			
			resume = JOptionPane.showConfirmDialog(null, "Would you like to Continue?");
		}
		
	// 3) Return Account Statement
		System.out.println("\n"); // start new output section
		char[] accountCodes = {'C', 'S', 'L', 'A'};
		for(Integer personID : customerSet){
			Customer person = customerMap.get(personID);
			System.out.println("Statement for Customer " + personID + ": ");
			System.out.println("\tName: " + person.getCustomerName());
			System.out.println("\tAddress: " + person.getCustomerAddress());
			for(char code : accountCodes){	// print account balances
				double balance = person.getAccountBalance(code);
				String accountString = Transaction.getAccountString(code);
				System.out.println("\t" + accountString + ":\t" + balance);
			}
			System.out.print("\n");
		}
		
		System.out.print("Thank you for using Macmaster Banking Services.");
	}
	
	
	/**	Service Customer Account
	 * 	perform a specified transaction on a customer's account
	 * 
	 * @param user					user to perform transaction upon
	 * @param transaction		action to perform
	 * @throws InsufficientFundsException
	 */
	public static void ServiceCustomerAccount(Customer user, Transaction transaction) throws InsufficientFundsException{
		//transaction fields
		char action = transaction.getTransactionType();
		char acctype1 = transaction.getAccountType1();
		char acctype2 = transaction.getAccountType2();
		double amount = transaction.getTransactionAmount();
				
		// balance log format string
		String balanceString = Transaction.getAccountString(acctype1) + " balance: "; 
		
		// decode transaction code
		switch(action){
			case 'D':	//deposit
				user.depositFunds(amount, acctype1);
				balanceString += user.getAccountBalance(acctype1);
				break;
			case 'W':	//withdraw
				user.withdrawFunds(amount, acctype1);
				balanceString += user.getAccountBalance(acctype1);
				break;
			case 'T':	//transfer
				user.transferFunds(amount, acctype1, acctype2);
				//include 2nd account balance
				balanceString += user.getAccountBalance(acctype1) + "\t";
				balanceString += Transaction.getAccountString(acctype2) + " balance: ";
				balanceString += user.getAccountBalance(acctype2);
				break;
			case 'I':	//interest
				user.addAccountInterest(acctype1);
				balanceString += user.getAccountBalance(acctype1);
				break;
			case 'G':	//get balance
				balanceString += user.getAccountBalance(acctype1);
				break;
		}
		
		// log new account balances
		System.out.println(balanceString);
	}

}
