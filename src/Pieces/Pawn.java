package Pieces;
import java.awt.Point;
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
		return null;
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "P";
	}
}