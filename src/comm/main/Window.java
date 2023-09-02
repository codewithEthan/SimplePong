package comm.main;

import javax.swing.JFrame;//used to make a frame

/*******************************************************\
\	Original Code:Coding Heaven(youtube channel)		/
/														\
\	Editor: Ethan Watson								/
/	Date:8/31/2023										\
\														/
/														\
\*******************************************************/

public class Window {
	// this class is to create the window for the game
	public Window(String title, Game game){
		
		JFrame frame = new JFrame(title);// Create the window and names it
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// What we do when the window is closed
		frame.setResizable(false);// Game is not able to be resize able at this time
		frame.add(game);// Game inherits from Canvas, a Component object, so it can be put in a JFrame
		frame.pack();
		frame.setLocationRelativeTo(null);// A way to center the window
		frame.setVisible(true);// Is used to let the frame be viable or not
//		game.start(); // Was used before main menu class was created
		
	}
	
}
