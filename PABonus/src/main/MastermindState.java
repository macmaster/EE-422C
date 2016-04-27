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
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * Manages the current state of the master mind GUI game
 * Contains the master mind image
 */
public class MastermindState extends GameState {

	public static GameStateManager gsm;
	private BufferedImage mastermindImage;
	private MastermindBoard board;
	private ArrayList<Code> guesses;
	
	protected static int MAX_GUESSES = 15;
	protected static int NUM_COLORS = 5;
	protected static int NUM_PEG_HOLES = 3;
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
		
		// animation unit
		frameUnit = gsm.currentlyTesting ? 480 : 0; 
		
		// place the game board
		int boardY = GamePanel.HEIGHT * 7 / 16;
		board = new MastermindBoard(0, boardY, GamePanel.WIDTH, GamePanel.HEIGHT - boardY);
		
		//Establish this as clickable
		gsm.panel.addMouseListener(board);
	}

	@Override
	public void init(GameState lastState) {
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
		if(frameUnit > 120) {
			int mastermindY = (GamePanel.HEIGHT / 16);
			int mastermindX = (GamePanel.WIDTH / 2) - (mastermindImage.getWidth() / 2);
			g.drawImage(mastermindImage,  mastermindX, mastermindY, null);
		}
		
		//draw game "board"
		if(frameUnit > 120) {
			board.draw(g);
		}
		
		// pixel alphas
		if(frameUnit > 120 && frameUnit < 240) {
			Composite comp = g.getComposite();
			float alpha = (float)((240 - frameUnit) * 0.1/12.0);			
			g.setColor(Color.black);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		   g.fillRect(0,0,GamePanel.WIDTH, GamePanel.HEIGHT);
		   g.setComposite(comp);
		}

		// title animation
		FontMetrics metrics = g.getFontMetrics(titleFont);
		int animY = (int)(280 + (frameUnit / 120.0) * (GamePanel.HEIGHT / 16)); 
		int titleY = frameUnit < 120 ?  animY : GamePanel.HEIGHT * 4 / 10;
		int titleX = (GamePanel.WIDTH - metrics.stringWidth(Game.GAME_NAME)) / 2;
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(Game.GAME_NAME, titleX, titleY);

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
