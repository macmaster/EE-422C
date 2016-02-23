/** Grocery **************************************************
 * Class for Grocery items
 * goods can be perishable (pay a premium).
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, mm75343
 * @author Ronald Macmaster, Michael Marino
 * @version 1.01 2/19/2016
 ************************************************************/

package Assignment3;

public class Grocery extends Item 
{	
	// perishable shipping rate
	private boolean perishable;
	private static final float PREMIUM = 0.20f; 
	
	public Grocery(String name, float price, int quantity, int weight){
		super(name, price, quantity, weight);
		this.perishable = false; //not perishable by default
	}
	
	@Override
  /** calculatePrice() **********************************************************
   * Calculates the total price of the clothing bundle
   * Price formula : Total = price * quantity
   * Include the shipping price.
   * Include the premium shipping rate for perishable items
   *               
   * @param return : the total price of the item				
   * ***************************************************************************/
	public float calculatePrice(){
		float final_price = this.price * this.quantity;
		float shipping_price = (20 * this.weight * this.quantity);
		
		// premium shipping
		if (this.perishable == true){ 
			shipping_price += shipping_price * PREMIUM;
		}
		
		final_price = final_price + shipping_price;
		return final_price;
	}
	
	@Override
	/**
	 * @return the shipping price
	 */
	public float getShippingPrice(){
		float shipping_price = (20 * this.weight * this.quantity);
		
		// fragile shipping
		if (this.perishable == true){
			shipping_price += shipping_price * PREMIUM;
		}
		
		return shipping_price;
	}
	
	@Override
  /** printItemAttributes() ****************************************************
   * Prints all the grocery attributes in a nice string format
   * Prints the Name, Quantity, and Price
   *               
   * @param return : the total price of the item				
   * ***************************************************************************/	
	public void printItemAttributes(){
		// Name, Price, Quantity and Perishable
		String itemString = ""; 
		
		// build string
		itemString += "Item: " + this.name + "\t";
		itemString += "Quantity: " + this.quantity + "\t";
		itemString += "Price: " + this.calculatePrice() + "\t\t";
		
		// perishable groceries
		if(this.perishable == true){ 
			itemString += "Perishable: " + "Yes";
		}
		else{
			itemString += "Perishable: " + "No";
		}
		
		// print string
		System.out.println(itemString);
	}
	
	/**
	 * Set's the groceries pershiable flag
	 * @param perishable : new perishable value
	 */
	public void setPersihable(boolean perishable){
		this.perishable = perishable;
	}
	
	/* 
	 * Automatically generated equals method for two Item objects
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Grocery other) {
		if(this == other)
			return true;
		if (!this.name.equals(other.getName()))
			return false;
		if (this.price != other.getPrice())
			return false;
		if (this.weight != other.getWeight())
			return false;
		if (this.perishable != other.perishable)
			return false;
		return true;
	}
	
}
