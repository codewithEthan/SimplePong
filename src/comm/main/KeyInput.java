package comm.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*******************************************************\
\	Original Code:Coding Heaven(youtube channel)		/
/														\
\	Editor: Ethan Watson								/
/	Date:8/31/2023										\
\														/
/														\
\*******************************************************/

public class KeyInput extends KeyAdapter{// KeyAdapter is used to process keys from keyboard

	// Paddle up and down movement variables
	private Paddle p1; // Left paddle
	private boolean up1 = false;
	private boolean down1 = false;
	
	private Paddle p2; // Right paddle
	private boolean up2 = false;
	private boolean down2 = false;
	
	public KeyInput(Paddle pd1, Paddle pd2) {// Constructor
		
		p1=pd1;
		p2=pd2;
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
			p2.switchDirection(-1);
			up2=true;
		}
		if (key == KeyEvent.VK_DOWN) {
			p2.switchDirection(1);
			down2=true;
		}
		if (key == KeyEvent.VK_W) {
			p1.switchDirection(-1);
			up1=true;
		}
		if (key == KeyEvent.VK_S) {
			p1.switchDirection(1);
			down1=true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
			up2=false;
		}
		if (key == KeyEvent.VK_DOWN) {
			down2=false;
		}
		if (key == KeyEvent.VK_W) {
			up1=false;
		}
		if (key == KeyEvent.VK_S) {
			down1=false;
		}
		
		// This will make the paddles stop correctly
		if (!up1 && !down1)
			p1.stop();
		if (!up2 && !down2)
			p2.stop();
	}
	
	
}
