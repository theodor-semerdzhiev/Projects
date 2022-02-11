package chess_game;

import java.awt.Image;

import chess_game.Player.PlayerColor;

public class Rook extends Chess_Pieces{

	private int[] InitialPosition;
	private PlayerColor color;
	private int[] CurrentPosition;
	private Type_of_Piece type;
	
	public Rook(PlayerColor color, Board board, int row, int column) {
		super(color, board, row, column);
		setInitialPosition(row, column);
		setPosition(InitialPosition[0], InitialPosition[1]);
		board.getBoard()[getInitialPosition()[1]][getInitialPosition()[0]]=this;
		setColor(color);
		setType(Type_of_Piece.ROOK);
	}
	@Override
	boolean isPermanent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void CaptureMovement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	Image getSprite(PlayerColor color) {
		switch(color) {
		case BLACK:
			return SpriteRenderer.Sprites.get(10);
		case WHITE:
			return SpriteRenderer.Sprites.get(4);
		}
		return null;
	}

	@Override
	int[] getInitialPosition() {
		return InitialPosition;
	}

	@Override
	void setInitialPosition(int row, int column) {
		InitialPosition=new int[] {row, column};
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
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	int[] getPosition() {
		return CurrentPosition;
	}

	@Override
	void setPosition(int row, int column) {
		CurrentPosition= new int[] {row, column};
	}

	@Override
	Type_of_Piece getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	void setType(Type_of_Piece type) {
		this.type= type;
		
	}
	@Override
	boolean isChecking(Board board, int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
}
