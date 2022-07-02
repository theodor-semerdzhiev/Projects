package chess_game;

import java.awt.Image;
import chess_game.Player.PlayerColor;

public class Bishop extends Chess_Pieces {

	private int[] InitialPosition;
	private PlayerColor color;
	private int[] CurrentPosition;
	private Type_of_Piece type;
	private boolean isMoved;
	
	public Bishop(PlayerColor color, Board board, int row, int column) {
		super(color, board, row, column);
		setInitialPosition(row, column);
		setPosition(InitialPosition[0], InitialPosition[1]);
		board.getBoard()[getInitialPosition()[1]][getInitialPosition()[0]]=this;
		setColor(color);
		setType(Type_of_Piece.BISHOP);
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
			return SpriteRenderer.Sprites.get(8);
		case WHITE:
			return SpriteRenderer.Sprites.get(2);
		}
		return null;
	}

	@Override
	void setInitialPosition(int row, int column) {
		InitialPosition = new int[] {row,column};
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
		} else if(Math.abs(column-getPosition()[0])==Math.abs(row-getPosition()[1])) {
			for(int i=(int) Math.signum(column-getPosition()[0]), j=(int) Math.signum(row-getPosition()[1]); Math.abs(i)<Math.abs(column-getPosition()[0]) && Math.abs(j)<Math.abs(row-getPosition()[1]); i+=Math.signum(column-getPosition()[0]), j+=Math.signum(row-getPosition()[1])) {
				if(board.getBoard()[getPosition()[1]+j][getPosition()[0]+i]!=null) {
					return false;
				}
			}
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
		this.type= type;
	}

	@Override
	boolean isMoved() {
		// TODO Auto-generated method stub
		return isMoved;
	}

	@Override
	void setMovedStatus(boolean isMoved) {
		this.isMoved=isMoved;
	}
}
