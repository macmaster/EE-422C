package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resources {

	public static BufferedImage[] BRAIN_IMAGES = new BufferedImage[100];
	private static final String IMG_PATH = "/brain.png";
	
	public static void initResources(GamePanel panel) throws IOException {
		BufferedImage imageSrc = ImageIO.read(
				panel.getClass().getResourceAsStream(IMG_PATH)
			);
		for(int i = 0; i < 100; i++) {
			double scale = 0.01 + (.99/100.0) * i * .4;
			BufferedImage image = toBufferedImage(imageSrc.getScaledInstance(
					(int) (imageSrc.getWidth() * scale),
					(int) (imageSrc.getHeight() * scale),
					Image.SCALE_DEFAULT));
			BRAIN_IMAGES[i] = image;
		}
					
	}
	
	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
}
