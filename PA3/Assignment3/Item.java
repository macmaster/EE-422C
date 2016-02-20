/** Item ****************************************************
 * Base Class for shopping cart items
 * store the common quantities.
 * provide default price calculation.
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, mm75343
 * @author Ronald Macmaster, Michael Marino
 * @version 1.01 2/19/2016
 ************************************************************/

package Assignment3;

public class Item 
{
	protected String name;  
	protected float price;  
	protected int quantity; 
	protected int weight;   
	
	Item(String name, float price, int quantity, int weight){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.weight = weight;
	}
	
	float calculatePrice(){
		float final_price = 0;
		float shipping_price = (20 * this.weight * this.quantity); 
		final_price = this.price + shipping_price;
		return final_price;
	}
	
	void printItemAttributes(){
		// Name, Price, and Quantity
		String itemString = ""; 
		
		// build string
		itemString += "Item: " + this.name + "\t";
		itemString += "Quantity: " + this.quantity + "\t";
		itemString += "Price: " + this.calculatePrice();
		
		// print string
		System.out.println(itemString);
	}
	
}
