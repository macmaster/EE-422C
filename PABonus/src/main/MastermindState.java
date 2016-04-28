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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * Manages the current state of the master mind GUI game
 * Contains the master mind image
 */
public class MastermindState extends GameState {

	/** master game state manager */
	public static GameStateManager gsm;
	
	/** mastermind resource image */
	private BufferedImage mastermindImage;
	
	/** subclass mastermind board */
	private MastermindBoard board;
	
	/** list of prev guesses */
	private ArrayList<Code> guesses;
	
	/** current guess index */
	protected int guessNumber;
	
	/** title color for font */
	protected Color titleColor;
	
	/** title font to print in */
	protected Font titleFont;
	
	/** win flag for current master mind state */
	protected boolean winFlag;
	
	/** lose flag for current master mind state */
	protected boolean loseFlag;
	
	/** animation unit for GUI placement */
	private int frameUnit;
	
	/** x-location of the back button */
	private int backX = GamePanel.WIDTH / 20;
	
	/** y-location of the back button */
	private int backY = GamePanel.WIDTH / 20;
	
	public MastermindState(GameStateManager gameStateManager) {
		// build the game title
		gsm = gameStateManager;
		titleColor = Color.white;
		titleFont = new Font(
				"Century Gothic",
				Font.PLAIN,
				27*GamePanel.HEIGHT/320);
	}

	@Override
	public void init(GameState lastState) {
		/// animation unit
		frameUnit = gsm.currentlyTesting ? 480 : 0; 
		
		// place the game board
		loseFlag = winFlag = false;
		int boardY = GamePanel.HEIGHT * 7 / 16;
		board = new MastermindBoard(0, boardY, 
					GamePanel.WIDTH, GamePanel.HEIGHT - boardY);
		
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
		
		//draw back button
		if(frameUnit > 120) {
			g.drawImage(Resources.MENU_IMAGE, backX, backY, null);
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
		String titleText = Game.GAME_NAME;
		int animY = (int)(7*GamePanel.HEIGHT/24 + (frameUnit / 120.0) * (GamePanel.HEIGHT * 13 / 120)); 
		
		// game was won
		if(winFlag){
			g.setColor(Color.WHITE);
			titleText = "CONGRATULATIONS, YOU WIN!";
		}
		// game was lost
		else if(loseFlag){
			g.setColor(Color.WHITE);
			titleText = "SORRY, YOU LOSE!";
		}
		
		//draw title banner
		int titleY = frameUnit < 120 ?  animY : GamePanel.HEIGHT * 4 / 10;
		int titleX = (GamePanel.WIDTH - metrics.stringWidth(titleText)) / 2;
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(titleText, titleX, titleY);
		
		
	}

	public void mouseReleased(MouseEvent me) {
		if (gsm.currentState == gsm.GAMESTATE) {
			//test board clicked
			board.mouseReleased(me);
			//test back button clicked
			if (me.getX() > backX && me.getX() < backX + Resources.MENU_IMAGE.getWidth() && me.getY() > backY
					&& me.getY() < backY + Resources.MENU_IMAGE.getHeight()) {
				backClicked();
			} 
		}
	}
	
	/**
	 * back button was clicked
	 * send the state back to the menu
	 */
	private void backClicked() {
		gsm.setState(GameStateManager.MENUSTATE);
	}
	
	/**
	 * Checks for a master mind winner
	 * useful for a win/lose state 
	 * @return the winner flag
	 */
	public boolean checkWinner(){
		return winFlag;
	}
	
	/**
	 * Sets the winner flag 
	 * useful for a win/lose state 
	 * @return the winner flag
	 */
	public void setWinner(boolean win){
		winFlag = win;
	}
	
	/**
	 * Checks for a master mind loser
	 * useful for a win/lose state 
	 * @return the loser flag
	 */
	public boolean checkLoser(){
		return loseFlag;
	}
	
	/**
	 * Sets the loser flag 
	 * useful for a win/lose state 
	 * @return the loser flag
	 */
	public void setLoser(boolean lose){
		loseFlag = lose;
	}
	
	@Override
	public void keyPressed(int k){}
	
	@Override
	public void keyReleased(int k){}
	
	@Override
	public void keyTyped(char key){
		board.keyTyped(key);
	}
}
