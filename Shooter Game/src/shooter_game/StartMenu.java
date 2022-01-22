package shooter_game;


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

	Main main;
	JLabel Text, Text1, label, GameTitle;
	static Image background;
	public static Font font;
	GraphicsEnvironment genv;
	BoxLayout layout;
	JButton StartButton;
	JButton OptionsButton;
	JButton ExitButton;
	int ButtonMagnifier;
	SettingsMenu settings;
	 
	
	public StartMenu() {
		
		ButtonMagnifier=10;
		
		try {
		    font = Font.createFont(Font.TRUETYPE_FONT, new File("PixelDigivolve-mOm9.ttf"));
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(font);
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.print("Unable to retrieve Font file");
		} catch(FontFormatException e) {
		    e.printStackTrace();
		    System.out.print("Font File invalid");
		}  
		
		setBounds(0, 0, Main.HEIGHT, Main.WIDTH);
		setVisible(true);
		setFocusable(true);
		setBackground(new Color(0,0,0,0));
		layout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
		setLayout(layout);
		
		GameTitle = new JLabel(" SHOOTER CITY");
		GameTitle.setForeground(new Color(255,0,104,200));
		GameTitle.setFont(font.deriveFont(100f));
		GameTitle.setLocation(500, 25);
		add(GameTitle);
	
		StartButton = new JButton("Start");
		StartButton.setFont(font.deriveFont(50f));
		StartButton.setOpaque(false);
		StartButton.setContentAreaFilled(false);
		StartButton.setBorderPainted(false);
		StartButton.setFocusPainted(false);
		StartButton.setForeground(Color.GREEN);
		StartButton.setSize(300, 100);
		add(StartButton);
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				setVisible(false);
				Main.Renderer.setVisible(true);
				Main.Running=true;
			}
		});
	    StartButton.addMouseListener( new MouseAdapter() {
	        public void mouseEntered(MouseEvent e) {
	        	UpdateButton_MouseEntered(StartButton.getFont(), StartButton, 15, Color.RED);
	        }
	        public void mouseExited(MouseEvent e) {
	        	UpdateButton_MouseExited(StartButton.getFont(), StartButton, 15, Color.GREEN);
	        }
	    });
	    settings = new SettingsMenu();
		add(settings);	
	    
		OptionsButton = new JButton("Settings");
		OptionsButton.setFont(font.deriveFont(50f));
		OptionsButton.setOpaque(false);
		OptionsButton.setContentAreaFilled(false);
		OptionsButton.setBorderPainted(false);
		OptionsButton.setFocusPainted(false);
		OptionsButton.setForeground(Color.GREEN);
		OptionsButton.setSize(300, 100);
		add(OptionsButton);
		OptionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(OptionsButton.getText()=="Settings") {
				System.out.println("Settings Menu Enabled");
				settings.setVisible(true);
				ExitButton.setVisible(false);
				StartButton.setVisible(false);
				OptionsButton.setText("<Back");
				} else {
				System.out.println("Settings Menu Disabled");
				settings.setVisible(false);	
				ExitButton.setVisible(true);
				StartButton.setVisible(true);
				OptionsButton.setText("Settings");
				}
			}
		});
		OptionsButton.addMouseListener( new MouseAdapter() {
	        public void mouseEntered(MouseEvent e) {
	        	UpdateButton_MouseEntered(OptionsButton.getFont(), OptionsButton, 15, Color.RED);
	        }
	        public void mouseExited(MouseEvent e) {
	        	UpdateButton_MouseExited(OptionsButton.getFont(), OptionsButton, 15, Color.GREEN);
	        }
	    });
		ExitButton = new JButton("Exit");
		ExitButton.setFont(font.deriveFont(50f));
		ExitButton.setOpaque(false);
		ExitButton.setContentAreaFilled(false);
		ExitButton.setBorderPainted(false);
		ExitButton.setFocusPainted(false);
		ExitButton.setForeground(Color.GREEN);
		ExitButton.setSize(300, 100);
		add(ExitButton);
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		ExitButton.addMouseListener( new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				UpdateButton_MouseEntered(ExitButton.getFont(), ExitButton, 15, Color.RED);
	        }
	        public void mouseExited(MouseEvent e) {
	        	UpdateButton_MouseExited(ExitButton.getFont(), ExitButton, 15, Color.GREEN);
	        }
	    });
		background=Toolkit.getDefaultToolkit().createImage("StartMenuBackground.gif");
	}
	protected void paintComponent(Graphics g){
        super.paintComponent(g);
        	//g.drawImage(background, 0,0,null);
        g.drawImage(background, 0+Main.WIDTH, 0, -1000, 1000, null);
	}
	public static void UpdateButton_MouseEntered(Font font, JButton button, float SizeMagnifier, Color NewColor) {
			button.setForeground(NewColor);
			button.setFont(font.deriveFont(font.getSize2D()+SizeMagnifier));
			
	}
	public static void UpdateButton_MouseExited(Font font, JButton button, float SizeMagnifier, Color InitialColor) {
			button.setForeground(InitialColor);
			button.setFont(font.deriveFont(font.getSize2D()-SizeMagnifier));
	}
}
