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

import java.awt.Color;
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
	
	/** graphical peg radius */
	protected int radius;
	
	/**
	 * default value for a peg radius
	 */
	protected static int DEFAULT_RADIUS = GamePanel.HEIGHT / 48;
	
	/** padding space between pegs */
	protected static int pegPadding = GamePanel.HEIGHT / 20;
	
	/** GraphicalCode
	 * Construct a GUI code object
	 * off a back-end code object
	 * 
	 * @param code underlying back-end code 
	 * @param x GUI x position
	 * @param y GUI y position
	 */
	public GraphicalCode(Code code, int x, int y, int radius) {
		// initialize code variables
		this.code = code;
		this.x = x;
		this.y = y;
		this.radius = radius;
		
		// set peg coordinates
		pegs = new ArrayList<GraphicalPeg>();
		for(int i = 0; i < code.getLength(); i++) {
			pegs.add(new GraphicalPeg(x, y + i*(radius + pegPadding), radius, code.getColor(i), code.getColors()));
		}
	}
	
	/** getCode
	 * fetch back-end code object
	 * @return code
	 */
	public Code getCode() {
		if (checkValid()) {
			ArrayList<Color> codeColors = new ArrayList<Color>();
			for(GraphicalPeg peg: pegs) {
				codeColors.add(peg.colorWheel.get(peg.colorIndex));
			}
			return new Code(codeColors);
		} else {
			return null;
		}
	}
	
	/** setCode
	 * re initialize the back-end code object
	 */
	public void setCode(Code code) {
		for(int i = 0; i < code.getLength(); i++) {
			pegs.get(i).color = code.getColor(i);
		}
		this.code = code;
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
	
	public boolean checkValid() {
		for(GraphicalPeg peg: pegs) {
			if(peg.colorIndex < 0) return false;
		}
		return true;
	}
}
