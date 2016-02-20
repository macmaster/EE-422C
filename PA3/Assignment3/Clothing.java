package Assignment3;

public class Clothing extends Item 
{
	// clothing tax rate
	private boolean tax;
	final private double TAX_RATE = 0.10; 
	
	
	Clothing(String name, float price, int quantity, int weight, boolean tax)
	{
		super(name, price, quantity, weight);
		this.tax = tax;
	}

	float calculatePrice () 
	{
		float final_price = 0;
		final_price = this.price;
		
		// clothing tax
		if (this.tax == true){
			final_price += final_price * TAX_RATE;
		}
		return final_price;
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
	}
	

}
