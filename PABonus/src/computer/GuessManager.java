/** GuessChecker ********************************************
 * Manages the backend of the Mastermind game
 * Checks the guesses up to a guess limit
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
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
