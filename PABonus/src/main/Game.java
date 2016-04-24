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
	
	/**
	 * Starts the game
	 * @param args No args
	 */
	public static void main(String[] args) {
		GamePanel panel = new GamePanel();
		try {
			Resources.initResources(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JFrame gameFrame = new JFrame(GAME_NAME);
		gameFrame.setContentPane(panel);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
}
