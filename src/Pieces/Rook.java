package Pieces;
import java.awt.Point;
import java.util.List;

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
	public Rook(PieceColor theColor, Point theLocation) {
		super(theColor, PiecePoints.ROOK, theLocation);
	}
	
	/**
	 * Returns a list of all the Points to where the rook
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
		return super.toString() + "R";
	}
}