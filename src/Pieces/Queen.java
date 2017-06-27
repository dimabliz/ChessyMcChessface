package Pieces;
import java.awt.Point;
import java.util.List;

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
	public Queen(PieceColor theColor, Point theLocation) {
		super(theColor, PiecePoints.QUEEN, theLocation);
	}
	
	/**
	 * Returns a list of all the Points to where the queen
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
		return super.toString() + "Q";
	}
}