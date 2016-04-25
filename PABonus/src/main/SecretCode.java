package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class SecretCode extends GraphicalCode {

	public SecretCode(int x, int y, int radius) {
		super(new Code(4), x, y, radius);
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		g.setFont(new Font(
				"Comic Sans",
				Font.PLAIN,
				24));
		g.setColor(Color.black);
		for(GraphicalPeg peg : pegs) {
			g.drawString("?", peg.x + 12 * peg.radius / 16, peg.y + 22 * peg.radius / 16);
		}
	}

}
