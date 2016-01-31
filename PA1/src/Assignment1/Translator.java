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
		
		return outputString;
		
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
		return word + "+";
	}
}
