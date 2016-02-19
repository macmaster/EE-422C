package Assignment3;

public class Item 
{
//Declare variables for this class. Think about its type: public, protected or private?
	protected String name;
	protected float price;
	protected int quantity;
	protected int weight;

// You will need a constructor (Why?). Create it here.
	
	Item(String n, float p, int q, int w)
	{
		name = n;
		price = p;
		quantity = q;
		weight = w;
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
		//Print all applicable attributes of this class
	}

}
