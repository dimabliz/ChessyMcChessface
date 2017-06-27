package Pieces;
import Enums.PieceColor;
import Enums.PiecePoints;

public class Queen extends AbstractPiece implements Piece { 
	public Queen(PieceColor theColor) {
		super(theColor, PiecePoints.QUEEN);
	}
	
	public String toString() {
		return super.toString() + "Q";
	}
}