/** WordLadderSolverTester *****************************************
 * Class to test the WordLadderSolver interface
 * Tests the computeLadder and validateResult Methods
 * Uses the JUnit framework
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, hjw396
 * @author Ronald Macmaster, Horng-Bin Justin Wei 
 * @version 1.01 2/27/2016
 ************************************************************/
package test;

// JUnit imports
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import java.util.List;

import assignment4.Assignment4Interface;
import assignment4.NoSuchLadderException;
import assignment4.WordLadderSolver;

public class WordLadderSolverTester{
	
	Assignment4Interface solver;
	
	@Before
	public void setUp() throws Exception{
		 solver = new WordLadderSolver();
	}


	@Test
	/** Tests the computeLadder Method.
	 * Method Signature: List<String> computeLadder(String startWord, String endWord)
	 */ 
	public void testComputeLadder() throws NoSuchLadderException{
		
		// compute ladders
		List<String> ladder1 = solver.computeLadder("honey", "money");
		List<String> ladder2 = solver.computeLadder("aloha", "aloha");
		List<String> ladder3 = solver.computeLadder("texas", "roads");
		List<String> ladder4 = solver.computeLadder("hello", "buddy");
		List<String> ladder5 = solver.computeLadder("babes", "child");

		// fail tests
		try{
			List<String> ladder6 = solver.computeLadder("this", "time");
			fail("solves invalid word ladders!");
		}catch(Exception err){}
		try{
			List<String> ladder7 = solver.computeLadder("angel", "demon");
			fail("solves invalid word ladders!");
		}catch(Exception err){}
		
		// check ladders
		assertEquals(true, solver.validateResult("honey", "money", ladder1));
		assertEquals(true, solver.validateResult("aloha", "aloha", ladder2));
		assertEquals(true, solver.validateResult("texas", "roads", ladder3));
		assertEquals(true, solver.validateResult("hello", "buddy", ladder4));
		assertEquals(true, solver.validateResult("babes", "child", ladder5));
		
	}

	@Test
	/** Tests the validateResult Method.
	 * Method Signature: boolean validateResult(String startWord, String endWord, List<String> wordLadder)
	 */ 
	public void testValidateResult(){
		// good ladder
		assertEquals(true, solver.validateResult("value", "value", asList("value", "value")));
		assertEquals(true, solver.validateResult("gears", "gears", asList("gears", "gears")));
		assertEquals(true, solver.validateResult("", "", asList("", "", "", ""))); // null case
		
		assertEquals(true, solver.validateResult("money", "honey", asList("money", "honey")));
		assertEquals(true, solver.validateResult("coned", "honey", asList("coned", "coney", "money", "honey")));
		assertEquals(true, solver.validateResult("coned", "honky", asList("coned", "coney", "money", "honey", "honky")));
		
		
		// bad ladder
		assertEquals(false, solver.validateResult("value", "valve", asList("value", "value")));
		assertEquals(false, solver.validateResult("gears", "gears", asList("gears", "fears")));
		
		assertEquals(false, solver.validateResult("aaaaa", "aaaab", asList("aaaaa", "aaaab")));
		assertEquals(false, solver.validateResult("gui", "hui", asList("gui", "hui")));
		assertEquals(false, solver.validateResult("trim", "slim", asList("trim", "tlim", "slim")));
	}

}
