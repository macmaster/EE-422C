package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

public class MastermindBoard {

	protected int x;
	protected int y;
	
	protected int width;
	protected int height;
	
	protected int brimWidth;
	protected int guessWidth;
	
	protected int resultHeight;
	protected int guessHeight;
	
	public MastermindBoard(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		guessWidth = (int)(width/16.0);
		brimWidth = guessWidth * 2;
		
		resultHeight = (int)(1.0*height/4.0);
		guessHeight = height - resultHeight;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		//Draw board base
		g.setColor(new Color (222,184,135));
		g.fillRect(x, y, width, height);
		g.setColor(new Color(101, 67, 33));
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(8));
		g.drawRect(x + 4, y + 4, width - 8, height - 8);
		g.drawRect(x + 4 + brimWidth, y + 4 + resultHeight, width - 2*brimWidth, height - 8);
		for(int i = 0; i < 13; i++) {
			g.drawRect(x + 4 + brimWidth, y + 4, i*guessWidth, height - 8);
		}
		g.setStroke(s);
		
		//draw strings
		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		g.drawString("Guess", x + 40, y + 40);
		g.drawString("Secret", x + width + 40 - brimWidth, y + 40);
		g.drawString("Code", x + width + 45 - brimWidth, y + 70);
	}
}
