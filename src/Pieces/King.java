package Pieces;
import Enums.PieceColor;
import Enums.PiecePoints;

public class King extends AbstractPiece { 
	public King(PieceColor theColor) {
		super(theColor, PiecePoints.KING);
	}
	
	public String toString() {
		return super.toString() + "K";
	}
}