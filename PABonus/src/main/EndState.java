package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class EndState extends GameState {

	/** master game state manager */
	public GameStateManager gsm;
	
	/** flag denoting a game winner */
	protected boolean winFlag;
	
	public EndState(GameStateManager gameStateManager) {
		gsm = gameStateManager;
	}

	@Override
	public void init(GameState lastState) {
		// enters init from gameplay ending
		MastermindState gameState = (MastermindState)lastState;
		winFlag = gameState.checkWinner();
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {		
		if(winFlag){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			g.setColor(Color.WHITE);
			g.drawString("YOU WIN!", GamePanel.WIDTH * 2 / 5, GamePanel.HEIGHT * 9 / 16);
		}
		else{
			g.setColor(Color.WHITE);
			g.drawString("YOU LOSE!", GamePanel.WIDTH * 2 / 5, GamePanel.HEIGHT * 9 / 16);
		}
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void keyTyped(char key){}

}
