/** GameStateManager *****************************************
 * Graphical User Interface manager for master mind
 * Manages the menu and basic gameplay
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.util.ArrayList;

public class GameStateManager {
	
	/**
	 * Game panel link-back
	 */
	public GamePanel panel;
	
	/** list of possible game states */
	public static ArrayList<GameState> gameStates;
	
	/** list index of current game state */
	private int currentState;
	
	/** menu state index */
	public static final int MENUSTATE = 0;
	
	/** gameplay state index */
	public static final int GAMESTATE = 1;
	
	/** settings state index */
	public static final int SETTINGSSTATE = 2;
	
	/** win state index */
	public static final int WINSTATE = 3;
	
	
	public static final boolean currentlyTesting = true;
	
	/** GameStateManager
	 * Used by a game panel to draw 
	 * based on current state of the game
	 */
	public GameStateManager(GamePanel panel) {
		// build game state list
		this.panel = panel;
		currentState = currentlyTesting ? GAMESTATE : MENUSTATE;
		gameStates = new ArrayList<GameState>();
		gameStates.add(new MenuState(this));
		gameStates.add(new MastermindState(this));
		//gameStates.add(new SettingsState(this));
		//gameStates.add(new WinState(this));
		setState(currentState);
	}
	
	/** setState()  
	 * Updates the gamestate to a valid game state
	 * @param state index of the new game state
	 */
	public void setState(int state) {
		// valid state index
		int lastState = currentState;
		if(state >= 0 && state < gameStates.size()){
			currentState = state;
			gameStates.get(currentState).init(gameStates.get(lastState));
		}
	}
	
	/** update the current game state */
	public void update() {
		gameStates.get(currentState).update();
	}
	
	/** draw the current game state */
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	/** call the key pressed manager */
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	/** call the key released manager */
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
}
