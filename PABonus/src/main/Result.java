/**
 * 
 */
package main;


public class Result{
	
	/** default length */
	private int length = 4;
	
	/** number of whites */
	private int whites = 0;
	
	/** number of blacks */
	private int blacks = 0;
	
	/**
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
	
	/**
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
		if(whites > length - blacks){
			this.whites = 4 - blacks;
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

}
