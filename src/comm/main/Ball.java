package comm.main;

import java.awt.Color;
import java.awt.Graphics;

/*******************************************************\
\	Original Code:Coding Heaven(youtube channel)		/
/														\
\	Editor: Ethan Watson								/
/	Date:8/31/2023										\
\														/
/														\
\*******************************************************/

public class Ball {

	public static final int SIZE = 16;// Ball size
	private int x, y; // Position of top left corner of square
	private int xVel,yVel; // Either 1 or -1 (left or right direction)
	private double speed = 2.0; // Speed of the ball
	
	public Ball() {// Constructor
		
		reset();// Reset the balls location
		
	}

	private void reset() {// Setup initial position and velocity
		// Initial speed
		speed = 2.0;
		// Initial position
		x = Game.WIDTH/2 - SIZE/2;
		y = Game.HEIGHT/2 - SIZE/2;
		// initial velocity(math.random() is used to make the starting direction random to an extent)
		xVel = Game.sign(Math.random()*2.0-1);
		yVel = Game.sign(Math.random()*2.0-1);
		
	}
	
	public void changeYDir() {// Changes the direction of the yVel to go in the opposite direction
		
		yVel *=-1;
		
	}
	
	public void changeXDir() {// Changes the direction of the xVel to go in the opposite direction and increase speed
		// Direction change
		xVel *=-1;
		// Speed increase
		speed += 0.5;
		
	}

	public void draw(Graphics g) {// Draw the ball
		g.setColor(Color.white);
		g.fillRect(x, y, SIZE, SIZE);
	}

	// Calls p1, and p2 for the scores
	public void update(Paddle p1, Paddle p2) {// Update position and collision tests
		// Update position
		x += xVel*speed;
		y += yVel*speed;
		// Collisions
		// Ceiling and  floor
		if (y + SIZE >= Game.HEIGHT || y <= 0)// Change direction if hits top or bottom wall
			changeYDir();
		
		// Walls
		if (x + SIZE >= Game.WIDTH) {// If the ball hits right wall, left player gets point
			p1.addPoint();
			reset();
		}
		
		if (x <= 0) {// If the ball hits left wall, right player gets point
			p2.addPoint();
			reset();
		}
		
	}

	public int getY() {// Returns y 
		return y;
	}

	public int getX() {// Returns x 
		return x;
	}

	public void upYDir() {// Used to make ball go in the up direction when hitting the paddle
		yVel = 1;
	}

	public void downYDir() {// Used to make ball go in the down direction when hitting the paddle
		yVel = -1;
		
	}

	public int getxVel() {// Returns xVel
		return xVel;
	}

	
	
}
