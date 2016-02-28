/** Word ***********************************************
 * Class to encapsulate a Dictionary Word
 * Contains word's string data and other identifiers
 * Used as a node object in the Dictionary graph
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, hjw396
 * @author Ronald Macmaster, Horng-Bin Justin Wei 
 * @version 1.01 2/27/2016
 ************************************************************/

package assignment4;

public class Word{
	// word data
	private String word;

	// word identifiers
	private int key; // key for the word map
	private Word parent; // parent node from visit in graph.

	public Word(String word){
		this.parent = null;
		this.word = word; // word data
	}

	// constructor with key
	public Word(String word, int key){
		this(word);
		this.key = key;
	}

	/** @return word */
	public String getWord(){
		return word;
	}

	/** @return key */
	public int getKey(){
		return key;
	}

	/** @return parent */
	public Word getParent(){
		return parent;
	}

	/**
	 * @param key
	 */
	public void setKey(int key){
		this.key = key;
	}

	/**
	 * @param parent
	 */
	public void setParent(Word parent){
		this.parent = parent;
	}

}
