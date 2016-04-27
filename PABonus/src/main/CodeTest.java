/**
 * 
 */
package main;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ronald
 *
 */
public class CodeTest{

	Code code1, code2, code3, code4;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception{
		code1 = new Code(new ArrayList<Color>(){{
			add(Color.RED);add(Color.BLUE);add(Color.GREEN);add(Color.MAGENTA);}});
		code2 = new Code(new ArrayList<Color>(){{
			add(Color.RED);add(Color.RED);add(Color.GREEN);add(Color.GREEN);}});
		code3 = new Code(new ArrayList<Color>(){{
			add(Color.GREEN);add(Color.GREEN);add(Color.RED);add(Color.RED);}});
		code4 = new Code(new ArrayList<Color>(){{
			add(Color.CYAN);add(Color.CYAN);add(Color.CYAN);add(Color.CYAN);}});
	}

	/**
	 * Test method for {@link main.Code#compareCode(main.Code)}.
	 */
	@Test
	public void testCompareCode(){
		Result result1 = code1.compareCode(code2);
		Result result2 = code1.compareCode(code3);
		Result result3 = code1.compareCode(code4);
		Result result4 = code2.compareCode(code3);
		Result result5 = code2.compareCode(code4);
		Result result6 = code3.compareCode(code4);
		
		System.out.println("***********************Round 1 tests**********************");
		System.out.println("1) blacks: " + result1.getBlacks() + "\t whites: " + result1.getWhites());
		System.out.println("2) blacks: " + result2.getBlacks() + "\t whites: " + result2.getWhites());
		System.out.println("3) blacks: " + result3.getBlacks() + "\t whites: " + result3.getWhites());
		System.out.println("4) blacks: " + result4.getBlacks() + "\t whites: " + result4.getWhites());
		System.out.println("5) blacks: " + result5.getBlacks() + "\t whites: " + result5.getWhites());
		System.out.println("6) blacks: " + result6.getBlacks() + "\t whites: " + result6.getWhites());
		System.out.println("***********************************************************\n\n");
		
		result1 = code2.compareCode(code1);
		result2 = code3.compareCode(code1);
		result3 = code4.compareCode(code1);
		result4 = code3.compareCode(code2);
		result5 = code4.compareCode(code2);
		result6 = code4.compareCode(code3);
		
		System.out.println("***********************Round 2 tests**********************");
		System.out.println("1) blacks: " + result1.getBlacks() + "\t whites: " + result1.getWhites());
		System.out.println("2) blacks: " + result2.getBlacks() + "\t whites: " + result2.getWhites());
		System.out.println("3) blacks: " + result3.getBlacks() + "\t whites: " + result3.getWhites());
		System.out.println("4) blacks: " + result4.getBlacks() + "\t whites: " + result4.getWhites());
		System.out.println("5) blacks: " + result5.getBlacks() + "\t whites: " + result5.getWhites());
		System.out.println("6) blacks: " + result6.getBlacks() + "\t whites: " + result6.getWhites());
		System.out.println("***********************************************************\n\n");
				
		result1 = code4.compareCode(code1);
		result2 = code4.compareCode(code2);
		result3 = code4.compareCode(code3);
		result4 = code3.compareCode(code1);
		result5 = code3.compareCode(code2);
		result6 = code2.compareCode(code1);
		
		System.out.println("***********************Round 3 tests**********************");
		System.out.println("1) blacks: " + result1.getBlacks() + "\t whites: " + result1.getWhites());
		System.out.println("2) blacks: " + result2.getBlacks() + "\t whites: " + result2.getWhites());
		System.out.println("3) blacks: " + result3.getBlacks() + "\t whites: " + result3.getWhites());
		System.out.println("4) blacks: " + result4.getBlacks() + "\t whites: " + result4.getWhites());
		System.out.println("5) blacks: " + result5.getBlacks() + "\t whites: " + result5.getWhites());
		System.out.println("6) blacks: " + result6.getBlacks() + "\t whites: " + result6.getWhites());
		System.out.println("***********************************************************\n\n");		

		result1 = code1.compareCode(code4);
		result2 = code2.compareCode(code4);
		result3 = code3.compareCode(code4);
		result4 = code1.compareCode(code3);
		result5 = code2.compareCode(code3);
		result6 = code1.compareCode(code2);
		
		System.out.println("***********************Round 4 tests**********************");
		System.out.println("1) blacks: " + result1.getBlacks() + "\t whites: " + result1.getWhites());
		System.out.println("2) blacks: " + result2.getBlacks() + "\t whites: " + result2.getWhites());
		System.out.println("3) blacks: " + result3.getBlacks() + "\t whites: " + result3.getWhites());
		System.out.println("4) blacks: " + result4.getBlacks() + "\t whites: " + result4.getWhites());
		System.out.println("5) blacks: " + result5.getBlacks() + "\t whites: " + result5.getWhites());
		System.out.println("6) blacks: " + result6.getBlacks() + "\t whites: " + result6.getWhites());
		System.out.println("***********************************************************\n\n");		

		
		result1 = code1.compareCode(code1);
		result2 = code2.compareCode(code2);
		result3 = code3.compareCode(code3);
		result4 = code4.compareCode(code4);
		
		System.out.println("***********************Round 5 tests**********************");
		System.out.println("1) blacks: " + result1.getBlacks() + "\t whites: " + result1.getWhites());
		System.out.println("2) blacks: " + result2.getBlacks() + "\t whites: " + result2.getWhites());
		System.out.println("3) blacks: " + result3.getBlacks() + "\t whites: " + result3.getWhites());
		System.out.println("4) blacks: " + result4.getBlacks() + "\t whites: " + result4.getWhites());
		System.out.println("***********************************************************\n\n");
		
	}

}
