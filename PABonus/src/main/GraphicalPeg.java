package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class GraphicalPeg {
	
	protected int x;
	protected int y;
	protected int radius;
	protected Color color;
	
	public GraphicalPeg(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		
		this.color = color == null ? Color.LIGHT_GRAY : color;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x, y, radius*2, radius*2);
		
		g.setColor(new Color(101, 67, 33)); //dark brown
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(5));
		g.drawOval(x, y, radius*2, radius*2);
		g.setStroke(s);
		
	}
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
}
