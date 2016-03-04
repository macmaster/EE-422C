package assignment2;

import exceptions.InsufficientFundsException;

/**
 * Customers own bank accounts, We treat them as Objects
 * in our Bank Account Manager
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953 
 * @author Ronald Macmaster
 * @version 1.01 2/12/2016
 */
class Customer
{   
  private int customerID; 				// The customer's unique ID
  private String customerName; 		// The name of the customer
  private String customerAddress; 	// the customer's address
  
  private CheckingAccount checkingAccount; //Personal Checking Account
  private SavingsAccount savingsAccount;   //Primary Savings Account
  private SavingsAccount studentAccount;   //Student Loan Savings Account
  private SavingsAccount autoAccount;      //Automobile Loan Savings Account
   
 /**
  * Default Customer Constructor
  * @param customerID    Unique Customer ID  */
 public Customer(int customerID){
	 this.customerName = "Name";
	 this.customerAddress = "Address";
    this.customerID = customerID;
    // initialize bank accounts
    checkingAccount = new CheckingAccount(customerID, customerName, 0);
    savingsAccount = new SavingsAccount(customerID, customerName, 0);
    studentAccount = new SavingsAccount(customerID, customerName, 0);
    autoAccount = new SavingsAccount(customerID, customerName, 0);
 }
 
 /**
  * Customer Constructor with names
  * @param customerID    		Unique Customer ID  
  * @param customerName	 		The Customer's Name
  * @param customerAddress 	The Customer's Address*/
 public Customer(int customerID, String customerName, String customerAddress){
	 this(customerID);
	 this.customerName = customerName;
	 this.customerAddress = customerAddress;
 }
 
// Bank Account Access Gateways
 /** Balance: Interface for bank account balance requests
  * 		
  * @param accountCode	code to identify bank account			
  * @return balance		balance in account requested 					*/	
 public double getAccountBalance(char accountCode){
	 BankAccount account = getBankAccount(accountCode);
	 double balance = account.getBalance();
	 return balance;
 }
 
 /** Deposit: Interface for bank account deposits
  * 
  * @param amount			amount to deposit into account		
  * @param accountCode	code to identify bank account				*/	
 public void depositFunds(double amount, char accountCode){
	 BankAccount account = getBankAccount(accountCode);
	 account.deposit(amount);
 }
 
 /** Withdraw: Interface for bank account withdrawals
  * 
  * @param amount			amount to withdraw from account		
  * @param accountCode	code to identify bank account			
  * @throws InsufficientFundsException 							*/	
 public void withdrawFunds(double amount, char accountCode) throws InsufficientFundsException{
	 BankAccount account = getBankAccount(accountCode);
	 double balance = account.getBalance();
	 
	 if(amount > balance){	//check valid account balance
		 if(account instanceof CheckingAccount){ //overDraw
		 	((CheckingAccount)account).overDraw(savingsAccount, amount);						
		 }
		 else{
		  	throw new InsufficientFundsException("Error: Insufficient Funds for Withdrawl!");
		 }
	 }
	 
	 //withdraw funds
	 account.withdraw(amount);
 }
 
 /** Transfer: Interface for bank account transfers
  * 
  * @param amount			amount to withdraw from account		
  * @param accountCode	code to identify bank account			
  * @throws InsufficientFundsException 							*/	
 public void transferFunds(double amount, char accountCode1, char accountCode2) throws InsufficientFundsException{
	 BankAccount source = getBankAccount(accountCode1);
	 BankAccount destination = getBankAccount(accountCode2);
	 double balance = source.getBalance();

	 if(amount > balance){	//check valid account balance
	 	 if(source instanceof CheckingAccount){ //overDraw
			 ((CheckingAccount)source).overDraw(savingsAccount, amount);						
		 }
		 else{
		    throw new InsufficientFundsException("Error: Insufficient Funds for Withdrawl!");
		 }
	 }
	
	 //transfer funds
	 source.withdraw(amount);
	 destination.deposit(amount);
 }
 
 /** Interest: Interface for bank account interest
  * 		
  * @param accountCode	code to identify bank account			*/	
 public void addAccountInterest(char accountCode){
	 BankAccount account = getBankAccount(accountCode);
	 if(account instanceof CheckingAccount){
	  	 System.err.println("Error: Checking Accounts don't support interest!");
	 }
	 else{
		 ((SavingsAccount)account).addInterest();
	 }
 }

//Get Methods
 /**
  * @return The customerID	*/
 public int getCustomerID(){
     return customerID;
 }
    
 /**
  * @return The Customer Address	*/
 public String getCustomerAddress(){
     return customerAddress;
 }

 /**
  * @return The Customer Name	*/
 public String getCustomerName(){
     return customerName;
 }
 
 /**
  * @param accountCode	unique account type identifier
  * @return The Customer's Bank Account (null if accountCode invalid)	*/
 private BankAccount getBankAccount(char accountCode){
	switch(accountCode){
	  case 'C':
		  return checkingAccount;
	  case 'S':
		  return savingsAccount;
	  case 'A':
		  return autoAccount;
	  case 'L':
		  return studentAccount;
	  default:
		  return null; //invalid Account Code
	}
 }
 
//Set Methods
 /**
  * @param customerID The customerID	*/
 public void setCustomerID(int customerID){
     this.customerID = customerID;
 }
   
 /**
  * @param customerAddress The Customer Address	*/
 public void setCustomerAddress(String customerAddress){
	 this.customerAddress = customerAddress;
 }

 /**
  * @param customerName The Customer Name	*/
 public void setCustomerName(String customerName){
    this.customerName = customerName;
 }
 
}
