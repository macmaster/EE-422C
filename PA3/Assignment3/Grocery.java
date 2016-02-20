package Assignment3;

public class Grocery extends Item 
{	
	// perishable shipping rate
	private boolean perishable;
	private final double PREMIUM = 0.20; 
	
	Grocery(String name, float price, int quantity, int weight){
		super(name, price, quantity, weight);
		this.perishable = false; //not perishable by default
	}
	
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
	
	void setPersihable(){
		this.perishable = true;
	}
	
	// Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	
}
