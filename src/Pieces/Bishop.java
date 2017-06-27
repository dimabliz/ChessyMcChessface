package Pieces;
import Enums.PieceColor;
import Enums.PiecePoints;

public class Bishop extends AbstractPiece { 
	public Bishop(PieceColor theColor) {
		super(theColor, PiecePoints.BISHOP);
	}
	
	public String toString() {
		return super.toString() + "B";
	}
}