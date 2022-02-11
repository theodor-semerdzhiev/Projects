package chess_game;

import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Main {

	Board board;
	public Main() {
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1000, 860);
		frame.setResizable(false);
		
		board = new Board();
		frame.add(board);
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
