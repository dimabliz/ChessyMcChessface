package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
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
	public Knight(PieceColor theColor, Point theLocation) {
		super(theColor, PiecePoints.KNIGHT, theLocation);
	}
	
	/**
	 * Returns a list of all the Points to where the knight
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves() {
		List<Point> moves = new ArrayList<Point>();
		
		// 8 available moves to the knight
		moves.add(new Point(myLocation.x-1, myLocation.y-2));
		moves.add(new Point(myLocation.x-2, myLocation.y-1));
		moves.add(new Point(myLocation.x+1, myLocation.y-2));
		moves.add(new Point(myLocation.x+2, myLocation.y-1));
		moves.add(new Point(myLocation.x-2, myLocation.y+1));
		moves.add(new Point(myLocation.x-1, myLocation.y+2));
		moves.add(new Point(myLocation.x+1, myLocation.y+2));
		moves.add(new Point(myLocation.x+2, myLocation.y+1));
		
		refineBounds(moves);
		
		return moves;
	}
	
	public List<Point> getAvailableMoves(final Piece[][] board) {
		List<Point> moves = new ArrayList<Point>();
		
		// 8 available moves to the knight
		moves.add(new Point(myLocation.x-1, myLocation.y-2));
		moves.add(new Point(myLocation.x-2, myLocation.y-1));
		moves.add(new Point(myLocation.x+1, myLocation.y-2));
		moves.add(new Point(myLocation.x+2, myLocation.y-1));
		moves.add(new Point(myLocation.x-2, myLocation.y+1));
		moves.add(new Point(myLocation.x-1, myLocation.y+2));
		moves.add(new Point(myLocation.x+1, myLocation.y+2));
		moves.add(new Point(myLocation.x+2, myLocation.y+1));
		
		refineBounds(moves);
		refineByPieces(moves, board);
		
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