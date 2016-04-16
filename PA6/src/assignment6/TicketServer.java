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
 * Class that serves TicketClients. Thread to handle incoming requests from the
 * clients Organizes the ticket client requests
 */
public class TicketServer{

	/**
	 * port number for the server to listen on, when started
	 */
	static int PORT = 20000;
	
	/**
	 * Default port number for assignment failures
	 */
	static int DEFAULT_PORT = 3000;

	/**
	 * Theater show that the server manages
	 */
	static TheaterShow SHOW = null;
	
	/**
	 * flag indicating whether or not the listener has started
	 */
	private static boolean started;

	/**
	 * start starts the listener thread and begins listening for new client
	 * requests
	 * 
	 * @param portNumber default port number
	 * @param callbackTheater Theater show for server to manage
	 * @throws IOException
	 */
	public static void start(int port, TheaterShow callbackTheater) throws IOException{
		if(!started){
			DEFAULT_PORT = port;
			started = true;
			SHOW = callbackTheater;
			Runnable ticketServerListener = new TicketServerListener(TicketServer.PORT);
			Thread serverThread = new Thread(ticketServerListener);
			serverThread.start();
		}
	}
	
	/**
	 * initializes the ticket server for operation
	 */
	public static void start(int port, TheaterShow callbackTheater) throws IOException{
		if(!started){
			DEFAULT_PORT = port;
			started = true;
			SHOW = callbackTheater;
			Runnable ticketServerListener = new TicketServerListener(TicketServer.PORT);
			Thread serverThread = new Thread(ticketServerListener);
			serverThread.start();
		}
	}
	

}

/**
 * Helper class for the TicketServer interface Listens to a specific client
 */
class ThreadedTicketServer implements Runnable{

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
	public ThreadedTicketServer(int port, TheaterShow theaterShowCallback){
		this.port = port;
		callbackTheater = theaterShowCallback;
	}

	/**
	 * Method that repeatedly accepts clients and handles their ticket requests
	 * Replies to ticket request with String version of Seat OR "null" if there's
	 * no seat available
	 */
	public void run(){
		ServerSocket serverSocket;
		try{
			serverSocket = new ServerSocket(port);
			while(true){
				// service client seat requests
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				String seatStr;
				try{
					seatStr = callbackTheater.reserveBestAvailableSeat().toString();
				} catch(NoSeatAvailableException e){
					seatStr = "null";
				}
				out.println(seatStr);
			}
		} catch(IOException e){
			e.printStackTrace();
		}

	}

}

/**
 * Listens for Clients, then assigns them a server thread
 */
class TicketServerListener implements Runnable{

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
	 * Create a Ticket Server Listener on the default port
	 */
	TicketServerListener(){
		port = TicketServer.PORT;
	}

	/**
	 * Create a Ticket Server Listener on this port
	 */
	TicketServerListener(int port){
		this.port = port;
	}

	/**
	 * Listens for new client requests and handles their new server requests
	 * assigns them a new server thread
	 */
	public void run(){
		ServerSocket serverSocket;
		ArrayList<Integer> ports = new ArrayList<Integer>(); // assigned ports
		try{
			// listen for new client requests
			serverSocket = new ServerSocket(port);
			while(true){
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

				// assign a new port for a new server thread
				int count = ports.size() + 1;
				int port = TicketServer.DEFAULT_PORT + count;
				ports.add(port);

				// start independent ticket thread
				Runnable ticketServer = new ThreadedTicketServer(port, TicketServer.SHOW);
				Thread serverThread = new Thread(ticketServer);
				serverThread.start();

				// reply the newly reserved port
				out.println(port);
			}
		} catch(IOException e){
			e.printStackTrace();
		}

	}

}
