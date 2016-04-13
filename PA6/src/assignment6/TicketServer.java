/** TicketServer *********************************************
 * Class that serves TicketClients.
 * Thread to handle incoming requests from the clients
 * Organizes the ticket client requests
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/11/2016
 ************************************************************/

package assignment6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class that serves TicketClients.
 * Thread to handle incoming requests from the clients
 * Organizes the ticket client requests
 */
public class TicketServer {
	
	/**
	 * Port number for the server to listen on, when started 
	 */
	static int PORT = 2222;
	
	public static void start(int portNumber, TheaterShow callbackTheater) throws IOException {
		PORT = portNumber;
		Runnable ticketServer = new ThreadedTicketServer(callbackTheater);
		Thread serverThread = new Thread(ticketServer);
		serverThread.start();
	}
}
/**
 * Helper class for the TicketServer interface
 */
class ThreadedTicketServer implements Runnable {

	/** 
	 * host to run server on
	 */
	String hostname = "127.0.0.1";
	
	/**
	 * name of the running server thread
	 */
	String threadname = "X";
	
	/**
	 * name of the calling test case
	 */
	String testcase;
	
	/**
	 * local variable for requesting ticket client
	 */
	TicketClient sc;
	
	/**
	 * manageable ADT for Theater Seat Map
	 */
	TheaterShow callbackTheater;

	/**
	 * Create a Ticket Server thread that manages a specific Theater Show
	 */
	public ThreadedTicketServer(TheaterShow theaterShowCallback) {
		callbackTheater = theaterShowCallback;
	}

	
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(TicketServer.PORT);
			//boolean keepServicing = true; do we need this?
			while(true){
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				String seatStr;
				try {
					seatStr = callbackTheater.reserveBestAvailableSeat().toString();
				}
				catch (NoSeatAvailableException e) {
					seatStr = "null";
				}
				out.println(seatStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}