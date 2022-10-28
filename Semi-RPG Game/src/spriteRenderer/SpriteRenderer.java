package spriteRenderer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteRenderer {

	public static ArrayList<Image> Render(String filename, int rows, int columns, Dimension dim) {
			BufferedImage img=null;
			
			ArrayList<Image> Sprites= new ArrayList<Image>();
			try {
				img = ImageIO.read(new File(filename));
			} catch (IOException e) {
				System.out.print("Can not retrieve Sprite Sheet");
				e.printStackTrace();
			} 
			for(int i=0; i<rows; i++) {
				for(int j=0; j<columns; j++) {
					Sprites.add(img.getSubimage((img.getWidth()/columns)*j, (img.getHeight()/rows)*i, img.getWidth()/columns, img.getHeight()/rows).getScaledInstance(dim.height/15, dim.height/15, Image.SCALE_SMOOTH));
				}
			}
			return Sprites;
	}
}
