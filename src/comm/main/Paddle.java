package comm.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/*******************************************************\
\	Original Code:Coding Heaven(youtube channel)		/
/														\
\	Editor: Ethan Watson								/
/	Date:8/31/2023										\
\														/
/														\
\*******************************************************/

public class Paddle {
	
	private int x,y;// Position
	private int vel = 0;// Paddle speed/direction
	private int speed = 10;// Speed of the paddle movement 
	private int width = 22, height = 85;// Dimensions
	private int score = 0;// Player score 
	private Color color;// Paddle color
	private boolean left;// True if it is the left paddle

	
	public Paddle(Color c, boolean left) {// What is the paddle color and if it is left or right paddle
		// Initialize properties of the paddles
		color = c;
		
		this.left = left;
				
		if (left)// True if left false if right
			x=0;
		else
			x = Game.WIDTH - width;
		
		y=Game.HEIGHT/2 - height/2;
		
	}
	
	public void addPoint() {// Adds a point to the player
		score++;
	}

	public void draw(Graphics g) {// Draws rectangle paddles and scores
		// Draws paddle
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		// Draws score
		int sx;
		String scoreText = Integer.toString(score);
		Font font = new Font("Roboto", Font.PLAIN, 50);
		
		int strWidth = g.getFontMetrics(font).stringWidth(scoreText) +1;
		int padding = 25;
		
		if (left)
			sx = Game.WIDTH/2 - padding - strWidth;
		else 
			sx = Game.WIDTH/2 + padding;
		
		g.setFont(font);
		g.drawString(scoreText, sx, 50);
		
			
	}

	public void update(Ball b) {// Updates position/collision
		// Update position
		y = Game.ensureRange(y += vel, 0, Game.HEIGHT - height);
		
		// Collision
		int ballX = b.getX();
		int ballY = b.getY();
		
		if (left) {// Is paddle left or right
			if (ballX <= width && ballY + Ball.SIZE >= y && ballY <= y + height && b.getxVel() == -1) {
				// Is the ball touching the paddle/going towards the paddle
				if (ballX <= width && ballY + Ball.SIZE >= y && ballY <= y + height/2) {
					// If the ball hits the bottom of the paddle, sends ball down 
					b.changeXDir();
					b.downYDir();
				}
				else {
					// If the ball hits the top of the paddle, send ball up
					b.changeXDir();
					b.upYDir();
				}
				
			}
		}
		else {
			if(ballX +Ball.SIZE >= Game.WIDTH - width && ballY + Ball.SIZE >= y && ballY <= y+height && b.getxVel() == 1) {
				// Is the ball touching the paddle/going towards the paddle
				if(ballX +Ball.SIZE >= Game.WIDTH - width && ballY + Ball.SIZE >= y && ballY <= y+height/2) {
					// If the ball hits the bottom of the paddle, sends ball down
					b.changeXDir();
					b.downYDir();
				}
				else {
					// If the ball hits the top of the paddle, send ball up
					b.changeXDir();
					b.upYDir();
				}
				
			}
		}
	}

	public void switchDirection(int direction) {// Switches the direction of the paddles
		vel = speed * direction;
	}
	public void stop() {// Stop moving the paddles
		vel = 0;
	}

	public int getScore() {// Used to know in game when score reaches 11
		return score;
	}

	public void setScore(int s) {// Used to reset the score in game when someone wins
		score=s;
		
	}

	public void resetPos() {// Used to reset the paddles position in game when someone wins
		
		if (left)
			x=0;
		else
			x = Game.WIDTH - width;
		
		y=Game.HEIGHT/2 - height/2;
		
	}
	
	
}
