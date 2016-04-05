/********************CarDrawer**********************************
 * Car Drawer Applet for 422C
 * Creates an Applet for a car racing interface
 * 
 * Section : F 2:00 - 3:30pm
 * Author: Ronny Macmaster
 * Date: 3/27/16
 * EID: rpm953
 ***************************************************************/

package assignment5;

// GUI
import java.applet.Applet;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

// Data Structures
import java.util.ArrayList;

public class CarDrawer extends Applet{
	// Applet size
	private int width = 900;
	private int height = 300;

	// GUI buttons
	private Button startButton;
	private Button stopButton;
	private Button resetButton;

	// image buffering
	private Image image;
	private Graphics graphics;

	// race trackers
	private Thread race;
	private boolean raceFlag;
	private boolean winFlag;
	private StopWatch raceTimer;
	private ArrayList<Car2D> cars;
	private ArrayList<Car2D> winners;

	private static int random(int range){
		return (int)((Math.random() * range) + 0.5);
	}

	/************************init()**********************************
	 * Runs before the Applet is opened
	 * Initializes the GUI Applet
	 * Creates the list of cars
	 * Initializes Race Manager Variables
	 * 
	 * Precondition: none 
	 ***************************************************************/
	public void init(){
		// initialize GUI
		this.resize(width, height);
		this.setLayout(null); // abs position
		this.initializeButtons();
		this.setFont(new Font("Times New Roman", Font.BOLD, 14));

		// generate cars
		cars = new ArrayList<Car2D>();
		cars.add(new Car2D("1", 0, 0));
		cars.add(new Car2D("2", 0, height * 1 / 8));
		cars.add(new Car2D("3", 0, height * 2 / 8));
		cars.add(new Car2D("4", 0, height * 3 / 8));
		cars.add(new Car2D("5", 0, height * 4 / 8));

		// initialize race managers
		winFlag = false;
		raceFlag = false;
		raceTimer = new StopWatch();
		winners = new ArrayList<Car2D>();
	}

	/************************paint()**********************************
	 * Draws all the Car Race elements to the screen
	 * Cars, Start / Finish line, Controls and Timer
	 * Congratulation Banner
	 * 
	 * @param g: Graphics object
	 * Precondition: none 
	 *****************************************************************/
	public void paint(Graphics g){
		// start and finish line
		Graphics2D g2 = (Graphics2D)g;
		drawBanner("Start!", Car2D.getWidth(), height / 30, g2, Color.red);
		drawBanner("Finish!", width * 23/24, height / 30, g2, Color.green);

		// draw cars
		for(Car2D car : cars){
			car.draw(g2);
		}
		// controls and timer
		double raceTime = raceTimer.getElapsedTime() / 1000.0;
		g2.drawString("Controls", 0, height * 13 / 16);
		g2.drawString("Time: " + raceTime, width / 3, height * 13 / 16);

		// winner banner
		if(winFlag){
			if(winners.size() > 1){
				String winString = "Tie between cars: ";
				for(Car2D winner : winners){
					winString += winner.getNum() + ", ";
				}
				winners.clear();
				g2.drawString(winString, width / 2, height * 7 / 8);
			}
			else if(winners.size() == 1){
				Car2D winner = winners.remove(0);
				String winString = "Congratulations Car #" + winner.getNum();
				g2.drawString(winString, width / 3, height * 7 / 8);
			}
		}
	}

	/************************update()**********************************
	 * Called right before paint method
	 * Initializes the Double-Buffer Image
	 * Paints the image graphics to the Applet
	 * 
	 * @param g: Graphics object
	 * Precondition: none 
	 *****************************************************************/
	public void update(Graphics g){
		// use graphics buffering
		image = createImage(this.getWidth(), this.getHeight());
		graphics = image.getGraphics();
		paint(graphics);
		g.drawImage(image, 0, 0, this);
	}

