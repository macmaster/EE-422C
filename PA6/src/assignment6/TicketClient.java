/** TicketClient *********************************************
 * Classes representing the ticket client.
 * Encapsulates connection/communication with the ticket server.
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
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that handles the background processes of the TicketClient.
 */
class ThreadedTicketClient implements Runnable{
	
	/**
	 * Host to connect to
	 */
	protected String hostname = "localhost";
	
	/**
	 * port to connect on
	 */
	protected int port;
	
	/**
	 * Name of this client
	 */
	protected String threadname;
	
	/**
	 * Associated TicketClient for result access purposes
	 */
	protected TicketClient sc;

	/**
	 * Constructed this client with the given parameters
	 * @param hostname Host to connect to
	 * @param threadname Name of this client
	 */
	public ThreadedTicketClient(TicketClient sc, String hostname, String threadname){
		this.port = requestPortNumber();
		this.sc = sc;
		this.hostname = hostname;
		this.threadname = threadname;
	}

	/**
	 * Connects to the ticket server using data specified and
	 * requests a ticket, the receives ticket information
	 */
	public void run(){
		System.out.flush();
		try{
			// setup server socket
			Socket echoSocket = new Socket(hostname, port);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			
			String seatString = in.readLine();
			//out.println("Thanks for the ticket.");
			sc.result = seatString;

			echoSocket.close();
		} catch(UnknownHostException e){
			// client host error
			System.err.println("Client Error: contact server host (Unknown Host Exception)!");
			e.printStackTrace();
		} catch(IOException e){
			// client IO error
			System.err.println("Client Error: IO exception (error in input / output)!");
			e.printStackTrace();
		}
	}
	
	/** requestPortNumber()
	 * requests a port number from the server listener
	 */
	private int requestPortNumber(){
		try{
			Socket echoSocket = new Socket(hostname, TicketServer.PORT);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			int portNumber = Integer.parseInt(in.readLine());
			echoSocket.close();
			return portNumber;
		}
		catch(Exception e){
			return TicketServer.DEFAULT_PORT;
		}
	}
}

/**
 * Ticket requesting client class: interacts with TicketServer
 */
public class TicketClient{

	/**
	 * ThreadedTicketClient used behind-the-scenes
	 */
	protected ThreadedTicketClient tc;
	
	/**
	 * Holds the result of the ticket request
	 */
	protected String result;
	
	/**
	 * Name of the host to connect to
	 */
	protected String hostName;
	
	/**
	 * Name of this client
	 */
	protected String threadName;

	/**
	 * Constructs a client with the given information
	 * @param hostname Host for this client to connect to
	 * @param threadname Name of this client
	 */
	public TicketClient(String hostname, String threadname){
		tc = new ThreadedTicketClient(this, hostname, threadname);
		hostName = hostname;
		threadName = threadname;
	}

	/**
	 * Tells this client to request a ticket
	 */
	public void requestTicket(){
		tc.run();
	}
}
