package chess_game;

import java.awt.Image;
import chess_game.Player.PlayerColor;

public class King extends Chess_Pieces {

	private int[] InitialPosition;
	private PlayerColor color;
	private int[] CurrentPosition;
	private Type_of_Piece type;
	private boolean isMoved;
	private Board board;
	
	public King(PlayerColor color, Board board,  int row, int column) {
		super(color, board, row, column);
		setInitialPosition(row, column);
		setPosition(InitialPosition[0], InitialPosition[1]);
		board.getBoard()[getInitialPosition()[1]][getInitialPosition()[0]]=this;
		setColor(color);
		setType(Type_of_Piece.PAWN);
		isMoved=false;
		this.board=board;
	}

	@Override
	boolean isPermanent() {
		return true;
	}
	
	boolean isChecked() {
		return false;
	}
	
	boolean IsMoved() {
		return isMoved;
	}
	
	public void SetMovedStatus(boolean isMoved) {
		this.isMoved=isMoved;
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
	boolean isLegalMoves(Board board, int row, int column) {
		if(board.getBoard()[row][column]!=null && board.getBoard()[row][column].getColor()==color) {
			return false;
		} else if(Math.abs(getPosition()[1]-row)==1 && Math.abs(getPosition()[0]-column)==0){
			return true;
		} else if(Math.abs(getPosition()[0]-column)==1 && Math.abs(getPosition()[1]-row)==0) {
			return true;
		} else if(Math.abs(getPosition()[0]-column)==1 && Math.abs(getPosition()[1]-row)==1) {
			return true;
		} else if(getColor() == PlayerColor.BLACK) {
			if(row==6 && column==0 && !isMoved && board.getBoard()[7][0].getType()==Type_of_Piece.ROOK && !board.getBoard()[7][0].isMoved() && board.getBoard()[6][0]==null && board.getBoard()[5][0]==null) {
				board.getBoard()[5][0]=board.getBoard()[7][0];
				board.getBoard()[5][0].setPosition(0, 5);
				board.getBoard()[5][0].setMovedStatus(true);
				board.getBoard()[7][0]=null;
				return true;
			} else if(row==2 && column==0 && !isMoved && board.getBoard()[0][0].getType()==Type_of_Piece.ROOK && !board.getBoard()[0][0].isMoved() && board.getBoard()[1][0]==null && board.getBoard()[2][0]==null && board.getBoard()[3][0]==null) {
				board.getBoard()[3][0]=board.getBoard()[0][0];
				board.getBoard()[3][0].setPosition(0, 3);
				board.getBoard()[3][0].setMovedStatus(true);
				board.getBoard()[0][0]=null;
				return true;
			}
		} else if(getColor() == PlayerColor.WHITE){
			if(row==6 && column==7 && !isMoved && board.getBoard()[7][7].getType()==Type_of_Piece.ROOK && !board.getBoard()[7][7].isMoved() && board.getBoard()[6][7]==null && board.getBoard()[5][7]==null) {
				board.getBoard()[5][7]=board.getBoard()[7][7];
				board.getBoard()[5][7].setPosition(7, 5);
				board.getBoard()[5][7].setMovedStatus(true);
				board.getBoard()[7][7]=null;
				return true;
			} else if(row==2 && column==7 && !isMoved && board.getBoard()[0][7].getType()==Type_of_Piece.ROOK && !board.getBoard()[0][7].isMoved() && board.getBoard()[1][7]==null && board.getBoard()[2][7]==null && board.getBoard()[3][7]==null) {
				board.getBoard()[3][7]=board.getBoard()[0][7];
				board.getBoard()[3][7].setPosition(7, 3);
				board.getBoard()[3][7].setMovedStatus(true);
				board.getBoard()[0][7]=null;
				return true;
			}
		} else {
			return false;
		}
		return null!=null;
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
	boolean isMoved() { 
		return isMoved;
	}

	@Override
	void setMovedStatus(boolean isMoved) {
		this.isMoved=isMoved;
	}
}
