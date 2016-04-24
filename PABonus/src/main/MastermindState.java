package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MastermindState extends GameState {

	private GameStateManager gsm;
	private BufferedImage mastermindImage;
	private MastermindBoard board;
	
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
		frameUnit = 480;
		board = new MastermindBoard(0, 420, GamePanel.WIDTH, GamePanel.HEIGHT - 420);
	}

	@Override
	public void init() {
		this.mastermindImage = Resources.MASTERMIND_IMAGE;
	}

	@Override
	public void update() {
		if(frameUnit < 500) frameUnit++;
	}

	@Override
	public void draw(Graphics2D g) {
		//draw bg
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw mastermind
		if(frameUnit > 240) {
			g.drawImage(mastermindImage, GamePanel.WIDTH / 2 - mastermindImage.getWidth() / 2, 60, null);
		}
		
		//draw game "board"
		if(frameUnit > 240) {
			board.draw(g);
		}
		
		if(frameUnit > 240 && frameUnit < 480) {
			Composite comp = g.getComposite();
			float alpha = (float)((480 - frameUnit) * 0.1/24.0);
			g.setColor(Color.black);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		    g.fillRect(0,0,GamePanel.WIDTH, GamePanel.HEIGHT);
		    g.setComposite(comp);
		}
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		int titleY = frameUnit < 240 ? (int)(280 + (frameUnit/240.0)*60.0) : 340;
		g.drawString(Game.GAME_NAME, 320, titleY);

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
