package world_generator;

import javax.swing.JFrame;


public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	public Console console;
	public Main() {
		
		console = new Console();
		
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200,900);
		setLocation(0,0);
		
		add(console);
	}
	public static void main(String[] args) {
		new Main();
	}
}
