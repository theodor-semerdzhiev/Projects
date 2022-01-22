package shooter_game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import shooter_game.Animation.Type_Of_Animation;


public class Player extends JLabel implements ActionListener,MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	
	Timer UpdateTimer,JumpTimer;
	public Projectile projectiles;
	int velX=0,velY=0, Cursor_Xpos, Spawntick=0, JumpTick=0;
	static boolean freefalling=false, InTheProcessOfShooting;
	public enum DirectionOfPlayer{
		FACING_LEFT, FACING_RIGHT, NOT_DEFINED;
	}
	static DirectionOfPlayer direction;
	Animation anime;
	Random rand;
	boolean InAnimation;
	
	public Player() {
		
		direction =  DirectionOfPlayer.NOT_DEFINED;
		InTheProcessOfShooting=false;
		InAnimation=true;
		
		Main.Renderer.addMouseMotionListener(this);
		
		
		anime = new Animation(50, this , Type_Of_Animation.IDLE);
		
		setIcon(new ImageIcon(Animation.GetFrames("hero_spritesheet.png",1,6,8,5)[0]));
		setVisible(true);
		setForeground(new Color(0,0,0,0));
		setBounds(Main.WIDTH/2, 751, getIcon().getIconWidth(), getIcon().getIconWidth());
		
		
		UpdateTimer = new Timer(1,this);
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "MoveLeftAction");
		getActionMap().put("MoveLeftAction", new MoveLeftAction());
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "StopLeftAction");
		getActionMap().put("StopLeftAction", new StopLeftAction());
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "MoveRightAction");
		getActionMap().put("MoveRightAction", new MoveRightAction()); 
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "StopRightAction");
		getActionMap().put("StopRightAction", new StopRightAction()); 
			
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "JumpAction");
		getActionMap().put("JumpAction", new JumpAction());
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "FallAction");
		getActionMap().put("FallAction", new FallAction());
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "ShootAction");
		getActionMap().put("ShootAction", new ShootAction());
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "ShootAgain");
		getActionMap().put("ShootAgain", new ShootAgain());
		
		UpdateTimer.start();
	}

	public class MoveLeftAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Move left");
			direction=DirectionOfPlayer.FACING_LEFT;
			if(getX()>-125) {
			SetVelX(-8);
			}
			if(InAnimation && !freefalling) {
				Animation.AnimationTimer.stop();
				anime =new Animation(60, Main.player, Type_Of_Animation.WALKING);
				InAnimation=false;
			}
		}
	}
	public class StopLeftAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			SetVelX(0);
			if(!InAnimation && !freefalling) {
				Animation.AnimationTimer.stop();
				anime =new Animation(60, Main.player, Type_Of_Animation.IDLE);
				InAnimation=true;
			}
		}
	}
	public class MoveRightAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			System.out.println("Move Right");
			direction=DirectionOfPlayer.FACING_RIGHT;
			if(getX()<Main.WIDTH-200) {
			SetVelX(8);
			}
			if(InAnimation && !freefalling) {
				Animation.AnimationTimer.stop();
				anime =new Animation(60, Main.player, Type_Of_Animation.WALKING);
				InAnimation=false;
			}
		}
	}
	public class StopRightAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			SetVelX(0);
			if(!InAnimation && !freefalling) {
				Animation.AnimationTimer.stop();
				anime =new Animation(60, Main.player, Type_Of_Animation.IDLE);
				InAnimation=true;
			}
		}
	}
	public class JumpAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {			
		
			if(!freefalling) {
				Animation.AnimationTimer.stop();
				setIcon(new ImageIcon(Animation.GetFrames("hero_spritesheet.png",3,7,8,5)[1]));
				Jump(-45,750, Main.player, 10);
			}
		}
	}
	public class FallAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {

		}
	}
	public class ShootAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
	
			if(!InTheProcessOfShooting) {
			Projectile.ShootProjectile(20, new Projectile());
			InTheProcessOfShooting=true;
				}
			if(InAnimation && !freefalling) {
			Animation.AnimationTimer.stop();
			anime =new Animation(60, Main.player, Type_Of_Animation.CROUCHING);
			Main.player.setIcon(new ImageIcon(Animation.GetFrames("hero_spritesheet.png",4,2,8,5)[0]));
			InAnimation=false;
				}
			}
		}
	public class ShootAgain extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
		
			InTheProcessOfShooting=false;
			InAnimation=true;
			Animation.AnimationTimer.stop();
			Main.player.setIcon(new ImageIcon(Animation.GetFrames("hero_spritesheet.png",4,2,8,5)[0]));		
		}
	}
	
	public void UpdatePlayer() {
		setLocation(getX()+velX, getY());
		setLocation(getX(),getY()+velY);
		if(getX()>Main.WIDTH-100) {
			SetVelX(0);
		}
		if(getX()<0) {
			setLocation(0, this.getY());
			SetVelX(0);
		}
		if(Cursor_Xpos>this.getX()+this.getWidth()/2) {
			direction=DirectionOfPlayer.FACING_RIGHT;
		} else {
			direction=DirectionOfPlayer.FACING_LEFT;
		}
	}
	
	public void SetVelX(int X) {velX=X;}
	public void SetVelY(int Y) {velY=Y;}
	
	public void actionPerformed(ActionEvent arg0) {
		UpdatePlayer();
		Spawntick++;
		if(Spawntick % 25 == 0 && Main.Running==true) {
			Main.Renderer.add(new Enemy(6,100));
		}
	}
	
	public void Jump(int JumpVel, int DurationOfJump, Object entity, int RefreshRate)  {
		freefalling=true;
		JumpTimer = new Timer(RefreshRate,new ActionListener(){
			int i=0;
			Boolean fallingdown=false;
			public void actionPerformed(ActionEvent e) {
				if(!fallingdown) {
					((JComponent) entity).setLocation(((JComponent) entity).getX(), ((JComponent) entity).getY()+(int)EasingOutFunction(JumpVel,DurationOfJump,i));
					i+=10;
				} else {
					((JComponent) entity).setLocation(((JComponent) entity).getX(),((JComponent) entity).getY()-(int)EasingOutFunction(JumpVel,DurationOfJump,i));
					i-=10;
				}
				if((int)EasingOutFunction(JumpVel,DurationOfJump,i)==0) {
					fallingdown=true;
				}
				//this (564) value must be smaller than initial Y value at game start
				if(((JComponent) entity).getY()>750) {
					freefalling=false;
					JumpTimer.stop();
					Animation.AnimationTimer.restart();
				}
				System.out.println("Y Pos:" + ((JComponent) entity).getY());
			}	
		});
		JumpTimer.start();
	}
	//TimeElapsed should be in milliseconds
	public static double EasingOutFunction(double InitialVelocity, double DurationOfAnimation, int TimeElapsed_Timer) {
		return (InitialVelocity/(Math.pow(DurationOfAnimation/2,2)))*Math.pow(TimeElapsed_Timer-(DurationOfAnimation/2),2);	
	}
	
	public void mouseDragged(MouseEvent arg0) {}
	
	public void mouseMoved(MouseEvent e) {
		Cursor_Xpos=e.getX();
	}
}
