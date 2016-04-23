package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
		// dimensions
		public static final int WIDTH = 1280;
		public static final int HEIGHT = 960;
		
		// game thread
		private Thread thread;
		private boolean running;
		private int FPS = 60;
		private long targetTime = 1000 / FPS;
		
		// game image
		private BufferedImage image;
		private Graphics2D g;
		
		// game state manager
		private GameStateManager gsm;
		
		/**
		 * Constructs the panel on which the game is drawn
		 */
		public GamePanel() {
			super();
			setPreferredSize(
				new Dimension(WIDTH, HEIGHT));
			setFocusable(true);
			requestFocus();
		}
		
		/**
		 * Called when this panel gets put on the window
		 */
		public void addNotify() {
			super.addNotify();
			if(thread == null) {
				thread = new Thread(this);
				addKeyListener(this);
				thread.start();
			}
		}
		
		private void init() {
			
			image = new BufferedImage(
						WIDTH, HEIGHT,
						BufferedImage.TYPE_INT_RGB
					);
			g = (Graphics2D) image.getGraphics();
			
			running = true;
			
			gsm = new GameStateManager();
			
		}
		
		/**
		 * Handles game operations on the highest level
		 */
		public void run() {
			
			init();
			
			long start;
			long elapsed;
			long wait;
			
			// game loop
			while(running) {
				
				start = System.nanoTime();
				
				update();
				draw();
				drawBufferToScreen();
				
				elapsed = System.nanoTime() - start;
				
				//account for desired fps
				wait = targetTime - elapsed / 1000000;
				if(wait < 0) wait = 5;
				
				try {
					Thread.sleep(wait);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		/**
		 * Updates game entities/objects
		 */
		private void update() {
			gsm.update();
		}
		/**
		 * Draws the game image to the buffer
		 */
		private void draw() {
			gsm.draw(g);
		}
		/**
		 * Draws the buffer onto the panel/screen
		 */
		private void drawBufferToScreen() {
			Graphics g2 = getGraphics();
			g2.drawImage(image, 0, 0,
					WIDTH, HEIGHT,
					null);
			g2.dispose();
		}
		
		/**
		 * Key typed - do nothing
		 */
		public void keyTyped(KeyEvent key) {}
		
		/**
		 * Key pressed - send code to gsm
		 */
		public void keyPressed(KeyEvent key) {
			gsm.keyPressed(key.getKeyCode());
		}
		
		/**
		 * Key released - send code to gsm
		 */
		public void keyReleased(KeyEvent key) {
			gsm.keyReleased(key.getKeyCode());
		}

}
