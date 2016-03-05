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
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Iterator;

public class Assign4Driver{
	
	private static Assignment4Interface wordLadderSolver;

	public static void main(String[] args){
		// Create dictionary
		// Dictionary dict = new Dictionary("A4words.dat");	//takes the input from file of that name
		// System.out.println(dict);
		// System.out.println("Dictionary Word count: " + dict.getCapacity());

		// Create a word ladder solver object
		

		try{
			// 1) Read dictionary and filename
			String dictfile; // dictionary filename
			String pairfile; // word pairs filename
			
			if(args.length != 2){ // invalid commandline arguments
				Scanner input = new Scanner(System.in);
				// prompt filenames
				System.out.println("Enter your dictionary filename: ");
				dictfile = input.next();
				System.out.println("Enter your word pairs filename: ");
				pairfile = input.next();
			}
			else{
				dictfile = args[0];
				pairfile = args[1];
			}
			
			// 2) create a wordladdersolver object (includes graph and dictionary)
			wordLadderSolver = new WordLadderSolver(dictfile);	
			
			// 3) file input loop:
				FileReader reader = new FileReader(pairfile);
				BufferedReader fhand = new BufferedReader(reader);
			
			String line;
			Assign4Driver.printBanner();
			while((line =  fhand.readLine()) != null){
				Pattern pattern = Pattern.compile("[a-zA-Z]{5}[\\s]+[a-zA-Z]{5}");
				Matcher matcher = pattern.matcher(line);
				
				if(matcher.find()){
					String[] words = matcher.group().split("[\\s]+");
					String start = words[0];
					String end  = words[1];
					//finds number of characters subtracts from banner amt and centers the next part
					int charamt = 26 + start.length() + end.length();
					int remainspace = (65 - charamt)/2;
					for(int i = 0; i < remainspace; i++){
						System.out.print(" ");
					}
					
					System.out.println("start word: " + start + "    end word: " + end);
					Assign4Driver.printLadder(start, end);
					Assign4Driver.printBanner();
				}
				else{
					System.err.println("Error: Input line does not contain a valid 5 letter word pair.");
					System.err.println("Original input: " + line);
					Assign4Driver.printBanner();
					continue;
				}
					
			}		
			
		}
		catch(Exception err){
			System.err.println("fail!!");
		}
		
	}
	
	private static void printBanner(){
		int bannerSize = 65;
		for(int i = 0; i < bannerSize; i++){
			System.out.print("*");
		}
		System.out.print("\n");
	}
	
	private static void printLadder(String start, String end){
	try{
		List<String> wordLadder = wordLadderSolver.computeLadder(start, end);
		Iterator ladderItr = wordLadder.iterator();
		//iterate through wordladder
		while(ladderItr.hasNext()){
			System.out.println(ladderItr.next());			
		}
	}
	catch(Exception err){
		System.err.println("No word ladder between: " + start + " and " + end + ".");}
	}
	

}

//they are giving us two inputs: first is dictionary, second contains the sets of two words
