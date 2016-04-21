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
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.locks.*;

/**
 * Class representing a single show at Bates Recital Hall. 
 * It handles keeping track of available seats and
 * finding seats for customers.
 */
public class TheaterShow{

	/**
	 * seats at the theater, sorted from best to worst, with boolean indicating whether they are reserved
	 */
	protected PriorityQueue<Seat> availableSeats;
	
	/**
	 * seats that have been reserved
	 */
	protected ArrayList<Seat> reservedSeats;
	
	/**
	 * Total seats at the recital hall
	 */
	protected int totalSeats;
	
	/**
	 * Name of the show
	 */
	protected String theaterShowing;
	
	/**
	 * Used to prevent multiple threads from accessing the seat map
	 */
	private final Lock threadLock = new ReentrantLock();
	
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
	
	/**
	 * Starts servicing ticket requests at the given port.
	 * @param port Port to service requests at
	 * @return True if successful, False otherwise
	 */
	public boolean startServicingTicketRequests(int port){
		try {
			ticketServer = new TicketServer(port, this);
			ticketServer.start();
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
	 * Determines if a certain seat is available
	 */
	public boolean isSeatAvailable(Seat seat) {
		return !reservedSeats.contains(seat);
	}
	
	/**
	 * @return Returns the best available Seat
	 * @throws NoSeatAvailableException If there's no seat available
	 */
	public Seat bestAvailableSeat() throws NoSeatAvailableException {
		Seat seat = null;
		
		//only one thread can find seats per each theatershow obj
		threadLock.lock();
		try {
			if (isSeatAvailable()) {
				seat = availableSeats.remove();
				reservedSeats.add(seat);
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadLock.unlock();
		}
		
		if(seat == null) 
			throw new NoSeatAvailableException();
		
		return seat;
		
	}
	
	/**
	 * Marks the given seat as taken
	 */
	public void markAvailableSeatTaken(Seat seat) {
		if(availableSeats.contains(seat)) {
			availableSeats.remove(seat);
			reservedSeats.add(seat);
		}
	}
	
	/**
	 * Marks the given seat as available
	 */
	public void markReservedSeatAvailable(Seat seat) {
		if(reservedSeats.contains(seat)) {
			reservedSeats.remove(seat);
			availableSeats.add(seat);
		}
	}
	
	/**
	 * Generates the available seats queue, containing all seats
	 * at Bates Recital Hall sorted automatically by best to worst.
	 * @return 
	 */
	private void initSeats() {
		availableSeats = new PriorityQueue<Seat>();
		reservedSeats = new ArrayList<Seat>();
		//first row through last row
		for(int row = 1; row <= 27; row++) {
			//left section to right section
			for(Seat.Section sect : Seat.Section.values()) {
				switch (sect) {
				//left section has seat numbers 122-128 
				//except for last row which has seats 127-128
				case HouseLeft:
					int seatStart = (row < 27) ? 122 : 127;
					for(int seatNum = seatStart; seatNum <= 128; seatNum++) {
						availableSeats.add(new Seat(sect, row, seatNum));
					}
					break;
				//middle section has seat numbers 108-121
				//except has no last 3 rows
				case HouseMiddle:
					if(row < 25) {
						for(int seatNum = 108; seatNum <= 121; seatNum++) {
							availableSeats.add(new Seat(sect, row, seatNum));
						}
					}
					break;
				//right section has seat numbers:
				//no rows 1-2
				//101-106 for row 3
				//101-107 for rows 4-26
				//101-104 and 116-118 for last row
				case HouseRight:
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
					break;
				}
			}
		}
		totalSeats = availableSeats.size();
	}

	/**
	 * @return Message regarding how many seats are booked and unbooked.
	 */
	public String getBookingMessage() {
		String heading = "<============= Booking at Bates Recital Hall: " + theaterShowing + " =============>";
		String footer = "<";
		for(int i = 0; i < heading.length() - 2; i++) {
			footer += "=";
		}
		footer += ">";
		return  "\n" + heading + "\n"
				+ "" + (totalSeats - availableSeats.size()) + " seats have been booked.\n"
				+ availableSeats.size() + " seats are open.\n"
				+ footer + "\n";
	}
	
}
