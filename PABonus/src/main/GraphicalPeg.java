/** GraphicalPeg *********************************************
 * Graphical User Interface representation of 
 * a master mind peg
 * Encapsulates the peg graphics
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

/**
 * Graphical User Interface representation of 
 * a master mind peg
 * Encapsulates the peg graphics
 */
public class GraphicalPeg {
	
	/** GUI x position */
	protected int x;
	
	/** GUI x position */
	protected int y;
	
	/** peg circle radius */
	protected int radius;
	
	/** GUI color of peg */
	protected Color color;
	
	/** selected peg (different border?) */
	boolean selected;
	
	/** list of valid code colors */
	protected List<Color> colorWheel;
	protected int colorIndex = -1;
	protected static final Color EMPTY_COLOR = new Color(225, 169, 95);
	
	/**
	 * Graphical User Interface representation of 
	 * a master mind peg
	 * Encapsulates the peg graphics
	 * 
	 * @param x position of x in GUI
	 * @param y position of y in GUI
	 * @param color GUI color of the peg
	 */
	public GraphicalPeg(int x, int y, int radius, Color color, List<Color> colorWheel) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
		this.colorWheel = colorWheel;
		this.selected = false;
		if(colorWheel != null) colorIndex = colorWheel.indexOf(color);
	}
	
	/**
	 * Overloaded draw method to draw the graphical peg
	 * @param g game graphics object to paint with
	 */
	public void draw(Graphics2D g) {
		// fill the inside color
		g.setColor(color);
		g.fillOval(x, y, radius * 2, radius *2 );
		
		// draw the border
		if(selected){
			g.setColor(Color.WHITE); // white border
		}
		else{
			Color darkBrown = new Color(101, 67, 33);
			g.setColor(darkBrown); //dark brown			
		}
		
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(5));
		g.drawOval(x, y, radius * 2, radius * 2);
		g.setStroke(s);
		
	}
	
	/**
	 * fetch the peg radius as an integer
	 * @return radius
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * update the peg radius
	 * @return radius
	 */
	public void setRadius(int radius) {
		if(radius > 0){
			this.radius = radius;
		}
	}
	
	/**
	 * fetch the peg color
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * update the peg color
	 * @return radius
	 */
	public boolean setColor(Color color) {
		// update color index
		int n = colorWheel.size(); 
		for(int idx = 0; idx < n; idx++){
			Color valid = colorWheel.get(idx);
			if(color.equals(valid)){
				// set color
				colorIndex = idx;
				this.color = color;
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * boolean mouse function to flag the mouse
	 * is within the given boundaries
	 * @param mX
	 * @param mY
	 * @return
	 */
	public boolean containsLoc(int mX, int mY) {
		int diam = radius * 2;
		return mX - x > 0 && mY - y > 0 &&  mX - x < diam && mY - y < diam;
	}
	
	/**
	 * sets the selection flag
	 * @param select
	 */
	public void setSelection(boolean select){
		this.selected = select;
	}

	/**
	 * boolean parameter
	 * indicates a forward rollover in color wheel
	 */
	public void click(boolean cycleForward) {
		int n = colorWheel.size(); // number of colors
		if(cycleForward) {
			//forward rollover
			colorIndex = (colorIndex + 1) % n;
		}
		else{
			// reverse rollover
			colorIndex = (colorIndex + (n-1)) % n;
		}
		
		// set new color
		this.color = colorWheel.get(colorIndex);
	}
	
	
	
}
