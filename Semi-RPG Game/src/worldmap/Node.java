package worldmap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import main.Main;

public class Node {

private Tile[][] NodeArray;
Thread RenderThread;

	public Node(int sizeModifier) {
		Random random = new Random();
		
		NodeArray=new Tile[(int) (Math.pow(2, sizeModifier)+1)][(int) (Math.pow(2, sizeModifier)+1)];
		for(int i = 0; i < NodeArray.length; i++) {
			for(int j = 0; j < NodeArray[i].length; j++) {
				NodeArray[i][j]= new Tile(this);
				NodeArray[i][j].setRelativePosition(i,j);
			}
		}
		CreatePerlinMap(NodeArray, random);
		
		
		for(int i=0; i < NodeArray.length; i++) {
			for(int j=0; j < NodeArray[i].length; j++) {
				NodeArray[i][j].SetInitialTerrainType(NodeArray[i][j].getPerlinValue());
				System.out.print(NodeArray[i][j].getPerlinValue()+", ");
				
			}
			System.out.print("\n");
		}
		RenderThread = new Thread(new RenderThread());
		RenderThread.start();
	}

	public void CreatePerlinMap(Tile [][] Array, Random rand) {
		/* Works on arrays that have (2^n + 1) x (2^n + 1) dimensions 
		 * repeats this loop until all indexes of [][]Array are not equal to 1.0 (default value) 
		 * All values of assigned to Array will be between -1 and 1 */
		Array[0][0].setPerlinValue(-1 + rand.nextFloat());
		Array[0][0].setInitilized(true);
		
		Array[0][Array[0].length - 1].setPerlinValue(-1 + rand.nextFloat());
		Array[0][Array[0].length - 1].setInitilized(true);
		
		Array[Array.length-1][0].setPerlinValue(-1 + rand.nextFloat());
		Array[Array.length-1][0].setInitilized(true);
		
		Array[Array.length-1][Array[0].length-1].setPerlinValue(-1 + rand.nextFloat());
		Array[Array.length-1][Array[0].length-1].setInitilized(true);
		
		for(int i = 0; !MapIsFull(Array); i++) {
			int Offset=(int)((Array.length-1) / Math.pow(2,i+1));
			//Square Step
			for(int k=0; k<Math.pow(2, i); k++) {
				for(int j=0; j<Math.pow(2, i); j++) {
					Array[Offset + (Offset*(k)*2)][Offset + (Offset*(j)*2)].setPerlinValue(getFloatValue_SquareStep(Array, Offset, (Offset + (Offset*k*2)),(Offset + (Offset*j*2)), rand));
				}
			}
			if(MapIsFull(Array)) {			
				return;
			}
			//Diamond Step
			for(int k=0; k<Math.pow(2, i); k++) {
				for(int j=0; j < Math.pow(2, i); j++) {	
					Array[Offset*(k)*2][(Offset + (Offset*(j)*2))].setPerlinValue(getFloatValue_DiamondStep(Array, Offset, Offset*k*2,(Offset + (Offset*j*2)), rand));
					Array[(Offset + (Offset*(k)*2))+Offset][(Offset + (Offset*(j)*2))].setPerlinValue(getFloatValue_DiamondStep(Array, Offset, (Offset + (Offset*k*2))+Offset,(Offset + (Offset*j*2)), rand));
					Array[(Offset + (Offset*(k)*2))][Offset*(j)*2].setPerlinValue(getFloatValue_DiamondStep(Array, Offset, (Offset + (Offset*k*2)),Offset*j*2, rand));
					Array[(Offset + (Offset*(k)*2))][(Offset + (Offset*(j)*2))+Offset].setPerlinValue(getFloatValue_DiamondStep(Array, Offset, (Offset + (Offset*k*2)),(Offset + (Offset*j*2))+Offset, rand));	
				}
			}
		}
	}
	
	
	private float getFloatValue_DiamondStep(Tile [][] Array, int Offset, int PositionY, int PositionX, Random rand) {
		float sum=0;
		int num=0;
		try {
			sum+=Array[PositionY-Offset][PositionX].getPerlinValue();
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY+Offset][PositionX].getPerlinValue();
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY][PositionX+Offset].getPerlinValue();
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY][PositionX-Offset].getPerlinValue();
			num++;
		}catch(Exception e) {}
		
		Array[PositionY][PositionX].setInitilized(true);
			
		return (sum/num)+rand.nextFloat()*(1-Math.abs((sum/num)));		
	}

	private float getFloatValue_SquareStep(Tile [][] Array, int Offset, int PositionY, int PositionX, Random rand) {
		float sum=0;
		int num=0;
		try {
			sum+=Array[PositionY-Offset][PositionX-Offset].getPerlinValue();
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY+Offset][PositionX+Offset].getPerlinValue();
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY-Offset][PositionX+Offset].getPerlinValue();
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY+Offset][PositionX-Offset].getPerlinValue();
			num++;
		}catch(Exception e) {}
		
		Array[PositionY][PositionX].setInitilized(true);
		
		return (sum/num)+rand.nextFloat()*(1-Math.abs((sum/num)));
	}

	//This method checks if the map as been filled
	public boolean MapIsFull(Tile [][]Array) {
		for(int i=0; i<Array.length; i++) {
			for(int j=0; j<Array[0].length; j++) {
				if (Array[i][j].isInitilized()==false) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Tile[][] getTileArray(){
		return NodeArray;
		
	}
	
	public class RenderThread extends Thread {
		Timer timer;
		public void run() {
			timer =new Timer(250,new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int i = 0; i < NodeArray.length; i++) {
						for(int j=0; j < NodeArray.length; j++) {
							if(i*World.TerrainSprites.get(0).getWidth(null) > Main.frame.getHeight()) {
								NodeArray[i][j].setRendered(false);
							} else if (j*World.TerrainSprites.get(0).getHeight(null) > Main.frame.getWidth()){
								NodeArray[i][j].setRendered(false);
							} else {
								NodeArray[i][j].setRendered(true);
							}
						}
					}
				}
			});
			timer.start();
		}
	}
}
