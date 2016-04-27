package main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class SettingsState extends GameState {

	protected GameStateManager gsm;
	
	protected HashMap<String, ArrayList<String>> settingsOptions;
	
	protected int currentOption = 0;
	protected int pegVal;
	protected int colorVal;
	protected int guessVal;
	
	public SettingsState(GameStateManager gameStateManager) {
		gsm = gameStateManager;
		
		settingsOptions = new HashMap<String, ArrayList<String>>();
		
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
		pegVal = settingsOptions.get("Pegs:").indexOf("" + Settings.NUM_PEGS);
		colorVal = settingsOptions.get("Colors:").indexOf("" + Settings.NUM_COLORS);
		guessVal = settingsOptions.get("Guesses:").indexOf("" + Settings.NUM_GUESSES);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		//draw the options: 
		//Yellow for current option, Green for current setting value
	}

	@Override
	public void keyPressed(int k) {}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_UP) {
			currentOption++;
			if(currentOption > 3) currentOption = 0;
		}
		if(k == KeyEvent.VK_DOWN) {
			currentOption++;
			if(currentOption < 0) currentOption = 3;
		}
		if(k == KeyEvent.VK_LEFT) {
			switch(currentOption) {
			case 0:
				pegVal++;
				if(currentOption < 0) currentOption = 2;
				break;
			case 1:
				
				break;
			case 2:
				
				break;
			}
		}
		if(k == KeyEvent.VK_RIGHT) {
			
		}
		if(k == KeyEvent.VK_ENTER) {
			if(currentOption == 3) {
				//save settings
				Settings.NUM_PEGS = settingsOptions.get("")
			}
		}
	}

}
