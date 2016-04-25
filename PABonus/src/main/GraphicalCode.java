/** GraphicalCode *******************************************
 * Graphical User Interface representation of 
 * a master mind game code
 * Constructed off a back-end code object
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GraphicalCode {
	
	/** backend code data */
	protected Code code;
	
	/** list of GUI pegs to print */
	protected ArrayList<GraphicalPeg> pegs;
	
	/** top level code x position */
	protected int x;
	
	/** top level code y position */
	protected int y;
	
	/** GraphicalCode
	 * Construct a GUI code object
	 * off a back-end code object
	 * 
	 * @param code underlying back-end code 
	 * @param x GUI x position
	 * @param y GUI y position
	 */
	public GraphicalCode(Code code, int x, int y) {
		this.code = code;
		this.x = x;
		this.y = y;
		
		pegs = new ArrayList<GraphicalPeg>();
		for(int i = 0; i < code.getLength(); i++) {
			pegs.add(new GraphicalPeg(x, y + i*60, 21, code.getColor(i)));
		}
	}
	
	/** getCode()
	 * back-end code object
	 * @return
	 */
	public Code getCode() {
		return code;
	}
	
	/** setCode()
	 * re initialize the back-end code object
	 * @return
	 */
	public void setCode(Code code) {
		for(int i = 0; i < code.getLength(); i++) {
			pegs.add(new GraphicalPeg(x, y + i*60, 21, code.getColor(i)));
		}
		this.code = code;
	}
	
	/** update()
	 * called before the draw method
	 */
	public void update() {
		
	}
	
	/** draw()
	 * overloaded draw method to draw all the pegs at once
	 * @param g game graphics
	 */
	public void draw(Graphics2D g) {
		for(GraphicalPeg peg : pegs) {
			peg.draw(g);
		}
	}
	
}
