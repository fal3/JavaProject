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
	private ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	
	//speed = speed of snake
	private int speed = 0;

	private int time;
	
	//direction snake is traveling at start
	private int direction = DOWN;
	
	// value for the direction the snake is moving
	private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	
	//size of each snake pieces
	private static final int SCALE = 6;
	
	//head of the snake
	private Point head;
	
	// # of cherrys colided with
	private int score;
	
	// used to refresh the box
	private Timer timer = new Timer(15,this);
		
	
	//the objective to run into called the "cherry"
	private Point cherry;
	
	private int level;
	
	private boolean paused;
	
	// ends game if true
	private boolean gameOver;
	
	private int refreshRate;
	
	//length of snake 
	private int tailLength;
	
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
	//all local variables
	public void startGame(){
		
		
		// starting location for snake
		head = new Point(0, 0);
		
		tailLength = 1;
		
		direction = DOWN;
		
		speed = 0;
		
		score = 0;
		
		time = 0;
		
		level = 1;
		
		refreshRate=9;
		
		paused = false;
		
		gameOver = false;
		
		Random rand = new Random();
		
		snakeParts.clear();
		
		cherry = new Point(rand.nextInt(110),rand.nextInt(90));
		
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
		
		if(speed % 2 ==0 && gameOver == false && paused ==false){
			//timer (seconds) for how long the snake is moving for
			time++;
		}
		//Algorithm for the snake moving on grid
				// speed % (x) sets speed of snake
		if(speed % refreshRate ==0 && gameOver == false && paused ==false){	
			snakeParts.add(new Point(head.x, head.y));
			if(direction == UP  && h.noTailAt(head.x, head.y - 1, snakeParts))
			{
				//up
				if(head.y - 1 >= 0 && h.noTailAt(head.x, head.y - 1, snakeParts))
					head = new Point(head.x, head.y - 1);
				else 
					gameOver = true;
			}
			else if(direction == DOWN)
			{
				//down
				 if ((head.y + 1 < 96) && h.noTailAt(head.x, head.y + 1, snakeParts))
					 head = new Point(head.x, head.y + 1);
				 else 
					gameOver = true;
			}
			else if(direction == LEFT && h.noTailAt(head.x - 1, head.y, snakeParts))
			{
				//left
				if (head.x - 1 >= 0 && h.noTailAt(head.x - 1, head.y, snakeParts))
					head = new Point(head.x - 1, head.y);
				else 
					gameOver = true;
			}
			else if(direction == RIGHT && h.noTailAt(head.x + 1, head.y, snakeParts))
			{
				//right
				if (head.x + 1 < 119 && h.noTailAt(head.x + 1, head.y, snakeParts))
					head = new Point(head.x + 1, head.y);
				else 
					gameOver = true;
			}
			else
			{
				gameOver = true;
			}
			
			if (snakeParts.size() > tailLength)
                snakeParts.remove(0);
			
			// if cherry is displayed
			if (cherry != null) {
				// if the snake head equals the cherry location
				
                if (head.equals(cherry)) {
                		//iterates speed
                	if(score == 1){
                		refreshRate-=2;
                		level++;
                		
                	}
                	//iterates speed
                	else if(score == 4){
                		refreshRate-=1;
                		level++;
                	}
                	//iterates speed
                	else if(score == 6){
                		refreshRate-=1;
                		level++;
                	}
                	//iterates speed
                	else if(score == 8){
                		refreshRate-=1;
                		level++;
                	}
                	//iterates speed
                	else if(score == 11){
                		refreshRate-=1;
                		level++;
                	}
                	//iterates speed
                	else if (score == 15){
                		refreshRate-=1;
                		level++;
                	}
                	
                	Random rand = new Random();
                	//sets cherry location
                    cherry.setLocation(rand.nextInt(119), rand.nextInt(96));
                    
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
	
	public int getTime(){
		return time;
	}
	public ArrayList<Point> getSnakeParts()
	{
		return snakeParts;
	}
	public static int getScale(){
		return SCALE;
	}
	public Point getHead(){
		return head;
	}
	public Point getCherry(){
		return cherry;
	}
	public int getTailLength(){
		return tailLength;
	}
	public int getScore(){
		return score;
	}
	public int getLevel(){
		return level;
	}
	public boolean getGameOver(){
		return gameOver;
	}
}
	
	


