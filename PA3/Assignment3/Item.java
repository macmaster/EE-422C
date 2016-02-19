package Assignment3;

public class Item 
{
	protected String name;  //item name
	protected float price;  //item price
	protected int quantity; //items quantity
	protected int weight;   //

	
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
		final_price = this.price;
		final_price += (20 * this.weight * this.quantity);
		return final_price;
	}
	

	void printItemAttributes () 
	{
		String itemString = ""; // Name, Price, and Quantity, Weight
		
		// build string
		itemString += "Item: " + this.name + "\t";
		itemString += "Price: " + this.calculatePrice() + "\t";
		itemString += "Quantity: " + this.quantity + "\t";
		itemString += "Weight: " + this.weight;
		
		// print string
		System.out.println(itemString);
	}

}
