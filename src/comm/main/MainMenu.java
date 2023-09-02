package comm.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*******************************************************\
\	Original Code:Coding Heaven(youtube channel)		/
/														\
\	Editor: Ethan Watson								/
/	Date:8/31/2023										\
\														/
/														\
\*******************************************************/

public class MainMenu extends MouseAdapter{// Menu for the game, MouseAdapter is used to make the mouse work for the menu

	public boolean active; // True if main menu is displaying
	
	// Play button
	private Rectangle playBtn;
	private String playTxt = "Play";
	private boolean pHighlight = false;
	
	// Quit button
	private Rectangle quitBtn;
	private String quitTxt = "Quit";
	private boolean qHighlight = false;
	
	// Winner display
	private int winner = 0;
	private Rectangle winBtn;
	private String winTxt = "Winner";
	
	private Font font;// Font
	
	public MainMenu(Game game) {// Constructor
		
		active = true;// So we start with the menu
		game.start();
		
		// Position and dimensions of each button/display
		int w,WINw,h,x,y;
		w=300;
		WINw=400;
		h=150;
		// Play
		y=Game.HEIGHT/2 - h/2;
		x=Game.WIDTH/4 - w/2;
		playBtn = new Rectangle(x,y,w,h);
		// Quit
		x=Game.WIDTH*3/4 - w/2;
		quitBtn = new Rectangle(x,y,w,h);
		// Winner
		x=Game.WIDTH*4/8 - WINw/2;
		winBtn = new Rectangle(x,y,WINw,h);
		
		font = new Font("Roboto",Font.PLAIN,100);
				
	}
	
	public void draw(Graphics g) {// Draw buttons/display (rectangles) and text in the Main Menu
		
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(font);
		// Draw buttons
		g.setColor(Color.black);// Play
		if (pHighlight)
			g.setColor(Color.white);
		g2d.fill(playBtn);

		g.setColor(Color.black);// Quit
		if (qHighlight)
			g.setColor(Color.white);
		g2d.fill(quitBtn);
		
		if (winner!=0) {// Winner
		g.setColor(Color.black);
		g2d.fill(winBtn);
		g.setColor(Color.white);// Draws winner boarders
		g2d.draw(winBtn);
		}
		
		// Draw button boarders
		g.setColor(Color.white);
		g2d.draw(playBtn);
		g2d.draw(quitBtn);
		
		// Draw text in buttons
		
		int strWidth;
		int strHeight;
		
		
		// Play button Text layout help(Forgot that this was here because this worked for quit too. I could have done this for winner)
		strWidth = g.getFontMetrics(font).stringWidth(playTxt);
		strHeight = g.getFontMetrics(font).getHeight();
		// Draw the text for Play
		g.setColor(Color.green);
		g.drawString(playTxt, (int) (playBtn.getX()+playBtn.getWidth()/2 - strWidth/2),
				(int) (playBtn.getY()+playBtn.getHeight()/2 + strHeight/4));
		// Draw the text for quit
		g.setColor(Color.red);
		g.drawString(quitTxt, (int) (quitBtn.getX()+quitBtn.getWidth()/2 - strWidth/2),
				(int) (quitBtn.getY()+quitBtn.getHeight()/2 + strHeight/4));
		// This pair of if statements is dependent on who won(the draw is done slightly different) 
		if (winner==1) {
			// Draw the text for winner
			g.setColor(Color.cyan);
			g.drawString(winTxt, (int) (winBtn.getX()+winBtn.getWidth()/3 - strWidth/2),
				(int) (winBtn.getY()+winBtn.getHeight()/2 + strHeight/4));
		}
		if (winner == -1) {
			// Draw the text for winner
			g.setColor(Color.orange);
			g.drawString(winTxt, (int) (winBtn.getX()+winBtn.getWidth()/3 - strWidth/2),
				(int) (winBtn.getY()+winBtn.getHeight()/2 + strHeight/4));
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		
		// Determine if mouse is clicked inside one of the buttons
		if (playBtn.contains(p))
			active = false;// Turns off the menu and starts game
		else if (quitBtn.contains(p))
			System.exit(0);// Closes window
	}
	
	@Override	
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		
		// Determine if mouse is hovering inside one of the buttons
		pHighlight = playBtn.contains(p);
		qHighlight = quitBtn.contains(p);
		
	}

	public void setActive() {// When called turns back  on the menu
		active = true;
		
	}

	public void setWinner(Paddle p1, Paddle p2) {
		if (p1.getScore() == 11)
			winner=1;
		else
			winner=-1;
	}


	
}
