package start_GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.Main;

public class MainMenu extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image background;
	private StartMenuGUI_Font font;
	private StartButton startbutton;
	private OptionButton optionbutton;
	private ExitButton exitbutton;
	private GameTitle title;
	private Timer timer;
	public Main main;
	
	public MainMenu(Main main) {
		timer = new Timer(100, this);
		timer.start();
		
		this.main=main;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		background = Toolkit.getDefaultToolkit().createImage("Images/Menu_Background.jpg");
		setSize(1024,796);
		setVisible(true);
		
		font = new StartMenuGUI_Font("Fonts/PixelDigivolve-mOm9.ttf");
		
		title = new GameTitle("  RPG LAND", new Color(0,19,200), font.font, 150f);
		add(title);
		
		startbutton = new StartButton("Start",Color.RED, font.font, 50f, this);
		add(startbutton);
		
		optionbutton = new OptionButton("Options",Color.RED, font.font, 50f, this); 
		add(optionbutton);
		
		exitbutton = new ExitButton("Exit",Color.RED, font.font, 50f, this); 
		add(exitbutton);
	}
	//keeps the image on the screen by printing
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, null);
		g.drawString("HI", 100, 100);
	}
	//repaint() is run every time the timer calls it
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		
	}
	
	
	//Return font
	public Font getStartMenuFont() {
		return font.font;
	}
	public JButton getStartButton() {
		return startbutton;
	}
	public JButton getOptionButton() {
		return optionbutton;
	}
	public JButton getExitButton() {
		return exitbutton;
	}
	public Timer getTimer() {
		return timer;
	}
	
	private class GameTitle extends JLabel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public GameTitle(String title, Color color, Font font, float size) {		
			setFont(font.deriveFont(size));
			setOpaque(false);
			setForeground(color);
			setSize(300, 100);
			setText(title);
		}
	}
}
