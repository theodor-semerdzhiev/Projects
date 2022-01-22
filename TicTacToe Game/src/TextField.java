
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class TextField extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JTextArea Textbox;
	public TextField() {
		
		setBounds(400,70, 400, 500);
		
		Textbox = new JTextArea(30,20);
		add(Textbox);
		Textbox.setVisible(true);
		Textbox.setLocation(0,0);
		Textbox.setEditable(true);
		Textbox.setText("Console:");
		
	}
}
