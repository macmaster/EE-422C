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
import java.util.Collections;
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
				  // debug System.out.println("command: "+operation+"\tData: "+data);
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
  
  /** Insert() ******************************************************************
   * insert <category> <name> <price> <quantity> <weight> 
   * 			<optional field1> <optional field2>
   * 
   * instantiates and object of the appropriate type (Groceries, Clothing, Electronics)
   * and adds it to the shopping cart
   * 
   * @param data : the data string which contains the item fields
   * ***************************************************************************/  
  private static void insert(String data){ 
	 String[] fields = data.trim().split(" +");
	 int fields_count = fields.length;
	 try{
		 // parse item fields
		 String life = ""; 
		 String destination = ""; 
		 String category = fields[0].toLowerCase(); 
		 String name = fields[1];
		 float price = Float.parseFloat(fields[2]);
		 int quantity = Integer.parseInt(fields[3]);
		 int weight = Integer.parseInt(fields[4]);
		 
		 // valid numerical input checks
		 if(price < 0){
			 throw new IllegalArgumentException(name + " cannot have a negative price!");
		 }
		 else if(quantity < 0){
			 throw new IllegalArgumentException(name + " cannot have a negative quantity!");
		 }
		 else if(weight < 0){
			 throw new IllegalArgumentException(name + " cannot have a negative weight");
		 }
		 
		 // handle item creation by type
		 if(category.equals("groceries")){
			 Grocery grocery = new Grocery(name, price, quantity, weight);
			 if(fields_count > 5){
			 	 life = fields[5].toUpperCase();
			 	 if(life.equals("P")){ // perishable groceries
			 		 grocery.setPersihable(true);
			 	 }
			 }
			 // check for existing duplicate
			 addCartItem(grocery);
		 }
		 else if(category.equals("electronics")){
			 Electronics electronic = new Electronics(name, price, quantity, weight);
			 if(fields_count == 6){
			 	 life = fields[5].toUpperCase();
			 	 if(life.equals("F")){ // fragile electronics
			 		 electronic.setFragile(true);
			 	 }
			 	 else{ // user may try to use 5th field as shipping state
			 		 destination = fields[5].toUpperCase();
			 		 electronic.setStateTax(destination);
			 	 }
			 }
			 if(fields_count > 6){
				 life = fields[5].toUpperCase();
				 destination = fields[6].toUpperCase();
				 if(life.equals("F")){ // fragile electronics
			 		 electronic.setFragile(true);
			 	 }
				 electronic.setStateTax(destination);
			 }
			 addCartItem(electronic);
		 }
		 else if(category.equals("clothing")){
			 Clothing clothing = new Clothing(name, price, quantity, weight);
			 addCartItem(clothing);
		 }
		 else{ // invalid category
			 String errmsg = "Error: " + category + " is an invalid item category!\n";
			 errmsg += "valid categories: groceries electronics clothing";
			 throw new IllegalArgumentException(errmsg);
		 }
		 
		 // success message
		 System.out.println(name + " was inserted into the cart.");
		 
	 }catch(NumberFormatException err){
		 System.err.println("Insert Failed!");
		 System.err.println("Could not interpret a number field.");
	 }catch(Exception err){
		 System.err.println("Insert Failed!");
		 System.err.println(err.getMessage());
	 }
	 
	 
	  
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
	  int object_quantity = 0;
	  Iterator<Item> cart_itr = shoppingCart.iterator();
	  while(cart_itr.hasNext()){
		  Item temp = cart_itr.next();
		  String temp_name = temp.getName().toLowerCase();
		  if(temp_name.equals(name)){
			  object_count++;
			  object_quantity += temp.getQuantity();
		  }
	  }
	  
	  // display results
	  System.out.println("number of " + name + " objects: " + object_count);
	  System.out.println("quantity of " + name + " objects: " + object_quantity);
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
			  cart_itr.remove();
			  object_count++;
		  }
	  }
	  
	  // display results
	  System.out.println(name + " objects deleted: " + object_count);
  }
  
  
  /** Update() ******************************************************************
   * update <name> <quantity> updates the quantity field for
   * the first occurrence of a matching name.
   * then output the name and new quantity value for that object to the screen.
   * 
   * @param data : the data string which contains the search name	to delete			
   * ***************************************************************************/
  private static void update(String data){
	  try{
		  // parse update fields
		  String[] fields = data.trim().split(" +");
		  boolean item_found = false;
		  String name = fields[0]; 
		  int quantity = Integer.parseInt(fields[1]);
		  
		  // check valid quantity
		  if(quantity < 0){
			  throw new NumberFormatException();
		  }
		  
		  //search for object to update
		  Iterator<Item> cart_itr = shoppingCart.iterator();
		  while(cart_itr.hasNext()){
			  Item temp = cart_itr.next();
			  String temp_name = temp.getName();
			  if(temp_name.equals(name)){
				  if(quantity == 0){ // remove item 
					  System.out.println(name + " was found and removed from the cart (quantity 0).");
					  cart_itr.remove();
					  return;
				  }
				  else{ // item found, update quantity
					  item_found = true;
					  temp.setQuantity(quantity);
					  break;
				  }
			  }
		  }
		  
		  // update results
		  if(item_found){
			  System.out.println(name + " quantity updated to: " + quantity);
		  }
		  else{
			  System.out.println(name + " was not found in the shopping cart.");
		  }

	  }catch(ArrayIndexOutOfBoundsException err){
		  String errmsg = "Error: invalid update command!\n";
		  errmsg += "Please enter the correct number of update fields.\n";
		  errmsg += "update <name> <quantity>";
		  throw new IllegalArgumentException(errmsg);
	  }
	  catch(NumberFormatException e){
		  String errmsg = "Error: invalid quantity!\n";
		  errmsg += "Please enter quantity as a positive integer.";
		  throw new IllegalArgumentException(errmsg);
	  }

  }
  
  /** Print() ******************************************************************
   *  prints the contents of the shopping cart in ascending order by name.
   *  prints each item's the name, quantity, price after tax and shipping charges
   *  After, prints the total charges for entire cart.
   *              				
   * ***************************************************************************/
  private static void print(){
	  // sorts the shopping Cart by name
	  // (using merge sort? O(nlogn)?)
	  Collections.sort(shoppingCart);
	  
	  // receipt output prompt
	  System.out.print("\n");
	  System.out.println("Printing Shopping Cart Receipt ... ");
	  System.out.print("\n");
	  
	  // print cart items / calculate total
	  float total_price = 0;
	  Iterator<Item> cart_itr = shoppingCart.iterator();
	  while(cart_itr.hasNext()){
		  Item temp = cart_itr.next();
		  System.out.print("\t"); //indent item fields
		  temp.printItemAttributes();
		  total_price += temp.calculatePrice(); // update total
	  }
	  
	  // total / thank the customer
	  System.out.print("\n");
	  System.out.println("Total: " + total_price);
	  System.out.println("Thank you for shopping at 422CMart. Come again.");
	  System.out.print("\n");
	  
  }

  /** addCartItem() *************************************************************
   *  Checks to see if the item already exists in the cart.
   *  If it does, it updates the existing quantity
   *  Otherwise, it adds the item as a new addition
   *              				
   * @param obj : item to add to the cart
   * ***************************************************************************/
  private static void addCartItem(Item obj){
	  int obj_quantity = obj.getQuantity(); //quantity of new item
	  Iterator<Item> cart_itr = shoppingCart.iterator();
	  while(cart_itr.hasNext()){
		  Item temp = cart_itr.next();
		  int temp_quantity = temp.getQuantity(); //quantity of old item
		  if(temp.equals(obj)){
			  temp.setQuantity(temp_quantity + obj_quantity);
			  return;
		  }
	  }
	  //item does not exist in the cart
	  shoppingCart.add(obj);
  }
  
}
