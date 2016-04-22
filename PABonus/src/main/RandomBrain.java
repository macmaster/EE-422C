package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class RandomBrain {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	public RandomBrain() {
		try {
			int randomIndex = (int) (Math.random() * 100);
			image = Resources.BRAIN_IMAGES[randomIndex];
			x = -image.getWidth();
			y = Math.random() * (GamePanel.HEIGHT  - image.getHeight());
			dx = image.getWidth() / 30.0;
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
		g.drawImage(image, (int) x, (int) y, null);
	}
	
	
}
