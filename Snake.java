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


public class Snake implements ActionListener,KeyListener{
	public JFrame jframe;
	
	public static Snake snake;
	// calls java file box
	public Box renderPanel;
	
	public Toolkit toolkit;
	
	// used to refresh the box
	public Timer timer = new Timer(20,this);
	
	//array for the actual snake
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	//speed = speed of snake
	public int speed = 0;
	
	//direction snake is traveling at start
	public int direction = DOWN;
	
	// value for the direction the snake is moving
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	
	//size of each snake pieces
	public static final int SCALE = 5;
	
	//head of the snake
	public Point head;
	
	
	//the objective to run into called the "cherry"
	public Point cherry;
	
	
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
		
		// starting location for snake
		head = new Point(0, 0);
		
		//to enable key recognition
		jframe.addKeyListener(this);
		
		// starts the snake
		timer.start();
	}
	
	public static void main(String [] args){
		snake = new Snake();	
	}	
	
	//unimplemented method from interface ActionListener
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//refreshes box
		renderPanel.repaint();
		
		speed++;
		//Algorithm for the snake moving on grid
		// speed % (x) sets speed of snake
		if(speed % 1 ==0){
			snakeParts.add(new Point(head.x, head.y));
			if(direction == UP)
				head = new Point(head.x, head.y - 1);
			if(direction == DOWN)
				head = new Point(head.x, head.y + 1);
			if(direction == LEFT)
				head = new Point(head.x - 1, head.y);
			if(direction == RIGHT)
			head = new Point(head.x + 1, head.y);
			// deletes parts of snake behind head
			snakeParts.remove(0);
		
			}
			
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
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	}
	
	


