/** TestTicketOffice *****************************************
 * Driver to manage the Word Ladder and Dictionary Classes.
 * find word ladders between pairs from word file.
 * check the data from the dictionary.
 * print out the word ladders for each pair.
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/11/2016
 ************************************************************/

package test;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import assignment6.Seat;
import assignment6.Seat.Section;
import assignment6.TheaterShow;
import assignment6.TicketClient;
import assignment6.TicketOffice;
import assignment6.TicketServer;

public class TestTicketOffice{

	public static int score = 0;

	@Test
	public void oneTicketOfficeTest() {
		//Create a theater showing
		TheaterShow show = new TheaterShow("Mcdonald's Commercial");
		Assert.assertTrue(show.startServicingTicketRequests(50001));
		
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
	}

	/*
	@Test
	public void testServerCachedHardInstance() {
		try {
			//TicketServer.start(16790);
		} catch (Exception e) {
			fail();
		}
		TicketClient client1 = new TicketClient("localhost", "c1");
		TicketClient client2 = new TicketClient("localhost", "c2");
		client1.requestTicket();
		client2.requestTicket();

	}*/
/*
	@Test
	public void twoNonConcurrentServerTest() {
		try {
			//TicketServer.start(16791);
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
*/
	/*@Test
	public void twoConcurrentServerTest(){
		try{
			// TicketServer.start(16792);
		} catch(Exception e){
			fail();
		}
		final TicketClient c1 = new TicketClient("conc1");
		final TicketClient c2 = new TicketClient("conc2");
		final TicketClient c3 = new TicketClient("conc3");
		Thread t1 = new Thread(){
			public void run(){
				c1.requestTicket();
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				c2.requestTicket();
			}
		};
		Thread t3 = new Thread(){
			public void run(){
				c3.requestTicket();
			}
		};
		t1.start();
		t2.start();
		t3.start();
		try{
			t1.join();
			t2.join();
			t3.join();
		} catch(Exception e){
			e.printStackTrace();
		}

	} */

	@Test
	public void testTicketOffice(){
		TheaterShow show = new TheaterShow("Romeo and Juliet");
		Assert.assertTrue(show.startServicingTicketRequests(50000));

		// create office threads
		TicketOffice o1 = new TicketOffice("localhost", "Office 1");
		TicketOffice o2 = new TicketOffice("localhost", "Office 2");
		Thread o1thread = new Thread(){
			public void run(){
				o1.run();
			}
		};
		Thread o2thread = new Thread(){
			public void run(){
				o2.run();
			}
		};

		// start the office threads
		o1thread.start();
		o2thread.start();

		// join the office threads
		try{
			o1thread.join();
			o2thread.join();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(show.getBookingMessage());

	}
}