	/************************drawBanner()******************************
	 * Draws a rotated Text Banner
	 * Useful for Rotated Start and Finish Line
	 * 
	 * @param g2: Graphics object
	 * @param text: string to print
	 * @param x,y: x and y coordinates to print at
	 * @param color: banner background color
	 * Precondition: none 
	 *****************************************************************/
	private void drawBanner(String text, int x, int y, Graphics2D g2, Color color){
		// set font
		Font originalFont = g2.getFont();
		Font newFont = new Font("Times New Roman", Font.BOLD, 24);
		g2.setFont(newFont);

		// draw outline
		g2.setColor(color);
		g2.fillRect(x - width / 48, y, width / 18, height * 3/ 5);
		g2.drawRect(x - width / 48, y, width / 18, height * 3/ 5);

		// transform text
		g2.setColor(Color.black);
		g2.translate((float)x, (float)y);
		g2.rotate(Math.toRadians(90));
		g2.drawString(text, height / 5, 0);
		g2.rotate(-Math.toRadians(90));
		g2.translate(-(float)x, -(float)y);

		// reset font
		g2.setFont(originalFont);
	}

	/*****************initializeButtons()******************************
	 * Build Start, Reset, and Stop buttons
	 * Initialize the event listeners
	 * 
	 * Inputs: none
	 * Precondition: none 
	 *****************************************************************/
	private void initializeButtons(){
		// start button
		startButton = new Button("Start");
		startButton.setBounds(0, height * 13 / 16, width / 6, height / 16);
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				startAction(event);
			}
		});
		this.add(startButton);

		// stop button
		stopButton = new Button("Stop");
		stopButton.setBounds(0, height * 14 / 16, width / 6, height / 16);
		stopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				stopAction(event);
			}
		});
		this.add(stopButton);

		// reset button
		resetButton = new Button("Reset");
		resetButton.setBounds(0, height * 15 / 16, width / 6, height / 16);
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				resetAction(event);
			}
		});
		this.add(resetButton);
	}

	/*****************startAction()***********************************
	 * Start race thread
	 * Start the race timer
	 * 
	 * @param event: Action Event trigger
	 * Precondition: none 
	 *****************************************************************/
	private void startAction(ActionEvent event){
		System.out.println("start button pressed!");
		if(raceFlag == false && winFlag == false){
			// start race thread
			raceTimer.start();
			race = new Thread(new Runnable(){
				public void run(){
					race();
				}
			});
			race.start();
		}
	}

	/*****************stopAction()***********************************
	 * Pause the race thread
	 * Pause the race timer
	 * 
	 * @param event: Action Event trigger
	 * Precondition: none 
	 *****************************************************************/
	private void stopAction(ActionEvent event){
		raceTimer.stop();
		System.out.println("stop button pressed!");
		raceFlag = false;
	}

	/******************resetAction()**********************************
	 * Reset the Race variables
	 * Generate a new list of cars
	 * Reset the Race Timer
	 * 
	 * @param event: Action Event trigger
	 * Precondition: none 
	 *****************************************************************/
	private void resetAction(ActionEvent event){
		System.out.println("reset button pressed!");
		// reinitialize car list
		if(!raceFlag){
			cars.clear();
			raceTimer.reset();
			winFlag = false; // clear winner
			cars.add(new Car2D("1", 0, 0));
			cars.add(new Car2D("2", 0, height * 1 / 8));
			cars.add(new Car2D("3", 0, height * 2 / 8));
			cars.add(new Car2D("4", 0, height * 3 / 8));
			cars.add(new Car2D("5", 0, height * 4 / 8));
			repaint();
		}
	}

	/******************race()**********************************
	 * Set the Race and Win Flags
	 * Translate Cars and Repaint Applet
	 * Declare a winner or a tie
	 * 
	 * Input: none
	 * Precondition: none 
	 *****************************************************************/
	private void race(){
		raceFlag = true;
		winFlag = false;
		while(!winFlag && raceFlag){
			// translate cars
			for(Car2D car : cars){
				int dx = random(3);
				int carFront = car.getX() + Car2D.getWidth() + dx;
				car.translate(dx, 0);
				if(carFront > width){
					winFlag = true;
					winners.add(car);
				}
			}
			// paint and delay
			repaint();
			try{
				Thread.sleep(8);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		raceFlag = false;
	}

}