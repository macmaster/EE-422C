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
		float final_price = this.price * this.quantity;
		float tax_amount = 0;
		
		// clothing tax
		if (this.tax == true){
			tax_amount = this.price * this.quantity * TAX_RATE;
		}
		
		final_price = final_price + tax_amount;
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
	
	/* 
	 * Automatically generated equals method for two Item objects
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Clothing other) {
		if(this == other)
			return true;
		if (!this.name.equals(other.getName()))
			return false;
		if (this.price != other.getPrice())
			return false;
		if (this.weight != other.getWeight())
			return false;
		if (this.tax != other.tax)
			return false;
		return true;
	}
	
	
}
