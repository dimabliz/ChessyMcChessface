package chess;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Enums.PieceColor;
import Pieces.*;

public class PieceTesting {

	public static String[][] myTestBoard = new String[8][8];
	
	public static void main(String[] args) {
		
		Pawn pawn = new Pawn(PieceColor.White, new Point(6, 2));
		insertPoint("O", 6, 2);
		inserts("X", pawn.getAvailableMoves());
		System.out.println(pawn.getAvailableMoves().size());
		
		printBoard();

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
