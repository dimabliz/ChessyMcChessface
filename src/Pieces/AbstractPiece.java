package Pieces;

import Enums.PieceColor;

/**
 * Abstract Chess Piece.
 * 
 * @author Maksimv@uw.edu
 */
public abstract class AbstractPiece implements Piece {
	/** Color of this piece. */
	PieceColor myColor;
	
	
	public AbstractPiece(PieceColor theColor) {
		myColor = theColor;
	}
	
	public boolean isWhite() {
		return myColor == PieceColor.White;
	}
  
  public String toString() {
    return "TEST";//filler
  }
  
  public int getPointValue() {
    return 1;//filler
  }
}