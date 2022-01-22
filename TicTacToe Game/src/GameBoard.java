import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPanel;


public class GameBoard extends JPanel {

	enum status
	{
		STATUS_X ,
		STATUS_O,
		STSTUS_UNOCUPIED
	}

	private static final long serialVersionUID = 1L;
	public static JButton A1,A2,A3,B1,B2,B3,C1,C2,C3;
	public static boolean PlayerTurn=true;
	static boolean GameOnGoing=true;

	public GameBoard() {

		A1=new JButton();
		A2=new JButton();
		A3=new JButton();
		B1=new JButton();
		B2=new JButton();
		B3=new JButton();
		C1=new JButton();
		C2=new JButton();
		C3=new JButton();
		add(A1);
		add(A2);
		add(A3);
		add(B1);
		add(B2);
		add(B3);
		add(C1);
		add(C2);
		add(C3);
		setLayout(new GridLayout(3,3));
		setBounds(10,130,400,400);

		A1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonActivated(A1);
			}
		});
		A2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonActivated(A2);
			}
		});
		A3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonActivated(A3);
			}
		});
		B1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonActivated(B1);
			}
		});
		B2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonActivated(B2);
			}
		});
		B3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonActivated(B3);
			}
		});
		C1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonActivated(C1);
			}
		});
		C2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonActivated(C2);
			}
		});
		C3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonActivated(C3);
			}
		});
	}

	public static void ButtonActivated (JButton Button) {

		Button.setEnabled(false);
		System.out.print("\n"+A1.getText().hashCode()+" "+A2.getText().hashCode());

		Button.setText("X");
		Button.setFont(new Font("Aerial", Font.BOLD,40));

		if(getFinalWinCondition(A1,A2,A3,B1,B2,B3,C1,C2,C3)==true && GameOnGoing==true) {
			GameOnGoing=false;
			TextField.Textbox.append("\nX Won!");
		} else if(!isBoardFull(A1,A2,A3,B1,B2,B3,C1,C2,C3)==true) {

			Bot.getAIMove(A1,A2,A3,B1,B2,B3,C1,C2,C3).setEnabled(false);
			if(getFinalWinCondition(A1,A2,A3,B1,B2,B3,C1,C2,C3)==true && GameOnGoing==true) {
				TextField.Textbox.append("\nO Won!");
			}
		} else {
			TextField.Textbox.append("\nDraw!");
		}
	}

	public static boolean isBoardFull (JButton a1, JButton a2, JButton a3, JButton b1, JButton b2, JButton b3, JButton c1, JButton c2, JButton c3) {

		ArrayList<JButton> Array = new ArrayList <>(Arrays.asList(a1,a2,a3,b1,b2,b3,c1,c2,c3));
		boolean bool=true;

		for(int i=1; i<Array.size(); i++) {
			if(Array.get(0).isEnabled()!=Array.get(i).isEnabled()) {
				bool=false;
				break;
			}	
		}
		return bool;
	}

	public static boolean getFinalWinCondition(JButton A1, JButton A2, JButton A3, JButton B1, JButton B2, JButton B3, JButton C1, JButton C2, JButton C3) {

		if(getVerticalWinCondtion(
				A1.getText().hashCode(),													
				A2.getText().hashCode(),													
				A3.getText().hashCode(),														
				B1.getText().hashCode(),														
				B2.getText().hashCode(),														
				B3.getText().hashCode(),														
				C1.getText().hashCode(),					
				C2.getText().hashCode(),											
				C3.getText().hashCode())==true) {
			return true;
		} else if(getDiagonalWinCondition(
				A1.getText().hashCode(),													
				A2.getText().hashCode(),													
				A3.getText().hashCode(),														
				B1.getText().hashCode(),														
				B2.getText().hashCode(),														
				B3.getText().hashCode(),														
				C1.getText().hashCode(),					
				C2.getText().hashCode(),											
				C3.getText().hashCode())==true) {
			return true;
		} else if(getHorizontalWinCondition(
				A1.getText().hashCode(),													
				A2.getText().hashCode(),													
				A3.getText().hashCode(),														
				B1.getText().hashCode(),														
				B2.getText().hashCode(),														
				B3.getText().hashCode(),														
				C1.getText().hashCode(),					
				C2.getText().hashCode(),											
				C3.getText().hashCode())==true) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean getVerticalWinCondtion(int a1, int a2, int a3, int b1, int b2, int b3, int c1, int c2, int c3) {
		int [][] Row1= {{a1,a2,a3},   
						{b1,b2,b3},
						{c1,c2,c3}};
		boolean checker=false;

		for(int i=0; i<Row1.length; i++) {
			if(Row1[0][i]==Row1[1][i] && Row1[0][i]==Row1[2][i]) {
				if(Row1[0][i]==0) {
					continue;
				} else {
					checker =true;
					break;
				}
			}
		}		
		return checker;
	}

	public static boolean getHorizontalWinCondition(int a1, int a2, int a3, int b1, int b2, int b3, int c1, int c2, int c3) {
		int [][] Row1= {{a1,a2,a3},   
						{b1,b2,b3},
						{c1,c2,c3}};
		boolean checker=false;

		for(int i=0; i<Row1.length; i++) {
			if(Row1[i][0]==Row1[i][1] && Row1[i][0]==Row1[i][2]) {
				if(Row1[i][0]==0) {
					continue;
				} else {
					checker =true;
					break;
				}
			}
	
		}		
		return checker;
	}

	public static boolean getDiagonalWinCondition(int a1, int a2, int a3, int b1, int b2, int b3, int c1, int c2, int c3) {
		int [][] Board={{a1,a2,a3},   
						{b1,b2,b3},
						{c1,c2,c3}};
		boolean checker=false;

		if(Board[0][0]==Board[1][1] && Board[0][0]==Board[2][2]) {
			if(Board[0][0]!=0) {
				checker=true;
			} 
		} else if(Board[0][2]==Board[1][1] && Board[0][2]==Board[2][0]) {
			if(Board[0][2]!=0) {
				checker=true;
			} 
		}
		return checker;
	}
}
