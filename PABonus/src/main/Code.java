/**
 * 
 */
package main;

import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;



public class Code{
	
	protected List<Integer> colorCode = new ArrayList<Integer>();
	
	private static final Map<Color, Integer> COLOR_MAP= new HashMap<Color, Integer>(){{
		put(Color.red, 0); put(Color.RED, 0);
		put(Color.orange, 1); put(Color.ORANGE, 1);
		put(Color.yellow, 2); put(Color.YELLOW, 2);
		put(Color.green, 3); put(Color.GREEN, 3);
		put(Color.blue, 4); put(Color.BLUE, 4);
		put(Color.magenta, 5); put(Color.MAGENTA, 5);
	}};
	
	/**
	 * creates a Code object to represent a mastermind code
	 * manages a list of it's valid color codes.
	 * 
	 * @param one first color
	 * @param two second color
	 * @param three third color
	 * @param four fourth color
	 */
	public Code(Color one, Color two, Color three, Color four){
		colorCode.add(COLOR_MAP.get(one));
		colorCode.add(COLOR_MAP.get(two));
		colorCode.add(COLOR_MAP.get(three));
		colorCode.add(COLOR_MAP.get(four));
	}
	
	/** getColor()
	 * 
	 * returns the color number of the
	 * color at position index in the code object 
	 * 
	 * @param index
	 * @return color number
	 */
	public Integer getColor(int index){
		if(index >= colorCode.size() || index < 0){
			return -1;
		}
		else{
			Integer colorID = colorCode.get(index);
			return colorID == null ? -1 : colorID;
		}
	}
	
	/** setColor()
	 * 
	 * sets the color number at the 
	 * specified code position.
	 * 
	 * @param index
	 * @return color number
	 */
	public boolean setColor(int index, Color color){
		if(index >= colorCode.size() || index < 0){
			return false;
		}
		else{
			Integer colorID = COLOR_MAP.get(color);
			colorCode.set(index, colorID);
			return true;
		}
	}
	
}
