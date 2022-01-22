import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;


public class Game {

	static int score=0;
	public static Game game;
	public static RestartButton resetbutton;
	public Paint paint;
	public static JFrame MainFrame;
	public static GameBoard panel;
	public static TextField Text;
	public static final int HEIGHT = 800, WIDTH = 800;

	public Game() {
		
		paint = new Paint();
		MainFrame = new JFrame();
		resetbutton = new RestartButton();
		panel = new GameBoard();
		Text = new TextField();
		
		MainFrame.getContentPane().add(Text);
		MainFrame.getContentPane().add(resetbutton);
		MainFrame.getContentPane().add(panel);
		MainFrame.add(paint);
		MainFrame.setVisible(true);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.setBounds(0, 0 , WIDTH, HEIGHT);
		MainFrame.setTitle("Tic Tac Toe");
		MainFrame.setResizable(false);
		
	}

	public void repaint(Graphics g) {
		
		g.drawString("Regular TicTacToe Game, first to start is X", 70, 600);
		g.drawString("Made By Theodor", 70, 620);
		
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Aerial", Font.LAYOUT_LEFT_TO_RIGHT, 60));
		g.drawString("TIC TAC TOE", 20, 90);
	}
	
	public static void main(String[] args) {

		game = new Game();
	}
}
