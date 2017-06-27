package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
/**
 * A bishop.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class Bishop extends AbstractPiece { 
	/**
	 * Creates a bishop of this color.
	 * 
	 * @param theColor the color of this bishop.
	 */
	public Bishop(PieceColor theColor, Point theLocation) {
		super(theColor, PiecePoints.BISHOP, theLocation);
	}
	
	/**
	 * Returns a list of all the Points to where the bishop
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves() {
		List<Point> moves = new ArrayList<Point>();
		
		// 8 available moves to the king
		moves.add(new Point(myLocation.x-1, myLocation.y-1));
		
		// Two loops below are adding points on this diagonal line:  /
		for(int i = 1; i < 8; i++) {
			if (myLocation.x+i < 8 && myLocation.y-i > 0) 
				moves.add(new Point(myLocation.x+i, myLocation.y-i));
		}
		for(int i = 1; i < 8; i++) {
			if (myLocation.x-i > 0 && myLocation.y+i < 8) 
				moves.add(new Point(myLocation.x-i, myLocation.y+i));
		}
		
		//Next two loops are adding points on this diagonal line: \
		for(int i = 1; i < 8; i++) {
			if (myLocation.x-i > 0 && myLocation.y-i > 0) 
				moves.add(new Point(myLocation.x-i, myLocation.y-i));
		}
		for(int i = 1; i < 8; i++) {
			if (myLocation.x+i < 8 && myLocation.y+i < 8) 
				moves.add(new Point(myLocation.x+i, myLocation.y+i));
		}
		
		return moves;
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "B";
	}
}