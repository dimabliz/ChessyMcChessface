package Pieces;
import java.awt.Point;

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
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "B";
	}
}