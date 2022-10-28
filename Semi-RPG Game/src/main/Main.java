package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import start_GUI.MainMenu;
import worldmap.World;

public class Main {

	MainMenu menu;
	public static int HEIGHT=1024, WIDTH=796;
	public static JFrame frame;
	World world;
	
	//creates JFrame and adds component for startmenu
	public Main() {
		frame = new JFrame();
		menu = new MainMenu(this);
		frame.add(menu);
		frame.setVisible(true);
		frame.setSize(HEIGHT, WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
	}
	
	public static Dimension getFrameDimension() {
		return new Dimension(HEIGHT,WIDTH);
	}
	//runs program
	public static void main (String[] args) {
		new Main();
	}
}
