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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Car2D{
	// top-left corner coordinates of car's square space
	int x, y; // x-coordinate, y-coordinate
	int height, width; // car height, width
	String num; // racecar number
	Polygon roof; // burnt orange car roof
	Rectangle body; // burnt orange car body
	Ellipse2D.Double frontTire; // black front wheel
	Ellipse2D.Double rearTire; // black front wheel

	// debug
	//Rectangle outline;

	public Car2D(String num, int x, int y){
		// initialize car data
		this.x = x;
		this.y = y;
		height = 35;
		width = 70;
		this.num = num;
		roof = new Polygon();
		body = new Rectangle();
		frontTire = new Ellipse2D.Double();
		rearTire = new Ellipse2D.Double();

		// build car parts
		buildCar();
	}

	private void buildCar(){
		// debug
		//outline = new Rectangle(x, y, width, height);

		// build roof
		roof.addPoint(x + 1 * width / 4, y);
		roof.addPoint(x + 3 * width / 4, y);
		roof.addPoint(x + 7 * width / 8, y + height / 4);
		roof.addPoint(x + 1 * width / 8, y + height / 4);

		// build body
		body.setRect(x, y + height / 4, width, height * 5 / 12);

		// build front tire
		frontTire.setFrame(x + width * 1 / 8, y + height * 2 / 3, width / 6, height / 3);

		// build rear tire
		rearTire.setFrame(x + width * 11 / 16, y + height * 2 / 3, width / 6, height / 3);
	}

	public void draw(Graphics2D g2){
		// debug
		// g2.draw(outline);

		// colors
		Color burntOrange = new Color(191, 87, 0);
		Color black = Color.black;
		g2.setColor(burntOrange);
		g2.fill(body);
		g2.fill(roof);

		g2.setColor(black);
		g2.fill(rearTire);
		g2.fill(frontTire);

		// draw images
		g2.draw(roof);
		g2.draw(body);
		g2.draw(frontTire);
		g2.draw(rearTire);
		g2.drawString(num, x + width * 5 / 12, y + height * 7 / 12);

	}

	public void translate(int dx, int dy){
		// translate wheel frames
		Rectangle rearFrame = rearTire.getBounds();
		Rectangle frontFrame = frontTire.getBounds();
		rearFrame.translate(dx, dy);
		frontFrame.translate(dx, dy);

		// translate car parts
		x += dx;
		y += dy;
		body.translate(dx, dy);
		roof.translate(dx, dy);
		rearTire.setFrame(rearFrame);
		frontTire.setFrame(frontFrame);
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public String getNum(){
		return num;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

}
