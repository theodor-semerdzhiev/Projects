
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RestartButton extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public static JButton TextBox;

	public RestartButton() {
		setBounds(300, 675, 200, 100);
		
		TextBox = new JButton("Restart");
		add(TextBox);
		TextBox.setBounds(100, 100, 100, 100);
		TextBox.setVisible(true);
		TextBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameBoard.GameOnGoing=true;
				GameBoard.A1.setEnabled(true);
				GameBoard.A1.setText("");
				GameBoard.A2.setEnabled(true);
				GameBoard.A2.setText("");
				GameBoard.A3.setEnabled(true);
				GameBoard.A3.setText("");
				GameBoard.B1.setEnabled(true);
				GameBoard.B1.setText("");
				GameBoard.B2.setEnabled(true);
				GameBoard.B2.setText("");
				GameBoard.B3.setEnabled(true);
				GameBoard.B3.setText("");
				GameBoard.C1.setEnabled(true);
				GameBoard.C1.setText("");
				GameBoard.C2.setEnabled(true);
				GameBoard.C2.setText("");
				GameBoard.C3.setEnabled(true);
				GameBoard.C3.setText("");
			}
		});
	}
}
