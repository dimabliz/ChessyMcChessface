package Pieces;
import java.awt.Point;

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
	 * {@inheritDoc Piece.java}
	 */
	public String toString() {
		return super.toString() + "P";
	}
}