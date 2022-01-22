package shooter_game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Enemy extends JLabel  {

	private static final long serialVersionUID = 1L;
	public static Timer EnemyTimer, EnemyMovementTimer;
	public static ArrayList <Enemy> TotalEnemies= new ArrayList<Enemy>();
	int Buffer;

	public Enemy(int SpeedOfEnemy, int EnemySpacing) {
		setSize(100,80);
		setIcon(new ImageIcon(SpriteRenderer.GetScaledImage("Enemy.png", this)));
		setVisible(true);
		TotalEnemies.add(this);
		
		switch(new Random().nextInt(2)) {
		case 0:
			setLocation(-120, new Random().nextInt((int)(800/EnemySpacing))*EnemySpacing);
			StartEnemyTimer(SpeedOfEnemy, 1, this);
			break;
		case 1:
			setLocation(Main.WIDTH, new Random().nextInt((int)(800/EnemySpacing))*EnemySpacing);
			StartEnemyTimer(SpeedOfEnemy, -1, this);
			break;
		}
	}
	
	private void StartEnemyTimer(int SpeedOfEnemy, int Direction, Enemy enemy) {
			Timer timer = new Timer(10, new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {		
					if(Main.Running) {
					enemy.setLocation(getX()+Direction*SpeedOfEnemy, getY());
					if(enemy.getX()<-120 || enemy.getX()>Main.WIDTH) {
						TotalEnemies.remove(enemy);
						Main.Renderer.remove(enemy);
						return;
					} else if(enemy.getBounds().intersects(Main.player.getBounds())) {	
						System.out.println("HitBox Collision");						
						}
					}
				}
			});
			timer.start();
	}
}
