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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * Manages the current state of the master mind GUI game
 * Contains the master mind image
 */
public class MastermindState extends GameState implements MouseListener {

	public static GameStateManager gsm;
	private BufferedImage mastermindImage;
	private MastermindBoard board;
	private ArrayList<Code> guesses;
	
	protected static int MAX_GUESSES = 15;
	protected static int NUM_COLORS = 6;
	protected static int NUM_PEG_HOLES = 3;
	protected int guessNumber;
	
	protected Color titleColor;
	protected Font titleFont;
	
	private int frameUnit;
	
	private int backX = GamePanel.WIDTH / 20;
	private int backY = GamePanel.WIDTH / 20;
	
	public MastermindState(GameStateManager gameStateManager) {
		// build the game title
		gsm = gameStateManager;
		titleColor = Color.white;
		titleFont = new Font("Century Gothic", Font.PLAIN, GamePanel.HEIGHT / 10);
		
		//Establish this as clickable
		gsm.panel.addMouseListener(this);
		
		this.mastermindImage = Resources.MASTERMIND_IMAGE;
		
		init(null);
	}

	@Override
	public void init(GameState lastState) {
		/// animation unit
		frameUnit = gsm.currentlyTesting ? 480 : 0; 
		
		// place the game board
		int boardY = GamePanel.HEIGHT * 7 / 16;
		if(board == null) 
			board = new MastermindBoard(0, boardY, 
					GamePanel.WIDTH, GamePanel.HEIGHT - boardY);
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
		
		//draw back button
		if(frameUnit > 120) {
			g.drawImage(Resources.BACK_IMAGE, backX, backY, null);
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
		int animY = (int)(7*GamePanel.HEIGHT/24 + (frameUnit / 120.0) * (GamePanel.HEIGHT / 16)); 
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
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent me) {}

	@Override
	public void mouseExited(MouseEvent me) {}

	@Override
	public void mousePressed(MouseEvent me) {}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (gsm.currentState == gsm.GAMESTATE) {
			//test board clicked
			board.mouseReleased(me);
			//test back button clicked
			if (me.getX() > backX && me.getX() < backX + Resources.BACK_IMAGE.getWidth() && me.getY() > backY
					&& me.getY() < backY + Resources.BACK_IMAGE.getHeight()) {
				backClicked();
			} 
		}
	}
	
	private void backClicked() {
		gsm.setState(GameStateManager.MENUSTATE);
	}
}
