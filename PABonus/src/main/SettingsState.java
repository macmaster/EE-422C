package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SettingsState extends GameState {

	protected GameStateManager gsm;
	protected GameState lastState;
	
	protected Map<String, ArrayList<String>> settingsOptions;
	
	protected int currentOption = 0;
	protected int pegVal;
	protected int colorVal;
	protected int guessVal;
	
	private Color titleColor;
	private Font titleFont;
	
	private Color optionColor = Color.YELLOW;
	private Color valColor = Color.GREEN;
	private Font optionFont;
	
	
	public SettingsState(GameStateManager gameStateManager) {
		gsm = gameStateManager;
		
		settingsOptions = new LinkedHashMap<String, ArrayList<String>>();
		
		ArrayList<String> pegOptions = new ArrayList<String>();
		ArrayList<String> colorOptions = new ArrayList<String>();
		ArrayList<String> guessOptions = new ArrayList<String>();
		
		pegOptions.add("4"); pegOptions.add("5"); pegOptions.add("6");
		colorOptions.add("4"); colorOptions.add("5"); colorOptions.add("6");
		guessOptions.add("8"); guessOptions.add("10"); guessOptions.add("12");
		
		settingsOptions.put("Pegs:", pegOptions);
		settingsOptions.put("Colors:", colorOptions);
		settingsOptions.put("Guesses:", guessOptions);
		settingsOptions.put("Done", null);
	}

	@Override
	public void init(GameState lastState) {
		currentOption = 0;
		
		pegVal = settingsOptions.get("Pegs:").indexOf("" + Settings.NUM_PEGS);
		colorVal = settingsOptions.get("Colors:").indexOf("" + Settings.NUM_COLORS);
		guessVal = settingsOptions.get("Guesses:").indexOf("" + Settings.NUM_GUESSES);
	
		this.lastState = lastState;
	}

	@Override
	public void update() {
		titleColor = Color.white;
		titleFont = new Font(
				"Century Gothic",
				Font.PLAIN,
				27*GamePanel.HEIGHT/320);
		
		optionFont = new Font("Arial", Font.PLAIN, 4*GamePanel.HEIGHT/80);
	}

	@Override
	public void draw(Graphics2D g) {
		//draw bg
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw the title:
		g.setColor(titleColor);
		g.setFont(titleFont);
		FontMetrics metrics = g.getFontMetrics(titleFont);
		int titleY = 7 * GamePanel.HEIGHT / 24;
		int titleX = (GamePanel.WIDTH - metrics.stringWidth(Game.GAME_NAME)) / 2;
		g.drawString(Game.GAME_NAME, titleX, titleY);
		//draw the options: 
		//Yellow for current option, Green for current setting value
		g.setFont(optionFont);
		int optX = GamePanel.WIDTH * 12 / 40;
		int yStart = GamePanel.HEIGHT * 18 / 40;
		int yDelta = GamePanel.HEIGHT * 4 / 40;
		int valX = GamePanel.WIDTH * 19 / 40;
		int valXDelta = GamePanel.WIDTH * 2 / 20;
		
		int optIndex = 0;
		for(String option : settingsOptions.keySet()) {
			//draw the option text, yellow if selected
			g.setColor(optIndex == currentOption ? optionColor : titleColor);
			g.drawString(option, optX, yStart + yDelta * optIndex);
			//iterate through value options
			int valIndex = 0;
			int desiredValIndex = -1;
			switch(optIndex) {
			case 0:
				desiredValIndex = pegVal;
				break;
			case 1:
				desiredValIndex = colorVal;
				break;
			case 2:
				desiredValIndex = guessVal;
				break;
			}
			if (settingsOptions.get(option) != null) {
				for (String val : settingsOptions.get(option)) {
					//draw option, green if selected
					g.setColor(valIndex == desiredValIndex ? valColor : titleColor);
					g.drawString(val, valX + valXDelta * valIndex, yStart + yDelta * optIndex);
					valIndex++;
				} 
			}
			optIndex++;
		}
	}

	@Override
	public void keyReleased(int k) {}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_DOWN) {
			currentOption++;
			if(currentOption > 3) currentOption = 0;
		}
		if(k == KeyEvent.VK_UP) {
			currentOption--;
			if(currentOption < 0) currentOption = 3;
		}
		if(k == KeyEvent.VK_LEFT) {
			switch(currentOption) {
			case 0:
				pegVal--;
				if(pegVal < 0) pegVal = 2;
				break;
			case 1:
				colorVal--;
				if(colorVal < 0) colorVal = 2;
				break;
			case 2:
				guessVal--;
				if(guessVal < 0) guessVal = 2;
				break;
			}
		}
		if(k == KeyEvent.VK_RIGHT) {
			switch(currentOption) {
			case 0:
				pegVal++;
				if(pegVal > 2) pegVal = 0;
				break;
			case 1:
				colorVal++;
				if(colorVal > 2) colorVal = 0;
				break;
			case 2:
				guessVal++;
				if(guessVal > 2) guessVal = 0;
				break;
			}
		}
		if(k == KeyEvent.VK_ENTER) {
			if(currentOption == 3) {
				//save settings
				Settings.NUM_PEGS = Integer.parseInt(settingsOptions.get("Pegs:").get(pegVal));
				Settings.NUM_COLORS = Integer.parseInt(settingsOptions.get("Colors:").get(colorVal));
				Settings.NUM_GUESSES = Integer.parseInt(settingsOptions.get("Guesses:").get(guessVal));
			
				//change state
				gsm.setState(gsm.gameStates.indexOf(lastState));
			}
		}
	}
	
	@Override
	public void keyTyped(char key){}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

}
