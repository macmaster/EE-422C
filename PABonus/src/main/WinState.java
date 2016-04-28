package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class WinState extends GameState {

	protected GameStateManager gsm;
	
	public WinState(GameStateManager gameStateManager) {
		gsm = gameStateManager;
	}

	@Override
	public void init(GameState lastState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		g.setColor(Color.WHITE);
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
