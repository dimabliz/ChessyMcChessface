package Pieces;

import java.awt.Point;

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
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public boolean isWhite() {
		return myColor == PieceColor.White;
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