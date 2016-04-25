package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GraphicalCode {

	protected Code code;
	protected ArrayList<GraphicalPeg> pegs;
	
	protected int x;
	protected int y;
	protected int radius;
	
	public GraphicalCode(Code code, int x, int y, int radius) {
		this.code = code;
		this.x = x;
		this.y = y;
		this.radius = radius;
		
		pegs = new ArrayList<GraphicalPeg>();
		for(int i = 0; i < code.getLength(); i++) {
			pegs.add(new GraphicalPeg(x, y + i*(radius + 50), radius, code.getColor(i)));
		}
	}
	
	public Code getCode() {
		return code;
	}
	
	public void setCode(Code code) {
		this.code = code;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		for(GraphicalPeg peg : pegs) {
			peg.draw(g);
		}
	}
}
