package shooter_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameLevel {
	public static Image Floor,background;
	public static int score;
	
	public GameLevel() {
		Floor = Toolkit.getDefaultToolkit().createImage("floor.png");
		background = Toolkit.getDefaultToolkit().createImage("Background.jpg");
	}
	
	public static void PaintBackground(Graphics g) {
		g.drawImage(background, 0, -100, null);
		
		g.drawImage(Floor, 0, Main.HEIGHT-Floor.getHeight(null)+75, null);
		g.drawImage(Floor, Floor.getWidth(null), Main.HEIGHT-Floor.getHeight(null)+75, null);
		g.drawImage(Floor, Floor.getWidth(null)*2, Main.HEIGHT-Floor.getHeight(null)+75, null);
		g.drawImage(Floor, Floor.getWidth(null)*3, Main.HEIGHT-Floor.getHeight(null)+75, null);
		
		g.setColor(Color.YELLOW);
		g.setFont(StartMenu.font.deriveFont(30f));
		g.drawString("Score: "+ score, Main.WIDTH/2, 40);
	}
}
