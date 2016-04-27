/** Resources *********************************************
 * External image resources manager
 * Also provides image transformation methods
 * (extend to octave in the future?)
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resources {
	
	/** image array of available brain pictures */ 
	public static BufferedImage[] BRAIN_IMAGES = new BufferedImage[100];
	private static final String BRAIN_PATH = "Resources/brain.png";
	
	/** image of the master mind himself */
	public static BufferedImage MASTERMIND_IMAGE;
	private static final String MASTERMIND_PATH = "Resources/mastermind.jpg";
	
	//public static BufferedImage[] TRANSPARENT_BLACK_ARRAY = new BufferedImage[100];
	
	/**
	 * generate the image arrays before using
	 * @param panel
	 * @throws IOException
	 */
	public static void initResources(GamePanel panel) throws IOException {
		
		//generate BRAINZ
		BufferedImage imageSrc = ImageIO.read(
				new FileInputStream(BRAIN_PATH)
			);
		for(int i = 0; i < 100; i++) {
			double scale = 0.01 + (.99/100.0) * i * .4;
			BufferedImage image = toBufferedImage(imageSrc.getScaledInstance(
					(int) (imageSrc.getWidth() * scale),
					(int) (imageSrc.getHeight() * scale),
					Image.SCALE_DEFAULT));
			BRAIN_IMAGES[i] = image;//blur(image);
		}
		
		//generate mastermind
		double scale = 0.5;
		imageSrc = ImageIO.read(
				new FileInputStream(MASTERMIND_PATH)
			);
		MASTERMIND_IMAGE = toBufferedImage(imageSrc.getScaledInstance(
				(int) (imageSrc.getWidth() * scale),
				(int) (imageSrc.getHeight() * scale),
				Image.SCALE_DEFAULT));
		
		
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
	
	/**
	 * Converts a given Image into a blurred image
	 * uses a blur transform
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage blur(BufferedImage img)
	{
		for(int i = 1; i < img.getWidth() - 1; i++) {
			for(int j = 1; j < img.getHeight() - 1; j++) {
				int[][] adj = getAdjacentPixels(i, j);
				Color c = new Color(img.getRGB(i, j), true);
				if(c.getAlpha() > 0) {
					int[] rgbSum = new int[3];
					for(int k = 0; k < 9; k++) {
						int[] rgb = rgbToVals(img.getRGB(adj[k][0], adj[k][1]));
						for(int l = 0; l < 3; l++) {
							rgbSum[l] += rgb[l];
						}
					}
					int[] rgbAvg = new int[3];
					for(int l = 0; l < 3; l++) {
						rgbAvg[l] = rgbSum[l] / 9;
					}
					System.out.println(rgbAvg[0] + "," + rgbAvg[1] + "," + rgbAvg[2]);
					img.setRGB(i, j, valsToRgb(rgbAvg));
				}
			}
		}
		
	    return img;
	}
	
	/**
	 * returns the 9x2 adjacent pixel rectangle for the current object
	 * @param x
	 * @param y
	 * @return
	 */
	public static int[][] getAdjacentPixels(int x, int y) {
		int[][] adj = new int[9][2];
		for(int i = 0; i < 3; i++) {
			adj[i][0] = x - 1 + i;
			adj[i][1] = y - 1;
		}
		for(int i = 0; i < 3; i++) {
			adj[i + 3][0] = x - 1 + i;
			adj[i + 3][1] = y;
		}
		for(int i = 0; i < 3; i++) {
			adj[i + 6][0] = x - 1 + i;
			adj[i + 6][1] = y + 1;
		}
		return adj;
	}
	
	/**
	 * converts the rgb values to an integer array
	 * @param rgb
	 * @return
	 */
	public static int[] rgbToVals(int rgb) {
		if(rgb > 0) {
			int blah = 9;
		}
		int[] vals = new int[3];
		vals[0] = (rgb >> 16) & 0xFF; //r
		vals[1] = (rgb >> 8) & 0xFF; //g
		vals[2] = rgb & 0xFF; //b
		return vals;
	}
	
	/**
	 * converts the color value array to an rgb value
	 * @param vals
	 * @return
	 */
	public static int valsToRgb(int[] vals) {
		int rgb = vals[0];
		rgb = (rgb << 8) + vals[1];
		rgb = (rgb << 8) + vals[2];
		return rgb;
	}
	
	/**
	 * sets the transparency of a given image
	 * @param img
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isTransparent( BufferedImage img, int x, int y ) {
		  int pixel = img.getRGB(x,y);
		  if( (pixel>>24) == 0x00 ) {
		      return true;
		  }
		  return false;
		}
	
	
}
