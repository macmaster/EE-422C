/** Electronics **********************************************
 * Class for Grocery items
 * goods can be fragile (pay a premium).
 * goods are taxable unless shipped to a tax-free state
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, mm75343
 * @author Ronald Macmaster, Michael Marino
 * @version 1.01 2/19/2016
 ************************************************************/

package Assignment3;

import java.util.Set;
import java.util.HashSet;

public class Electronics extends Item 
{
	//electronics tax
	private boolean tax;	
	private static final float TAX_RATE = 0.10f;
	private static final Set<String> TAX_FREE_STATES = new HashSet<String>(){{
		add("TX"); add("NM"); add("VA"); add("AZ"); add("AK");
	}};
	
	// fragile shipping rate
	private boolean fragile;
	final private float PREMIUM = 0.20f; 

	Electronics(String name, float price, int quantity, int weight){
		super(name, price, quantity, weight);
		this.tax = true; //taxed by default
		this.fragile = false; // not fragile by default
	}
	
	@Override 
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
	
	void setStateTax(String state){
		if(TAX_FREE_STATES.contains(state)){
			this.tax = false;
		}
	}
	
	void setFragile(boolean fragile){
		this.fragile = fragile;
	}

	//Implement calculate price/print methods as necessary

}
