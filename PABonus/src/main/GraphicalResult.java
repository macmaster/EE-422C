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
	
	/** GraphicalResult
	 * Construct a GUI result object
	 * off a back-end result object
	 * 
	 * @param result underlying back-end result 
	 * @param x GUI x position
	 * @param y GUI y position
	 */
	public GraphicalResult(Result result, int x, int y) {
		this.result = result;
		this.x = x;
		this.y = y;
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
