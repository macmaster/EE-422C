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
	private static final Set<String> US_STATES = new HashSet<String>(){{
		add("AL"); add("AK"); add("AZ"); add("AR"); add("CA"); 
		add("CO"); add("CT"); add("DE"); add("FL"); add("GA"); 
		add("HI"); add("ID"); add("IL"); add("IN"); add("IA"); 
		add("KS"); add("KY"); add("LA"); add("ME"); add("MD"); 
		add("MA"); add("MI"); add("MN"); add("MS"); add("MO"); 
		add("MT"); add("NE"); add("NV"); add("NH"); add("NJ"); 
		add("NM"); add("NY"); add("NC"); add("ND"); add("OH"); 
		add("OK"); add("OR"); add("PA"); add("RI"); add("SC"); 
		add("SD"); add("TN"); add("TX"); add("UT"); add("VT"); 
		add("VA"); add("WA"); add("WV"); add("WI"); add("WY"); 
		add("");
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
  /** calculatePrice() **********************************************************
   * Calculates the total price of the item bundle
   * Price formula : Total = price * quantity
   * Include the shipping price.
   * Include the tax amount
   * Include the fragile premium shipping cost
   *               
   * @param return : the total price of the item				
   * ***************************************************************************/
	public float calculatePrice(){
		float tax_amount = 0; 
		float final_price = this.price * this.quantity;
		float shipping_price = (20 * this.weight * this.quantity);
		
		// electronics tax
		if (this.tax == true){
			tax_amount = this.price * this.quantity * TAX_RATE;
		}
		
		// fragile shipping
		if (this.fragile == true){
			shipping_price += shipping_price * PREMIUM;
		}
		
		final_price = final_price + tax_amount + shipping_price;
		return final_price;
	}
	
	@Override
	/**
	 * @return the price after tax
	 */
	public float getPriceAfterTax(){
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
	/**
	 * @return the shipping price
	 */
	public float getShippingPrice(){
		float shipping_price = (20 * this.weight * this.quantity);
		
		// fragile shipping
		if (this.fragile == true){
			shipping_price += shipping_price * PREMIUM;
		}
		
		return shipping_price;
	}
	
  /** printItemAttributes() ****************************************************
   * Prints all the item attributes in a nice string format
   * Prints the Item, Quantity, and Price
   * Indicates fragile and taxable electronics
   *               
   * @param return : the total price of the item				
   * ***************************************************************************/	
	public void printItemAttributes(){
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
	
	/**
	 * Set's the tax rules for the shipping state.
	 * @param state : state to ship to.
	 */
	public void setStateTax(String state){
		if(TAX_FREE_STATES.contains(state)){
			this.tax = false;
		}
	}
	
	/**
	 * Set's the electronics' fragile flag
	 * @param fragile : new fragile value
	 */
	public void setFragile(boolean fragile){
		this.fragile = fragile;
	}
	
	/**
	 * ValidState()
	 * @param state
	 * @return true if the shipping destination is valid.
	 */
	public static boolean validState(String state){
		if(US_STATES.contains(state)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/* 
	 * Automatically generated equals method for two Item objects
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Electronics other) {
		if(this == other)
			return true;
		if (!this.name.equals(other.getName()))
			return false;
		if (this.price != other.getPrice())
			return false;
		if (this.weight != other.getWeight())
			return false;
		if (this.fragile != other.fragile)
			return false;
		if (this.tax != other.tax)
			return false;
		return true;
	}

}
