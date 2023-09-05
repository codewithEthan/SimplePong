# SimplePong
 
I gave myself a project to make a simple Pong game. This project was done in SpringToolSuit4.

I followed a YouTube tutorial from [CodingHeaven](https://www.youtube.com/playlist?list=PL7g8l3pNV7-aVAJHzHBj73L1lYbcgZTxD)\

Things I added/changed
* The paddles, ball, and score were hidden on the main menu.
* Made the game end when one of the players reaches 11 points(returns to main menu).
* Show who won the previous game in the main menu by displaying the word winner in the winner's color. 
* Reset the game so that you can play again with a new game without having to close and reopen the game
* Change the y direction of the ball based on where it hit the paddle. (If the ball hit the bottom half of the paddle, the ball would go down. If the ball hit the top half of the paddle, the ball would go up.)
* Fixed a bug where if the ball hits the top or bottom side of the paddle the ball can glitch into the paddle and constantly change its x direction until the ball is no longer in the paddle. Either by technically scoring a point on itself or sending the ball towards the opponent
* Changed the colors of the paddles
* Added when the ball hits a paddle the velocity of the ball increases

How to use:
* Copy all the .java files then run the game.java file.
* Once in game you can select play or quit with the mouse
* The control for the paddles are w and s keys for the left paddle. For the right paddle the keys are the up and down arrows.
