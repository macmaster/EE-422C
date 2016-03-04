/** WordGraph ***********************************************
 * Class to encapsulate a class of Dictionary Words
 * Contains word nodes and their respective edges
 * Implements an Adjacency Matrix
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, hjw396
 * @author Ronald Macmaster, Horng-Bin Justin Wei 
 * @version 1.01 2/27/2016
 ************************************************************/

package assignment4;

// data structures
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WordGraph{
	// adjacency matrix
	private int capacity;
	private boolean[][] graph;

	// Word data
	private Set<String> wordSet;
	private Map<String, Integer> indexMap;

	public WordGraph(int capacity){
		if(capacity < 0){
			this.capacity = 0;
		}
		else{
			this.capacity = capacity;
		}
		wordSet = new HashSet<String>();
		indexMap = new HashMap<String, Integer>();
	}

	public void buildGraph(Dictionary dictionary){
		// TODO: Build the adjacency matrix from the word map
		List<String> wordList = dictionary.getWordList(); // all dictionary words
		graph = new boolean[capacity][capacity]; // false by default
		
		// build word sets
		for(String wordString : wordList){
			Word word = dictionary.getWord(wordString);
			Integer index = dictionary.getWordIndex(wordString);
			wordSet.add(word.getWord()); // mark word
			indexMap.put(word.getWord(), index); // hash index
		}

		// check every possible connection and build graph
		for(String word : wordSet){ // each word in the set
//			System.out.print(word + "\t\t");
			for(int pos = 0; pos < word.length(); pos++){
				// permutate every char in the word
				for(char ch = 'a'; ch < 'z'; ch++){
					StringBuffer pword = new StringBuffer(word);// permutable word
					pword.setCharAt(pos, ch); // substitute every letter
					String pstring = pword.toString(); // words are connected
					if((wordSet.contains(pstring)) && !pstring.equals(word)){
						// plot connection
						int index1 = indexMap.get(word);
						int index2 = indexMap.get(pstring);
						graph[index1][index2] = true;
						graph[index2][index1] = true;
//						System.out.print(pword + ", ");
					}
				}
			}
//			System.out.print("\n");
		}
		try{ //debug
			// printGraph();
		}catch(Exception err){
			
		}
		return;
	}

	/** @return list of word neighbors */
	public List<Integer> getNeighbors(int index){
		// TODO: Return a list of the indexed word's neighbors
		// can return a null list too.
		List<Integer> neighbors = new ArrayList<Integer>();
		
		//traverse graph
		for(int neighbor_index = 0; neighbor_index < capacity; neighbor_index++){
			if(graph[index][neighbor_index] == true){ //words are adjacent
				neighbors.add(neighbor_index);
			}
		}
		
		return neighbors;
	}

	/** @return capacity */
	public int getCapacity(){
		return capacity;
	}
	
	//debug print graph
	public void printGraph() throws IOException{
		
		// build graph plot
		FileWriter writer = new FileWriter("test/graph.csv");
		BufferedWriter fhand = new BufferedWriter(writer);
		for(int i = 0; i < capacity; i++){
			//fhand.write(i + "\t[");
			for(int j = 0; j < capacity; j++){
				if(graph[i][j]){
					fhand.write("1, ");
				}
				else{
					fhand.write("0, ");				}
			}
			//fhand.write("]\n");
			fhand.write("\n");
		}
		fhand.close();
		
		
		// build key file
		writer = new FileWriter("test/key.txt");
		fhand = new BufferedWriter(writer);
		
		for(String word : wordSet){
			fhand.write(word + "\t" + indexMap.get(word) + "\n");
		}
		
		fhand.close();
	}

}
