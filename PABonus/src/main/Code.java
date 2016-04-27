/** Code *****************************************************
 * Representation of a Mastermind Code
 * Used as a guess or a secret code
 * Get and set the colors of the code at integer indexes
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class Code{
	
	/**
	 * length of the game code
	 */
	protected int length;
	
	/**
	 * list of code's colors, list.size() 
	 * must equal length
	 */
	protected ArrayList<Color> colorList = new ArrayList<Color>();
	
	/**
	 * list of code's colors, list.size() 
	 * must equal length
	 */
	protected ArrayList<Integer> valueList = new ArrayList<Integer>();
	
	/**
	 * default value for an empty color code
	 */
	protected static final Color EMPTY_COLOR = new Color(225, 169, 95);
	
	private static final Map<Color, Integer> COLOR_MAP= new HashMap<Color, Integer>(){{
		put(Color.red, 0); put(Color.RED, 0);
		put(Color.orange, 1); put(Color.ORANGE, 1);
		put(Color.yellow, 2); put(Color.YELLOW, 2);
		put(Color.green, 3); put(Color.GREEN, 3);
		put(Color.blue, 4); put(Color.BLUE, 4);
		put(Color.magenta, 5); put(Color.MAGENTA, 5);
	}};
	
	/** Code
	 * creates a Code object to represent a master mind code
	 * manages a list of it's valid color codes.
	 * 
	 * @param colorList
	 */
	public Code(List<Color> colorList){
		// build color lists
		for(Color color : colorList){
			this.colorList.add(color);
			this.valueList.add(COLOR_MAP.get(color));
		}
		
		// length of the color code
		length = colorList.size();
	}
	
	/** Code
	 * creates a Code object to represent a master mind code
	 * manages a list of it's valid color codes.
	 * 
	 * @param length initial length of the gray code
	 */
	public Code(int length) {
		// build color lists
		for(int i = 0; i < length; i++) {
			this.colorList.add(null);
			this.valueList.add(COLOR_MAP.get(null));
		}
		this.length = length;
	}
	
	/** getColor()
	 * 
	 * returns the color of the
	 * color at position index in the code object 
	 * 
	 * @param index
	 * @return color 
	 */
	public Color getColor(int index){
		// return valid color
		if(index >= length || index < 0){
			return EMPTY_COLOR;
		}
		else{
			Color color = colorList.get(index);
			return color == null ? EMPTY_COLOR : color;
		}
	}
	
	/** getColorValue()
	 * 
	 * returns the color number of the
	 * color at position index in the code object 
	 * 
	 * @param index
	 * @return color number
	 */
	public Integer getColorValue(int index){
		// return valid color value
		if(index >= length || index < 0){
			return -1;
		}
		else{
			Integer colorID = valueList.get(index);
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
		// update color in valid range
		if(index >= length || index < 0){
			return false;
		}
		else{
			Integer colorID = COLOR_MAP.get(color);
			valueList.set(index, colorID);
			colorList.set(index, color);
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
	
	/**
	 * compareCode returns a result object with 
	 * the number of whites and blacks assigned
	 */
	public Result compareCode(Code otherCode){		
		// assign blacks
		int color1, color2;
		int blacks = 0, whites = 0;
		ArrayList<Integer> leftColors = new ArrayList<Integer>();
		ArrayList<Integer> rightColors = new ArrayList<Integer>();
		for(int index = 0; index < length; index++){
			color1 = this.getColorValue(index);
			color2 = otherCode.getColorValue(index);
			
			// same color same index
			if(color1 == color2){
				blacks++;
			}
			// check for white pegs
			else{
				leftColors.add(color1);
				rightColors.add(color2);
			}
		}
		
		// assign whites
		Integer colorL, colorR;
		Iterator<Integer> itrL, itrR;
		itrL = leftColors.iterator();
		while(itrL.hasNext()){
			colorL = itrL.next();
			itrR = rightColors.iterator();
			while(itrR.hasNext()){
				colorR = itrR.next();
				// remove one-to-one color pairs
				if(colorL.equals(colorR)){
					itrL.remove();
					itrR.remove();
					whites++; break;
				}
			}
		}
		
		// return the result
		return new Result(whites, blacks);
	}

	public ArrayList<Color> getColors() {
		return new ArrayList<Color>(COLOR_MAP.keySet());
				
	}
}
