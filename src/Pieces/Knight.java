package Pieces;
import java.awt.Point;

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
	 * {@inheritDoc Piece.java}
	 */
	public String toString() {
		return super.toString() + "N";
	}
}