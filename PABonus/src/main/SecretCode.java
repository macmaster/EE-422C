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
	private static int length = 4;
	
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
	
	public static Code randomCode(int length) {
		Code c = new Code(length);
		for(int i = 0; i < length; i++) {
			int ri = (int)(Math.random() * length);
			c.setColor(i, c.getColors().get(ri));
		}
		return c;
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
		
		// draw question marks
		g.setColor(Color.black);
		g.setFont(new Font("Comic Sans", Font.PLAIN, 24));
		for(GraphicalPeg peg : pegs) {
			int pegX = peg.x + 12 * peg.radius / 16;
			int pegY = peg.y + 22 * peg.radius / 16;
			g.drawString("?", pegX, pegY);
		}
	}
	
	@Override
	public Code getCode() {
		return code;
	}

}
