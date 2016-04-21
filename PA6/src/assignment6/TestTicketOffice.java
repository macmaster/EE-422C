/** TestTicketOffice *****************************************
 * JUnit tests for our Theater Ticket Booking design solution
 * for EE422C A6.
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/11/2016
 ************************************************************/

package assignment6;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class that implements and runs tests of our design solution
 */
public class TestTicketOffice{
	
	public static int score = 0;

	// @Test
	public void basicServerTest() {
		try {
			TicketServer.start(16789);
		} catch (Exception e) {
			fail();
		}
		TicketClient client = new TicketClient();
		client.requestTicket();
	}

	@Test
	public void testServerCachedHardInstance() {
		try {
			TicketServer.start(16790);
		} catch (Exception e) {
			fail();
		}
		TicketClient client1 = new TicketClient("localhost", "c1");
		TicketClient client2 = new TicketClient("localhost", "c2");
		client1.requestTicket();
		client2.requestTicket();
		
	}

	@Test
	public void twoNonConcurrentServerTest() {
		try {
			TicketServer.start(16791);
		} catch (Exception e) {
			fail();
		}
		TicketClient c1 = new TicketClient("nonconc1");
		TicketClient c2 = new TicketClient("nonconc2");
		TicketClient c3 = new TicketClient("nonconc3");
		c1.requestTicket();
		c2.requestTicket();
		c3.requestTicket();
	}

	@Test
	public void twoConcurrentServerTest() {
		try {
			TicketServer.start(16792);
		} catch (Exception e) {
			fail();
		}
		final TicketClient c1 = new TicketClient("conc1");
		final TicketClient c2 = new TicketClient("conc2");
		final TicketClient c3 = new TicketClient("conc3");
		Thread t1 = new Thread() {
			public void run() {
				c1.requestTicket();
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				c2.requestTicket();
			}
		};
		Thread t3 = new Thread() {
			public void run() {
				c3.requestTicket();
			}
		};
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

/*******************************************************************************
 * Custom Test Cases                                                           *
 *******************************************************************************/
	

	/**
	 * Number of offices for the "many ticket offices" test
	 */
	public int many = 6;
	
	@Test
	/**
	 * Tests our design with one ticket office and one theater show.
	 */
	public void oneTicketOfficeOneShowTest() {
		//Create the theater showing
		TheaterShow show = new TheaterShow("Thread Poets Society");
		Assert.assertTrue(show.startServicingTicketRequests(40000));
		
		//Open a single office
		TicketOffice office = new TicketOffice("localhost", "Single Office");
		Thread thread = new Thread(office);
		thread.start();
		
		try {
			thread.join();
		}
		catch(Exception ex) {
			fail();
		}
		
		System.out.println(show.getBookingMessage());
	}

	@Test
	/**
	 * Tests our design with two ticket offices and one theater show.
	 */
	public void twoTicketOfficeOneShowTest(){
		//Create the theater showing
		TheaterShow show = new TheaterShow("The Hunt for Thread October");
		Assert.assertTrue(show.startServicingTicketRequests(20000));

		// create office threads
		Thread o1 = new Thread(new TicketOffice("localhost", "Office 1"));
		Thread o2 = new Thread(new TicketOffice("localhost", "Office 2"));

		// start the office threads
		o1.start();
		o2.start();

		// join the office threads
		try{
			o1.join();
			o2.join();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(show.getBookingMessage());
	}
	
	@Test
	/**
	 * Tests our design with many ticket offices and one theater show.
	 */
	public void manyTicketOfficeOneShowTest(){
		//Create the theater showing
		TheaterShow show = new TheaterShow("Night of the Living Thread");
		Assert.assertTrue(show.startServicingTicketRequests(10000));

		// create office threads
		ArrayList<Thread> officeThreads = new ArrayList<Thread>();
		for(int i = 1; i <= many; i++) {
			officeThreads.add(new Thread(new TicketOffice("localhost", "Office " + i)));
		}

		// start the office threads
		for(Thread thr : officeThreads) {
			thr.start();
		}

		// join the office threads
		try{
			for(Thread thr: officeThreads) {
				thr.join();
			}
		} catch(Exception e){
			e.printStackTrace();
			fail();
		}
		
		System.out.println(show.getBookingMessage());
	}
	
}
