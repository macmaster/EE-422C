package Assignment3;

public class Item 
{
	protected String name;  
	protected float price;  
	protected int quantity; 
	protected int weight;   

	
	Item(String name, float price, int quantity, int weight)
	{
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.weight = weight;
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		float shipping_price = (20 * this.weight * this.quantity); 
		final_price = this.price + shipping_price;
		return final_price;
	}
	
	void printItemAttributes () 
	{
		// Name, Price, and Quantity, Weight
		String itemString = ""; 
		
		// build string
		itemString += "Item: " + this.name + "\t";
		itemString += "Quantity: " + this.quantity + "\t";
		itemString += "Price: " + this.calculatePrice();
		
		// print string
		System.out.println(itemString);
	}
	
	
}
