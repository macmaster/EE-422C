/** Result **************************************************
 * Representation of a Mastermind Code response
 * The response to a user's code guess
 * Contains black and white pegs
 * Checks for successful guess
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;


public class Result{
	
	/** default length */
	private int length = 4;
	
	/** number of whites */
	private int whites = 0;
	
	/** number of blacks */
	private int blacks = 0;
	
	
	/** Result
	 * creates a Result object to encapsulate
	 * the number of white and black answer pegs
	 * 
	 * @param whites number of whites in the answer pegs
	 * @param blacks number of blacks in the answer pegs
	 */
	public Result(int whites, int blacks){
		this.whites = whites;
		this.blacks = blacks;
	}
	
	/** Result
	 * creates a Result object to encapsulate
	 * the number of white and black answer pegs
	 * 
	 * @param whites number of whites in the answer pegs
	 * @param blacks number of blacks in the answer pegs
	 * @param length color code length
	 */
	public Result(int whites, int blacks, int length){
		this(whites, blacks);
		this.length = length;
	}
	
	/** getWhites()
	 * 
	 * returns the number of whites in the response
	 * 
	 * @return number of whites
	 */
	public Integer getWhites(){
		return whites;
	}
	
	/** getBlacks()
	 * 
	 * returns the number of blacks in the response
	 * 
	 * @return number of blacks
	 */
	public Integer getBlacks(){
		return blacks;
	}
	
	/** setWhites()
	 * 
	 * sets the number of whites in the result
	 * 
	 * @param whites
	 */
	public void setWhites(int whites){
		// update whites to valid number
		if(whites > length - blacks){
			this.whites = length - blacks;
		}
		else if(whites < 0){
			this.whites = 0;
		}
		else{
			this.whites = whites;
		}
	}
	
	/** setBlacks()
	 * 
	 * sets the number of blacks in the result
	 * 
	 * @param blacks
	 */
	public void setBlacks(int blacks){
		// update blacks to valid number
		if(blacks > length - blacks){
			this.blacks = length - whites;
		}
		else if(blacks < 0){
			this.blacks = 0;
		}
		else{
			this.blacks = blacks;
		}
	}
	
	/** getLength()
	 * 
	 * @return length of the result code
	 */
	public int getLength(){
		return length;
	}
	
	/** checkSuccess()
	 * 
	 * success if all of the pegs are black
	 * 
	 * @return success true or false
	 */
	public boolean getSuccess(){
		// winning condition
		return length == blacks;
	}

}
