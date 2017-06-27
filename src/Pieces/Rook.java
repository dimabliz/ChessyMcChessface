package Pieces;
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
	public Rook(PieceColor theColor) {
		super(theColor, PiecePoints.ROOK);
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	public String toString() {
		return super.toString() + "R";
	}
}