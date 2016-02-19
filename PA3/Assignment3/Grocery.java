package Assignment3;

public class Grocery extends Item {

	private boolean perishable;
	
	Grocery(String n, float p, int q, int w, boolean pe) {
		super(n, p, q, w);
		perishable = pe;
	}
	
	//override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	
}
