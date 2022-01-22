package shooter_game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class SpriteRenderer extends ImageIcon {
	private static final long serialVersionUID = 1L;

	public static Image GetScaledImage(String FileName, JComponent entity) {
		Image Sprite = null;
		try {
			Sprite =(ImageIO.read(new File(FileName)).getScaledInstance(entity.getWidth(), entity.getHeight(), Image.SCALE_SMOOTH));
		} catch (IOException e) {
			System.out.print("Cant retreive Bullet Sprite");
			e.printStackTrace();
		}
		return Sprite;
	}
}
