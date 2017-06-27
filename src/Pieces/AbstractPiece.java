package Pieces;

import Enums.PieceColor;
import Enums.PiecePoints;

/**
 * Abstract Chess Piece.
 * 
 * @author Maksimv@uw.edu
 */
public abstract class AbstractPiece implements Piece {
	/** Color of this piece. */
	PieceColor myColor;
	/** Points this piece is worth. */
	PiecePoints myPoints;
	
	protected AbstractPiece(PieceColor theColor, PiecePoints thePoints) {
		myColor = theColor;
		myPoints = thePoints;
	}
	
	public boolean isWhite() {
		return myColor == PieceColor.White;
	}
  
	public String toString() {
		return myColor == PieceColor.White ? "W" : "B";
	}
  
	public int getPointValue() {
		return myPoints.getValue();
	}
}