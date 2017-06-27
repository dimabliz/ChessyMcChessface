package Pieces;
import java.awt.Point;
import java.util.List;

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
	 * Returns a list of all the Points to where the knight
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
		return super.toString() + "N";
	}
}