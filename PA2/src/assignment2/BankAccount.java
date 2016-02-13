package assignment2;

/**
 * Model for general bank account object.  The purpose is to record money,
 * and allow for various financial transactions to be performed over the
 * life of a specific bank account.
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953 
 * @author Ronald Macmaster
 * @version 1.01 2/12/2016
 */
class BankAccount 
{   
  protected int accountNumber; // A unique number that identifies the account
  //protected Customer accountOwner // The Customer who owns the account
  protected String ownersName; // The name of the person that this account belongs to
  protected double balance; // the current value (in dollars) of the money in this account
   
 /**
  * Create an account with an initial balance.
  * @param initialBalance     The initial balance of the account  */
 public BankAccount(double initialBalance)
 {
     balance = initialBalance;
 }
    
 /**
  * Create an account with initial parameters.
  * @param acct               The account number
  * @param owner              The owner of the account
  * @param initBalance        The initial balance of the account   */
 public BankAccount(int acct, String owner, double initBalance)
 {
     accountNumber = acct;
     ownersName = owner;
     balance = initBalance; 
 }
    
 // Balance Changing Methods
 /**
  * Updates the current balance by adding in a given amount.
  * Post condition: the new balance is increased by the amount.
  * @param amount                The amount to add					*/
 public void deposit(double amount) 
 {  
    balance = balance + amount; 
 }
    
 /**
  * Update the current balance by subtracting the given amount.
  * Precondition: the current balance must have at least the amount in it.
  * Postcondition: the new balance is decreased by the given amount.
  * @param amount  The amount to subtract									*/
 public void withdraw(double amount) 
 {  
     if (balance >=  amount)
         balance = balance - amount; 
 }

    
 // Get Methods
 /**
  * @return The available balance.	*/
 public double getBalance( )
 {
     return balance;
 }
    
 /**
  * @return The account number.		*/
 public int getAccountNumber( )
 {
     return accountNumber;
 }
    
 /**
  * @return The owner's name.			*/
 //public Customer getOwner( )
 public String getOwner( )
 {
     return ownersName;
 }

    
// set: postconditions- these all are used to set new values for the instance variables
 /**
  * Set the balance.
  * @param newBalance  The new balance.		*/
 public void setBalance(double newBalance )
 {
     balance = newBalance;
 }
    
 /**
  * Set the acount number.
  * @param newAcctNumber The new account number.	*/
 public void setAccountNumber(int newAcctNumber )
 {
     accountNumber = newAcctNumber;
 }
    
 /**
  * Set the new owner of the account.
  * @param newOwner											*/
 //public void setOwner(Customer newOwner )
 public void setOwner(String newOwner )
 {
     ownersName = newOwner;
 }
 
}
