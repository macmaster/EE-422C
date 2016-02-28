/** Dictionary ***********************************************
 * Class to encapsulate Dictionary Data Structure.
 * Contains Dictionary Data and Search Methods.
 * Manages the list of valid English Words.
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, hjw396
 * @author Ronald Macmaster, Horng-Bin Justin Wei 
 * @version 1.01 2/27/2016
 ************************************************************/
package assignment4;

//file imports
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// regular expressions 
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//data structures
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;

public class Dictionary{
	// constants
	private final static int WORD_SIZE = 5;

	// word graph
	private int capacity;
	private WordGraph wordGraph;

	// word sets
	private Set<String> wordSet;
	private Map<Integer, Word> wordMap;

	public Dictionary(String filename){
		try{
			// data structure
			capacity = 0;
			wordSet = new HashSet<String>();
			wordMap = new HashMap<Integer, Word>();

			// build dictionary from file
			buildFromFile(filename);
			wordGraph = new WordGraph(capacity);
			wordGraph.buildGraph(wordMap);

		} catch(FileNotFoundException ferr){
			System.err.println("Failed to open dictionary file! Dictionary set to empty");
		} catch(Exception err){
			System.err.println(err.getMessage());
		}
	}

	private void buildFromFile(String filename) throws IOException{
		// open file
		FileReader reader = new FileReader(filename);
		BufferedReader fhand = new BufferedReader(reader);

		String line; // raw word data
		while((line = fhand.readLine()) != null){
			// word regex (extract word @ start of line)
			Pattern pattern = Pattern.compile("^([a-zA-Z]+)");
			Matcher matcher = pattern.matcher(line);

			// extract word data
			if(matcher.find()){
				int index = capacity; // word key
				String word = matcher.group();
				Word word_node = new Word(word, index);

				// bad word size
				if(word.length() != WORD_SIZE){
					continue;
				}
				// word already in dictionary
				if(wordSet.contains(word)){
					continue;
				}

				// add to dictionary
				word = word.toLowerCase();
				wordSet.add(word);
				wordMap.put(index, word_node);
				capacity++;
			}
		}

		// close file
		fhand.close();
	}

	public void setCapacity(int n){
		// n is positive
		int minsize = wordSet.size();
		if(n < minsize){
			System.err.println("must have capacity to store word list!");
			n = minsize;
		}

		// update capacity.
		capacity = n;
		wordGraph = new WordGraph(n);
		wordGraph.buildGraph(wordMap);
	}

	public int getCapacity(){
		return capacity;
	}

	public Word getWord(int index){
		return wordMap.get(index);
	}

	/**
	 * public int getWordKey(String word){ if(wordKeys.containsKey(word)){ return
	 * wordKeys.get(word); } else{ return -1; // nonexistent word } }
	 */

	public String toString(){
		int word_count = 0;
		String dict_string = "";

		// build dictionary string
		for(String word : wordSet){
			dict_string += word + ", ";
			word_count++;
			if(word_count == 10){
				dict_string += "\n";
				word_count = 0;
			}
		}
		return dict_string;
	}
}
