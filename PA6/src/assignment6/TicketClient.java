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
	protected String hostname;
	
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
		this.sc = sc;
		this.hostname = hostname;
		this.threadname = threadname;
	}

	public void run(){
		System.out.flush();
		try{
			// setup server socket
			Socket echoSocket = new Socket(hostname, TicketServer.PORT);
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
}

public class TicketClient{
	ThreadedTicketClient tc;
	String result = "dummy";
	String hostName = "";
	String threadName = "";

	public TicketClient(String hostname, String threadname){
		tc = new ThreadedTicketClient(this, hostname, threadname);
		hostName = hostname;
		threadName = threadname;
	}

	public TicketClient(String name){
		this("localhost", name);
	}

	public TicketClient(){
		this("localhost", "unnamed client");
	}

	public void requestTicket(){
		tc.run();
	}

	public void sleep(){
		try{
			Thread.sleep(100);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
