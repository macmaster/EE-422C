package Assignment3;

public class Electronics extends Item 
{
	private boolean fragile;
	private boolean tax;

	Electronics(String n, float p, int q, int w, boolean f, boolean t) {
		super(n, p, q, w);
		fragile = f;
		tax = t;
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
