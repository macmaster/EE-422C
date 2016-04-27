package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class RandomBrain {
	
	private BufferedImage image;
	
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	protected double width;
	protected double height;
	protected int index;
	
	protected int timeUnit;
	
	public RandomBrain() {
		try {
			int randomIndex = (int) (3.0 / (Math.random() + 3.0/100.0) - 2);
			if(randomIndex < 0) randomIndex = 0;
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

	public void update() {
		x += dx;
		y += dy;
	}

	public void draw(Graphics2D g) {
		double deviation = 1.5 * dx * Math.sin(x*2*Math.PI / (100*dx));
		int drawY = (int)(y + deviation);
		g.drawImage(image, (int) x, drawY, null);
	}
	
	
}
