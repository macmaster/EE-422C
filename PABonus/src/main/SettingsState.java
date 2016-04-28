/** SettingsState ********************************************
 * Manages the settings menu and controls
 * Contains the logic for settings interfacing
 * Sets the settings variables
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Settings game state to change game settings
 */
public class SettingsState extends GameState {

	/** master game state manager */
	protected GameStateManager gsm;
	/** the last state that occured */
	protected GameState lastState;
	
	/** list of possible settings options */
	protected Map<String, ArrayList<String>> settingsOptions;
	
	/** currently selected option */
	protected int currentOption = 0;
	/** number of pegs */
	protected int pegVal;
	/** number of colors */
	protected int colorVal;
	/** number of guesses */
	protected int guessVal;
	
	/** color the title font */
	private Color titleColor;
	/** font object of the title */
	private Font titleFont;
	/** color for the option fonts */
	private Color optionColor = Color.YELLOW;
	/** color for the value fonts */
	private Color valColor = Color.GREEN;
	/** font object for the option fonts */
	private Font optionFont;
	
	/** options collision detection rectangles */
	private ArrayList<Rectangle2D> optionRects;
	/** values collision detection rectangles */
	private ArrayList<Rectangle2D> valueRects;
	
	/**
	 * @param gameStateManager Link back to game state manager
	 */
	public SettingsState(GameStateManager gameStateManager) {				
		//initialize the option lists
		gsm = gameStateManager;
		settingsOptions = new LinkedHashMap<String, ArrayList<String>>();
		ArrayList<String> pegOptions = new ArrayList<String>();
		ArrayList<String> colorOptions = new ArrayList<String>();
		ArrayList<String> guessOptions = new ArrayList<String>();
		
		// build the options list
		pegOptions.add("4"); pegOptions.add("5"); pegOptions.add("6");
		colorOptions.add("4"); colorOptions.add("5"); colorOptions.add("6");
		guessOptions.add("8"); guessOptions.add("10"); guessOptions.add("12");
		
		// build the settings options
		settingsOptions.put("Pegs:", pegOptions);
		settingsOptions.put("Colors:", colorOptions);
		settingsOptions.put("Guesses:", guessOptions);
		settingsOptions.put("Done", null);
		
		//establish font/color fields
		titleColor = Color.white;
		titleFont = new Font(
				"Century Gothic",
				Font.PLAIN,
				27*GamePanel.HEIGHT/320);
		
		optionFont = new Font("Arial", Font.PLAIN, 4*GamePanel.HEIGHT/80);
	}

	@Override
	public void init(GameState lastState) {
		currentOption = 0;
		
		//set peg values based on current game settings
		pegVal = settingsOptions.get("Pegs:").indexOf("" + Settings.NUM_PEGS);
		colorVal = settingsOptions.get("Colors:").indexOf("" + Settings.NUM_COLORS);
		guessVal = settingsOptions.get("Guesses:").indexOf("" + Settings.NUM_GUESSES);
	
		this.lastState = lastState;
	}

	@Override
	public void update() {
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
			//draw values if not on "done"
			if (settingsOptions.get(option) != null) {
				for (String val : settingsOptions.get(option)) {
					//draw option, green if selected
					g.setColor(valIndex == desiredValIndex ? valColor : titleColor);
					g.drawString(val, valX + valXDelta * valIndex, yStart + yDelta * optIndex);
					valIndex++;
				} 
			}
			optIndex++;
			
			//set up text dimensions
			if(optionRects == null) {
				optionRects = new ArrayList<Rectangle2D>();
				valueRects = new ArrayList<Rectangle2D>();
				optIndex = 0;
				for(String optionStr : settingsOptions.keySet()) {
					//set up optsRects
					FontMetrics optMetrics = g.getFontMetrics(optionFont);
					int hgt = optMetrics.getHeight();
					int width = optMetrics.stringWidth(optionStr);
					Rectangle2D.Double optRect = new Rectangle2D.Double();
					optRect.setRect(optX - 5, yStart + yDelta * optIndex - hgt - 5, width + 10, hgt + 10);
					optionRects.add(optRect);
					
					//set up value dimensions
					if (settingsOptions.get(optionStr) != null) {
						//set up valRects
						valIndex = 0;
						for (String val : settingsOptions.get(optionStr)) {
							FontMetrics valMetrics = g.getFontMetrics(optionFont);
							hgt = valMetrics.getHeight();
							width = valMetrics.stringWidth(val);
							Rectangle2D.Double valRect = new Rectangle2D.Double();
							valRect.setRect(valX + valXDelta * valIndex - 5, yStart + yDelta * optIndex - hgt - 5, width + 10, hgt + 10);
							valueRects.add(valRect);
							valIndex++;
						}
						optIndex++;
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(int k) {}

	@Override
	public void keyPressed(int k) {
		//navigate around the settings options menu
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
				done();
			}
		}
	}
	
	private void done() {
		//save settings
		Settings.NUM_PEGS = Integer.parseInt(settingsOptions.get("Pegs:").get(pegVal));
		Settings.NUM_COLORS = Integer.parseInt(settingsOptions.get("Colors:").get(colorVal));
		Settings.NUM_GUESSES = Integer.parseInt(settingsOptions.get("Guesses:").get(guessVal));
	
		//change state
		gsm.setState(gsm.gameStates.indexOf(lastState));
	}

	@Override
	public void keyTyped(char key){}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (optionRects != null) {
			//check all option boxes
			int rectIndex = 0;
			for (Rectangle2D rect : optionRects) {
				if(rect.contains(me.getPoint())) {
					currentOption = rectIndex;
					if(currentOption == 3) {
						done();
					}
				}
				rectIndex++;
			} 
		}
		if (valueRects != null) {
			//check all option boxes
			int rectIndex = 0;
			for (Rectangle2D rect : valueRects) {
				if(rect.contains(me.getPoint())) {
					currentOption = rectIndex / 3;
					switch(currentOption) {
					case 0:
						pegVal = rectIndex % 3;
						break;
					case 1:
						colorVal = rectIndex % 3;
						break;
					case 2:
						guessVal = rectIndex % 3;
						break;
					}
					System.out.println(rectIndex);
				}
				rectIndex++;
			} 
		}
	}

}
