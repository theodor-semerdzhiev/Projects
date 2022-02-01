package world_generator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MapGenerator {

	private Rectangle[][] tilemap;
	private TopoMap topomap;
	private Random rand;
	
	public MapGenerator(TopoMap topomap) {
	
		this.topomap = topomap;
		rand= new Random();
		tilemap= new Rectangle [topomap.getTopoMapHeight()][topomap.getTopoMapWidth()];
		for(int i=0; i<topomap.getTopoMapHeight(); i++) {
			for(int j=0; j<topomap.getTopoMapWidth(); j++) {
				tilemap[i][j]= new Rectangle(MapGeneratorFrame.WIDTH/topomap.getTopoMapWidth(),  MapGeneratorFrame.HEIGHT/topomap.getTopoMapHeight());
			}
		}			
	}
	public void paintFinalMap(Graphics g) {
		for(int i=0; i<topomap.GetTopoMapArray().length; i++) {
			for(int j=0; j<topomap.GetTopoMapArray()[0].length; j++) {
				if(topomap.GetTopoMapArray()[i][j]>topomap.getSeaElevation()) {
					g.setColor(new Color(0,(int) Math.floor(255 * (topomap.GetTopoMapArray()[i][j]/topomap.getMaxElevation())), 0));
					g.fillRect((int)tilemap[i][j].getWidth()*i, 
							(int)tilemap[i][j].getHeight()*j, 
							(int)tilemap[i][j].getWidth(), 
							(int)tilemap[i][j].getHeight());
					continue;
				} else if(topomap.GetTopoMapArray()[i][j]<topomap.getSeaElevation()) {
					g.setColor(new Color( 102 , (int) Math.floor(178 * (topomap.GetTopoMapArray()[i][j]/ topomap.getSeaElevation()))  ,200));
					g.fillRect((int)tilemap[i][j].getWidth()*i, 
							(int)tilemap[i][j].getHeight()*j, 
							(int)tilemap[i][j].getWidth(), 
							(int)tilemap[i][j].getHeight());
					continue;
				} 

			}
		}
	}
	public Rectangle[][] getTileMap(){
		return tilemap;
	}
}
