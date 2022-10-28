package start_GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class ExitButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExitButton(String text, Color color, Font font, float size, MainMenu menu) {
		setFont(font.deriveFont(size));
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setForeground(color);
		setSize(300, 100);
		setText(text);
		
		//When button is clicked it exits game, hence action listener
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				System.out.println("Runtime stopped");
				Runtime.getRuntime().exit(1);
			}
		});
		//Each time mouse hovers over component, it will become larger and change color
		//it will revert back to original size when mouse is no longer hovering
		addMouseListener( new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setText(text+" <--");
				setFont(font.deriveFont((size*1.25f)));
				setForeground(Color.GREEN);
			}
			public void mouseExited(MouseEvent e) {
				setText(text);
				setFont(font.deriveFont(size));
				setForeground(color);
			}
		});
	}
}

