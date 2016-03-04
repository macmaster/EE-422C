/** WordLadderSolver *****************************************
 * Class to search for the Word Ladder between 2 words.
 * Calls other methods to implement BFS.
 * Implements the validateResult method. Checks valid ladder.
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, hjw396
 * @author Ronald Macmaster, Horng-Bin Justin Wei 
 * @version 1.01 2/27/2016
 ************************************************************/

package assignment4;

// data structures
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface{
	// Dictionary Data Structure
	private Dictionary dictionary;
	private static final String defaultDictionaryName = "A4-words.txt";
	
	//word graph
	private int count;
	private WordGraph wordGraph;
	

	// add a constructor for this object. 
	// HINT: it would be a good idea to set up the dictionary there
	WordLadderSolver(String dictionaryName){
		dictionary = new Dictionary(dictionaryName);
		count = dictionary.getCapacity();
		wordGraph = new WordGraph(count);
		wordGraph.buildGraph(dictionary);
	}
	
	WordLadderSolver(){
		this(defaultDictionaryName);
	}
	

	/** search algorithm
		BFS(Graph, roof){
			For each node in G
			N distance = infinity
			N parent = null
			Empty Queue Q
			Root distance = 0
			Q enque(roof)
			While(not empty){
					For each node adjacent to current
					If(n.distance = invfinity)
						n.distance = current.distance+1
						n.parent = current
						Q.enqueue(N)
	 */
	// do not change signature of the method implemented from the interface
	@Override
	public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException{
		Word start = dictionary.getWord(startWord);
		Word end = dictionary.getWord(endWord);
		Queue<Word> searchQueue = new LinkedList<Word>();
		Set<Word> visitedWords = new HashSet<Word>(); 
		
		// same-word empty ladder
		if(start.equals(end)){
			start.setParent(null);
			return reconstructPath(start, end);
		}
		
		// Breadth-First search
		start.setParent(null);
		searchQueue.add(start);
		visitedWords.add(start);
		while(!searchQueue.isEmpty()){
			// visit next word in queue
			Word word = searchQueue.remove();
			Integer index = word.getKey();
			List<Integer> adjacentWords = wordGraph.getNeighbors(index);
			
			// add each
			for(Integer neighborIndex : adjacentWords){
				Word neighbor = dictionary.getWord(neighborIndex);
				if(!visitedWords.contains(neighbor)){
					neighbor.setParent(word);
					visitedWords.add(neighbor); // mark word
					searchQueue.add(neighbor); // enqueue neighbor
					if(neighbor.equals(end)){ // found goal
						return reconstructPath(start, end);
					}
				}
			}
			
		}
		
		//failed to find word ladder
		System.err.println("No word ladder between: " + start.getWord() + " and " + end.getWord() + ".");
		return new LinkedList<String>();
		
	}

	//checks if word ladder is valid: if words are in dictionary, if every two adjacent word are off by only 1 letter
	@Override
	public boolean validateResult(String startWord, String endWord, List<String> wordLadder){
		if(!startWord.equals(wordLadder.get(0)) || !endWord.equals(wordLadder.get(wordLadder.size()-1))) {
			return false;
		}
		for(int i = 0; i < wordLadder.size() - 1; i++) {
			if(dictionary.containsWord(wordLadder.get(i)) && dictionary.containsWord(wordLadder.get(i + 1))) {
				if(!diffByOne(wordLadder.get(i), wordLadder.get(i +1)))
					return false;
			} else 
				return false;
		}
		return true;
//		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	//makes sure the adjacent words are only off by 1 letter
	private boolean diffByOne(String s1, String s2) {
		int counter = 0;
		for(int i = 0; i < 5; i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				counter++;
			}
		}
		return counter == 1;
	}
	
	private List<String> reconstructPath(Word start, Word end){
		List<String> wordLadder = new LinkedList<String>();
		List<Word> wordPath = new LinkedList<Word>();
		Word current = end;
		Word parent = current.getParent();
		
		// same-word empty ladder
		if(start.equals(end)){
			wordLadder.add(start.getWord());
			wordLadder.add(end.getWord());
			return wordLadder;
		}
		
		// build reverse path of word objects
		wordPath.add(current);
		while(parent != null){
			wordPath.add(parent);
			current = parent;
			parent = current.getParent();
		}
		
		// reverse iterate through the path
		// construct word string list
		ListIterator<Word> listItr = wordPath.listIterator(wordPath.size());
		while(listItr.hasPrevious()){
			current = listItr.previous();
			wordLadder.add(current.getWord());
		}
		
		return wordLadder;
	}
}
