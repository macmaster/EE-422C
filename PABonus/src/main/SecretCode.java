/** SecretCode ********************************************
 * Generates a random secret code answer
 * default secret code length is 4
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class SecretCode extends GraphicalCode {
	
	/** default secret code length */
	protected static int length = 4;
	
	/** flag to show the secret code in draw method*/
	protected boolean showCode = false;
	
	/** back-managed secret code object*/
	protected Code secretCode;
	
	/**
	 * Standard constructor with no length specified
	 * @param x coordinate x for the secret code pegs
	 * @param y coordinate y for the secret code pegs
	 * @param radius secret code peg radius
	 */
	public SecretCode(int x, int y, int radius) {
		super(new Code(length), x, y, radius);
		secretCode = randomCode(length);
	}
	
	/**
	 * Generate a randomly colored code object
	 * @param length the length of the code
	 * @return the generated code object
	 */
	public static Code randomCode(int length) {
		// random color code build
		Code code = new Code(length);
		for(int i = 0; i < length; i++) {
			int ri = (int)(Math.random() * length);
			code.setColor(i, code.getColors().get(ri));
		}
		
		// return the generated code object
		return code;
	}
	
	/**
	 * Standard constructor with a length field specified
	 * @param x coordinate x for the secret code pegs
	 * @param y coordinate y for the secret code pegs
	 * @param radius secret code peg radius
	 * @param length secret code length
	 */
	public SecretCode(int x, int y, int radius, int length) {
		super(new Code(length), x, y, radius);
		secretCode = randomCode(length);
		this.length = length;
	}
	
	/**
	 * override the draw method to 
	 * draw the graphical pegs with question marks 
	 */
	@Override
	public void draw(Graphics2D g) {
		// draw Secret Code pegs
		super.draw(g);
		
		if(!showCode) {
			// draw question marks
			g.setColor(Color.black);
			g.setFont(new Font("Comic Sans", Font.PLAIN, 24));
			for(GraphicalPeg peg : pegs) {
				int pegX = peg.x + 12 * peg.radius / 16;
				int pegY = peg.y + 22 * peg.radius / 16;
				g.drawString("?", pegX, pegY);
			}
		}
	}
	
	@Override
	public Code getCode() {
		return secretCode;
	}
	
	/**
	 * set the show code parameter
	 * @param show flag to show the secret code
	 */
	public void showCode(boolean show) {
		if (show) {
			this.setCode(secretCode);
		} 
		else {
			this.setCode(new Code(length));
		}
		showCode = show;
	}

}
