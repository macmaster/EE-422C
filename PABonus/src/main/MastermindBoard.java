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
	protected ArrayList<GraphicalResult> results;
	protected SecretCode secretCode;
	protected GraphicalCode pendingGuess;
	
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
		
		// code proportions
		guessWidth = (int)(width/16.0);
		brimWidth = guessWidth * 2;
		resultHeight = (int)(1.0*height/4.0);
		guessHeight = height - resultHeight;
		
		// Graphical code initializations
		codes = new ArrayList<GraphicalCode>();
		ArrayList<Color> colors = new ArrayList<Color>();
		for(int i = 0; i < 12; i++) {
			codes.add(new GraphicalCode(new Code(4), x + brimWidth + 21 + i * guessWidth, y + resultHeight + 20, 21));
		}
		
		//init results
		results = new ArrayList<GraphicalResult>();
		for(int i = 0; i < 12; i++) {
			results.add(new GraphicalResult(new Result(0, 0), x + brimWidth + 21 + i * guessWidth, y + 20, 4));
		}
		
		pendingGuess = new GraphicalCode(new Code(4), x + brimWidth / 3, y + resultHeight + 20, 30);
		secretCode = new SecretCode(x + width - brimWidth + brimWidth / 3, y + resultHeight + 20, 30);
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
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
