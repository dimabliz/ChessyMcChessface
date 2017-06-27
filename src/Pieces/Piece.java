package Pieces;

import java.awt.Point;

/**
 * Piece interface.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public interface Piece {
  
	/** Returns whether this piece is white or not.
	 * 
	 * @return true if this piece is white, false otherwise.
	 */
	boolean isWhite();
	
	/**
	 * Returns point value of this piece.
	 * 
	 * @return point value of this piece.
	 */
	int getPointValue();
	
	/**
	 * Returns visual representation of this piece.
	 * 
	 * @return visual representation of this piece.
	 */
	String toString();
	
	/**
	 * Returns the location of this piece.
	 * 
	 * @return the location of this piece.
	 */
	Point getLocation();
	
	/**
	 * Returns x location of this piece.
	 * 
	 * @return x location of this piece.
	 */
	int getX();
	
	/**
	 * Returns y location of this piece.
	 * 
	 * @return y location of this piece.
	 */
	int getY();
}