package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;

/**
 * A Queen
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class Queen extends AbstractPiece implements Piece { 
	/**
	 * Creates a queen of the given color.
	 * 
	 * @param theColor is the color of this queen.
	 */
	public Queen(PieceColor theColor, Point theLocation) {
		super(theColor, PiecePoints.QUEEN, theLocation);
	}
	
	/**
	 * Returns a list of all the Points to where the queen
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves(final Piece[][] board) {
		
		// Next four for loops are taken from the bishop's getAvailableMoves method
		List<Point> moves = new ArrayList<Point>();
		// Two loops below are adding points on this diagonal line:  /
		for(int i = 1; i < 8; i++) {
			if (myLocation.x+i < 8 && myLocation.y-i >= 0) 
				moves.add(new Point(myLocation.x+i, myLocation.y-i));
		}
		for(int i = 1; i < 8; i++) {
			if (myLocation.x-i >= 0 && myLocation.y+i < 8) 
				moves.add(new Point(myLocation.x-i, myLocation.y+i));
		}
			
		//Next two loops are adding points on this diagonal line: \
		for(int i = 1; i < 8; i++) {
			if (myLocation.x-i >= 0 && myLocation.y-i >= 0) 
				moves.add(new Point(myLocation.x-i, myLocation.y-i));
		}
		for(int i = 1; i < 8; i++) {
			if (myLocation.x+i < 8 && myLocation.y+i < 8) 
				moves.add(new Point(myLocation.x+i, myLocation.y+i));
		}
		
		
		// Next four for loops are taken from the rook's getAvailableMoves method
		
		//up squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.y-i >= 0) 
				moves.add(new Point(myLocation.x, myLocation.y-i));
			else
				break;
		}
				
		//down squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.y+i < 8) 
				moves.add(new Point(myLocation.x, myLocation.y+i));
			else
				break;
		}

		//left squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.x-i >= 0) 
				moves.add(new Point(myLocation.x-i, myLocation.y));
			else
				break;
		}
				
		//right squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.x+i < 8) 
				moves.add(new Point(myLocation.x+i, myLocation.y));
			else
				break;
		}	
			
		return moves;	
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "Q";
	}
}