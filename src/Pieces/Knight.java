package Pieces;
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
	public Knight(PieceColor theColor) {
		super(theColor, PiecePoints.KNIGHT);
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	public String toString() {
		return super.toString() + "N";
	}
}