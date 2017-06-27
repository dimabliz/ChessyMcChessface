package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;

/**
 * A pawn.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class Pawn extends AbstractPiece { 
	/**
	 * Creates a pawn of this color.
	 * 
	 * @param theColor the color of this pawn.
	 */
	public Pawn(PieceColor theColor, Point theLocation) {
		super(theColor, PiecePoints.PAWN, theLocation);
	}
	
	/**
	 * Returns a list of all the Points to where the pawn
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves() {
		List<Point> moves = new ArrayList<Point>();
		
		//white pawns move up the board
		if (myColor == PieceColor.White) {
			if (myLocation.y-1 > 0) { // one square up
				moves.add(new Point(myLocation.x, myLocation.y-1));
			} else if (myLocation.y-2 > 0) { //two squares up
				moves.add(new Point(myLocation.x, myLocation.y-2));
			} else if (myLocation.y-1 > 0 && myLocation.x-1 > 0) { //take left
				moves.add(new Point(myLocation.x-1, myLocation.y-1));
			} else if (myLocation.y-1 > 0 && myLocation.x+1 < 8) { //take right
				moves.add(new Point(myLocation.x+1, myLocation.y-1));
			}
		} 
		
		//black pawns move down the board
		else {
			if (myLocation.y+1 < 8) { // one square down
				moves.add(new Point(myLocation.x, myLocation.y+1));
			} else if (myLocation.y+2 < 8) { //two squares down
				moves.add(new Point(myLocation.x, myLocation.y+2));
			} else if (myLocation.y+1 > 0 && myLocation.x-1 > 0) { //take right
				moves.add(new Point(myLocation.x-1, myLocation.y+1));
			} else if (myLocation.y+1 > 0 && myLocation.x+1 < 8) { //take left
				moves.add(new Point(myLocation.x+1, myLocation.y+1));
			}
		}
		
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