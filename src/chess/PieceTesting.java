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
		
		//board.initializePieces();
//		Piece kinginit = new Rook(PieceColor.White, new Point(3, 5));
//		board.placePiece(kinginit, 3, 5);
		//board.printBoard();
		
		//Piece king = board.getPiece(6, 2);
		//System.out.println(king);
		//board.printAllowedMoves(king);
	}
	
	public static void printAllBlackPawns() {
		Piece pawn;
		for (int i = 0; i <= 7; i++) {
			pawn = board.getPiece(1, i);
			System.out.println("\nNow printing avaliable moves of piece on [1][" + i + "] (" + (char)(i + 65) + "7) which is :" + pawn);
			board.printAllowedMoves(pawn);			
		}
	}
	
	public static void printAllWhitePawns() {
		Piece pawn;
		for (int i = 0; i <= 7; i++) {
			pawn = board.getPiece(6, i);
			System.out.println("\nNow printing avaliable moves of piece on [6][" + i + "] (" + (char)(i + 65) + "2) which is :" + pawn);
			board.printAllowedMoves(pawn);			
		}
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
