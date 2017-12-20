package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
import chess.Board;
/**
 * A Knight.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class Knight extends AbstractPiece { 
	/**
	 * Creates a knight of this color.
	 * 
	 * @param theColor of this knight.
	 */
	public Knight(PieceColor theColor, Point theLocation, Board theBoard) {
		super(theColor, PiecePoints.KNIGHT, theLocation, theBoard);
	}
	
	/**
	 * Returns a list of all the Points to where the knight
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves(final Piece[][] board) {
		List<Point> moves = new ArrayList<Point>();
		
		// 8 available moves to the knight
		moves.add(new Point(myLocation.y-2, myLocation.x-1));
		moves.add(new Point(myLocation.y-1, myLocation.x-2));
		
		moves.add(new Point(myLocation.y+2, myLocation.x-1));
		moves.add(new Point(myLocation.y+1, myLocation.x-2));
		
		moves.add(new Point(myLocation.y-2, myLocation.x+1));
		moves.add(new Point(myLocation.y-1, myLocation.x+2));
		
		moves.add(new Point(myLocation.y+2, myLocation.x+1));
		moves.add(new Point(myLocation.y+1, myLocation.x+2));
		
		refineBounds(moves);
		refineByPieces(moves, board);
		refineByCheck(moves);
		return moves;
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "N";
	}
}