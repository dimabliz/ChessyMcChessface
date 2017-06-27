package chess;

import Pieces.*;

public class PieceTesting {

	public static String[][] myTestBoard = new String[8][8];
	
	public static void main(String[] args) {
		
		printBoard();

	}
	
	/**
	 * Prints the current state of the board.
	 */
	public static void printBoard() {
		for(String[] row : myTestBoard) {
			System.out.print("|");
			for(String piece : row) {
				if (piece == null)
					System.out.print("  |");
				else
					System.out.print(piece + "|");
			}
			System.out.println();
		}
	}

}
