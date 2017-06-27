package Pieces;
import Enums.PieceColor;
import Enums.PiecePoints;

public class Knight extends AbstractPiece { 
	public Knight(PieceColor theColor) {
		super(theColor, PiecePoints.KNIGHT);
	}
	
	public String toString() {
		return super.toString() + "N";
	}
}