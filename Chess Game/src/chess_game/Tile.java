package chess_game;

import java.awt.Rectangle;

public class Tile extends Rectangle {

	private static final long serialVersionUID = 1L;
	private boolean isFocused;
	private boolean isHighlighted;

	public Tile(int x, int y, int width, int height) {
		isFocused =false;
		setBounds(x,y,width,height);
	}
	
	public boolean getFocusState() {
		return isFocused;
	}
	public void setFocusState(boolean isFocused) {
		this.isFocused = isFocused;
	}
	public boolean getHighLightState() {
		return isHighlighted;
	}
	public void setHighLightState(boolean isHighlighted) {
		this.isHighlighted = isHighlighted;
	}
}
