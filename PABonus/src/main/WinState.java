package main;

import java.awt.Graphics2D;

public class WinState extends GameState {

	protected GameStateManager gsm;
	
	protected int frameUnit = 0;
	
	public WinState(GameStateManager gameStateManager) {
		gsm = gameStateManager;
	}

	@Override
	public void init(GameState lastState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		if(frameUnit > 180) {
			gsm.setState(gsm.MENUSTATE);
		}
		frameUnit++;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawString("YOU WIN!", GamePanel.WIDTH * 2 / 5, GamePanel.HEIGHT * 9 / 16);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
