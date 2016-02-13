package assignment2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
		List<Customer> customerList = new ArrayList();
		
	// 2) Process Transactions
		int resume = 0; // 1 = no
		while(resume != 1){
			Transaction transaction; 
			try{ // prompt teller
				String command = JOptionPane.showInputDialog("Please enter a bank account transation: ");
				transaction = new Transaction(command);
			}catch(CustomerIDException e){
				System.out.println("Error: Invalid Customer ID!");
				continue;
			}catch(TransactionTypeException e){
				System.out.println("Error: Invalid Transaction Type!");
				continue;
			}catch(AmountException e){
				System.out.println("Error: Invalid Transaction Amount!");
				continue;
			}catch(AccountTypeException e){
				System.out.println("Error: Invalid Account Type!");
				continue;
			}catch(Exception e){
				System.out.println("Error: Invalid Transaction Format!");
				continue;
			}

			// Log transaction
			System.out.println(transaction);
			resume = JOptionPane.showConfirmDialog(null, "Would you like to Continue?");
		}
		
	// 3) Return Account Statement
		
	}
	
	

}
