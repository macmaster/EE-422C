/** MasterMindState ********************************************
 * Manages the current state of the mastermind GUI game
 * Contains the mastermind image
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
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
import java.util.ArrayList;

import javax.imageio.ImageIO;
/**
 * Manages the current state of the master mind GUI game
 * Contains the master mind image
 */
public class MastermindState extends GameState {

	private GameStateManager gsm;
	private BufferedImage mastermindImage;
	private MastermindBoard board;
	private ArrayList<Code> guesses;
	
	protected static int MAX_GUESSES = 15;
	protected static int NUM_COLORS = 6;
	protected static int NUM_PEG_HOLES = 6;
	protected int guessNumber;
	
	protected Color titleColor;
	protected Font titleFont;
	
	private int frameUnit;
	
	public MastermindState(GameStateManager gameStateManager) {
		// build the game title
		gsm = gameStateManager;
		titleColor = Color.white;
		titleFont = new Font(
				"Century Gothic",
				Font.PLAIN,
				GamePanel.HEIGHT / 10);
		
		/** what is a frame Unit? */
		frameUnit = gsm.currentlyTesting ? 480 : 0;
		
		// place the game board
		int boardY = GamePanel.HEIGHT * 7 / 16;
		board = new MastermindBoard(0, boardY, GamePanel.WIDTH, GamePanel.HEIGHT - boardY);
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
			int mastermindY = (GamePanel.HEIGHT / 16);
			int mastermindX = (GamePanel.WIDTH / 2) - (mastermindImage.getWidth() / 2);
			g.drawImage(mastermindImage,  mastermindX, mastermindY, null);
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
		int titleY = frameUnit < 240 ? (int)(280 + (frameUnit/240.0)*60.0) : GamePanel.HEIGHT * 3 / 10;
		g.drawString(Game.GAME_NAME, GamePanel.WIDTH / 4, titleY);

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
