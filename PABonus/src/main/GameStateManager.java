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

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameStateManager {
	
	/**
	 * Game panel link-back
	 */
	public static GamePanel panel;
	
	/** list of possible game states */
	public static ArrayList<GameState> gameStates;
	
	/** list index of current game state */
	protected int currentState;
	
	/** loading state index */
	public static final int LOADINGSTATE = 0;
	
	/** menu state index */
	public static final int MENUSTATE = 1;
	
	/** gameplay state index */
	public static final int GAMESTATE = 2;
	
	/** settings state index */
	public static final int SETTINGSSTATE = 3;
	
	/** win state index */
	public static final int ENDSTATE = 4;
	
	public static boolean currentlyTesting = false;
	
	/** GameStateManager
	 * Used by a game panel to draw 
	 * based on current state of the game
	 */
	public GameStateManager(GamePanel panel) {
		// build game state list
		this.panel = panel;
		currentState = LOADINGSTATE;
		gameStates = new ArrayList<GameState>();
		gameStates.add(new LoadingState(this));
		gameStates.add(new MenuState(this));
		gameStates.add(new MastermindState(this));
		gameStates.add(new SettingsState(this));
		gameStates.add(new EndState(this));
		setState(currentState);
	}
	
	/** setState
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
	
	/** getCurrentState
	 * @return 
	 * @return the current game state object
	 */
	public GameState getCurrentState() {
		return gameStates.get(currentState);
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
	
	/** call the key typed manager */
	public void keyTyped(char key) {
		gameStates.get(currentState).keyTyped(key);
	}

	public void mouseReleased(MouseEvent me) {
		gameStates.get(currentState).mouseReleased(me);
	}
	
}
