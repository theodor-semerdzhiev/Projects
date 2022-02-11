package chess_game;


public class Player {

	public boolean Castled_Queen_Side;
	public enum PlayerColor{
		BLACK, WHITE
	}
	private boolean isTurn;
	private PlayerColor color;
	
	public Player(PlayerColor color, Board board) {
		switch(color) {
		case WHITE:
			new Pawn(color, board, 6,0);
			new Pawn(color, board, 6,1);
			new Pawn(color, board, 6,2);
			new Pawn(color, board, 6,3);
			new Pawn(color, board, 6,4);
			new Pawn(color, board, 6,5);
			new Pawn(color, board, 6,6);
			new Pawn(color, board, 6,7);
			new Rook(color, board, 7,0);
			new Rook(color, board, 7,7);
			new Bishop(color, board, 7,5);
			new Bishop(color, board, 7,2);
			new Knight(color, board, 7,1);
			new Knight(color, board, 7,6);
			new Queen(color, board, 7,3);
			new King(color, board, 7,4);
			this.color = color;
			isTurn=true;
			break;
		case BLACK:
			new Pawn(color, board, 1,0);
			new Pawn(color, board, 1,1);
			new Pawn(color, board, 1,2);
			new Pawn(color, board, 1,3);
			new Pawn(color, board, 1,4);
			new Pawn(color, board, 1,5);
			new Pawn(color, board, 1,6);
			new Pawn(color, board, 1,7);
			new Rook(color, board, 0,0);
			new Rook(color, board, 0,7);
			new Bishop(color, board, 0,5);
			new Bishop(color, board, 0,2);
			new Knight(color, board, 0,1);
			new Knight(color, board, 0,6);
			new Queen(color, board, 0,3);
			new King(color, board, 0,4);
			this.color = color;
			isTurn=false;
		break;
		}
	}
	public void setTurn(boolean isTurn) {
		this.isTurn= isTurn;
	}
	public boolean getTurn() {
		return isTurn;
	}
	public PlayerColor getPlayerColor() {
		return color;
	}
}
