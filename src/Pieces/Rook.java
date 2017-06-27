package Pieces;
import Enums.PieceColor;
import Enums.PiecePoints;
/**
 * A rook.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class Rook extends AbstractPiece { 
	
	public Rook(PieceColor theColor) {
		super(theColor, PiecePoints.ROOK);
	}
	
	public String toString() {
		return super.toString() + "R";
	}
}