package chess;

import java.awt.Point;

import Enums.PieceColor;
import Pieces.Piece;
import Pieces.*;

public class Board {
	
	Piece[][] myBoard;
	
	public Board() {
		myBoard = new Piece[8][8];
	}
	
	/**
	 * Method to set the board into the beginning of the state of the game.
	 */
	public void initializePieces() {
		// set up black major pieces
		myBoard[0][0] = new Rook(PieceColor.Black, new Point(0, 0)); //top left rook
		myBoard[0][1] = new Knight(PieceColor.Black, new Point(0, 1)); //top left knight
		myBoard[0][2] = new Bishop(PieceColor.Black, new Point(0, 2)); //top left bishop
		myBoard[0][3] = new Queen(PieceColor.Black, new Point(0, 3)); //top queen
		myBoard[0][4] = new King(PieceColor.Black, new Point(0, 4)); //top king
		myBoard[0][5] = new Bishop(PieceColor.Black, new Point(0, 5)); //top right bishop
		myBoard[0][6] = new Knight(PieceColor.Black, new Point(0, 6)); //top right knight
		myBoard[0][7] = new Rook(PieceColor.Black, new Point(0, 7)); //top right rook
	}
	
	public void printBoard() {
		for(Piece[] row : myBoard) {
			System.out.print("|");
			for(Piece piece : row) {
				if (piece == null)
					System.out.print("  |");
				else
					System.out.print(piece.toString() + "|");
			}
			System.out.println();
		}
	}
}

