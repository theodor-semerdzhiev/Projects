package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	public Main main;
	public JLabel GameTitle;
	public static ScoreBoard board;
	public BoxLayout layout;
	public JButton StartButton;
	public JButton OptionsButton;
	public JButton ExitButton;
	public int ButtonMagnifier;
	
	
	public StartMenu() {
		
		
		setBounds(0, 0, Main.HEIGHT, Main.WIDTH);
		setVisible(true);
		setFocusable(true);
		setBackground(new Color(0,0,0,0));
		layout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
		setLayout(layout);
		
		
		GameTitle = new JLabel(" Snake Game");	
		GameTitle.setFont(new Font("Areial",30,30));
		GameTitle.setForeground(new Color(255,0,104,200));
		GameTitle.setLocation(500, 25);
		add(GameTitle);
	
		StartButton = new JButton("Start");
		StartButton.setFont(new Font("Areial",20,20));
		
		StartButton.setForeground(Color.GREEN);
		StartButton.setSize(300, 100);
		add(StartButton);
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.Running=true;
				Main.Renderer.setVisible(true);
				Main.menu.setVisible(false);
				setVisible(false);
			}
		});
		ExitButton = new JButton("Exit");
		ExitButton.setOpaque(false);
		ExitButton.setForeground(Color.GREEN);
		ExitButton.setSize(300, 100);
		add(ExitButton);
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
	}
}

