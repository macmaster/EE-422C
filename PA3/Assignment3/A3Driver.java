/** ShoppingCartDriver **************************************
 * Driver to manage the Shopping Cart Classes
 * perform commands through a transaction file
 * print out the shopping cart total at the end
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, mm75343
 * @author Ronald Macmaster, Michael Marino
 * @version 1.01 2/19/2016
 ************************************************************/

package Assignment3;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class A3Driver 
	{

	  public static void main(String[] args) 
	  {
		try{  
			//Open file; file name specified in args (command line)
			String filename = args[0]; // name of file
			FileReader reader = new FileReader(filename);
			BufferedReader fhand = new BufferedReader(reader);
			
			//Parse input, take appropriate actions.
			Map<String, Item> itemMap = new HashMap<String, Item>(); // item database		
			ArrayList<Item> shoppingCart = new ArrayList<Item>(); 	// shopping cart list
			
			String line; // transaction string
			while((line = fhand.readLine()) != null){
				System.out.println(line);
			}
			
			//Close the file
			reader.close(); 
			fhand.close();
		}catch(ArrayIndexOutOfBoundsException err){
			System.err.println("Failed to detect file in command line args. exiting...");
			System.exit(1);
		}catch(Exception err){
			System.err.println(err.getMessage());
			System.exit(1);
		}

		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		/*Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			temp.calculatePrice(); 
			temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
		}		
		 */
	  }

}
