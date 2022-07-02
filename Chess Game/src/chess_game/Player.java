package chess_game;

import chess_game.Chess_Pieces.Type_of_Piece;

public class Player {

	public boolean Castled_Queen_Side;
	public enum PlayerColor{
		BLACK, WHITE
	}
	private boolean isTurn;
	private PlayerColor color;
	private boolean IsChecked;
	private King king;
	
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
			king = new King(color, board, 7,4);
			this.color = color;
			isTurn=true;
			IsChecked=false;
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
			king = new King(color, board, 0,4);
			this.color = color;
			isTurn=false;
			IsChecked=false;
		break;
		}
	}
	public boolean isChecked(Board board) {
		//horizontal checking
		for(int i=1; i<7-king.getPosition()[0]; i++) {
			if(board.getBoard()[king.getPosition()[1]][king.getPosition()[0]+i]!=null && board.getBoard()[king.getPosition()[1]][king.getPosition()[0]+i].getColor()!=king.getColor()) {
				if(board.getBoard()[king.getPosition()[1]][king.getPosition()[0]+i].getType()==Type_of_Piece.ROOK || board.getBoard()[king.getPosition()[1]][king.getPosition()[0]+i].getType()==Type_of_Piece.QUEEN) {
					System.out.print("Horizaontal CHECK 1 on "+color+"\n");
					return true;
				} else {
					break;
				}
			} else if(board.getBoard()[king.getPosition()[1]][king.getPosition()[0]+i]!=null && board.getBoard()[king.getPosition()[1]][king.getPosition()[0]+i].getColor()==king.getColor()) {
				break;
			}
		}
		for(int i=1; i<king.getPosition()[1]; i++) {
			if(board.getBoard()[king.getPosition()[1]+i][king.getPosition()[0]]!=null && board.getBoard()[king.getPosition()[1]+i][king.getPosition()[0]].getColor()!=king.getColor()) {
				if(board.getBoard()[king.getPosition()[1]+i][king.getPosition()[0]].getType()==Type_of_Piece.ROOK|| board.getBoard()[king.getPosition()[1]+i][king.getPosition()[0]].getType()==Type_of_Piece.QUEEN) {
					System.out.print("Horizaontal CHECK 2 on "+color+"\n");
					return true;
				} else {
					break;
				}
			} else if(board.getBoard()[king.getPosition()[1]+i][king.getPosition()[0]]!=null && board.getBoard()[king.getPosition()[1]+i][king.getPosition()[0]].getColor()==king.getColor()){
				break;
			}
		}
		for(int i=1; i<king.getPosition()[0]; i++) {
			if(board.getBoard()[king.getPosition()[1]][king.getPosition()[0]-i]!=null && board.getBoard()[king.getPosition()[1]][king.getPosition()[0]-i].getColor()!=king.getColor()) {
				if(board.getBoard()[king.getPosition()[1]][king.getPosition()[0]-i].getType()==Type_of_Piece.ROOK || board.getBoard()[king.getPosition()[1]][king.getPosition()[0]-i].getType()==Type_of_Piece.QUEEN) {
					System.out.print("Horizaontal CHECK 3 on "+color+"\n");
					return true;
				} else {
					break;
				}
			} else if(board.getBoard()[king.getPosition()[1]][king.getPosition()[0]-i]!=null && board.getBoard()[king.getPosition()[1]][king.getPosition()[0]-i].getColor()==king.getColor()){
				break;
			}
		}
		for(int i=1; i<7-king.getPosition()[1]; i++) {
			if(board.getBoard()[king.getPosition()[1]-i][king.getPosition()[0]]!=null && board.getBoard()[king.getPosition()[1]-i][king.getPosition()[0]].getColor()!=king.getColor()) {
				if(board.getBoard()[king.getPosition()[1]-i][king.getPosition()[0]].getType()==Type_of_Piece.ROOK || board.getBoard()[king.getPosition()[1]-i][king.getPosition()[0]].getType()==Type_of_Piece.QUEEN) {
					System.out.print("Horizaontal CHECK 4 on "+color+"\n");
					return true;
				} else {
					break;
				}
			} else if(board.getBoard()[king.getPosition()[1]-i][king.getPosition()[0]]!=null && board.getBoard()[king.getPosition()[1]-i][king.getPosition()[0]].getColor()==king.getColor()) {
				break;
			}
		}
		//diagonal checking
		for(int i=1,j=1; i<7-king.getPosition()[0] && j<7-king.getPosition()[1]; i++,j++) {
			if(board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]+i]!=null && board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]+i].getColor()!=king.getColor()) {
				if(board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]+i].getType()==Type_of_Piece.BISHOP || board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]+i].getType()==Type_of_Piece.QUEEN) {
					System.out.print("Diagonal CHECK 1 on "+color+"\n");
					return true;
				} else {
					break;
				}
			} else if(board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]+i]!=null && board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]+i].getColor()==king.getColor()){
				break;
			}
		}
		for(int i=1,j=1; i<king.getPosition()[0] && j<king.getPosition()[1]; i++,j++) {
			if(board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]-i]!=null && board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]-i].getColor()!=king.getColor()) {
				if(board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]-i].getType()==Type_of_Piece.BISHOP ||board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]-i].getType()==Type_of_Piece.QUEEN) {
					System.out.print("Diagonal CHECK 2 on "+color+"\n");
					return true;
				} else {
					break;
				}
			} else if(board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]-i]!=null && board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]-i].getColor()==king.getColor()) {
				break;
			}
		}
		for(int i=1,j=1; i<king.getPosition()[0] && j<7-king.getPosition()[1]; i++,j++) {
			if(board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]-i]!=null && board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]-i].getColor()!=king.getColor()) {
				if(board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]-i].getType()==Type_of_Piece.BISHOP || board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]-i].getType()==Type_of_Piece.QUEEN) {
					System.out.print("Diagonal CHECK 3 on "+color+"\n");
					return true;
				} else {
					break;
				}
			} else if(board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]-i]!=null && board.getBoard()[king.getPosition()[1]+j][king.getPosition()[0]-i].getColor()==king.getColor()) {
				break;
			}
		}
		for(int i=1,j=1; i<7-king.getPosition()[0] && j<king.getPosition()[1]; i++,j++) {
			if(board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]+i]!=null && board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]+i].getColor()!=king.getColor()) {
				if(board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]+i].getType()==Type_of_Piece.BISHOP || board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]+i].getType()==Type_of_Piece.QUEEN) {
					System.out.print("Diagonal CHECK 4 on "+color+"\n");
					return true;
				} else {
					break;
				}
			} else if(board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]+i]!=null && board.getBoard()[king.getPosition()[1]-j][king.getPosition()[0]+i].getColor()==king.getColor()) {
				break;
			} 
		}

		return false;
	}
	public King getKing() {
		return king;
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
	public boolean getCheckStatus() {
		return IsChecked;
	}
	public void setCheckStatus(boolean isChecked) {
		this.IsChecked=isChecked;
	}
}
