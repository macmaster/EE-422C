/** MasterMindBoard ********************************************
 * Manages the back canvas of the mastermind board
 * Used as a GUI component
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Manages the back canvas of the mastermind board
 * Used as a GUI component
 */
public class MastermindBoard {

	/** Board x position */
	protected int x;
	/** Board y position */
	protected int y;
	
	/** Board width */
	protected int width;
	/** Board height */
	protected int height;
	
	/** border thickness */
	protected int brimWidth;
	/** guess border thickness */
	protected int guessWidth;
	
	/** height of result area*/
	protected int resultHeight;
	/** height of guess area*/
	protected int guessHeight;
	
	/** list of all the graphical codes to print */
	protected ArrayList<GraphicalCode> codes;
	/** list of all the graphical results to print */
	protected ArrayList<GraphicalResult> results;
	/** current game's secret code */
	protected SecretCode secretCode;
	
	/** user's pending guess */
	protected GraphicalCode pendingGuess;
	
	/** current index of the user's guess */
	protected int nextGuess = 0;
	
	/**
	 * creates a new mastermind board object
	 * @param x initial x position
	 * @param y initial y position
	 * @param width initial width
	 * @param height initial height
	 */
	public MastermindBoard(int x, int y, int width, int height) {
		
		// initialize gui data
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		// game state data
		int numGuesses = Settings.NUM_GUESSES;
		int numPegs = Settings.NUM_PEGS;
		
		// code proportions
		guessWidth = (int)(width / (numGuesses + 4.0));
		brimWidth = guessWidth * 2;
		resultHeight = (int)(height / 6.0);
		guessHeight = height - resultHeight;
		
		// Graphical code initializations
		int pegX = 0, pegY = 0, pegRadius = 0;
		codes = new ArrayList<GraphicalCode>();
		for(int i = 0; i < numGuesses; i++) {
			// build GUI peg
			pegRadius = GraphicalCode.DEFAULT_RADIUS;
			pegX = x + brimWidth + pegRadius + i * guessWidth;
			pegY = y + resultHeight + pegRadius + guessHeight / (numPegs * 2);
			codes.add(new GraphicalCode(new Code(numPegs), pegX, pegY, pegRadius));
		}
		
		// init results
		results = new ArrayList<GraphicalResult>();
		for(int i = 0; i < numGuesses; i++) {
			// build GUI peg
			pegRadius = GraphicalCode.DEFAULT_RADIUS;
			pegX = x + brimWidth + pegRadius + i * guessWidth;
			pegY = y + pegRadius;
			results.add(new GraphicalResult(new Result(0, 0), pegX, pegY, Settings.NUM_PEGS));
		}
		
		// build guess and secret GUI pegs
		pegY = y + resultHeight + pegRadius;
		pegRadius = GraphicalCode.DEFAULT_RADIUS * 5 / 4;
		pendingGuess = new GraphicalCode(new Code(numPegs), x + (brimWidth / 3), pegY, pegRadius);
		secretCode = new SecretCode(x + width - (2 * brimWidth / 3), pegY, pegRadius, Settings.NUM_PEGS);
	}
	
	/**
	 * update the current codes to print
	 */
	public void update() {
		for(GraphicalCode code : codes) {
			code.update();
		}
		for(GraphicalResult result : results) {
			result.update();
		}
		secretCode.update();
		pendingGuess.update();
	}
	
	/**
	 * draw the master mind board
	 * overloaded method
	 * @param g
	 */
	public void draw(Graphics2D g) {
		//Draw board base
		g.setColor(new Color (222,184,135));
		g.fillRect(x, y, width, height);
		g.setColor(new Color(101, 67, 33));
		
		// draw board borders
		int border = 8; // thickness
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(border));
		g.drawRect(x + border / 2, y + border / 2, width - border, height - border);
		
		//off border
		g.drawRect(x + border/2 + brimWidth, y + (2 * border) + resultHeight, 
				Settings.NUM_GUESSES * guessWidth, height - border);
		
		for(int i = 0; i <= Settings.NUM_GUESSES; i++) {
			g.drawRect(x + border/2 + brimWidth, y + border / 2, i * guessWidth, height - border);
		}
		g.setStroke(s);
		
		//draw strings
		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		g.drawString("Guess", x + (brimWidth / 8), y + (guessHeight / 10));
		g.drawString("Secret", x + width - (brimWidth * 9 / 10), y + (guessHeight * 4 / 40));
		g.drawString("Code", x + width - (brimWidth * 9 /10), y + (guessHeight * 7 / 40));
		
		if(pendingGuess.checkValid()) {
			g.drawImage(Resources.SUBMIT_IMAGE, x + (brimWidth / 8), y + (guessHeight / 8), null);	
		}
		else {
			g.setFont(new Font("Century Gothic", Font.PLAIN, 16));
			g.drawString("Click below", x + (brimWidth / 8), y + (guessHeight * 3 / 20));
			g.drawString("to form a guess!", x + (brimWidth / 8), y + (guessHeight * 4 / 20));
		}
		
		//draw codes
		for(GraphicalCode code : codes) {
			code.draw(g);
		}
		
		//draw results
		for(GraphicalResult result : results) {
			result.draw(g);
		}
		
		secretCode.draw(g);
		pendingGuess.draw(g);
	}

	public void mouseReleased(MouseEvent me) {
		int mX = me.getX();
		int mY = me.getY();
		for(GraphicalPeg peg : pendingGuess.pegs) {
			if(peg.containsLoc(mX, mY)){
				peg.click();
			}
		}
		if(mX - (x + (brimWidth / 8)) > 0 
				&& mY - (y + (guessHeight / 8)) > 0 
				&& mX - (x + (brimWidth / 8)) < Resources.SUBMIT_IMAGE.getWidth()
				&& mY - (y + (guessHeight / 8)) < Resources.SUBMIT_IMAGE.getHeight()) {
			submitCode();
		}
	}
	
	protected void submitCode() {
		// compare guess with answer
		Code guess = pendingGuess.getCode();
		Code answer = secretCode.getCode();
		Result result = guess.compareCode(answer);
		
		
		GraphicalCode setCode = codes.get(nextGuess);
		setCode.setCode(pendingGuess.getCode());
		GraphicalResult setResult = results.get(nextGuess);
		setResult.setResult(result);
		
		//now refresh pending guess
		pendingGuess = new GraphicalCode(new Code(pendingGuess.pegs.size()), 
				x + (brimWidth / 3), pendingGuess.y, pendingGuess.radius);
		nextGuess++;
		
		// update win/loss states
		MastermindState gameState = (MastermindState)MastermindState.gsm.getCurrentState();
		if(result.isWinner()) {
			gameState.setWinner(true);
			endgame();
		}
		else if(nextGuess >= Settings.NUM_GUESSES){
			endgame();
		}
	}
	
	protected void endgame() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MastermindState.gsm.setState(GameStateManager.ENDSTATE);
	}
}
