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
	private Helper h = new Helper();
	private static Snake snake;
	// calls java file box
	public Box renderPanel;
	public Toolkit toolkit;
	
	
	//array for the actual snake
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	
	//speed = speed of snake
	public int speed = 0;

	public int time;
	
	//direction snake is traveling at start
	public int direction = DOWN;
	
	// value for the direction the snake is moving
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	
	//size of each snake pieces
	public static final int SCALE = 6;
	
	//head of the snake
	public Point head;
	
	// # of cherrys colided with
	public int score;
	
	// used to refresh the box
	public Timer timer = new Timer(20,this);
		
	
	//the objective to run into called the "cherry"
	public Point cherry;
	
	private boolean paused;
	
	// ends game if true
	public boolean gameOver;
	
	//length of snake 
	public int tailLength;
	
	public static Snake getInstance(){
		if (snake == null) {
			snake = new Snake();
		}
		return snake;
	}
	
	//default constructor
	private Snake() {
		//gets dimensions of monitor
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//title 
		jframe = new JFrame("Snake");
		
		//size of interface box
		jframe.setSize(720, 600);
		jframe.setResizable(false);
		
		// centers interface on screen
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		
		// creates gui interface for snake
		jframe.setLayout(null);
		jframe.add(renderPanel = new Box());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//to enable key recognition
		jframe.addKeyListener(this);
		
		//makes jframe appear
		jframe.setVisible(true);
				
		
		startGame();
	
	}
	
	public void startGame(){
		
		
		// starting location for snake
		head = new Point(0, 0);
		
		tailLength = 1;
		
		direction = DOWN;
		
		speed = 0;
		
		score = 0;
		
		time = 0;
		
		paused = false;
		
		gameOver = false;
		
		Random rand = new Random();
		
		snakeParts.clear();
		
		cherry = new Point(rand.nextInt(69),rand.nextInt(69));
		
		// starts the snake
		timer.start();
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
        if (i == KeyEvent.VK_SPACE)
            if (gameOver)
                    startGame();
            else
                paused = !paused;
		
	}

	


	@Override
	public void actionPerformed(ActionEvent arg0){
		//refreshes box
		renderPanel.repaint();
		speed++;
		//Algorithm for the snake moving on grid
		// speed % (x) sets speed of snake
		if(speed % 2 ==0 && gameOver == false && paused ==false){
			time ++;
			snakeParts.add(new Point(head.x, head.y));
			if(direction == UP  && h.noTailAt(head.x, head.y - 1, snakeParts)) 
				if(head.y - 1 >= 0 && h.noTailAt(head.x, head.y - 1, snakeParts))
					head = new Point(head.x, head.y - 1);
			else
				gameOver = true;
			if(direction == DOWN && h.noTailAt(head.x, head.y + 1, snakeParts))
				 if (head.y + 1 < 95 && h.noTailAt(head.x, head.y + 1, snakeParts))
					 head = new Point(head.x, head.y + 1);
			else
				gameOver = true;
			if(direction == LEFT && h.noTailAt(head.x - 1, head.y, snakeParts))
				if (head.x - 1 >= 0 && h.noTailAt(head.x - 1, head.y, snakeParts))
					head = new Point(head.x - 1, head.y);
			else
				gameOver= true;
			if(direction == RIGHT && h.noTailAt(head.x + 1, head.y, snakeParts))
				if (head.x + 1 < 119 && h.noTailAt(head.x + 1, head.y, snakeParts))
					head = new Point(head.x + 1, head.y);
			else
				gameOver = true;
			
			if (snakeParts.size() > tailLength)
                snakeParts.remove(0);
			
			if (cherry != null) {
				
                if (head.equals(cherry)) {
                	
                	Random rand = new Random();
                	//sets cherry location
                    cherry.setLocation(rand.nextInt(47), rand.nextInt(25));
                    
                    tailLength += 2;
                 	score++;
                  
                    }
                }
			}
			
		}
	// returns false if tail is at point you try to turn to
	// returns true if it is safe
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	
	


