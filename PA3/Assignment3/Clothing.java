/** Clothing ************************************************
 * Class for Clothing goods
 * clothing goods are ALWAYS taxable 
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, mm75343
 * @author Ronald Macmaster, Michael Marino
 * @version 1.01 2/19/2016
 ************************************************************/


package Assignment3;

public class Clothing extends Item
{
	// clothing tax rate
	private boolean tax;
	private static final float TAX_RATE = 0.10f; 
		
	Clothing(String name, float price, int quantity, int weight){
		super(name, price, quantity, weight);
		this.tax = true; //taxed by default
	}

	@Override
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
	
	@Override
	float getPriceAfterTax(){
		float base_price = 0;
		float tax_amount = 0; 
		
		// clothing tax
		if (this.tax == true){
			tax_amount = this.price * TAX_RATE;
		}
		base_price = this.price + tax_amount;
		
		return base_price;
	}
	
	
}
