package Pieces;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Enums.PieceColor;
import Enums.PiecePoints;
/**
 * A rook.
 * 
 * @author dimabliz@uw.edu
 * @author Maksimv@uw.edu
 */
public class Rook extends AbstractPiece { 
	
	/**
	 * Creates a rook of the given color.
	 * 
	 * @param theColor is the color of this rook.
	 */
	public Rook(PieceColor theColor, Point theLocation) {
		super(theColor, PiecePoints.ROOK, theLocation);
	}
	
	/**
	 * Returns a list of all the Points to where the rook
	 * is allowed to move.
	 * 
	 * @return 
	 */
	public List<Point> getAvailableMoves(final Piece[][] board) {
		List<Point> moves = new ArrayList<Point>();
		
		//up squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.y-i >= 0) { // checking bound
				if (board[myLocation.y-i][myLocation.x] != null)  { //piece at that location
					if (board[myLocation.y-i][myLocation.x].getColor() != myColor) {
						moves.add(new Point(myLocation.x, myLocation.y-i)); //different color piece (legal move)
					}
					break;
				} else {
					moves.add(new Point(myLocation.x, myLocation.y-i));
				}
			} else
				break;
		}
		
		//down squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.y+i < 8) {
				if (board[myLocation.y+i][myLocation.x] != null)  { //piece at that location
					if (board[myLocation.y+i][myLocation.x].getColor() != myColor) {
						moves.add(new Point(myLocation.x, myLocation.y+i)); //different color piece (legal move)
					}
					break;
				} else {
					moves.add(new Point(myLocation.x, myLocation.y+i));
				}
			}
			else
				break;
		}

		//left squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.x-i >= 0) {
				if (board[myLocation.y][myLocation.x-i] != null)  { //piece at that location
					if (board[myLocation.y][myLocation.x-i].getColor() != myColor) {
						moves.add(new Point(myLocation.x-i, myLocation.y)); //different color piece (legal move)
					}
					break;
				} else {
					moves.add(new Point(myLocation.x-i, myLocation.y));
				}
			}
			else
				break;
		}
		
		//right squares
		for(int i = 1; i < 8; i++) {
			if (myLocation.x+i < 8) {
				if (board[myLocation.y][myLocation.x+i] != null)  { //piece at that location
					if (board[myLocation.y][myLocation.x+i].getColor() != myColor) {
						moves.add(new Point(myLocation.x+i, myLocation.y)); //different color piece (legal move)
					}
					break;
				} else {
					moves.add(new Point(myLocation.x+i, myLocation.y));
				}
			} else
				break;
		}	
		return moves;
	}
	
	/**
	 * {@inheritDoc Piece.java}
	 */
	@Override
	public String toString() {
		return super.toString() + "R";
	}
}