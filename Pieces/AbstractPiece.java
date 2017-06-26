public abstract class AbstractPiece implements Piece {
	
	private boolean isWhite;
	
	private int pointValue;
	
	private int myX;
	
	private int myY;
	
	private String pieceName;
	
//	protected AbstractPiece(PieceType pt, boolean isTheWhite) {
//		isWhite = isTheWhite;
//		switch(pt) {
//			case PieceType.PAWN:
//				
//			case PieceType.ROOK:
//				
//		}
//	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(pieceName);
		if(isWhite) {
			sb.append(", white, ");
		} else {
			sb.append(", black, {");
		}
		sb.append(myX);
		sb.append(", ");
		sb.append(myY);
		sb.append("}\n");
	}
}