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
	
	protected int length;
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
	 * creates a Code object to represent a master mind code
	 * manages a list of it's valid color codes.
	 * 
	 * @param colorList
	 */
	public Code(List<Color> colorList){
		for(Color color : colorList){
			colorCode.add(COLOR_MAP.get(color));			
		}
		
		// length of the color code
		length = colorCode.size();
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
		if(index >= length || index < 0){
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
		if(index >= length || index < 0){
			return false;
		}
		else{
			Integer colorID = COLOR_MAP.get(color);
			colorCode.set(index, colorID);
			return true;
		}
	}
	
	/** getLength()
	 * 
	 * @return length of the color code
	 */
	public int getLength(){
		return length;
	}
	
}
