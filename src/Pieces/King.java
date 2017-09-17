package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
import chess.Board;
/**
 * A king.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class King extends AbstractPiece { 
	
	/**
	 * if the king has moved in this game yet.
	 */
	private boolean hasMoved = false;
	
	/**
	 * Creates a king of this color.
	 * 
	 * @param theColor of this king.
	 */
	public King(PieceColor theColor, Point theLocation, Board theBoard) {
		super(theColor, PiecePoints.KING, theLocation, theBoard);
	}
	
	/**
	 * Set that the piece has moved this game
	 */
	public void setMoved() {
		if (!hasMoved)
			hasMoved = true;
	}
	
	/**
	 * Return if the piece has moved this game.
	 * @return
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
	
	/**
	 * Returns a list of all the Points to where the king
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves(final Piece[][] board) {
		List<Point> moves = new ArrayList<Point>();
		System.out.println("getAvailableMoves()");
		System.out.println();
		// 8 available moves to the king
		moves.add(new Point(myLocation.y, myLocation.x-1));
		moves.add(new Point(myLocation.y-1, myLocation.x-1));
		moves.add(new Point(myLocation.y+1, myLocation.x-1));
		
		moves.add(new Point(myLocation.y-1, myLocation.x));
		moves.add(new Point(myLocation.y+1, myLocation.x));
		
		moves.add(new Point(myLocation.y, myLocation.x+1));
		moves.add(new Point(myLocation.y-1, myLocation.x+1));
		moves.add(new Point(myLocation.y+1, myLocation.x+1));
		
		refineBounds(moves);
		refineByPieces(moves, board);
		System.out.println();
		for (int i = 0; i < moves.size(); i++) {
			System.out.println(i + ". x=" + moves.get(i).getX() + ", y=" + moves.get(i).getY());
		}
		
		if (!hasMoved()) {
				//checking right castle
				if (board[myLocation.x][myLocation.y+1] == null && board[myLocation.x][myLocation.y+2] == null 
						&& board[myLocation.x][myLocation.y+3] != null && board[myLocation.x][myLocation.y+3] instanceof Rook) {
					
					Rook rightRook = (Rook) board[myLocation.x][myLocation.y+3];
					if (!rightRook.hasMoved()) {
						moves.add(new Point(myLocation.y+2, myLocation.x));
					}
				}
				//checking left castle
				if (board[myLocation.x][myLocation.y-1] == null && board[myLocation.x][myLocation.y-2] == null 
						&& board[myLocation.x][myLocation.y-3] == null
						&& board[myLocation.x][myLocation.y-4] != null && board[myLocation.x][myLocation.y-4] instanceof Rook) {
					
					Rook leftRook = (Rook) board[myLocation.x][myLocation.y-4];
					if (!leftRook.hasMoved()) {
						moves.add(new Point(myLocation.y-2, myLocation.x));
					}
				}
		}
		refineByCheck(moves);
		
		return moves;
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "K";
	}
}