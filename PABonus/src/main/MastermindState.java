package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MastermindState extends GameState {

	private GameStateManager gsm;
	private BufferedImage mastermindImage;
	
	private Color titleColor;
	private Font titleFont;
	
	private int frameUnit;
	
	public MastermindState(GameStateManager gameStateManager) {
		gsm = gameStateManager;
		titleColor = Color.white;
		titleFont = new Font(
				"Century Gothic",
				Font.PLAIN,
				108);
	}

	@Override
	public void init() {
		this.mastermindImage = Resources.MASTERMIND_IMAGE;
	}

	@Override
	public void update() {
		frameUnit++;
	}

	@Override
	public void draw(Graphics2D g) {
		//draw bg
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw mastermind
		if(frameUnit > 240) 
			g.drawImage(mastermindImage, GamePanel.WIDTH / 2 - mastermindImage.getWidth() / 2, 50, null);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(Game.GAME_NAME, 320, 280 );
		
		//draw game "table"
		g.setColor(new Color (222,184,135));
		g.fillRect(0, 240, GamePanel.WIDTH, GamePanel.HEIGHT);

	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
