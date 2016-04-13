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

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import assignment6.TheaterShow;
import assignment6.TicketOffice;

public class TestTicketOffice{

	public int many = 6;
	
	@Test
	public void oneTicketOfficeOneShowTest() {
		//Create a theater showing
		TheaterShow show = new TheaterShow("Mcdonald's Commercial");
		Assert.assertTrue(show.startServicingTicketRequests(50000));
		
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
	public void twoTicketOfficeOneShowTest(){
		TheaterShow show = new TheaterShow("Romeo and Juliet");
		Assert.assertTrue(show.startServicingTicketRequests(50001));

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
	
	@Test
	public void manyTicketOfficeOneShowTest(){
		TheaterShow show = new TheaterShow("Antman vs. Superman");
		Assert.assertTrue(show.startServicingTicketRequests(50002));

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
