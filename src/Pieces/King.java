package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
import chess.Board;
/**
 * A king.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class King extends AbstractPiece { 
	
	/**
	 * if the king has moved in this game yet.
	 */
	private boolean hasMoved = false;
	
	/**
	 * Creates a king of this color.
	 * 
	 * @param theColor of this king.
	 */
	public King(PieceColor theColor, Point theLocation, Board theBoard) {
		super(theColor, PiecePoints.KING, theLocation, theBoard);
	}
	
	/**
	 * Set that the piece has moved this game
	 */
	public void setMoved() {
		if (!hasMoved)
			hasMoved = true;
	}
	
	/**
	 * Return if the piece has moved this game.
	 * @return
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
	
	/**
	 * Returns a list of all the Points to where the king
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves(final Piece[][] board) {
		List<Point> moves = new ArrayList<Point>();
		// 8 available moves to the king
		moves.add(new Point(myLocation.y, myLocation.x-1));
		moves.add(new Point(myLocation.y-1, myLocation.x-1));
		moves.add(new Point(myLocation.y+1, myLocation.x-1));
		
		moves.add(new Point(myLocation.y-1, myLocation.x));
		moves.add(new Point(myLocation.y+1, myLocation.x));
		
		moves.add(new Point(myLocation.y, myLocation.x+1));
		moves.add(new Point(myLocation.y-1, myLocation.x+1));
		moves.add(new Point(myLocation.y+1, myLocation.x+1));
		
		refineBounds(moves);
		refineByPieces(moves, board);
		
		if (!hasMoved()) {
				//checking right castle
				if (board[myLocation.x][myLocation.y+1] == null && board[myLocation.x][myLocation.y+2] == null 
						&& board[myLocation.x][myLocation.y+3] != null && board[myLocation.x][myLocation.y+3] instanceof Rook) {
					
					boolean canCastle = true;
					if (this.getColor() == PieceColor.White) {
						if (myBoard.checkCheck(this.getOppositeColor())) // make sure king is not in check
							canCastle = false;
						
						myBoard.setWhiteKingLocation(new Point(myLocation.x, myLocation.y + 1)); // make sure king is not in check on R + 1 side
						if (myBoard.checkCheck(this.getOppositeColor()))
							canCastle = false;
						
						myBoard.setWhiteKingLocation(new Point(myLocation.x, myLocation.y + 1));
						if (myBoard.checkCheck(this.getOppositeColor()))
							canCastle = false;
						
						myBoard.setWhiteKingLocation(new Point(myLocation.x, myLocation.y - 2)); // set back to original location
					} else {
						if (myBoard.checkCheck(this.getOppositeColor())) // make sure king is not in check
							canCastle = false;
						
						myBoard.setBlackKingLocation(new Point(myLocation.x, myLocation.y + 1)); // make sure king is not in check on R + 1 side
						if (myBoard.checkCheck(this.getOppositeColor()))
							canCastle = false;
						
						myBoard.setBlackKingLocation(new Point(myLocation.x, myLocation.y + 1));
						if (myBoard.checkCheck(this.getOppositeColor()))
							canCastle = false;
						
						myBoard.setBlackKingLocation(new Point(myLocation.x, myLocation.y - 2)); // set back to original location
					}
							
					Rook rightRook = (Rook) board[myLocation.x][myLocation.y+3];
					if (!rightRook.hasMoved() && canCastle) {
						moves.add(new Point(myLocation.y+2, myLocation.x));
					}
				}
				//checking left castle
				if (board[myLocation.x][myLocation.y-1] == null && board[myLocation.x][myLocation.y-2] == null 
						&& board[myLocation.x][myLocation.y-3] == null
						&& board[myLocation.x][myLocation.y-4] != null && board[myLocation.x][myLocation.y-4] instanceof Rook) {
					
					boolean canCastle = true;
					if (this.getColor() == PieceColor.White) {
						if (myBoard.checkCheck(this.getOppositeColor())) // make sure king is not in check
							canCastle = false;
						
						myBoard.setWhiteKingLocation(new Point(myLocation.x, myLocation.y - 1)); // make sure king is not in check on L + 1 side
						if (myBoard.checkCheck(this.getOppositeColor()))
							canCastle = false;
						
						myBoard.setWhiteKingLocation(new Point(myLocation.x, myLocation.y - 1));
						if (myBoard.checkCheck(this.getOppositeColor()))
							canCastle = false;
						
						myBoard.setWhiteKingLocation(new Point(myLocation.x, myLocation.y + 2)); // set back to original location
					} else {
						if (myBoard.checkCheck(this.getOppositeColor())) // make sure king is not in check
							canCastle = false;
						
						myBoard.setBlackKingLocation(new Point(myLocation.x, myLocation.y - 1)); // make sure king is not in check on L + 1 side
						if (myBoard.checkCheck(this.getOppositeColor()))
							canCastle = false;
						
						myBoard.setBlackKingLocation(new Point(myLocation.x, myLocation.y - 1));
						if (myBoard.checkCheck(this.getOppositeColor()))
							canCastle = false;
						
						myBoard.setBlackKingLocation(new Point(myLocation.x, myLocation.y + 2)); // set back to original location
					}
					
					Rook leftRook = (Rook) board[myLocation.x][myLocation.y-4];
					if (!leftRook.hasMoved() && canCastle) {
						moves.add(new Point(myLocation.y-2, myLocation.x));
					}
				}
		}
		refineByCheck(moves);
		refineByKingsLocation(this.getOppositeColor(), moves);
		
		return moves;
	}

	public void refineByKingsLocation(PieceColor opositeColor, List<Point> moves) {
		for (Iterator<Point> iterator = moves.iterator(); iterator.hasNext(); ) {
			Point currentPoint = iterator.next();
//			Piece daPiece = myBoard.getPiece(currentPoint.y, currentPoint.x);
//			if (daPiece != null && daPiece instanceof King && daPiece.getColor() == opositeColor) {
//				iterator.remove();
//				System.out.println("removed");
//			}
            List<Point> surroundingPoints = new ArrayList<>();

            surroundingPoints.add(new Point(currentPoint.x-1, currentPoint.y-1));
            surroundingPoints.add(new Point(currentPoint.x-1, currentPoint.y));
            surroundingPoints.add(new Point(currentPoint.x-1, currentPoint.y+1));

            surroundingPoints.add(new Point(currentPoint.x, currentPoint.y-1));
            surroundingPoints.add(new Point(currentPoint.x, currentPoint.y+1));

            surroundingPoints.add(new Point(currentPoint.x+1, currentPoint.y-1));
            surroundingPoints.add(new Point(currentPoint.x+1, currentPoint.y));
            surroundingPoints.add(new Point(currentPoint.x+1, currentPoint.y+1));

            refineBounds(surroundingPoints);

            for (Iterator<Point> iterator0 = surroundingPoints.iterator(); iterator0.hasNext(); ) {
                Point surrounding = iterator0.next();
                Piece daPiece = myBoard.getPiece(surrounding.y, surrounding.x);
                if (daPiece != null && daPiece instanceof King && daPiece.getColor() == opositeColor) {
                    iterator.remove();
                }
            }
		}
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "K";
	}
}