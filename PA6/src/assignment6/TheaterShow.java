/** TheaterShow **********************************************
 * Class that represents a single show at Bates Recital Hall
 * Encapsulates the tracking and finding of available seats
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678, rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/11/2016
 ************************************************************/

package assignment6;

import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Class representing a single show at Bates Recital Hall. 
 * It handles keeping track of available seats and
 * finding seats for customers.
 */
public class TheaterShow{

	/**
	 * Queue of seats at the theater, sorted from best to worst
	 */
	protected PriorityQueue<Seat> availableSeats;
	
	protected int totalSeats;
	
	protected String theaterShowing;
	
	/**
	 * Server for ticket offices to request tickets on 
	 */
	protected TicketServer ticketServer;
	
	/**
	 * Constructs a new TheaterShow object - with all seats available
	 * @throws IOException When server fails to start
	 */
	public TheaterShow(String showName){
		theaterShowing = showName;
		initSeats();
	}
	
	public boolean startServicingTicketRequests(int port){
		try {
			TicketServer.start(port, this);
			return true;
		}
		catch(IOException ex) {
			return false;
		}
	}
	
	/**
	 * Determines whether a seat is available at this show.
	 * @return True is a seat is available for the show, False otherwise.
	 */
	public boolean isSeatAvailable() {
		return !availableSeats.isEmpty();
	}
	
	/**
	 * @return Returns the best available Seat
	 * @throws NoSeatAvailableException If there's no seat available
	 */
	public Seat reserveBestAvailableSeat() throws NoSeatAvailableException {
		//only one thread can find seats per each theatershow obj
		synchronized (availableSeats) {
			if (isSeatAvailable())
				return availableSeats.remove();
			else
				throw new NoSeatAvailableException();
		}
	}
	
	/**
	 * Generates the available seats queue, containing all seats
	 * at Bates Recital Hall sorted automatically by best to worst.
	 * @return 
	 */
	private void initSeats() {
		availableSeats = new PriorityQueue<Seat>();
		//first row through last row
		for(int row = 1; row <= 27; row++) {
			//left section to right section
			for(Seat.Section sect : Seat.Section.values()) {
				switch (sect) {
				//left section has seat numbers 122-128 
				//except for last row which has seats 127-128
				case Left:
					int seatStart = (row < 27) ? 122 : 127;
					for(int seatNum = seatStart; seatNum <= 128; seatNum++) {
						availableSeats.add(new Seat(sect, row, seatNum));
					}
				//middle section has seat numbers 108-121
				//except has no last 3 rows
				case Middle:
					if(row < 25) {
						for(int seatNum = 108; seatNum <= 121; seatNum++) {
							availableSeats.add(new Seat(sect, row, seatNum));
						}
					}
				//right section has seat numbers:
				//no rows 1-2
				//101-106 for row 3
				//101-107 for rows 4-26
				//101-104 and 116-118 for last row
				case Right:
					if(row > 2 && row != 27) {
						int seatStop = (row != 3)? 107 : 106;
						for(int seatNum = 101; seatNum <= seatStop; seatNum++) {
							availableSeats.add(new Seat(sect, row, seatNum));
						}
					}
					if(row == 27) {
						for(int seatNum = 101; seatNum <= 104; seatNum++) {
							availableSeats.add(new Seat(sect, row, seatNum));
						}
						for(int seatNum = 116; seatNum <= 118; seatNum++) {
							availableSeats.add(new Seat(sect, row, seatNum));
						}
					}
				}
			}
		}
		totalSeats = availableSeats.size();
	}

	public String getBookingMessage() {
		return  "\n<============= Booking at Bates Recital Hall: " + theaterShowing + " =============>\n"
				+ "" + (totalSeats - availableSeats.size()) + " seats have been booked.\n"
				+ availableSeats.size() + " seats are open.";
	}
	
}
