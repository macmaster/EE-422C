package Assignment3;

public class Electronics extends Item 
{
	// fragile shipping rate
	private boolean fragile;
	final private float PREMIUM = 0.20f; 
	//electronics tax
	private boolean tax;	
	final private float TAX_RATE = 0.10f;

	Electronics(String name, float price, int quantity, int weight){
		super(name, price, quantity, weight);
		this.fragile = false; // not fragile by default
		this.tax = true;	//taxed by default
	}

	float calculatePrice(){
		float final_price = 0;
		float tax_amount = 0; 
		float shipping_price = (20 * this.weight * this.quantity);
		final_price = this.price;
		
		// electronics tax
		if (this.tax == true){
			final_price += final_price * TAX_RATE;
		}
		
		// fragile shipping
		if (this.fragile == true){
			shipping_price += shipping_price * PREMIUM;
		}
		else{
			final_price += (20 * this.weight * this.quantity);
		}
		return final_price;
	}
	
	void noTax(){
		this.tax = false;
	}
				
		// premium shipping
		if (this.perishable == true){ 
			
		}
		final_price = this.price + shipping_price;
		return final_price;
	//Implement calculate price/print methods as necessary

}
