package Pieces;
import java.awt.Point;
import java.util.List;

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
	 * Returns a list of all the Points to where the bishop
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
		return super.toString() + "B";
	}
}