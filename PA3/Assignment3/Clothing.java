package Assignment3;

public class Clothing extends Item
{
	// clothing tax rate
	private boolean tax;
	final private float TAX_RATE = 0.10f; 
		
	Clothing(String name, float price, int quantity, int weight){
		super(name, price, quantity, weight);
		this.tax = true; //taxed by default
	}

	float calculatePrice(){
		float final_price = 0;
		float tax_amount = 0;
		
		// clothing tax
		if (this.tax == true){
			tax_amount = this.price * TAX_RATE;
		}
		
		final_price = this.price + tax_amount;
		return final_price;
	}
	
	void printItemAttributes(){
		//Print all applicable attributes of this sub-class
	}
	
}
