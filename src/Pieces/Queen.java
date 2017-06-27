package Pieces;
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
	public Queen(PieceColor theColor) {
		super(theColor, PiecePoints.QUEEN);
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	public String toString() {
		return super.toString() + "Q";
	}
}