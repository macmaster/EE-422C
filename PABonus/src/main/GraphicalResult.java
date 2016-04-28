/** GraphicalResult *******************************************
 * Graphical User Interface representation of 
 * a master mind game response
 * Constructed off a back-end result object
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GraphicalResult {
	
	/** backend result data */
	protected Result result;
	
	/** list of GUI pegs to print */
	protected ArrayList<GraphicalPeg> pegs;
	
	/** top level code x position */
	protected int x;
	
	/** top level code y position */
	protected int y;
	
	/** distance between pegs */
	protected int distBetween = GamePanel.HEIGHT / 32;
	
	/** the number of pegs in the result */
	protected int numberPegs;
	
	/** pixel radius of each result peg */
	protected int pegRadius = GamePanel.HEIGHT / 96;
	
	/** GraphicalResult
	 * Construct a GUI result object
	 * off a back-end result object
	 * 
	 * @param result underlying back-end result 
	 * @param x GUI x position
	 * @param y GUI y position
	 */
	public GraphicalResult(Result result, int x, int y, int numberPegs) {
		// initialize constructor variables
		this.result = result;
		this.x = x;
		this.y = y;
		this.numberPegs = numberPegs;
		
		// build the list of graphical pegs from back-end result
		setResult(result);
	}

	/** getCode
	 * fetch back-end result object
	 * @return result
	 */
	public Result getResult() {
		return result;
	}

	/** setCode
	 * re initialize the back-end result object
	 */
	public void setResult(Result result) {
		pegs = new ArrayList<GraphicalPeg>();
		if(result != null) {
			int whites = result.getWhites();
			int blacks = result.getBlacks();
			for(int i = 0; i < numberPegs; i++) {
				boolean isEven = i % 2 == 0;
				boolean isBlack = i < blacks;
				boolean isColored = i < whites + blacks;
				pegs.add(new GraphicalPeg(
						x + (isEven ? 0 : distBetween),
						y + (i / 2) * distBetween,
						pegRadius,
						isBlack ? Color.black : 
							(isColored ? Color.white : GraphicalPeg.EMPTY_COLOR),
						null));
			}
		}
		this.result = result;
	}
	
	
	/** update
	 * called before the draw method
	 */
	public void update() {
	}
	
	/** draw
	 * overloaded draw method to draw all the pegs at once
	 * @param g game graphics
	 */
	public void draw(Graphics2D g) {
		for(GraphicalPeg peg : pegs) {
			peg.draw(g);
		}
	}
	
}
