package world_generator;

import java.text.DecimalFormat;
import java.util.Random;

public class TopoMap {

	private float[][] TopoArray;
	private float MaxElevation;
	private float SeaElevation;
	private float SteepModifier;
	private int SizeModifier;
	private DecimalFormat df;
	

	public TopoMap(int SizeModifier, float MaxElevation, float SeaElevation) {
		this.MaxElevation = MaxElevation;
		this.SeaElevation = SeaElevation;
		this.SizeModifier = SizeModifier;

		TopoArray = GenerateTopoMap(this.SizeModifier, this.MaxElevation, this.SeaElevation);
	}

	public float[][] GenerateTopoMap(int SizeModifier, float MaxElevation, float MinElevation) {
		float[][] topomap = new float[(int) (Math.pow(2, SizeModifier)+1)][(int) (Math.pow(2, SizeModifier)+1)];
		Random rand = new Random();
		
		createElevationGradient(topomap,rand);
		RemoveSinglePool(topomap, rand);
		RemoveSingleIslands(topomap, rand);
		return topomap;
	}

	private void createElevationGradient(float[][] Array, Random rand) {
		Console.display.append("Loading Topo Array...\n");
		setCorners(Array ,rand);
		for(int i = 0; !MapIsFull(Array); i++) {
			int Offset=(int)((Array.length-1) / Math.pow(2,i+1));
			//Square Step
			for(int k=0; k<Math.pow(2, i); k++) {
				for(int j=0; j<Math.pow(2, i); j++) {
					Array[Offset + (Offset*(k)*2)][Offset + (Offset*(j)*2)]= getFloatValue_SquareStep(Array, Offset, (Offset + (Offset*k*2)),(Offset + (Offset*j*2)), rand);
				}
			}
			if(MapIsFull(Array)) {			
				return;
			}
			//Diamond Step
			for(int k=0; k<Math.pow(2, i); k++) {
				for(int j=0; j < Math.pow(2, i); j++) {	
					Array[Offset*(k)*2][(Offset + (Offset*(j)*2))]= getFloatValue_DiamondStep(Array, Offset, Offset*k*2,(Offset + (Offset*j*2)), rand);
					Array[(Offset + (Offset*(k)*2))+Offset][(Offset + (Offset*(j)*2))]= getFloatValue_DiamondStep(Array, Offset, (Offset + (Offset*k*2))+Offset,(Offset + (Offset*j*2)), rand);
					Array[(Offset + (Offset*(k)*2))][Offset*(j)*2]= getFloatValue_DiamondStep(Array, Offset, (Offset + (Offset*k*2)),Offset*j*2, rand);
					Array[(Offset + (Offset*(k)*2))][(Offset + (Offset*(j)*2))+Offset]= getFloatValue_DiamondStep(Array, Offset, (Offset + (Offset*k*2)),(Offset + (Offset*j*2))+Offset, rand);	
				}
			}
		}
	}

	private void setCorners(float [][] Array, Random rand) {
		Array[0][0]=SeaElevation-rand.nextFloat()*(MaxElevation-SeaElevation);
		Array[0][Array[0].length-1]=rand.nextFloat()*(SeaElevation);
		Array[Array.length-1][0]=rand.nextFloat()*(SeaElevation);
		Array[Array.length-1][Array[0].length-1]=SeaElevation-rand.nextFloat()*(MaxElevation-SeaElevation);	
	}
	
	private void RemoveSingleIslands(float [][] Array, Random rand) {
		Console.display.append("Removing Islands...\n");
		for(int i=0; i < Array.length; i++) {
			for(int j=0; j< Array[0].length; j++) {
				if(Array[i][j]>	SeaElevation && isSingleIslands(Array, i, j, SeaElevation)) {
					Array[i][j]=getFloatValue_DiamondStep(Array, 1, i ,j, rand);
				}
			}
		}
	}
	
