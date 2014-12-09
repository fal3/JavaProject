package snake; 

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Snake implements KeyListener,ActionListener{
	public JFrame jframe;
	
	public static Snake snake;
	// calls java file box
	public Box renderPanel;
	
	public Toolkit toolkit;
	
	
	//array for the actual snake
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	//speed = speed of snake
	public int speed = 0;
	
	// display of the current speed setting
	public int howFast;
	
	//direction snake is traveling at start
	public int direction = DOWN;
	
	// value for the direction the snake is moving
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	
	//size of each snake pieces
	public static final int SCALE = 6;
	
	//head of the snake
	public Point head;
	
	public int x;
	
	// used to refresh the box
	public Timer timer = new Timer(x,this);
		
	
	//the objective to run into called the "cherry"
	public Point cherry;
	
	// ends game if true
	public boolean gameOver;
	
	//length of snake 
	public int tailLength = 10;
	
	//default constructor
	public Snake() {
		//gets dimensions of monitor
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//title 
		jframe = new JFrame("Snake");
		//makes jframe appear
		jframe.setVisible(true);
		
		//size of interface box
		jframe.setSize(800, 800);
		
		// centers interface on screen
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		
		// creates gui interface for snake
		jframe.add(renderPanel = new Box());
		
		//to enable key recognition
		jframe.addKeyListener(this);
		
		startGame();
	
	}
	
	public void startGame(){
		
		
		// starting location for snake
		head = new Point(0, 0);
		
		tailLength = 10;
		
		speed = 0;
		
		howFast = 0;
		// initial speed
		x=20;
		
		gameOver = false;
		
		Random rand = new Random();
		
		snakeParts.clear();
		
		cherry = new Point(rand.nextInt(69),rand.nextInt(69));
		
		// starts the snake
		timer.start();
	}
	
	public static void main(String [] args){
		snake = new Snake();	
	}	
	
	// when arrow is pushed, snake moves in that direction
	@Override
	public void keyPressed(KeyEvent arrow) {
		int i = arrow.getKeyCode();
        if ((i == KeyEvent.VK_LEFT)  && direction != RIGHT)
                direction = LEFT;
        if ((i == KeyEvent.VK_RIGHT) && direction != LEFT)
                direction = RIGHT;
        if ((i == KeyEvent.VK_UP)  && direction != DOWN)
                direction = UP;
        if ((i == KeyEvent.VK_DOWN) && direction != UP)
                direction = DOWN;
		
	}

	


	@Override
	public void actionPerformed(ActionEvent arg0){
		//refreshes box
		renderPanel.repaint();
		
		speed++;
		//Algorithm for the snake moving on grid
		// speed % (x) sets speed of snake
		if(speed % x ==0 && gameOver == false){
			snakeParts.add(new Point(head.x, head.y));
			if(direction == UP  && noTailAt(head.x, head.y - 1)) 
				if(head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
					head = new Point(head.x, head.y - 1);
			else
				gameOver = true;
			if(direction == DOWN && noTailAt(head.x, head.y + 1))
				 if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1))
					 head = new Point(head.x, head.y + 1);
			else
				gameOver = true;
			if(direction == LEFT && noTailAt(head.x - 1, head.y))
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
					head = new Point(head.x - 1, head.y);
			else
				gameOver= true;
			if(direction == RIGHT && noTailAt(head.x + 1, head.y))
				if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y))
					head = new Point(head.x + 1, head.y);
			else
				gameOver = true;
			
			if (snakeParts.size() > tailLength)
                snakeParts.remove(0);
			
			if (cherry != null) {
				
                if (head.equals(cherry)) {
                	
                	Random rand = new Random();
                	
                    cherry.setLocation(rand.nextInt(79), rand.nextInt(66));
                    
                    tailLength += 2;
                    
                    if(x-1 > 0){
                    	
                    	howFast++;
                    	
                    	x -=1;
                    }
                }
			}
			
		}
	}
	// returns false if tail is at point you try to turn to
	// returns true if it is safe
	public boolean noTailAt(int x, int y) {
        for (Point point : snakeParts) {
                if (point.equals(new Point(x, y))) {
                        return false;
                }
        }
        return true;
}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	
	


