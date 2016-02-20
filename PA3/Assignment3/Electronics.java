package Assignment3;

public class Electronics extends Item 
{
	private boolean fragile;
	private boolean tax;

	Electronics(String name, float price, int quantity, int weight, boolean fragile, boolean tax)
	{
		super(name, price, quantity, weight);
		this.fragile = fragile;
		this.tax = tax;
	}

	float calculatePrice () 
	{
		float final_price = 0;
		final_price = this.price;
		if (this.tax == true)
			final_price *= 1.1;
		if (this.fragile == true)
			final_price += (20 * this.weight * this.quantity*1.2);
		else
			final_price += (20 * this.weight * this.quantity);
		return final_price;
	}
	//Implement calculate price/print methods as necessary

}
