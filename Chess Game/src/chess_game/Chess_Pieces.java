package chess_game;

import java.awt.Image;
import chess_game.Player.PlayerColor;

public abstract class Chess_Pieces {

	enum Type_of_Piece{
		PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING;
	}
	public Chess_Pieces(PlayerColor color, Board board, int row, int column) {
		
	}
	abstract boolean isLegalMoves(Board board, int row, int column);
	abstract boolean isPermanent();
	abstract Image getSprite(PlayerColor color);
	abstract void setInitialPosition(int row, int column);
	abstract int[] getInitialPosition();
	abstract PlayerColor getColor();
	abstract void setColor(PlayerColor color);
	abstract int[] getPosition();
	abstract void setPosition(int row, int column);
	abstract Type_of_Piece getType();
	abstract void setType(Type_of_Piece type);
	abstract boolean isMoved();
	abstract void setMovedStatus(boolean isMoved);
}
