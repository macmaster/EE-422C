/** GameState ************************************************
 * Top level abstract state class
 * Contains the basic methods that a game state should have
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	/** initialize the game graphics objects */
	public abstract void init(GameState lastState);
	
	/** redraw the GUI graphics */
	public abstract void update();
	
	/** draw method for the game graphics */
	public abstract void draw(java.awt.Graphics2D g);
	
	/** key pressed event handler */
	public abstract void keyPressed(int k);
	
	/** key released event handler */
	public abstract void keyReleased(int k);
	
	/** key released event handler */
	public abstract void keyTyped(char key);
	
}
