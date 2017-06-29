package chess;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Enums.PieceColor;
import Pieces.*;

public class PieceTesting {

	public static String[][] myTestBoard = new String[8][8];
	public static Board board = new Board();
	
	public static void main(String[] args) {
		
		board.initializePieces();
		board.printBoard();
		board.printAllowedMoves(new Point(1, 6));

	}
	
	public static void insertPoint(String what, int x, int y) {
		myTestBoard[y][x] = what;
	}
	
	public static void inserts(String what, List<Point> elements) {
		for (Iterator<Point> iterator = elements.iterator(); iterator.hasNext(); ) {
		    Point currentPoint = iterator.next();
		    myTestBoard[currentPoint.y][currentPoint.x] = what;
		}
	}
	
	/**
	 * Prints the current state of the board.
	 */
	public static void printBoard() {
		for(String[] row : myTestBoard) {
			System.out.print("|");
			for(String piece : row) {
				if (piece == null)
					System.out.print(" |");
				else
					System.out.print(piece + "|");
			}
			System.out.println();
		}
	}

}
