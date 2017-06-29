package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
/**
 * A king.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class King extends AbstractPiece { 
	/**
	 * Creates a king of this color.
	 * 
	 * @param theColor of this king.
	 */
	public King(PieceColor theColor, Point theLocation) {
		super(theColor, PiecePoints.KING, theLocation);
	}
	
	/**
	 * Returns a list of all the Points to where the king
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves(final Piece[][] board) {
		List<Point> moves = new ArrayList<Point>();
		
		// 8 available moves to the king
		moves.add(new Point(myLocation.x-1, myLocation.y-1));
		moves.add(new Point(myLocation.x-1, myLocation.y));
		moves.add(new Point(myLocation.x-1, myLocation.y+1));
		moves.add(new Point(myLocation.x, myLocation.y-1));
		moves.add(new Point(myLocation.x, myLocation.y+1));
		moves.add(new Point(myLocation.x+1, myLocation.y-1));
		moves.add(new Point(myLocation.x+1, myLocation.y));
		moves.add(new Point(myLocation.x+1, myLocation.y+1));
		
		refineBounds(moves);
		refineByPieces(moves, board);
		
		return moves;
	}
	
//	/**
//	 * Private helper method to get rid of the available moves that
//	 * are out of bounds of the board.
//	 * 
//	 * @return refined list
//	 */
//	private void refineBounds(List<Point> moves) {
//		for (Iterator<Point> iterator = moves.iterator(); iterator.hasNext(); ) {
//		    Point currentPoint = iterator.next();
//		    if (currentPoint.x < 0 || currentPoint.x > 7 
//		    		|| currentPoint.y < 0 || currentPoint.y > 7) {
//		        iterator.remove();
//		    }
//		}
//	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "K";
	}
}