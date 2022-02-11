package chess_game;

import java.awt.Image;
import chess_game.Player.PlayerColor;

public class Pawn extends Chess_Pieces{

	private int[] InitialPosition;
	private PlayerColor color;
	private int[] CurrentPosition;
	private Type_of_Piece type;
	
	public Pawn(PlayerColor color,  Board board, int row, int column) {
		super(color, board, row, column);
		setInitialPosition(row, column);
		setPosition(InitialPosition[0], InitialPosition[1]);
		board.getBoard()[getInitialPosition()[1]][getInitialPosition()[0]]=this;
		setColor(color);
		setType(Type_of_Piece.PAWN);
	}

	@Override
	void CaptureMovement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean isPermanent() {
		return false;
	}

	@Override
	boolean isLegalMoves(Board board, int column, int row) {
		switch(getColor()) {
		case BLACK:
			if(getPosition()[0]==InitialPosition[0]) {
				if(row - getPosition()[0] ==2  && getPosition()[1]==column && board.getBoard()[column][row-1]==null) {
					return true;
				} else if(row - getPosition()[0] ==1  && getPosition()[1]==column) {
					return true;
				}
			} else if(row - getPosition()[0] ==1 && getPosition()[1]==column) {
				return true;
			} 
			return false;
		case WHITE:
			if(getPosition()[0]==InitialPosition[0]) {
				if(getPosition()[0] - row ==2  && getPosition()[1]==column && board.getBoard()[column][row+1]==null) {
					return true;
				} else if(getPosition()[0] - row ==1  && getPosition()[1]==column) {
					return true;
				}
			} else if(getPosition()[0] - row ==1 && getPosition()[1]==column) {
				return true;			
			}
			return false;
		default:
			return null != null;
		}
	}

	@Override
	Image getSprite(PlayerColor color) {
		switch(color) {
		case BLACK:
			return SpriteRenderer.Sprites.get(11);
		case WHITE:
			return SpriteRenderer.Sprites.get(5);
		}
		return null;
	}

	int[] getInitialPosition() {
		return InitialPosition;
	}

	void setInitialPosition(int row, int column) {
		InitialPosition= new int[] {row, column};
	}

	@Override
	PlayerColor getColor() {
		return color;
	}

	@Override
	void setColor(PlayerColor color) {
		this.color = color;
	}

	@Override
	int[] getPosition() {
		return CurrentPosition;
	}

	@Override
	void setPosition(int row, int column) {
		CurrentPosition=new int[] {row, column};		
	}

	@Override
	Type_of_Piece getType() {
		return type;
	}

	@Override
	void setType(Type_of_Piece type) {
		this.type = type;
	}

	@Override
	boolean isChecking(Board board, int row, int column) {
		
		return false;
	}
}
