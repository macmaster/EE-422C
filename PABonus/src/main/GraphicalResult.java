package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GraphicalResult {

	protected Result result;
	protected ArrayList<GraphicalPeg> pegs;
	
	protected int x;
	protected int y;
	
	public GraphicalResult(Result result, int x, int y) {
		this.result = result;
		this.x = x;
		this.y = y;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	public void update() {
	}
	
	public void draw(Graphics2D g) {
		for(GraphicalPeg peg : pegs) {
			peg.draw(g);
		}
	}
	
}
