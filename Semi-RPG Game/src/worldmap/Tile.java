package worldmap;

public class Tile {

	public enum Terrain {
		GRASS, DIRT, ROCK, SAND, WATER;
	}
	private Terrain terrain;
	private boolean walkable;
	private boolean mineable;
	private float PerlinValue;
	private boolean isInitilized;
	private boolean Rendered;
	private int [] RelativePosition;
	private int RelativeOffsetX;
	private int RelativeOffsetY;

	public Tile(Node node) {
		RelativeOffsetX=0;
		RelativeOffsetY=0;
		PerlinValue=0f;
		isInitilized = false;
		RelativePosition = new int[2];
		//subject to change
		Rendered=true;
	}
	
	public void SetInitialTerrainType(float PerlinVal) {
		
		if(PerlinVal >= -1f && PerlinVal <= 0f) {
			terrain=Terrain.WATER;
		} else if(PerlinVal >= 0f && PerlinVal <= 0.25f) {
			terrain=Terrain.SAND;
		} else if(PerlinVal >= 0.25f && PerlinVal <= 0.9f) {
			terrain=Terrain.GRASS;
		} else if(PerlinVal >= 0.9f && PerlinVal <= 1f) {
			terrain=Terrain.ROCK;
		}
	}
	

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public boolean isMineable() {
		return mineable;
	}

	public void setMineable(boolean mineable) {
		this.mineable = mineable;
	}

	public float getPerlinValue() {
		return PerlinValue;
	}

	public void setPerlinValue(float perlinValue) {
		PerlinValue = perlinValue;
	}

	public boolean isInitilized() {
		return isInitilized;
	}

	public void setInitilized(boolean isInitilized) {
		this.isInitilized = isInitilized;
	}

	public boolean isRendered() {
		return Rendered;
	}

	public void setRendered(boolean rendered) {
		Rendered = rendered;
	}

	public int [] getRelativePosition() {
		return RelativePosition;
	}

	public void setRelativePosition(int row, int column) {
		RelativePosition[0] = row;
		RelativePosition[1] = column;
	}

	public int getRelativeOffsetX() {
		return RelativeOffsetX;
	}

	public void setRelativeOffsetX(int relativeOffset) {
		RelativeOffsetX = relativeOffset;
	}
	
	public int getRelativeOffsetY() {
		return RelativeOffsetY;
	}

	public void setRelativeOffsetY(int relativeOffset) {
		RelativeOffsetY = relativeOffset;
	}
}
