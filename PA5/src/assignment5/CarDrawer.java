/********************CarDrawer**********************************
 * Car Drawer Applet for 422C
 * Creates an applet for a car racing interface
 * 
 * Section : F 2:00 - 3:30pm
 * Author: Ronny Macmaster
 * Date: 3/27/16
 * EID: rpm953
 ***************************************************************/

/** the purpose of this example applet it to draw a boxy car out of
 basic graphical shapes (rectangles, circles and lines) on the applet
 window. It's position and size has been predetermined by sketching
 it out on graph paper first. The specific values of the coordinates
 of the shapes have been arbitrarily chosen.
*/

package assignment5;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class CarDrawer extends Applet{
	public void init(){
		
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		// create the car body
		Rectangle body = new Rectangle(100, 110, 60, 10);
		// create the car tires
		Ellipse2D.Double frontTire = new Ellipse2D.Double(110, 120, 10, 10);
		Ellipse2D.Double rearTire = new Ellipse2D.Double(140, 120, 10, 10);
		// create the 4 points connecting the windshields and roof
		Point2D.Double r1 = new Point2D.Double(110, 110);
		Point2D.Double r2 = new Point2D.Double(120, 100);
		Point2D.Double r3 = new Point2D.Double(140, 100);
		Point2D.Double r4 = new Point2D.Double(150, 110);
		// create the windshields and roof of the car
		Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
		Line2D.Double roofTop = new Line2D.Double(r2, r3);
		Line2D.Double rearWindshield = new Line2D.Double(r3, r4);

		// draw all of the car parts on the screen
		g2.draw(body);
		g2.draw(frontTire);
		g2.draw(rearTire);
		g2.draw(frontWindshield);
		g2.draw(roofTop);
		g2.draw(rearWindshield);

		// draw the label under the car
		g2.drawString("UT JavaMobile 1.0", 100, 150);
	} // end of paint
} // end of CarDrawer