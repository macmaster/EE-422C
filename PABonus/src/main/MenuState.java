package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuState extends GameState {
	
	private ArrayList<RandomBrain> brains;
	
	private int currentChoice = 0;
	private String[] options = {
		"Start",
		"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			
			brains = new ArrayList<RandomBrain>();
			
			titleColor = Color.BLUE;
			titleFont = new Font(
					"Century Gothic",
					Font.PLAIN,
					56);
			
			font = new Font("Arial", Font.PLAIN, 24);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {}
	
	public void update() {
		
		//generate random arrivals:
		boolean brainArrival = Math.random() < .053;
		if(brainArrival) {
			brains.add(new RandomBrain());
		}
		
		//update brains
		for(RandomBrain brain : brains) {
			brain.update();
		}
	}
	
	public void draw(Graphics2D g) {
		//draw bg
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		//draw brains
		for(RandomBrain brain : brains) {
			brain.draw(g);
		}
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(Game.GAME_NAME, 160, 140);
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLUE);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 290, 280 + i * 30);
		}
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			brains.add(new RandomBrain());
		}
		if(currentChoice == 1) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
}










