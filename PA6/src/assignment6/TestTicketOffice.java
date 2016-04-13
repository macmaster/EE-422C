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

package assignment6;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class that implements and runs tests of our design solution
 */
public class TestTicketOffice{

	/**
	 * Number of offices for the "many ticket offices" test
	 */
	public int many = 6;
	
	@Test
	/*
	 * Tests our design with one ticket office and one theater show.
	 */
	public void oneTicketOfficeOneShowTest() {
		//Create the theater showing
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
	/*
	 * Tests our design with two ticket offices and one theater show.
	 */
	public void twoTicketOfficeOneShowTest(){
		//Create the theater showing
		TheaterShow show = new TheaterShow("The Hunt for Thread October");
		Assert.assertTrue(show.startServicingTicketRequests(50001));

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
	/*
	 * Tests our design with many ticket offices and one theater show.
	 */
	public void manyTicketOfficeOneShowTest(){
		//Create the theater showing
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
