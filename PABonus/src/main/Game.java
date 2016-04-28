/** Game *****************************************************
 * Top level Game class
 * Calls the initialization methods of GamePanel
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * Entry point.
 * Starts the game.
 */
public class Game {

	/**
	 * Name of the game
	 */
	protected static final String GAME_NAME = "Mastermind";
	
	/** main
	 * Starts the game
	 * @param args string of CL args
	 */
	public static void main(String[] args) {
		GamePanel panel = new GamePanel();
		
		// initialize the JFrame container
		JFrame gameFrame = new JFrame(GAME_NAME);
		gameFrame.setContentPane(panel);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
}
