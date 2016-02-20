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
	private static final double PREMIUM = 0.20; 
	
	Grocery(String name, float price, int quantity, int weight){
		super(name, price, quantity, weight);
		this.perishable = false; //not perishable by default
	}
	
	@Override
	float calculatePrice(){
		float final_price = 0;
		float shipping_price = (20 * this.weight * this.quantity);
		
		// premium shipping
		if (this.perishable == true){ 
			shipping_price += shipping_price * PREMIUM;
		}
		
		final_price = this.price + shipping_price;
		return final_price;
	}
	
	void setPersihable(boolean perishable){
		this.perishable = perishable;
	}
	
	// Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	
}
