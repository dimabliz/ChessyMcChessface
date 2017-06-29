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
		
//		Pawn pawn = new Pawn(PieceColor.White, new Point(6, 2));
//		insertPoint("O", 6, 2);
//		inserts("p", pawn.getAvailableMoves());
//		System.out.println(pawn.getAvailableMoves().size());
		
//		King king = new King(PieceColor.White, new Point(0, 7));
//		insertPoint("O", 0, 7);
//		inserts("K", king.getAvailableMoves());
//		System.out.println(king.getAvailableMoves().size());
		
//		Knight knight = new Knight(PieceColor.White, new Point(6, 3));
//		insertPoint("O", 6, 3);
//		inserts("N", knight.getAvailableMoves());
//		System.out.println(knight.getAvailableMoves().size());
		
//		Bishop bishop = new Bishop(PieceColor.White, new Point(7, 7));
//		insertPoint("O", 7, 7);
//		inserts("B", bishop.getAvailableMoves());
//		System.out.println(bishop.getAvailableMoves().size());
		
//		Rook rook = new Rook(PieceColor.White, new Point(0, 0));
//		insertPoint("R", 0, 0);
//		inserts("X", rook.getAvailableMoves());
//		System.out.println(rook.getAvailableMoves().size());
		
		Queen queen = new Queen(PieceColor.White, new Point(1, 1));
		insertPoint("Q", 1, 1);
		inserts("X", queen.getAvailableMoves());
		System.out.println(queen.getAvailableMoves().size());
		
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
