package Pieces;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
import chess.Board;

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
	/** Board class */
	protected Board myBoard;
	
	/**
	 * Creates a piece.
	 * 
	 * @param theColor is color of this piece.
	 * @param thePoints is point value of this piece.
	 */
	protected AbstractPiece(final PieceColor theColor, final PiecePoints thePoints, final Point theLocation, Board theBoard) {
		myColor = theColor;
		myPoints = thePoints;
		myLocation = theLocation;
		myBoard = theBoard;
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
		for (Iterator<Point> iterator = moves.iterator(); iterator.hasNext(); ) {
		    Point currentPoint = iterator.next();
		    if (board[currentPoint.y][currentPoint.x] != null 
		    		&& board[currentPoint.y][currentPoint.x].getColor() == myColor) {
		        iterator.remove();
		    }
		}
	}
	
	/**
	 * Removes from available moves, if the new square will have the king in check.
	 * 
	 * @param moves
	 * @param board
	 */
	protected void refineByCheck(List<Point> moves) {
		System.out.println("refineByCheck()");
		System.out.println();
		
		Point myOriginalLocation = new Point(myLocation.x, myLocation.y);
		//for (Iterator<Point> iterator = moves.iterator(); iterator.hasNext(); ) {
		//    Point currentPoint = iterator.next();
			Point currentPoint = moves.get(0);
		    System.out.println("simple move:");
		    myBoard.simpleMove(new Point(myLocation.y, myLocation.x), new Point(currentPoint.x, currentPoint.y));
		    myBoard.printBoard();
//		    if (myBoard.checkCheck(myBoard.getPiece(currentPoint.y, currentPoint.x).getColor())) {
		    if (myBoard.checkCheck(PieceColor.White)) {
		    	//iterator.remove();
		    	System.out.println("ELIMINATED BY CHECK!");
		    }
		    myBoard.simpleMove(new Point(currentPoint.x, currentPoint.y), new Point(myOriginalLocation.y, myOriginalLocation.x));
		    //System.out.println("currentPoint (x, y): " + currentPoint.x + ", " + currentPoint.y);
		    //System.out.println("myOriginalLocation (x, y): " + myOriginalLocation.x + ", " + myOriginalLocation.y);
		    System.out.println("put back");
		    myBoard.printBoard();
		//}
		for (int i = 0; i < moves.size(); i++) {
			System.out.println(i + ". x=" + moves.get(i).getX() + ", y=" + moves.get(i).getY());
		}
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
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public void setX(int x) {
		myLocation.setLocation(x, getY());
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public void setY(int y) {
		myLocation.setLocation(getX(), y);
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public void setXY(int x, int y) {
		myLocation.setLocation(x, y);
	}
}