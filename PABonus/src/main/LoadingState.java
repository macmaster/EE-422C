package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class LoadingState extends GameState {

	protected GameStateManager gsm;
	
	protected double percentComplete = 0;
	
	protected String loadString = "Loading Resources...";
	protected int loadStringX;
	protected int loadStringY;
	protected Font loadFont;
	protected Color loadColor;
	
	protected int loadBarX;
	protected int loadBarY;
	protected int loadBarWidth;
	protected int loadBarHeight;
	protected Stroke loadBarStroke;
	
	protected String errorString = "There was an error loading resources.\n" + 
									"Please make sure image files are in place.";
	protected int errorX;
	protected int errorYStart;
	protected int errorYDelta;
	protected Font errorFont;
	protected Color errorColor;
	
	protected boolean loadFailed = false;
	
	public LoadingState(GameStateManager gsm) {
		this.gsm = gsm;
		
		loadColor = Color.WHITE;
		loadFont = new Font("Century Gothic", Font.PLAIN, 3 * GamePanel.HEIGHT / 80);
		
		loadStringX = GamePanel.WIDTH * 3 / 10;
		loadStringY = GamePanel.HEIGHT * 9 / 20;
		
		loadBarX = GamePanel.WIDTH * 2 /6;
		loadBarY = GamePanel.HEIGHT * 11 /20;
		loadBarWidth = GamePanel.WIDTH * 2 / 6;
		loadBarHeight = GamePanel.HEIGHT / 20;
		loadBarStroke = new BasicStroke(GamePanel.HEIGHT * 1 / 60);
		
		errorX = GamePanel.WIDTH * 4 / 20;
		errorYStart = GamePanel.HEIGHT * 6 / 10;
		errorYDelta = GamePanel.HEIGHT * 1 / 10;
		errorColor = Color.RED;
		errorFont = loadFont;
	}
	
	@Override
	public void init(GameState lastState) {
		Thread resourceLoadingThread = new Thread(new Resources());
		resourceLoadingThread.start();
	}

	@Override
	public void update() {
		//update percentage complete and check if done loading
		percentComplete = Resources.loadProgress / (double)(Resources.LOAD_MAX);
		
		//check if done loading
		if (Resources.loadSuccess) { 
			//proceed to menu
			gsm.setState(gsm.currentlyTesting ? gsm.GAMESTATE : gsm.MENUSTATE);
		}
		
		//update failure status
		loadFailed = Resources.loadFailure;
	}

	@Override
	public void draw(Graphics2D g) {
		//draw bg
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		if (!loadFailed) {
			//set loading color and font
			g.setColor(loadColor);
			g.setFont(loadFont);
			
			//draw loading string
			g.drawString(loadString, loadStringX, loadStringY);
			
			//draw loading bar
			g.drawRect(loadBarX, loadBarY, loadBarWidth, loadBarHeight);
			g.fillRect(loadBarX, loadBarY, (int) (loadBarWidth * percentComplete), loadBarHeight);
		}
		else {
			//set error color and font
			g.setColor(errorColor);
			g.setFont(errorFont);
			
			//draw error strings
			int lineY = errorYStart;
			for(String line : errorString.split("\n")) {
				g.drawString(line, errorX, lineY);
				lineY += errorYDelta;
			}
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
	public void keyTyped(char key){
		
	}

}
