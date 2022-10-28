package worldmap;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import main.Main;
import player.Player;
import spriteRenderer.SpriteRenderer;

public class World extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node[][] WorldArray;
	public JPanel WorldScreen;
	static ArrayList<Image> TerrainSprites;
	private Dimension dim;
	private Timer timer;
	private Player player;
	
	public World(int dimension) {
		 
		dim = new Dimension(Main.HEIGHT, Main.WIDTH);
		setSize(dim);
		setVisible(true);
		
		player=new Player(this);
		add(player);
		
		TerrainSprites = SpriteRenderer.Render("SpriteSheets/Pixilart Sprite Sheet (2).png", 1, 5, dim);
		
		WorldArray = new Node[dimension][dimension];
		
		for(int i=0; i < dimension; i++) {
			for(int j=0; j < dimension; j++) {
				WorldArray[i][j]= new Node(6);
			}
		}
		timer = new Timer(100, this);
		timer.start();
	}

	protected void paintComponent(Graphics g) {
		for(int i=0; i < WorldArray[player.getGlobalPosition()[0]][player.getGlobalPosition()[1]].getTileArray().length; i++) {
			for(int j=0; j < WorldArray[player.getGlobalPosition()[0]][player.getGlobalPosition()[1]].getTileArray()[0].length; j++) {
				if(WorldArray[player.getGlobalPosition()[0]][player.getGlobalPosition()[1]].getTileArray()[j][i].isRendered()) {
				switch(WorldArray[player.getGlobalPosition()[0]][player.getGlobalPosition()[1]].getTileArray()[j][i].getTerrain()) {
				case WATER:
					g.drawImage(TerrainSprites.get(3), TerrainSprites.get(3).getWidth(null)*i + player.getPosX(), TerrainSprites.get(3).getHeight(null)*j + player.getPosY(), null);
					break;
				case SAND:
					g.drawImage(TerrainSprites.get(2), TerrainSprites.get(2).getWidth(null)*i + player.getPosX(), TerrainSprites.get(2).getHeight(null)*j + player.getPosY(), null);
					break;
				case GRASS:
					g.drawImage(TerrainSprites.get(0), TerrainSprites.get(0).getWidth(null)*i + player.getPosX(), TerrainSprites.get(0).getHeight(null)*j + player.getPosY(), null);
					break;
				case ROCK:
					g.drawImage(TerrainSprites.get(1), TerrainSprites.get(1).getWidth(null)*i + player.getPosX(), TerrainSprites.get(1).getHeight(null)*j + player.getPosY(), null);
					break;
				default:
					continue;
					}
				} else {
					break;
				}
			}
		}			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
