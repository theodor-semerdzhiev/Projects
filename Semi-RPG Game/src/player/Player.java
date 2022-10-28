package player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import worldmap.World;

public class Player extends JComponent implements Runnable {
	

private static final long serialVersionUID = 1L;
private int []GlobalPosition;
private int PosY;
private int PosX;
private int VelX;
private int VelY;
private Thread PlayerUpdate;
private Timer UpdateTimer;

	public Player(World world) {
		GlobalPosition=(new int[2]);

		PosY=0;
		PosX=0;
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "MoveLeftAction");
		getActionMap().put("MoveLeftAction", new MoveLeftAction());
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "HaltLeftAction");
		getActionMap().put("HaltLeftAction", new HaltLeftAction());
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "MoveRightAction");
		getActionMap().put("MoveRightAction", new MoveRightAction()); 
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "HaltRightAction");
		getActionMap().put("HaltRightAction", new HaltRightAction()); 
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "MoveDownAction");
		getActionMap().put("MoveDownAction", new MoveDownAction());
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "HaltDownAction");
		getActionMap().put("HaltDownAction", new HaltDownAction());
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "MoveUpAction");
		getActionMap().put("MoveUpAction", new MoveUpAction()); 
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "HaltUptAction");
		getActionMap().put("HaltUpAction", new HaltUpAction()); 
		
		PlayerUpdate=new Thread(this);
		PlayerUpdate.start();
	}
	@Override
	public void run() {
		UpdateTimer=new Timer(25, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PosX+=getVelX();
				PosY+=getVelY();
				System.out.print("Hi");
			}
		});
		UpdateTimer.start();
	}
	
	private class MoveDownAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent arg0) {
			setVelY(5);
		}
	}
	private class HaltDownAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent arg0) {
			setVelY(0);
		}
	}
	private class MoveUpAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			setVelY(-5);
		}
	}
	private class HaltUpAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			setVelY(0);
		}
	}
	private class MoveLeftAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			setVelX(-5);
		}
	}
	private class HaltLeftAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			setVelX(0);
		}
	}
	private class MoveRightAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			setVelX(5);
		}
	}	
	private class HaltRightAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			setVelX(0);
		}
	}
	//returns the players position in terms of the WorldArray, basically gives index of noode player is in
	public int [] getGlobalPosition() {
		return GlobalPosition;
	}

	//sets the above
	public void setGlobalPosition(int [] currentPosition) {
		GlobalPosition = currentPosition;
	}

	public int getVelX() {
		return VelX;
	}

	public void setVelX(int velX) {
		this.VelX = velX;
	}

	public int getVelY() {
		return VelY;
	}

	public void setVelY(int velY) {
		this.VelY = velY;
	}

	public int getPosY() {
		return PosY;
	}

	public void setPosY(int posY) {
		PosY = posY;
	}

	public int getPosX() {
		return PosX;
	}

	public void setPosX(int posX) {
		PosX = posX;
	}
}
