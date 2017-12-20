package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
import chess.Board;

/**
 * A pawn.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class Pawn extends AbstractPiece { 
	
	/**
	 * If the piece is about to make a first move.
	 */
	private boolean firstMove = true;
	
	/**
	 * if the pawn moved two squares last move.
	 */
	private boolean movedTwoSquares = false;
	
	/**
	 * Creates a pawn of this color.
	 * 
	 * @param theColor the color of this pawn.
	 */
	public Pawn(PieceColor theColor, Point theLocation, Board theBoard) {
		super(theColor, PiecePoints.PAWN, theLocation, theBoard);
	}
	
	/**
	 * Set that the piece has just moved two squares.
	 */
	public void setMovedTwoSquares() {
		if (!movedTwoSquares)
			movedTwoSquares = true;
	}
	
	/**
	 * Return if the pawn has moved two squares up last time it was moved.
	 * @return
	 */
	public boolean hasMovedTwoSquares() {
		return movedTwoSquares;
	}
	
	/**
	 * Returns if the piece is in first move.
	 * @return returns true if the pawn has not moved yet and false otherwise.
	 */
	public boolean isFirstMove() {
		return firstMove;
	}
	
	/**
	 * Sets that the pawn has already moved.
	 */
	public void setMoved() {
		firstMove = false;
	}
	
	/**
	 * Returns a list of all the Points to where the pawn
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves(final Piece[][] board) {
		List<Point> moves = new ArrayList<Point>();
		
		//white pawns move up the board
		if (myColor == PieceColor.White) {
			if (myLocation.x-1 >= 0 && board[myLocation.x-1][myLocation.y] == null) { // one square up
				moves.add(new Point(myLocation.y, myLocation.x-1));
			}
			if (firstMove) {
				if (myLocation.x-2 >= 0 && board[myLocation.x-2][myLocation.y] == null) { //two squares up
					moves.add(new Point(myLocation.y, myLocation.x-2));
				}
			}
			if (myLocation.x-1 >= 0 && myLocation.y-1 >= 0) { //take left
				if (board[myLocation.x-1][myLocation.y-1] != null 
						&& board[myLocation.x-1][myLocation.y-1].getColor() != myColor) {
					moves.add(new Point(myLocation.y-1, myLocation.x-1));
				}
			}
			if (myLocation.x-1 >= 0 && myLocation.y+1 <= 7) { //take right
				if (board[myLocation.x-1][myLocation.y+1] != null 
						&& board[myLocation.x-1][myLocation.y+1].getColor() != myColor) {
					moves.add(new Point(myLocation.y+1, myLocation.x-1));
				}
			}
			
			//adding the en passant left
			if (myLocation.x-1 >= 0 && myLocation.y-1 >= 0) { //take left
				if (board[myLocation.x][myLocation.y-1] != null 
						&& board[myLocation.x][myLocation.y-1].getColor() != myColor
						&& board[myLocation.x][myLocation.y-1] instanceof Pawn
						&& ((Pawn) board[myLocation.x][myLocation.y-1]).hasMovedTwoSquares()) {
					if (board[myLocation.x][myLocation.y - 1].equals(myBoard.getLastPieceMoved()))
						moves.add(new Point(myLocation.y-1, myLocation.x-1));
				}
			}

			//adding en passant right
			if (myLocation.x-1 >= 0 && myLocation.y+1 <= 7) { //take right
				if (board[myLocation.x][myLocation.y+1] != null 
						&& board[myLocation.x][myLocation.y+1].getColor() != myColor
						&& board[myLocation.x][myLocation.y+1] instanceof Pawn
						&& ((Pawn) board[myLocation.x][myLocation.y+1]).hasMovedTwoSquares()) {
					if (board[myLocation.x][myLocation.y + 1].equals(myBoard.getLastPieceMoved()))
						moves.add(new Point(myLocation.y+1, myLocation.x-1));
				}
			}
			
		} 
		//black pawns move down the board
		else {
			if (myLocation.x+1 <= 7 && board[myLocation.x+1][myLocation.y] == null) { // one square down
				moves.add(new Point(myLocation.y, myLocation.x+1));
			}
			if (firstMove) {
				if (myLocation.x+2 <= 7 && board[myLocation.x+2][myLocation.y] == null) { //two squares down
					moves.add(new Point(myLocation.y, myLocation.x+2));
				}
			}
			if (myLocation.x+1 <= 7 && myLocation.y-1 >= 0) { //take right
				if (board[myLocation.x+1][myLocation.y-1] != null 
						&& board[myLocation.x+1][myLocation.y-1].getColor() != myColor) {
					moves.add(new Point(myLocation.y-1, myLocation.x+1));
				}
			}
			if (myLocation.x+1 <= 7 && myLocation.y+1 <= 7) { //take left
				if (board[myLocation.x+1][myLocation.y+1] != null 
						&& board[myLocation.x+1][myLocation.y+1].getColor() != myColor) {
					moves.add(new Point(myLocation.y+1, myLocation.x+1));
				}
			}
			
			//adding the en passant left
			if (myLocation.x+1 <= 7 && myLocation.y+1 <= 7) { //take left
				if (board[myLocation.x][myLocation.y+1] != null 
						&& board[myLocation.x][myLocation.y+1].getColor() != myColor
						&& board[myLocation.x][myLocation.y+1] instanceof Pawn
						&& ((Pawn) board[myLocation.x][myLocation.y+1]).hasMovedTwoSquares()) {
					if (board[myLocation.x][myLocation.y + 1].equals(myBoard.getLastPieceMoved()))
						moves.add(new Point(myLocation.y+1, myLocation.x+1));
				}
			}
			
			//adding en passant right
			if (myLocation.x+1 <= 7 && myLocation.y-1 >= 0) { //take right
				if (board[myLocation.x][myLocation.y-1] != null 
						&& board[myLocation.x][myLocation.y-1].getColor() != myColor
						&& board[myLocation.x][myLocation.y-1] instanceof Pawn
						&& ((Pawn) board[myLocation.x][myLocation.y-1]).hasMovedTwoSquares()) {
					if (board[myLocation.x][myLocation.y - 1].equals(myBoard.getLastPieceMoved()))
						moves.add(new Point(myLocation.y-1, myLocation.x+1));
				}
			}
		}
		
		refineByPieces(moves, board);
		return moves;
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "P";
	}
}