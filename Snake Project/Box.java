package snake;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;


// this class creates and paints the display black background
@SuppressWarnings("serial")
public class Box extends JPanel {
	//paints the gui black, snake red
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		// color of background
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
		Snake figure = Snake.snake;
		// color of snake
		g.setColor(Color.yellow);
		//paints the color in certain locations for the snake 
		for (Point location : figure.snakeParts){
			g.fillRect(location.x * Snake.SCALE, location.y*Snake.SCALE, figure.SCALE, figure.SCALE);
		}
		g.fillRect(figure.head.x * Snake.SCALE, figure.head.y*Snake.SCALE, figure.SCALE, figure.SCALE);
		g.setColor(Color.pink);
		g.fillRect(figure.cherry.x * Snake.SCALE, figure.cherry.y*Snake.SCALE, figure.SCALE, figure.SCALE);
				
		}
	
		
}

