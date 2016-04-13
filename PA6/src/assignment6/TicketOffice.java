/** TicketOffice ***********************************************
 * Sells seat tickets to clients
 * Manages ticket booth workers
 * Has it's own ticket printer
 * Interfaces the ticket server and ticket clients
 * 
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/11/2016
 ************************************************************/

package assignment6;

import java.util.Queue;

public class TicketOffice implements Runnable{

	private TicketClient client;
	private TicketPrinter printer;
	private int customersLeftInLine;
	
	public TicketOffice(){
		customersLeftInLine = ((int)(Math.random()*900) + 1000);
		client = new TicketClient();
		printer = new TicketPrinter();
	}
	
	public TicketOffice(int customers){
		customersLeftInLine = customers;
		client = new TicketClient();
		printer = new TicketPrinter();
	}
	
	public TicketOffice(String hostname, String threadname){
		customersLeftInLine = ((int)(Math.random()*900) + 1000);
		client = new TicketClient(hostname, threadname);
		printer = new TicketPrinter();
	}
	
	/** run ***********************************
	 * opens the ticket office for business
	 * serves all the customers in line
	 *****************************************/
	public void run(){
<<<<<<< HEAD
		int custNo = 1;
		while(customersLeftInLine > 0){
			client.requestTicket();
			if(client.result.equals("null")) { //customer did not get a ticket
				System.out.println(client.threadName + ": Customer #" + (custNo++) + " did not receive a ticket.");
			}
			else { //customer got a ticket!
				System.out.println(client.threadName + ": Customer #" + (custNo++) + " gets ticket: " + client.result);
			}
=======
		for(int i = 1; i <= customersLeftInLine; i++){
			client.requestTicket();
			System.out.println("Office customer #" + i + " gets ticket: " + client.result);
>>>>>>> 8144a7ef6c3411bfce8ccddd01b2e598255cbbf5
			customersLeftInLine--;
		}
	}

}
