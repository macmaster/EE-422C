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
import java.util.ArrayList;

/**
 * Class that serves TicketClients.
 * Thread to handle incoming requests from the clients
 * Organizes the ticket client requests
 */
public class TicketServer {
	
	/**
	 * Default port number for the server to listen on, when started 
	 */
	static int PORT = 5000;
	
	/**
	 * list of all the ports that have been assigned
	 */
	private static ArrayList<Integer> ports = new ArrayList<Integer>();
	
	/** start
	 * starts the server thread execution
	 * @param portNumber default port number
	 * @param callbackTheater Theater show for server to manage
	 * @throws IOException 
	 */
	public static void start(TheaterShow callbackTheater) throws IOException {
		for(int port : ports){
			Runnable ticketServer = new ThreadedTicketServer(port, callbackTheater);
			Thread serverThread = new Thread(ticketServer);
			serverThread.start();	
		}
	}
	
	public static int requestPortNumber(){
		int count = ports.size();
		int port = TicketServer.PORT + count;
		ports.add(port);
		return port;
	}
	
	
}

/**
 * Helper class for the TicketServer interface
 * Listens to a specific client
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
	 * port number for current server thread
	 */
	int port;
	
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
	 * runs on the default port
	 */
	public ThreadedTicketServer(TheaterShow theaterShowCallback) {
		port = TicketServer.PORT;
		callbackTheater = theaterShowCallback;
	}
	
	/**
	 * Create a Ticket Server thread that manages a specific Theater Show
	 */
	public ThreadedTicketServer(int port, TheaterShow theaterShowCallback) {
		this.port = port;
		callbackTheater = theaterShowCallback;
	}

	/**
	 * Method that repeatedly accepts clients and handles their ticket requests
	 * Replies to ticket request with String version of Seat OR "null" if there's 
	 * no seat available
	 */
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			while(true){
				// service client seat requests
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



/**
 * Listens for Clients, then assigns them a server thread
 */
class TicketServerListener implements Runnable {

	/** 
	 * host to run server on
	 */
	String hostname = "127.0.0.1";
	
	/**
	 * name of the running server thread
	 */
	String threadname = "X";
	
	/**
	 * port number for current server thread
	 */
	int port;
	
	/**
	 * name of the calling test case
	 */
	String testcase;

	/**
	 * Create a Ticket Server thread that manages a specific Theater Show 
	 * runs on the default port
	 */
	public TicketServerListener() {
		port = TicketServer.PORT;
	}
	
	/**
	 * Create a Ticket Server thread that manages a specific Theater Show
	 */
	public TicketServerListener(int port) {
		this.port = port;
	}

	/**
	 * Method that repeatedly accepts clients and handles their ticket requests
	 * Replies to ticket request with String version of Seat OR "null" if there's 
	 * no seat available
	 */
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			while(true){
				// service client seat requests
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