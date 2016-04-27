/** GamePanel ************************************************
 * Top level Game Panel
 * Descendant of JPanel class
 * Runnable GUI manager
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/25/2016
 ************************************************************/
package main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Panel on which the game is drawn.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
		/** width dimension */
		public static int WIDTH; // original 1280
		
		/** height dimension */
		public static int HEIGHT; // original 960
		
		/** game thread */
		private Thread thread;
		
		/** run flag */
		private boolean running;
		
		/** frames per second */
		private int FPS = 60;
		
		/** frame time */
		private long targetTime = 1000 / FPS;
		
		/** game canvas image */
		private BufferedImage image;
		
		/** game graphics manager*/
		private Graphics2D g;
		
		/** game state manager */
		private GameStateManager gsm;
		
		/**
		 * Constructs the panel on which the game is drawn
		 */
		public GamePanel(){
			super();
			
			// set the height and width due to computer screen
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			WIDTH = (int)screenSize.getWidth() * 23 / 32;
			HEIGHT = (int)screenSize.getHeight() * 29 / 32;
			
			setPreferredSize(
				new Dimension(WIDTH, HEIGHT));
			setFocusable(true);
			requestFocus();
		}
		
		/**
		 * Called when this panel gets put on the window
		 */
		public void addNotify(){
			super.addNotify();
			if(thread == null) {
				thread = new Thread(this);
				addKeyListener(this);
				thread.start();
			}
		}
		
		private void init(){
			// initialize game graphics
			image = new BufferedImage(
						WIDTH, HEIGHT,
						BufferedImage.TYPE_INT_RGB
					);
			g = (Graphics2D) image.getGraphics();
			
			// initialize game manager
			running = true;
			gsm = new GameStateManager();
		}
		
		/**
		 * Handles game operations on the highest level
		 */
		public void run(){
			// game loop
			init();			
			long start, elapsed, wait;
			while(running){
				
				start = System.nanoTime();
				
				// update game graphics
				update();
				draw();
				drawBufferToScreen();
				
				elapsed = System.nanoTime() - start;
				
				//account for desired fps
				wait = targetTime - elapsed / 1000000;
				if(wait < 0){wait = 5;}
				
				// delay
				try {Thread.sleep(wait);}
				catch(Exception e){e.printStackTrace();}
			}		
		}
		
		/**
		 * Updates game entities/objects
		 */
		private void update(){
			gsm.update();
		}
		/**
		 * Draws the game image to the buffer
		 */
		private void draw(){
			gsm.draw(g);
		}
		/**
		 * Draws the buffer onto the panel/screen
		 */
		private void drawBufferToScreen(){
			Graphics g2 = getGraphics();
			g2.drawImage(image, 0, 0,
					WIDTH, HEIGHT,
					null);
			g2.dispose();
		}
		
		/**
		 * Key typed - do nothing
		 */
		public void keyTyped(KeyEvent key){}
		
		/**
		 * Key pressed - send code to gsm
		 */
		public void keyPressed(KeyEvent key){
			gsm.keyPressed(key.getKeyCode());
		}
		
		/**
		 * Key released - send code to gsm
		 */
		public void keyReleased(KeyEvent key){
			gsm.keyReleased(key.getKeyCode());
		}
		
		public void addMouseListener(Component c) {
			this.addMouseListener(c);
		}

}
