import java.awt.Font;


import javax.swing.JButton;

public class Bot {

	public static JButton getAIMove(JButton a1, JButton a2, JButton a3, JButton b1, JButton b2, JButton b3, JButton c1, JButton c2, JButton c3) {

		JButton Current_preferred_move=null;

		JButton [][] Board={{a1, a2, a3},   
							{b1, b2, b3},
							{c1, c2, c3}};
		
		for(int i=0; i<Board.length; i++) {
			for(int j=0; j<Board.length; j++) {
				if(Board[i][j].isEnabled()) {
					Board[i][j].setText("O");
					if(GameBoard.getFinalWinCondition(a1, a2, a3, b1, b2, b3, c1, c2, c3)) {
						Board[i][j].setText("O");
						Board[i][j].setFont(new Font("Aerial", Font.BOLD, 40));
						return Board[i][j];
					} else {
						Board[i][j].setText("X");
						if(GameBoard.getFinalWinCondition(a1, a2, a3, b1, b2, b3, c1, c2, c3)) {
							Board[i][j].setText("O");
							Board[i][j].setFont(new Font("Aerial", Font.BOLD, 40));
							Current_preferred_move=Board[i][j];
						}
						Board[i][j].setText("");
					}
				}
			}
		}
		if(Current_preferred_move == null) {
			for(int k=0;k<Board.length;k++) {
				for(int i=0;i<Board[k].length;i++){
					if(Board[k+1][i].isEnabled()) {
						Board[k+1][i].setText("O");
						Board[k+1][i].setFont(new Font("Aerial", Font.BOLD, 40));
						return Board[k+1][i];
					} 
				}
			}
		}

		Current_preferred_move.setText("O");
		Current_preferred_move.setFont(new Font("Aerial", Font.BOLD, 40));

		return Current_preferred_move;
	}
}
