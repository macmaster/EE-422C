/**
 * 
 */
package computer;


public class GuessManager{
	
	private int guessCount;
	private int guessLimit;
	
	public GuessManager(){
		guessCount = 0;
		guessLimit = 12;
	}
	
	public GuessManager(int maxGuesses){
		guessCount = 0;
		guessLimit = maxGuesses;
	}
	
	

}
