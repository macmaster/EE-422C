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

/**
 * Class representing an office selling tickets. 
 */
public class TicketOffice implements Runnable{

	/**
	 * Client used by this office to request tickets
	 */
	private TicketClient client;
	
	/**
	 * Printer used by this office to print ticket stubs
	 */
	private TicketPrinter printer;
	
	/**
	 * Costumers left in the line that want a ticket
	 */
	private int customersLeftInLine;
	
	/**
	 * Constructs a ticket office with the given info.
	 * @param hostname Host to connect to
	 * @param threadname Name of the ticket office
	 */
	public TicketOffice(String hostname, String threadname){
		customersLeftInLine = ((int)(Math.random()*900) + 100);
		client = new TicketClient(hostname, threadname);
		printer = new TicketPrinter();
	}
	
	/** 
	 * run:
	 * opens the ticket office for business
	 * serves all the customers in line
	 */
	public void run(){
		int custNo = 1;
		while(customersLeftInLine > 0){
			client.requestTicket();
			if(client.result.equals("null")) { //customer did not get a ticket
				System.out.println(client.threadName + ": Customer #" + (custNo++) + " did not receive a ticket.");
			}
			else { //customer got a ticket!
				System.out.println(client.threadName + ": Customer #" + (custNo++) + " gets ticket:");
				Seat ticketSeat = new Seat(client.result);
				printer.printTicketSeat(ticketSeat);
			}
			customersLeftInLine--; //customer leaves b/c he is satisfied
		}
		
		client.notifyServer();
	}

}
