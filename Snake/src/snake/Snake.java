package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Snake {
	

	static ArrayList <Rectangle> Snake;
	public static RedDot dot;
	public static int XDirection=0;
	public static int YDirection=0;
	private static Random rand=new Random();;

	public Snake() {

		dot=new RedDot();
		Snake = new ArrayList <Rectangle>();
		Snake.add(new Rectangle(500,500,50,50));

	}

	public static void MoveSnake(int XDirection, int YDirection) {

		if(XDirection==1) {
			MoveBody(XDirection,YDirection);
			addToSnake(XDirection,YDirection);
		} else if(XDirection==-1) {
			MoveBody(XDirection,YDirection);
			addToSnake(XDirection,YDirection);
		} else if(YDirection==1) {
			MoveBody(XDirection,YDirection);
			addToSnake(XDirection,YDirection);
		} else if(YDirection==-1) {
			MoveBody(XDirection,YDirection);
			addToSnake(XDirection,YDirection);
		}
	}
	 
	public static void MoveBody(int X, int Y) {
		for(int i=Snake.size()-1; i>0; i--) {
			Snake.get(i).setLocation(Snake.get(i-1).getLocation());
		}
		if(X==1) {
			Snake.get(0).x+=Snake.get(0).width;
		} else if(X==-1) {
			Snake.get(0).x-=Snake.get(0).width;
		} else if(Y==1) {
			Snake.get(0).y+=Snake.get(0).width;
		} else if (Y==-1) {
			Snake.get(0).y-=Snake.get(0).width;
		}
	}
	public static void addToSnake(int XDirection, int YDirection) {
		if(RedDot.dot.intersects(Snake.get(0))) {
			ScoreBoard.score++;
			Snake.add(new Rectangle(Snake.get(Snake.size()-1).x+(XDirection*50),Snake.get(Snake.size()-1).y-(YDirection*50),50,50));
			while(true) {
				RedDot.dot.setLocation((rand.nextInt(19)+1)*50,(rand.nextInt(19)+1)*50);
				for(int i=0; i<Snake.size();i++) {
					if(RedDot.dot.intersects(Snake.get(i))){
						i=0;
						RedDot.dot.setLocation((rand.nextInt(19)+1)*50,(rand.nextInt(19)+1)*50);
						continue;
					}
				}
				break;
			}
		} 
	}

	public static void PaintSnake(Graphics g) {
		for(int i=0; i<Snake.size(); i++) {
			if(i==0) {
				g.setColor(Color.orange);
				g.fillRect(Snake.get(i).x, Snake.get(i).y, Snake.get(i).width, Snake.get(i).height);
			} else {
				g.setColor(Color.yellow);
				g.fillRect(Snake.get(i).x, Snake.get(i).y, Snake.get(i).width, Snake.get(i).height);
			}
		}
	}
}
