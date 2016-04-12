/** TicketServer *********************************************
<<<<<<< HEAD
 * Class that serves TicketClients.
=======
 * Thread to handle incoming requests from the clients
 * Orgranizes the ticket client requests
>>>>>>> 246fcd6622704683937982f876b9709d2a768fdd
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
 * 
 *
 */
public class TicketServer {
	
	/**
	 * Port number for the server to listen on, when started 
	 */
	static int PORT = 2222;

	/**
	 * Maximum number of parallel threads
	 */
	final static int MAXPARALLELTHREADS = 3;
	
	protected static Thread serverThread = null;
	
	public static void start(int portNumber, TheaterShow callbackTheater) throws IOException {
		if (serverThread == null) {
			PORT = portNumber;
			Runnable ticketServer = new ThreadedTicketServer(callbackTheater);
			serverThread = new Thread(ticketServer);
			serverThread.start();
		}
	}
}

class ThreadedTicketServer implements Runnable {

	String hostname = "127.0.0.1";
	String threadname = "X";
	String testcase;
	TicketClient sc;
	TheaterShow callbackTheater;

	public ThreadedTicketServer(TheaterShow theaterShowCallback) {
		callbackTheater = theaterShowCallback;
	}

	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(TicketServer.PORT);
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
<<<<<<< HEAD
=======
			
			// TODO Auto-generated catch block
>>>>>>> 246fcd6622704683937982f876b9709d2a768fdd
			e.printStackTrace();
		}

	}
	
}