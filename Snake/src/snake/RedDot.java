package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class RedDot {
	
	static Random Rand = new Random();
	public static Rectangle dot;
	
	public RedDot() {
		
		dot = new Rectangle();
		dot.setSize(50, 50);
		dot.setLocation((Rand.nextInt(19)+1)*50,(Rand.nextInt(19)+1)*50);
	}

	public static void paintRedDot(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillRect(dot.x, dot.y, dot.width, dot.height);
	}
}
