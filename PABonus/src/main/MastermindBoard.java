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
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Manages the back canvas of the mastermind board
 * Used as a GUI component
 */
public class MastermindBoard implements MouseListener{

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
		int numGuesses = MastermindState.MAX_GUESSES;
		int numPegs = MastermindState.NUM_PEG_HOLES;
		
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
			results.add(new GraphicalResult(new Result(0, 0), pegX, pegY, MastermindState.NUM_PEG_HOLES));
		}
		
		// build guess and secret GUI pegs
		pegY = y + resultHeight + pegRadius;
		pegRadius = GraphicalCode.DEFAULT_RADIUS * 3 / 2;
		pendingGuess = new GraphicalCode(new Code(numPegs), x + (brimWidth / 3), pegY, pegRadius);
		secretCode = new SecretCode(x + width - (2 * brimWidth / 3), pegY, pegRadius, MastermindState.NUM_PEG_HOLES);
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
				width - 2 * (brimWidth + border), height - border);
		
		for(int i = 0; i <= MastermindState.MAX_GUESSES; i++) {
			g.drawRect(x + border/2 + brimWidth, y + border / 2, i*guessWidth, height - border);
		}
		g.setStroke(s);
		
		//draw strings
		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		g.drawString("Guess", x + 40, y + 40);
		g.drawString("Secret", x + width + 40 - brimWidth, y + 40);
		g.drawString("Code", x + width + 45 - brimWidth, y + 70);
		
		if(pendingGuess.checkValid()) {
			g.drawImage(Resources.SUBMIT_IMAGE, x + 20, y + 60, null);	
		}
		else {
			g.setFont(new Font("Century Gothic", Font.PLAIN, 16));
			g.drawString("Click below", x + 27, y + 70);
			g.drawString("to form a guess!", x + 17, y + 95);
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
		int mX = me.getX();
		int mY = me.getY();
		for(GraphicalPeg peg : pendingGuess.pegs) {
			if(peg.containsLoc(mX, mY)){
				peg.click();
			}
		}
		if(mX - (x+20) > 0 
				&& mY - (y + 60) > 0 
				&& mX - (x + 20) < Resources.SUBMIT_IMAGE.getWidth()
				&& mY - (y + 60) < Resources.SUBMIT_IMAGE.getHeight()) {
			submitCode();
		}
	}
	
	protected void submitCode() {
		Result result = pendingGuess.getCode().compareCode(secretCode.secretCode);
		GraphicalCode setCode = codes.get(nextGuess);
		setCode.setCode(pendingGuess.getCode());
		GraphicalResult setResult = results.get(nextGuess);
		setResult.setResult(result);
		
		//now refresh pending guess
		pendingGuess = new GraphicalCode(new Code(pendingGuess.pegs.size()), x + (brimWidth / 3), pendingGuess.y, pendingGuess.radius);
		nextGuess++;
		if(result.isWinner()) {
			winner();
		}
	}
	
	protected void winner() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MastermindState.gsm.setState(GameStateManager.WINSTATE);
	}
}
