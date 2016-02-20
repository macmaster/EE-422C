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
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;

public class A3Driver 
{
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
				  data = line.substring(npos+1);
			  }
			  else{ //single parameter
				  operation = line.toLowerCase();
				  data = null;
			  }
			  
			  try{ //perform commands
				  System.out.println("command: "+operation+"\tData: "+data);
				  performCommand(operation, data);
			  }catch(IllegalArgumentException err){
				  System.err.println(err.getMessage());
				  continue; //don't crash!
			  }
			  
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
  
  private static void performCommand(String command, String data){
	  // resolves command
	  if(command.equals("insert")){
		  insert(data);
	  }
	  else if(command.equals("search")){
		  search(data);
	  }
	  else if(command.equals("delete")){
		  delete(data);
	  }
	  else if(command.equals("update")){
		  update(data);
	  }
	  else if(command.equals("print")){
		  print(); 
	  }
	  else{ // invalid command
		  String errmsg = "Error: " + command + " is an invalid command!\n";
		  errmsg += "valid commands: insert search delete update print";
		  throw new IllegalArgumentException(errmsg);
	  }
  }
  
  private static void insert(String data){ 
	  //TODO implement method
	  // insert <category> <name> <price> <quantity> <weight> <optional field1> <optional field2>
	  /*  For the insert operation, you will need to instantiate an 
	   * 	object of the appropriate type (Groceries,
		*	Clothing or Electronics), and add it into an arraylist.*/
  }
  
  /** Search() ******************************************************************
   * search <name> searches for all OBJECTS with name field as <name> and then
   * outputs the number of OBJECTS found to the screen. 
   * 
   * @param data : the data string which contains the search name					
   * ***************************************************************************/
  private static void search(String data){
	  // extract search_name
	  String name;
	  int npos = data.indexOf(' ');
	  if(npos > 0){ 
		  name = data.substring(0, npos).toLowerCase();
	  }
	  else{ //single parameter
		  name = data;
	  }
	  
	  //use iterators! (Or Binary Search ... your choice. nothing wrong w. a little O(n))
	  int object_count = 0;
	  Iterator<Item> cart_itr = shoppingCart.iterator();
	  while(cart_itr.hasNext()){
		  Item temp = cart_itr.next();
		  String temp_name = temp.getName().toLowerCase();
		  if(temp_name.equals(name)){
			  object_count++;
		  }
	  }
	  
	  // display results
	  System.out.println("number of " + name + " objects: " + object_count);
  }
  
  /** Delete() ******************************************************************
   * delete <name> searches for and deletes
   * all OBJECTS (not quantity) with the name field that matches the given <name>.
   * 
   * @param data : the data string which contains the search name	to delete				
   * ***************************************************************************/
  private static void delete(String data){
	  // extract search_name
	  String name;	
	  int npos = data.indexOf(' ');
	  if(npos > 0){ 
		  name = data.substring(0, npos).toLowerCase();
	  }
	  else{ //single parameter
		  name = data;
	  }
	  
	  //use iterators! (Or Binary Search ... your choice. nothing wrong w. a little O(n))
	  int object_count = 0;
	  Iterator<Item> cart_itr = shoppingCart.iterator();
	  while(cart_itr.hasNext()){
		  Item temp = cart_itr.next();
		  String temp_name = temp.getName();
		  if(temp_name.equals(name)){
			  shoppingCart.remove(temp);
			  object_count++;
		  }
	  }
	  
	  // display results
	  System.out.println(name + " objects deleted: " + object_count);
  }
  
  private static void update(String data){
	  //TODO implement method
	  // update <name> <quantity> updates the quantity field for
	  // the first occurrence of a matching name.
	  // then output the name and new quantity value for that object to the screen.
	  /** Make this stuff nice
	  try{
		  int quant = Integer.parseInt(data);
		  if(quant < 0){
			  throw new NumberFormatException();
		  }
	  }
	  catch(NumberFormatException e){
		  String errmsg = "Error: invalid quantity!\n";
		  errmsg += "Please enter quantity as a positive integer.";
		  throw new IllegalArgumentException(errmsg);
	  }
	  
	  int index = cartSearch(name);
	  if (index != -1){
		  shoppingCart.get(index).setQuantity(quantity);
	  }
	  else{
		  String errmsg = "Error: " + name + " is not in your shopping cart!\n";
		  throw new IllegalArgumentException(errmsg);
	  }
	  */
  }
  
  private static void print(){
	  //TODO implement method
	/** print the contents of the shopping cart in ascending order by name, 
	 * show all name, quantity, price after tax and shipping charges
	 * After, print the total charges for entire cart.
	 * Output is to the screen, make it readable              */
  }
  
  /** static int cartSearch(String name){
  
  // I see what you tried to do  here with the binary search
  
  
  int low = 0;
  int high = shoppingCart.size() - 1;
  while (low <= high)
  {
	  int mid = low + (high - low) / 2;
	  if (name.compareTo(shoppingCart.get(mid).getName()) < 0)
		  high = mid - 1;
	  else if (name.compareTo(shoppingCart.get(mid).getName()) > 0)
		  low = mid + 1;
	  else{ //this ensures we hit the first item of "name" if there are multiple
		  while (name.compareTo(shoppingCart.get(mid).getName()) == 0)
			  mid-=1;
		  return mid+1;
	  }
  }
  return -1;
  
}*/

}
