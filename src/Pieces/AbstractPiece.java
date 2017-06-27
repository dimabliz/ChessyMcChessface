package Pieces;

import Enums.PieceColor;
import Enums.PiecePoints;

/**
 * Abstract Chess Piece.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public abstract class AbstractPiece implements Piece {
	/** Color of this piece. */
	PieceColor myColor;
	/** Points this piece is worth. */
	PiecePoints myPoints;
	
	/**
	 * Creates a piece.
	 * 
	 * @param theColor is color of this piece.
	 * @param thePoints is point value of this piece.
	 */
	protected AbstractPiece(PieceColor theColor, PiecePoints thePoints) {
		myColor = theColor;
		myPoints = thePoints;
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	public boolean isWhite() {
		return myColor == PieceColor.White;
	}
  
	/**
	 * {@inheritDoc Piece.java}
	 */
	public String toString() {
		return myColor == PieceColor.White ? "W" : "B";
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	public int getPointValue() {
		return myPoints.getValue();
	}
}