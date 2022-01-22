package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;


	public class Main extends JFrame implements ActionListener, KeyListener {
		
		private static final long serialVersionUID = 1L;
		
		public static Main main;
		public static Renderer Renderer;
		public final static int WIDTH=1000;
		public final static int HEIGHT=1025;
		public static boolean Running;
		public static  Timer timer;
		public static StartMenu menu;
		public Snake snake;
		public RedDot dot;;
		public ScoreBoard board;
		public Image image, background;


		public Main() {

			Running = false;
			
			timer = new Timer(20 , this);

			board = new ScoreBoard();
			Renderer = new Renderer();
			snake = new Snake();
			dot = new RedDot();
			menu = new StartMenu();
			
			
			
			image = Toolkit.getDefaultToolkit().createImage("StarBackGround.gif");
			
			addKeyListener(this);
			add(Renderer);
			setVisible(true);
			setFocusable(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(WIDTH,HEIGHT);
			setTitle("Snake");
			setResizable(false);
			add(menu);
			add(board);
					
			Renderer.setSize(getWidth(), getHeight());
			Renderer.setVisible(false);
			
			
			timer.start();
		}
		  
		public void actionPerformed(ActionEvent e) {
		
			Renderer.repaint();
			
		}

		public void repaint(Graphics g) {
			
			if(Running) {
			g.drawImage(image, 0 , 0 , null);
			g.drawImage(image, 500 , 0 , null);
			g.drawImage(image, 0 , 500 , null);
			g.drawImage(image, 500 , 500 , null);
			
			RedDot.paintRedDot(g);
			Snake.PaintSnake(g);
			
			g.setColor(Color.CYAN);
			g.setFont(new Font("Areial",30,30));
			g.drawString("Score: " + Integer.toString(ScoreBoard.score), 10, 25);
			}
		}
		
		public static void main(String[] args) {

			main = new Main();
		}

		public void keyPressed(KeyEvent e) {
			if(Main.Running==true) {
				if(e.getKeyCode()==KeyEvent.VK_D) {
					if(Snake.Snake.get(0).x > getWidth()-100) {
						System.out.println("OUT OF BOUNDS");
					} else {	
						Snake.MoveSnake(1, 0);
					}
				}
				if(e.getKeyCode()==KeyEvent.VK_A) {
					if(Snake.Snake.get(0).x == 0) {
						System.out.println("OUT OF BOUNDS");
					} else {
						Snake.MoveSnake(-1, 0);
					}
				}
				if(e.getKeyCode()==KeyEvent.VK_W) {
					if(Snake.Snake.get(0).y == 0) {
						System.out.println("OUT OF BOUNDS");
					} else {	
						Snake.MoveSnake(0, -1);
					}
				}
				if(e.getKeyCode()==KeyEvent.VK_S) {
					if(Snake.Snake.get(0).y>getHeight()-100) {
						System.out.println("OUT OF BOUNDS");
					} else {
						Snake.MoveSnake(0, 1);
					}
				}
				if(e.getKeyCode()== KeyEvent.VK_ENTER) {
						
					Rectangle Snakehead= Snake.Snake.get(0);
					Snake.Snake.clear();
					Snake.Snake.add(Snakehead);
					
					Renderer.setVisible(false);
					menu.setVisible(true);
					Running = false;
					
					Random rand = new Random();
					RedDot.dot.setLocation((rand.nextInt(19)+1)*RedDot.dot.width, (rand.nextInt(19)+1)*RedDot.dot.height);
					
					Snake.Snake.get(0).setLocation(500,500);
					
					ScoreBoard.board.append("\n"+Integer.toString(ScoreBoard.score));
					ScoreBoard.score=0;
				}
				if(e.getKeyCode()== KeyEvent.VK_SPACE) {
					
					ScoreBoard.score=0;
					Rectangle Snakehead= Snake.Snake.get(0);
					Snake.Snake.clear();
					Snake.Snake.add(Snakehead);
					
					Random rand = new Random();
					RedDot.dot.setLocation((rand.nextInt(19)+1)*RedDot.dot.width, (rand.nextInt(19)+1)*RedDot.dot.height);
					
					Snake.Snake.get(0).setLocation(500,500);
					
				}
			}
		}

		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}

	}
