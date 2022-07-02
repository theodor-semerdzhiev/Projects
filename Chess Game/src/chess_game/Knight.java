package chess_game;

import java.awt.Image;

import chess_game.Chess_Pieces.Type_of_Piece;
import chess_game.Player.PlayerColor;


public class Knight extends Chess_Pieces {

	private int[] InitialPosition;
	private PlayerColor color;
	private int[] CurrentPosition;
	private Type_of_Piece type;
	private boolean isMoved;
	
	public Knight(PlayerColor color, Board board, int row, int column) {
		super(color, board, row ,column);
		setInitialPosition(row, column);
		setPosition(InitialPosition[0], InitialPosition[1]);
		board.getBoard()[getInitialPosition()[1]][getInitialPosition()[0]]=this;
		setColor(color);
		setType(Type_of_Piece.KNIGHT);
		isMoved=false;
	}

	@Override
	boolean isPermanent() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	Image getSprite(PlayerColor color) {
		switch(color) {
		case BLACK:
			return SpriteRenderer.Sprites.get(9);
		case WHITE:
			return SpriteRenderer.Sprites.get(3);
		}
		return null;
	}

	@Override
	void setInitialPosition(int row, int column) {
		InitialPosition=new int[] {row, column};
	}

	@Override
	int[] getInitialPosition() {
		return InitialPosition;
	}

	@Override
	PlayerColor getColor() {
		return color;
	}

	@Override
	void setColor(PlayerColor color) {
		this.color=color;
	}

	@Override
	boolean isLegalMoves(Board board, int row, int column) {
		if(board.getBoard()[row][column]!=null && board.getBoard()[row][column].getColor()==color) {
			return false;
		} else if(Math.abs(getPosition()[0]-column)==2 && Math.abs(getPosition()[1]-row)==1) {
			return true;
		} else if(Math.abs(getPosition()[0]-column)==1 && Math.abs(getPosition()[1]-row)==2) {
			return true;
		} else {
		return false;
		}
	}

	@Override
	int[] getPosition() {
		return CurrentPosition;
	}

	@Override
	void setPosition(int row, int column) {
		CurrentPosition = new int[] {row, column};
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
	boolean isMoved() {
		return isMoved;
	}

	@Override
	void setMovedStatus(boolean isMoved) {
		this.isMoved=isMoved;	
	}
}
