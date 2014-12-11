package snake;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;


// this class creates and paints the display black background
@SuppressWarnings("serial")
public class Box extends JPanel {
	//paints the gui black, snake red
	
	public Box() {
		this.setBounds(0,0,800,800);
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		// color of background
		g.setColor(Color.black);
		g.fillRect(0,0, 720, 600);
		Snake figure = Snake.getInstance();
		// color of snake
		g.setColor(Color.yellow);
		//paints the color in certain locations for the snake 
		for (Point location : figure.getSnakeParts()){
			g.fillRect(location.x * Snake.getScale(), location.y*Snake.getScale(), figure.getScale(), figure.getScale());
		}
		g.fillRect(figure.getHead().x * Snake.getScale(), figure.getHead().y*Snake.getScale(), figure.getScale(), figure.getScale());
		g.setColor(Color.pink);
		g.fillRect(figure.getCherry().x * Snake.getScale(), figure.getCherry().y*Snake.getScale(), figure.getScale(), figure.getScale());
		//Printed stuff on the top
		String string = "Score: " + figure.getScore() + ", Length: "
                + figure.getTailLength() + ", time: " +figure.getTime() / 20 ;
		
		 g.setColor(Color.white);
         g.drawString(string, (int) (getWidth() / 2.3 - string.length() * 2.5f),
                         10);
         
         
//         Prints x and y position of our snake and its parts for testing purposes
//         
//         String test = "x: " + figure.getHead().x + ", Y: "
//                 + figure.getHead().y + ", gameover: " +figure.gameOver  + figure.snakeParts;
// 		
// 		
// 		 g.setColor(Color.white);
//         g.drawString(test, (int) (getWidth() / 4.6 - string.length() * 5f),
//                          20);
//         
         string = "Game Over!";
         if (figure.getGameOver())
             g.drawString(string, (int) (getWidth() / 2.3 - string.length() * 2.5f),
            		 (int) getHeight() / 3);
         
		}
	
		
}

