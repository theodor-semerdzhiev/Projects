package chess_game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class SpriteRenderer {

	static ArrayList<Image> Sprites;
	
	public SpriteRenderer(String Filename, int rows, int columns, int ParentFrameWidth, int ParentFrameHeight){
		BufferedImage img=null;
		
		Sprites= new ArrayList<Image>();
		try {
			img = ImageIO.read(new File(Filename));
		} catch (IOException e) {
			System.out.print("Can not retrieve Sprite Sheet");
			e.printStackTrace();
		} 
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Sprites.add(img.getSubimage((img.getWidth()/columns)*j, (img.getHeight()/rows)*i, img.getWidth()/columns, img.getHeight()/rows).getScaledInstance(ParentFrameWidth/8, ParentFrameHeight/8, Image.SCALE_SMOOTH));
			}
		}
	}
	public static ArrayList<Image> getSprites() {
		return Sprites;
	}
}
