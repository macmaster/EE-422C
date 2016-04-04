/********************Car2D***************************************
 * Java Car Class
 * Defines a Car class to keep track of Car attributes
 * 
 * Section : F 2:00 - 3:30pm
 * Author: Ronny Macmaster
 * Date: 3/27/16
 * EID: rpm953
 ***************************************************************/

package assignment5;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Car2D{
	// top-left corner coordinates of car's square space
	int x, y; // x-coordinate, y-coordinate
	int height, width; // car height, width
	Polygon roof; // burnt orange car roof
	Rectangle body; // burnt orange car body
	Ellipse2D.Double frontTire; // black front wheel
	Ellipse2D.Double rearTire; // black front wheel

	// debug
	Rectangle outline;

	public Car2D(int x, int y){
		// initialize car data
		this.x = x;
		this.y = y;
		height = 30;
		width = 60;
		roof = new Polygon();
		body = new Rectangle();
		frontTire = new Ellipse2D.Double();
		rearTire = new Ellipse2D.Double();

		// build car parts
		buildCar();
	}

	private void buildCar(){
		// debug
		outline = new Rectangle(x, y, width, height);

		// build roof
		roof.addPoint(x + width / 3, y);
		roof.addPoint(x + 2 * width / 3, y);
		roof.addPoint(x + 3 * width / 4, y + height / 4);
		roof.addPoint(x + width / 4, y + height / 4);

		// build body
		body.setRect(x, y+height/4, width, height/3);
		
		// build front tire
		
		// build rear tire
	}

	public void draw(Graphics2D g2){
		// debug
		//g2.draw(outline);

		g2.draw(roof);
		g2.draw(body);
	}

}
