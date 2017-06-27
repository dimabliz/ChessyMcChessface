package Pieces;
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
	public Bishop(PieceColor theColor) {
		super(theColor, PiecePoints.BISHOP);
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	public String toString() {
		return super.toString() + "B";
	}
}