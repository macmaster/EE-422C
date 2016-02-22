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
	private static final float PREMIUM = 0.20f; 

	public Electronics(String name, float price, int quantity, int weight){
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
	
	@Override
	float getPriceAfterTax(){
		float base_price = 0;
		float tax_amount = 0; 
		
		// electronics tax
		if (this.tax == true){
			tax_amount = this.price * TAX_RATE;
		}
		base_price = this.price + tax_amount;
		
		return base_price;
	}
	
	@Override
	float getShippingPrice(){
		float shipping_price = (20 * this.weight * this.quantity);
		
		// fragile shipping
		if (this.fragile == true){
			shipping_price += shipping_price * PREMIUM;
		}
		
		return shipping_price;
	}
	
	void printItemAttributes(){
		// Name, Price, Quantity and Perishable
		String itemString = ""; 
		
		// build string
		itemString += "Item: " + this.name + "\t";
		itemString += "Quantity: " + this.quantity + "\t";
		itemString += "Price: " + this.calculatePrice() + "\t\t";
		
		// fragile electronics
		if(this.fragile == true){ 
			itemString += "Fragile: " + "Yes" + "\t\t";
		}
		else{
			itemString += "Fragile: " + "No" + "\t\t";
		}
		
		// taxable electronics
		if(this.tax == true){ 
			itemString += "Taxed: " + "Yes";
		}
		else{
			itemString += "Taxed: " + "No";
		}
		
		// print string
		System.out.println(itemString);
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
