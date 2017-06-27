package Pieces;
import Enums.PieceColor;
import Enums.PiecePoints;

public class Pawn extends AbstractPiece { 
	public Pawn(PieceColor theColor) {
		super(theColor, PiecePoints.PAWN);
	}
	
	public String toString() {
		return super.toString() + "P";
	}
}