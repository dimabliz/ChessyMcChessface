package Pieces;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;

/**
 * Abstract Chess Piece.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public abstract class AbstractPiece implements Piece {
	/** Color of this piece. */
	protected PieceColor myColor;
	/** Points this piece is worth. */
	protected PiecePoints myPoints;
	/** My location on board. */
	protected Point myLocation;
	
	/**
	 * Creates a piece.
	 * 
	 * @param theColor is color of this piece.
	 * @param thePoints is point value of this piece.
	 */
	protected AbstractPiece(PieceColor theColor, PiecePoints thePoints, Point theLocation) {
		myColor = theColor;
		myPoints = thePoints;
		myLocation = theLocation;
	}
	
	/**
	 * Private helper method to get rid of the available moves that
	 * are out of bounds of the board.
	 * 
	 * @return refined list
	 */
	protected void refineBounds(List<Point> moves) {
		for (Iterator<Point> iterator = moves.iterator(); iterator.hasNext(); ) {
		    Point currentPoint = iterator.next();
		    if (currentPoint.x < 0 || currentPoint.x > 7 
		    		|| currentPoint.y < 0 || currentPoint.y > 7) {
		        iterator.remove();
		    }
		}
	}
	
	/**
	 * Removes from available moves, if the square contains a piece of the same color.
	 * Used by King and Knight.
	 * 
	 * @param moves
	 * @param board
	 */
	protected void refineByPieces(List<Point> moves, final Piece[][] board) {
//		for (Iterator<Point> iterator = moves.iterator(); iterator.hasNext(); ) {
//		    Point currentPoint = iterator.next();
//		    if (board[currentPoint.y][currentPoint.x] != null 
//		    		&& board[currentPoint.y][currentPoint.x].getColor() == myColor) {
//		        iterator.remove();
//		    }
//		}
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public boolean isWhite() {
		return myColor == PieceColor.White;
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	public PieceColor getColor() {
		return myColor;
	}
  
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return myColor == PieceColor.White ? "W" : "B";
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public int getPointValue() {
		return myPoints.getValue();
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public Point getLocation() {
		return (Point) myLocation.clone();
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public int getX() {
		return (int) myLocation.getX();
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public int getY() {
		return (int) myLocation.getY();
	}
}