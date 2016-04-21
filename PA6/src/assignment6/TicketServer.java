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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;

/**
 * Class that serves TicketClients. 
 * Thread to handle incoming requests from the
 * clients Organizes the ticket client requests
 */
public class TicketServer{

	/**
	 * port number for the server to listen on, when started
	 */
	static int PORT = 10000;

	/**
	 * Default port number for assignment failures
	 */
	static int DEFAULT_PORT = 3000;

	/**
	 * Theater show that the server manages
	 */
	protected TheaterShow show;

	/**
	 * Listener Thread for the ticket server
	 */
	private Thread listener;

	/**
	 * TicketServer() creates a new ticket server object
	 * 
	 * @param portNumber default port number
	 * @param callbackTheater Theater show for server to manage
	 */
	public TicketServer(int port, TheaterShow callbackTheater){
		setDefaultPort(port);
		show = callbackTheater;
		Runnable ticketServerListener = new TicketServerListener(TicketServer.PORT, show);
		listener = new Thread(ticketServerListener);
	}

	/**
	 * start starts the listener thread and begins listening for new client
	 * requests
	 * 
	 * @precondition: start has not been called yet
	 * @throws IOException
	 */
	public void start() throws IOException{
		listener.start();
	}
	
	/**
	 * static start 
	 * starts the listener thread on a new default port
	 * and begins listening for new client
	 * 
	 * Uses a default theater show class
	 * 
	 * @precondition: start has not been called yet
	 * @throws IOException
	 */
	public static void start(int port) throws IOException{
		TheaterShow show = new TheaterShow("Default T");
		show.startServicingTicketRequests(port);
	}
	
	/**
	 * setDefaultPort
	 * set the default listener port on the server.
	 *********************************************/
	public static void setDefaultPort(int port){
		PORT = port;
		DEFAULT_PORT = port;
	}

}

/**
 * Helper class for the TicketServer 
 * interface Listens to a specific client
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
	 * Create a Ticket Server thread that 
	 * manages a specific Theater Show
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
			boolean clientOpen = true;
			serverSocket = new ServerSocket(port);
			while(clientOpen){
				// service client seat requests
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				String seatStr;
				try{
					seatStr = callbackTheater.bestAvailableSeat().toString();
				} catch(NoSeatAvailableException e){
					seatStr = "null";
				}
				out.println(seatStr);

				// check for end thread request
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				String msg = in.readLine();
				if(msg != null){
					// System.err.println("client finished!");
					out.println("received client termination request.");
					clientOpen = false;
				}
			}
			serverSocket.close();
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
	 * Socket for the listener to listen on.
	 */
	ServerSocket serverSocket;

	/**
	 * TheaterShow for the listener
	 */
	TheaterShow show;
	
	/**
	 * Lock for the port assignment section
	 */
	private final Lock lock = new ReentrantLock();
	ArrayList<Integer> ports = new ArrayList<Integer>(); // assigned ports
	
	/**
	 * Create a Ticket Server Listener on the default port
	 */
	TicketServerListener(){
		this.show = null;
		this.port = TicketServer.PORT;
	}

	/**
	 * Create a Ticket Server Listener on this port
	 */
	TicketServerListener(int port){
		this.show = null;
		this.port = port;
	}

	/**
	 * Create a Ticket Server Listener on this port for a specific show
	 */
	TicketServerListener(int port, TheaterShow show){
		this.show = show;
		this.port = port;
	}

	/**
	 * Listens for new client requests and handles their new server requests
	 * assigns them a new server thread
	 */
	public void run(){
		
		try{
			// listen for new client requests
			serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(1000); // 3s time
			while(true){
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

				// assign a new port for a new server thread
				lock.lock();
				int port;
				try {
					int count = ports.size() + (int)(Math.random() * 5000);
					port = TicketServer.DEFAULT_PORT + count;
					ports.add(port);
				} catch (Exception e) {
					throw e;
				}
				finally {
					lock.unlock();
				}

				// start independent ticket thread
				Runnable ticketServer = new ThreadedTicketServer(port, show);
				Thread serverThread = new Thread(ticketServer);
				serverThread.start();

				// reply the newly reserved port
				out.println(port);
			}
		} catch(SocketTimeoutException e){
			try{
				System.err.println("Closing Listener Socket!");
				serverSocket.close();
			} catch(IOException e1){
				e1.printStackTrace();
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
