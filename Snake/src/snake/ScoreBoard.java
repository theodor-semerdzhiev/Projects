package snake;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ScoreBoard extends JPanel  {
	
	public static int score;
	public static JTextArea board;

	private static final long serialVersionUID = 1L;

	public ScoreBoard() {
		
		score = 0;
		
		board = new JTextArea(16,1); 
		
		setVisible(true);
		setBounds(10,585,200,425);
		setBackground(new Color(0,0,0,0));
		
		board.setText("Personal HighScores:");
		board.setFont(new Font("Areial", 20,20));
		board.setEditable(false);
		add(board);
	}
}
