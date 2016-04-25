package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GraphicalResult {

	protected Result result;
	protected ArrayList<GraphicalPeg> pegs;
	
	protected int x;
	protected int y;
	protected int distBetween = 30;
	protected int numberPegs;
	protected int pegRadius = 10;
	
	public GraphicalResult(Result result, int x, int y, int numberPegs) {
		this.result = result;
		this.x = x;
		this.y = y;
		this.numberPegs = numberPegs;
		
		pegs = new ArrayList<GraphicalPeg>();
		if(result != null) {
			int whites = result.getWhites();
			int blacks = result.getBlacks();
			for(int i = 0; i < numberPegs; i++) {
				boolean isEven = i%2 == 0;
				boolean isWhite = i < whites;
				boolean isColored = i < blacks;
				pegs.add(new GraphicalPeg(
						x + (isEven ? 0 : distBetween),
						y + i/2*distBetween,
						pegRadius,
						isWhite ? Color.white : (isColored ? Color.black : GraphicalPeg.EMPTY_COLOR)));
			}
		}
		else {
			
		}
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
