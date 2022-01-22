package shooter_game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Animation {
	
	enum Type_Of_Animation {
		SHOOTING, WALKING, CROUCHING, IDLE;
	}
	BufferedImage [] ShootingAnime_RIGHT = GetFrames("hero_spritesheet.png",2,6,8,5);
	BufferedImage [] WalkAnime_RIGHT = GetFrames("hero_spritesheet.png",1,6,8,5);
	BufferedImage [] CrouchingAnime_RIGHT = GetFrames("hero_spritesheet.png",4,2,8,5);
	BufferedImage [] IdleAnime_RIGHT = GetFrames("hero_spritesheet.png",0,8,8,5);
	
	
	
	static Timer AnimationTimer;

	public Animation(int Delay, Player player, Type_Of_Animation animation) {
		
		switch(animation) {
		case IDLE:
			StartAnimationTimer(IdleAnime_RIGHT,player, Delay,8);
			break;
		case SHOOTING:
			StartAnimationTimer(ShootingAnime_RIGHT,player, Delay,6);
			break;
		case WALKING:	
			StartAnimationTimer(WalkAnime_RIGHT,player, Delay,6);
			break;
		case CROUCHING:
			StartAnimationTimer(CrouchingAnime_RIGHT,player, Delay, 2);
			break;		
		}
	}
	
	public static BufferedImage [] GetFrames(String SpriteSheetFile, int column, int TotalFrames, int MaxRows, int MaxColumns) {
		
		BufferedImage img=null;
		try {
			img = ImageIO.read(new File(SpriteSheetFile));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("Unable to Retrieve Sprite Sheet due to "+ e);
		}
		int ImgWidth=img.getWidth();	
		int ImgHeight=img.getHeight();
		
		
		BufferedImage [] frames = new BufferedImage[TotalFrames] ;
		
		
		for(int i=0; i < TotalFrames; i++) {
			frames[i]=img.getSubimage((ImgWidth/MaxRows)*i, column*(ImgHeight/MaxColumns), (ImgWidth/MaxRows), ImgHeight/MaxColumns);
			}
		return frames;
	}
	
	private void StartAnimationTimer(BufferedImage [] animation, Player player, int Delay, int MaxAnimeFrames) {
		AnimationTimer = new Timer(Delay, new ActionListener() {	
			int i=0;
			public void actionPerformed(ActionEvent arg0) {
				if(i< MaxAnimeFrames) {
				player.setIcon(new ImageIcon(animation[i]));	
				i++;
				} else {
				i=0;
				}
			}
		});
		AnimationTimer.start();
	}
}
