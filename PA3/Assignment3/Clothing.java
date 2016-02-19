package Assignment3;

public class Clothing extends Item 
{
	private boolean tax;

	// variables, constructors as necessary
	
	Clothing(String n, float p, int q, int w, boolean t) {
		super(n, p, q, w);
		tax = t;
	}

	float calculatePrice () 
	{
		float final_price = 0;
		final_price = this.price;
		if (this.tax == true)
			final_price *= 1.1;
		return final_price;
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
	}
	

}
