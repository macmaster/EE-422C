/** RandomBrain ********************************************
 * Manage the random generation of the brain.
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class RandomBrain {
	
	/** image of a random brain */
	private BufferedImage image;
	
	/** the brain's x-position */
	protected double x;
	/** the brain's y-position */
	protected double y;
	
	/** the brain's dx-units */
	protected double dx;
	/** the brain's dy-units */
	protected double dy;
	
	/** current brain width */
	protected double width;
	/** current brain height */
	protected double height;
	
	/** brain's number index */
	protected int index;
	/** time animation unit */
	protected int timeUnit;
	
	/** Construct a random brain image */
	public RandomBrain() {
		try {
			//randomly calculate size index of brain
			int randomIndex = (int) (3.0 / (Math.random() + 3.0/100.0) - 2);
			if(randomIndex < 0) randomIndex = 0; // normalize
			
			//set fields
			image = Resources.BRAIN_IMAGES[randomIndex];
			index = randomIndex;
			width = image.getWidth();
			height = image.getHeight();
			x = -width;
			y = Math.random() * (GamePanel.HEIGHT  - height);
			dx = width / 30.0;
			timeUnit = 0;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * update the brain's position with dx & dy units
	 */
	public void update() {
		x += dx;
		y += dy;
	}

	/** draw a brain object */
	public void draw(Graphics2D g) {
		double deviation = 1.5 * dx * Math.sin(x*2*Math.PI / (100*dx));
		int drawY = (int)(y + deviation);
		g.drawImage(image, (int) x, drawY, null);
	}
}
