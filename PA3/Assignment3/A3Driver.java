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

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class A3Driver 
{
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
	
  private static ArrayList<Item> shoppingCart = new ArrayList<Item>(); // shopping cart list
	
  public static void main(String[] args){
	  try{  
		  //Open file; file name specified in args (command line)
		  String filename = args[0]; // name of file
		  FileReader reader = new FileReader(filename);
		  BufferedReader fhand = new BufferedReader(reader);
		  
		  //Parse input transactions
		  // input ex: <operation> <category> <name> <price> <quantity> <weight>
		  //           <optional field1> <optional field2>
		  String line; // transaction string
		  while((line = fhand.readLine()) != null){
			  // harvest operation and data
			  line = line.trim();
			  String operation, data;
			  int npos = line.indexOf(' ');
			  
			  if(npos > 0){ //multiple parameters
				  operation = line.substring(0, npos).toLowerCase();
				  data = line.substring(npos);
			  }
			  else{ //single parameter
				  operation = line.toLowerCase();
				  data = null;
			  }
			  
			  try{ //perform commands
				  performCommand(operation, data);
			  }catch(IllegalArgumentException err){
				  System.err.println(err.getMessage());
				  continue; //don't crash!
			  }
			  
			  System.out.println("command: "+operation+"\tData: "+data);
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
  }
  
  static void performCommand(String command, String data){
	  // resolves command
	  if(command.equals("insert")){
		  
	  }
	  else if(command.equals("search")){
		  
	  }
	  else if(command.equals("delete")){
		  
	  }
	  else if(command.equals("update")){
		  
	  }
	  else if(command.equals("print")){
		  
	  }
	  else{ // invalid command
		  String errmsg = "Error: " + command + " is an invalid command!\n";
		  errmsg += "valid commands: insert search delete update print";
		  throw new IllegalArgumentException(errmsg);
	  }
  }
  
  static void insert(){ 
	  //TODO implement method
	  // insert <category> <name> <price> <quantity> <weight> <optional field1> <optional field2>
	  /*  For the insert operation, you will need to instantiate an 
	   * 	object of the appropriate type (Groceries,
		*	Clothing or Electronics), and add it into an arraylist.*/
  }
  static void search(){
	  //TODO implement method
	  // search <name> searches for all OBJECTS with name field as <name> and then
	  // outputs the number of OBJECTS found to the screen.
  }
  static void delete(){
	  //TODO implement method
	  // delete <name> searches and deletes 
	  // all OBJECTS (not quantity) with the name field that matches the given <name>.
  }
  static void update(){
	  //TODO implement method
	  // update <name> <quantity> updates the quantity field for
	  // the first occurrence of a matching name.
	  // then output the name and new quantity value for that object to the screen. 
  }
  static void print(){
	  //TODO implement method
	/** print the contents of the shopping cart in ascending order by name, 
	 * show all name, quantity, price after tax and shipping charges
	 * After, print the total charges for entire cart.
	 * Output is to the screen, make it readable              */
  }

}
