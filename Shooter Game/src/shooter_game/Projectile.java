package shooter_game;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Projectile extends JLabel  {

	private static final long serialVersionUID = 1L;
	static BufferedImage BulletSprite;
	static Image FinalBulletSprite;
	static ArrayList <Projectile> MaxBulletInMotion = new ArrayList <Projectile>();

	public Projectile() {
		
		new JLabel();
		setSize(100, 50);
		setVisible(true);	
	}
	public static void ShootProjectile(int SpeedOfProjectile, Projectile Bullet) {

		if(Main.Running) {
		Bullet.setLocation(Main.player.getX(), Main.player.getY());
		Main.Renderer.add(Bullet);
		MaxBulletInMotion.add(Bullet);
		switch(Player.direction) {
		case FACING_LEFT:
			System.out.println("Shooting left");
			Bullet.setIcon(new ImageIcon(SpriteRenderer.GetScaledImage("Fireball_FACING_LEFT.png", Bullet)));
			StartEntityTimer(SpeedOfProjectile, -1, Bullet);
			break;
		case FACING_RIGHT: 
			System.out.println("Shooting Right");
			Bullet.setIcon(new ImageIcon(SpriteRenderer.GetScaledImage("Fireball_FACING_RIGHT.png", Bullet)));
			StartEntityTimer(SpeedOfProjectile, 1, Bullet);
			break;
		case NOT_DEFINED:
			Main.Renderer.remove(Bullet);
			return;
			}
		}
	}
	private static void StartEntityTimer(int SpeedOfProjectile, int Direction, JLabel Bullet) {
		Timer timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.Running) {
				if(Bullet.getLocation().x<-120 || Bullet.getLocation().y>Main.WIDTH) {
					Bullet.setVisible(false);
					return;
				} else {
					Bullet.setLocation(Bullet.getX()+Direction*SpeedOfProjectile, Bullet.getY());
				}
				for(int j=0; j<MaxBulletInMotion.size();j++) {
					for(int i=0; i<Enemy.TotalEnemies.size();i++) {
						if(MaxBulletInMotion.get(j).getBounds().intersects(Enemy.TotalEnemies.get(i).getBounds())) {
						MaxBulletInMotion.get(j).setVisible(false);
						MaxBulletInMotion.remove(j);
						Enemy.TotalEnemies.get(i).setVisible(false);
						Enemy.TotalEnemies.remove(i);
						GameLevel.score++;
						break;
							}
						}
					}
				}
			}
		});
		timer.start();
	}
}
