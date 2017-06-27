package Pieces;
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
	public King(PieceColor theColor) {
		super(theColor, PiecePoints.KING);
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	public String toString() {
		return super.toString() + "K";
	}
}