public abstract class AbstractPiece implements Piece {
	
	private boolean isWhite;
	
	private int pointValue;
	
	protected AbstractPiece(PieceType pt, boolean isTheWhite) {
		isWhite = isTheWhite;
		switch(pt) {
			case PieceType.PAWN:
				
			case PieceType.ROOK:
				
		}
	}
}