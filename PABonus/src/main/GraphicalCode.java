package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GraphicalCode {

	protected Code code;
	protected ArrayList<GraphicalPeg> pegs;
	
	protected int x;
	protected int y;
	
	public GraphicalCode(Code code, int x, int y) {
		this.code = code;
		this.x = x;
		this.y = y;
		
		pegs = new ArrayList<GraphicalPeg>();
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
		
	}
}
