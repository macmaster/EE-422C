/** WordLadderDriver ****************************************
 * Driver to manage the Word Ladder and Dictionary Classes.
 * find word ladders between pairs from word file.
 * check the data from the dictionary.
 * print out the word ladders for each pair.
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, hjw396
 * @author Ronald Macmaster, Horng-Bin Justin Wei 
 * @version 1.01 2/27/2016
 ************************************************************/

package assignment4;

import java.util.List;

public class Assign4Driver{

	public static void main(String[] args){
		// Create dictionary
		// Dictionary dict = new Dictionary("A4words.dat");
		// System.out.println(dict);
		// System.out.println("Dictionary Word count: " + dict.getCapacity());

		// Create a word ladder solver object
		Assignment4Interface wordLadderSolver = new WordLadderSolver();

		try{
			List<String> result = wordLadderSolver.computeLadder("money", "honey");
			System.out.println(result);
			result = wordLadderSolver.computeLadder("cokes", "magic");
			System.out.println(result);
			
			System.out.println(wordLadderSolver.computeLadder("youth", "tiger"));
			System.out.println(wordLadderSolver.computeLadder("alone", "aloha"));
			System.out.println(wordLadderSolver.computeLadder("leave", "value"));
			
			System.out.println(wordLadderSolver.computeLadder("value", "value"));
			System.out.println(wordLadderSolver.computeLadder("", ""));
		}
		catch(Exception err){
			System.err.println("fail!!");
		}
		
		/*
		 * try{ List<String> result = wordLadderSolver.computeLadder("money",
		 * "honey"); boolean correct = wordLadderSolver.validateResult("money",
		 * "honey", result); } catch (NoSuchLadderException e){
		 * 
		 * e.printStackTrace(); }
		 */
	}
}
