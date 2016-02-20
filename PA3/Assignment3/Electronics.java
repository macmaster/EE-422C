package Assignment3;

public class Electronics extends Item 
{
	//electronics tax
	private boolean tax;	
	final private float TAX_RATE = 0.10f;
	// fragile shipping rate
	private boolean fragile;
	final private float PREMIUM = 0.20f; 

	Electronics(String name, float price, int quantity, int weight){
		super(name, price, quantity, weight);
		this.tax = true;	//taxed by default
		this.fragile = false; // not fragile by default
	}

	float calculatePrice(){
		float final_price = 0;
		float tax_amount = 0; 
		float shipping_price = (20 * this.weight * this.quantity);
		
		// electronics tax
		if (this.tax == true){
			tax_amount = this.price * TAX_RATE;
		}
		
		// fragile shipping
		if (this.fragile == true){
			shipping_price += shipping_price * PREMIUM;
		}
		
		final_price = this.price + tax_amount + shipping_price;
		return final_price;
	}
	
	void noTax(){
		this.tax = false;
	}

	//Implement calculate price/print methods as necessary

}
