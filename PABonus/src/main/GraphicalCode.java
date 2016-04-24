package main;

import java.awt.Graphics2D;

public class GraphicalCode {

	protected Code code;
	
	protected int x;
	protected int y;
	
	public GraphicalCode(Code code, int x, int y) {
		this.code = code;
		this.x = x;
		this.y = y;
	}
	
	public Code getCode() {
		return Code;
	}
	
	public void setCode(Code code) {
		this.code = code;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		
	}
	
}
