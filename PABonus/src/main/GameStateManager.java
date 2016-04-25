package main;

import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int GAMESTATE = 1;
	public static final boolean currentlyTesting = true;
	
	public GameStateManager() {
		
		gameStates = new ArrayList<GameState>();
		
		currentState = currentlyTesting ? GAMESTATE : MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new MastermindState(this));
		setState(currentState);
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
}
