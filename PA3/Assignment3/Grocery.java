package Assignment3;

public class Grocery extends Item {

	private boolean perishable;
	
	Grocery(String n, float p, int q, int w, boolean pe) {
		super(n, p, q, w);
		perishable = pe;
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		final_price = this.price;
		if (this.perishable == true)
			final_price += (20 * this.weight * this.quantity*1.2);
		else
			final_price += (20 * this.weight * this.quantity);
		return final_price;
	}
	
	//override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	
}
