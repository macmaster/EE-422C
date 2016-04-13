/** TicketPrinter *********************************************
 * Prints the seat ticket according to the output specs
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/11/2016
 ************************************************************/

package assignment6;

public class TicketPrinter{

	public TicketPrinter(){
		// TODO Auto-generated constructor stub
	}
	
	/*Procedure - printTicketSeat(seat)
	Input: seat is the location of an available seat in the theater.
	output: A ticket for that seat is printed to the screen – leave it on the screen long enough to be
	read easily by the client. The output format is up to you, but should contain the essential information
	found on a theater ticket. */
	public void printTicketSeat(Seat seat){
		String ticket = "<==== Bates Recital Hall Ticket ====>\n"
				+ "Seat Section: " + seat.section + "\n"
				+ "Seat Row: " + seat.row + "\n"
				+ "Seat Number: " + seat.number + "\n"
				+    "<===================================>\n";
		System.out.println(ticket);
	}

}
