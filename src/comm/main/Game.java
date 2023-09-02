package comm.main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

/*******************************************************\
\	Original Code:Coding Heaven(youtube channel)		/
/														\
\	Editor: Ethan Watson								/
/	Date:8/31/2023										\
\														/
/														\
\*******************************************************/

public class Game extends Canvas implements Runnable {
	// Game inherits from Canvas, a Component object, so it can be put in a JFrame
	// Runnable overrides the run method and is used with threads
	private static final long serialVersionUID = -930619019861896736L;//used to make sure the same class is used in serialization and deserialization 
	
	public static final int WIDTH = 1500;
	public static final int HEIGHT = WIDTH * 9/16; // 16:9 aspect ratio
	
	public boolean running = false; // True if the game is running
	private Thread gameThread;// Thread where the game is updated AND drawn (single thread game)
	
	private Ball ball;// Ball object
	private Paddle paddle1;// Left paddle object
	private Paddle paddle2;// Right paddle object
	
	public MainMenu menu;//Main menu  object
	
	public Game(){// Constructor
	
		canvasSetup();
		
		new Window("SimplePong",this);
		
		initialize();
		
		this.addKeyListener(new KeyInput(paddle1,paddle2));// Makes keyboard work for the paddles in the game
		this.addMouseListener(menu);// Makes mouse work for menu
		this.addMouseMotionListener(menu);// Makes mouse work for menu
		this.setFocusable(true);
		
	}

	private void initialize() {// Initialize all the games objects
		ball = new Ball();
		
		paddle1 = new Paddle(Color.cyan, true);
		paddle2 = new Paddle(Color.orange, false);
		
		menu = new MainMenu(this);
	}

	private void canvasSetup() {// Sets up the canvas to desired size
		
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));		
		this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		
	}

	@Override
	public void run() {// Game loop
		
		this.requestFocus();

		// Game timer
		long lastTime = System.nanoTime();
		double amountOfTicks = 120.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				update();
				delta--;
				draw();
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}

		stop();
		
	}

	private void draw() {// Draws the back and objects
		// Initialize drawing tools first before drawing
		BufferStrategy buffer = this.getBufferStrategy();// extract buffer so we can use them
		// a buffer is basically like a blank canvas we can draw on
		
		if (buffer == null) {// If it does not exist, we can't draw
			this.createBufferStrategy(3);// Creating a Triple Buffer
			/*
			 * triple buffering basically means we have 3 different canvases this is used to
			 * improve performance but the drawbacks are the more buffers, the more memory
			 * needed so if you get like a memory error or something, put 2 instead of 3.
			 * 
			 * BufferStrategy:
			 * https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferStrategy.html
			 */
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();// Extract drawing tool from the buffers
		/*
		 * Graphics is class used to draw rectangles, ovals and all sorts of shapes and
		 * pictures so it's a tool used to draw on a buffer
		 * 
		 * Graphics: https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
		 */

		drawBackround(g);// Draws background
		
		if(menu.active)// Draw main menu contents
			menu.draw(g);
		
		if(!menu.active) {// If menu is false will draw the other objects
		// This make the main menu screen look cleaner in my own opinion
		ball.draw(g);// draw ball
		
		// Draw paddles (score will be drawn with them)
		paddle1.draw(g);
		paddle2.draw(g);
		}
		
		// Actually draw
		g.dispose();// Disposes of this graphics context/releases any system resources that it is using
		buffer.show();// Actually shows us what we drew
	}

	private void drawBackround(Graphics g) {// Draws the Background
		// Set the background to black
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Dotted line in the middle
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D) g;// a more complex Graphics class used to draw Objects 
										//(as in give in an Object in parameter and not dimensions or coordinates)
		
		// How to make a dotted line:
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0);
		g2d.setStroke(dashed);
		g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
	}

	private void update() {// Update settings and move all objects
		if (!menu.active) {// Game starts when menu is false
			// Updates all the objects movements
			ball.update(paddle1,paddle2);
			paddle1.update(ball);
			paddle2.update(ball);
			if (paddle1.getScore()==11||paddle2.getScore()==11) {// End of the game
				menu.setWinner(paddle1,paddle2);// Shows who the winner is on the main menu
				menu.setActive();// Makes menu true so that we can go back to the menu
				// Resets the position of the game(paddles and he score)
				paddle1.setScore(0);
				paddle2.setScore(0);
				paddle1.resetPos();
				paddle2.resetPos();
			}
		}	
	}

	public void start() {// Starts the thread/game
		
		gameThread = new Thread(this);
		gameThread.start();
		running = true;
		
	}
	
	public void stop() {// Stops the thread/game
		
		try {
			gameThread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int sign(double d) {// Returns if input is positive or negative
									  // Also makes it constant
		if (d<=0)
			return -1;
		return 1;
		
	}
	
	public static void main(String[] args) {
	
		new Game();// Starts the game
		
	}
	
	public static int ensureRange(int val, int min, int max) {
		/**
		 * used to keep the value between the min and max
		 * 
		 * @param value - integer of the value we have
		 * @param min   - minimum integer
		 * @param max   - maximum integer
		 * @return: the value if value is between minimum and max, minimum is returned
		 *          if value is smaller than minimum, maximum is returned if value is
		 *          bigger than maximum
		 */
		return Math.min(Math.max(val, min), max);
	
	}
	
}
