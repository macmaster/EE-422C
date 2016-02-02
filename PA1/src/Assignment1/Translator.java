/** 
	Translator.java
	Translates English into Pig Latin
 	Solves EE422C programming assignment #1
 	Section : F 2:00 - 3:30pm
 	UT EID: rpm953
 	@author Ronald Macmaster
 	@version 1.01 1/30/2016
*/

package Assignment1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Translator 
{
	
	public static void main (String args[]) 
	{ 
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile (args[0]);
		
	}

	/******************************************************************************
	* Method Name: processLinesInFile                                             *
	* Purpose: Opens the file specified in String filename, reads each line in it *
	*          Invokes translate () on each line in the file, and prints out the  *
	*          translated piglatin string.                                        *
	* Returns: None                                                               *
	******************************************************************************/
	public static void processLinesInFile (String filename) 
	{ 

		Translator translator = new Translator(); 
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String pigLatin = translator.translate(s);
				System.out.println(pigLatin);
			}
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/******************************************************************************
	* Method Name: translate                                                      *
	* Purpose: Converts String inputString into piglatin based on rules specified *
	*          in your assignment write-up.                                       *
	* Returns: String object containing the piglatin translation of               *
	*          String inputString                                                 *
	******************************************************************************/
	
	public String translate (String inputString) 
	{ 
		// Split the input string into words
		String outputString = new String(); 
		String[] words = inputString.split(" +"); 
		
		/* Translate each word into piglatin
		 * Append the piglatin word to output */
		for(String word : words){
			outputString = outputString + " " + translateWord(word);
		}
		
		return outputString.trim();
		
		/* debug / print string array */
		/*	String s = "";
			s = s + "{";
			for(String word : words){
				s = s + " " + word + ",";
			}
			s = s + "}";
			System.out.print(s);
			return "";							*/
		
	}
	
	/******************************************************************************
	* Method Name: translateWord                                                  *
	* Purpose: Converts String word into piglatin.								 			*
	* Returns: String object containing the piglatin translation of String Word   *                                     													*
	*                                                                             *
	* @param word English word to be converted into piglatin                      *
	* @return word translated into piglatin                                       *
	******************************************************************************/
	
	public String translateWord(String word)
	{	
		// Check word validity (contains only letters and punctuation)
		if(!word.matches("[^a-zA-Z'-]*[a-zA-Z'-]+[^a-zA-Z'-]*")){
			return word;
		}
		
		/* Hyphen and Punctuation Handler
		 *	Resolve either Hyphen or Punctuation, 
		 * Only resolve them one at a time     	*/
			
		// pre-punctuation case ex. !!latin -> !!atinlay
		if(word.matches("[^a-zA-Z'-]+[a-zA-Z'-]+[^a-zA-Z'-]*")){ 
			String[] letters = word.split("[^a-zA-Z'-]+");
			String[] punctuation = word.split("[a-zA-Z'-]+");
			word = "";
			for(String part : letters){
				word = word + translateWord(part);
			}
			switch(punctuation.length){
				case 1: // only pre
					word = punctuation[0] + word;
					break;
				case 2: // pre and post
					word = punctuation[0] + word + punctuation[1];
					break;
			}
			return word;
		}
		
		// post-punctuation case ex. latin!! -> atinlay!!
		if(word.matches("[^a-zA-Z'-]*[a-zA-Z'-]+[^a-zA-Z'-]+")){ 
			String[] letters = word.split("[^a-zA-Z'-]+");
			String[] punctuation = word.split("[a-zA-Z'-]+");
			word = "";
			for(String part : letters){
				word = word + translateWord(part);
			}
			switch(punctuation.length){
				case 1: // only post
					word = word + punctuation[0];
					break;
				case 2: // pre and post
					word = punctuation[0] + word + punctuation[1];
					break;
			}
			return word;
		}
		
		// Hyphen handler
		if(word.contains("-")){
			String[] letters = word.split("[-]+");
			word = "";
			for(String part : letters){
				word = word + translateWord(part) + " ";
			}
			word = word.trim().replace(" ", "-"); // sprinkle in hyphens
			return word;
		}
		
		// Starts with vowel
		if(word.matches("[aeiouAEIOU'].*")){
			word = word + "y";
		}
		
		// First char is not yet vowel, but the word contains AT LEAST 1 vowel
		while(!word.matches("[aeiouAEIOU'].*")&&!word.matches("[^aeiouAEIOU']+")){
			// rotate first char to back
			char c = word.charAt(0);
			word = word.substring(1) + c;
		}
		
		return word + "ay";
		
	}
}
