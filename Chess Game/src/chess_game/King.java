package chess_game;

import java.awt.Image;
import chess_game.Player.PlayerColor;

public class King extends Chess_Pieces {

	private int[] InitialPosition;
	private PlayerColor color;
	private int[] CurrentPosition;
	private Type_of_Piece type;
	
	public King(PlayerColor color, Board board,  int row, int column) {
		super(color, board, row, column);
		setInitialPosition(row, column);
		setPosition(InitialPosition[0], InitialPosition[1]);
		board.getBoard()[getInitialPosition()[1]][getInitialPosition()[0]]=this;
		setColor(color);
		setType(Type_of_Piece.PAWN);
	}

	@Override
	boolean isPermanent() {
		return true;
	}

	@Override
	void CaptureMovement() {
		// TODO Auto-generated method stub
		
	}
	
	boolean isChecked() {
		return false;
	}

	@Override
	Image getSprite(PlayerColor color) {
		switch(color) {
		case BLACK:
			return SpriteRenderer.Sprites.get(6);
		case WHITE:
			return SpriteRenderer.Sprites.get(0);
		}
		return null;
	}

	@Override
	void setInitialPosition(int row, int column) {
		InitialPosition= new int[] {row, column};
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
	boolean isLegalMoves(PlayerColor color, Board board, int[] CurrentPosition, int row, int column) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void setColor(PlayerColor color) {
		this.color=color;
		
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
		this.type=type;
	}

	@Override
	boolean isChecking(boolean isPermanent) {
		// TODO Auto-generated method stub
		return false;
	}
}
