package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
/**
 * A rook.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class Rook extends AbstractPiece { 
	
	/**
	 * Creates a rook of the given color.
	 * 
	 * @param theColor is the color of this rook.
	 */
	public Rook(PieceColor theColor, Point theLocation) {
		super(theColor, PiecePoints.ROOK, theLocation);
	}
	
	/**
	 * Returns a list of all the Points to where the rook
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves() {
		List<Point> moves = new ArrayList<Point>();
		
		//up squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.y-i > 0) 
				moves.add(new Point(myLocation.x, myLocation.y-i));
		}
		
		//down squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.y+i < 8) 
				moves.add(new Point(myLocation.x, myLocation.y+i));
		}

		//left squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.x-i > 0) 
				moves.add(new Point(myLocation.x-1, myLocation.y));
		}
		
		//right squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.x+i < 8) 
				moves.add(new Point(myLocation.x+1, myLocation.y));
		}	
		
		return moves;
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "R";
	}
}