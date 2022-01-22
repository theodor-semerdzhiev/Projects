package shooter_game;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	public static Main main;
	public static Renderer Renderer;
	public static GameLevel level;
	public static Player player;
	public static Enemy enemy;
	public final static int WIDTH=1000;
	public final static int HEIGHT=1000;
	public static StartMenu menu;
	public static boolean Running;
	public static  Timer timer;
	public Image Floor, Player;
	SoundPlayer audioPlayer;

 
	public Main() throws IOException {

		Running = false;
		
		timer = new Timer(20 , this);
		
		Renderer = new Renderer();
		menu = new StartMenu();
		player = new Player();
		level = new GameLevel();
		audioPlayer = new SoundPlayer();

		add(Renderer);
		addKeyListener(this);
		setVisible(true);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setTitle("Snake");
		setResizable(false);
		getContentPane().add(menu);
				
		Renderer.setLayout(null);
		Renderer.setSize(getWidth(), getHeight());
		Renderer.add(player);
		
		Renderer.setVisible(false);
	
		timer.start();
	}
	  
	public void actionPerformed(ActionEvent e) {
		if(Running) {
		Renderer.repaint();
		} else if(!Running) {
		menu.repaint();
		}
	}
	 
	public void repaint(Graphics g) {
		
		GameLevel.PaintBackground(g);
	}
	
	public static void main(String[] args) throws IOException {

		main = new Main();
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			Renderer.setVisible(false);
			menu.setVisible(true);
			Running=false;
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