	private void RemoveSinglePool(float [][] Array, Random rand) {
		Console.display.append("Removing Single Lakes...\n");
		for(int i=0; i < Array.length; i++) {
			for(int j=0; j< Array[0].length; j++) {
				if(Array[i][j] < SeaElevation && isSinglePool(Array, i, j, SeaElevation)) {
					Array[i][j]= (getFloatValue_DiamondStep(Array, 1 , i, j, rand)+getFloatValue_SquareStep(Array,1, i ,j ,rand)) / 2;
				}
			}
		}
	}
	
	private float getFloatValue_DiamondStep(float [][] Array, int Offset, int PositionY, int PositionX, Random rand) {
		float sum=0;
		int num=0;
		try {
			sum+=Array[PositionY-Offset][PositionX];
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY+Offset][PositionX];
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY][PositionX+Offset];
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY][PositionX-Offset];
			num++;
		}catch(Exception e) {}
			
		return (sum/num)+rand.nextFloat()*((MaxElevation-(sum/num))/7);		
	}

	private float getFloatValue_SquareStep(float [][] Array, int Offset, int PositionY, int PositionX, Random rand) {

		float sum=0;
		int num=0;
		try {
			sum+=Array[PositionY-Offset][PositionX-Offset];
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY+Offset][PositionX+Offset];
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY-Offset][PositionX+Offset];
			num++;
		}catch(Exception e) {}
		try {
			sum+=Array[PositionY+Offset][PositionX-Offset];
			num++;
		}catch(Exception e) {}
		return (sum/num)+rand.nextFloat()*((MaxElevation-(sum/num))/7);
	}

	//This method checks if the map as been filled
	public boolean MapIsFull(float [][]Array) {
		for(int i=0; i<Array.length; i++) {
			for(int j=0; j<Array[0].length; j++) {
				if (Array[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isSingleIslands(float [][]Array, int HeightIndex, int WidthIndex, float SeaElevation) {
		try {
			if (Array[HeightIndex-1][WidthIndex]<SeaElevation && 
				Array[HeightIndex+1][WidthIndex]<SeaElevation && 
				Array[HeightIndex][WidthIndex-1]<SeaElevation &&
				Array[HeightIndex][WidthIndex+1]<SeaElevation) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean isSinglePool(float [][]Array, int HeightIndex, int WidthIndex, float SeaElevation) {
		try {
			if (Array[HeightIndex-1][WidthIndex]>SeaElevation && 
				Array[HeightIndex+1][WidthIndex]>SeaElevation && 
				Array[HeightIndex][WidthIndex-1]>SeaElevation &&
				Array[HeightIndex][WidthIndex+1]>SeaElevation &&
				Array[HeightIndex-1][WidthIndex+1]>SeaElevation &&
				Array[HeightIndex+1][WidthIndex-1]>SeaElevation &&
				Array[HeightIndex-1][WidthIndex-1]>SeaElevation &&
				Array[HeightIndex+1][WidthIndex+1]>SeaElevation) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}

	public void DisplayTopoMapInfo() {
		df = new DecimalFormat("0.00");
		Console.display.append("Topography Map Info:");
			Console.display.append("\n");
			for(int i=0; i<TopoArray.length; i++) {
				for(int j=0; j<TopoArray[0].length; j++) {
					Console.display.append(df.format(TopoArray[j][i]) + "     ");
				}
				Console.display.append("\n");
		}
	}
	
	public int getSizeModifier() {
		return SizeModifier;
	}
	public void setSizeModifier(int SizeModifier) {
		this.SizeModifier=SizeModifier;
	}
	public float [][] GetTopoMapArray(){
		return TopoArray;
	}
	public float getSteepModifier() {
		return SteepModifier;
	}
	public void setSteepModifier(float SteepModifier) {
		this.SteepModifier= SteepModifier;
	}
	public int getTopoMapHeight() {
		return TopoArray.length;	
	}
	public int getTopoMapWidth() {
		return TopoArray[0].length;
	}
	public float getSeaElevation() {
		return SeaElevation;
	}
	public void setSeaElevation(float SeaElevation) {
		this.SeaElevation=SeaElevation;
	}
	public float getMaxElevation() {
		return MaxElevation;
	}
	public void setMaxElevation(float MaxElevation) {
		this.MaxElevation=MaxElevation;
	}
}
