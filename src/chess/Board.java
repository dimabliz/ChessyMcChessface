package chess;

import java.util.List;
import java.awt.Point;

import Enums.PieceColor;
import Pieces.*;

public class Board {
	
	private Piece[][] myBoard;
	
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
		
		initializePawns(1, PieceColor.Black);
		
		// set up white major pieces
		myBoard[7][0] = new Rook(PieceColor.White, new Point(7, 0)); //bottom left rook
		myBoard[7][1] = new Knight(PieceColor.White, new Point(7, 1)); //bottom left knight
		myBoard[7][2] = new Bishop(PieceColor.White, new Point(7, 2)); //bottom left bishop
		myBoard[7][3] = new Queen(PieceColor.White, new Point(7, 3)); //bottom queen
		myBoard[7][4] = new King(PieceColor.White, new Point(7, 4)); //bottom king
		myBoard[7][5] = new Bishop(PieceColor.White, new Point(7, 5)); //bottom right bishop
		myBoard[7][6] = new Knight(PieceColor.White, new Point(7, 6)); //bottom right knight
		myBoard[7][7] = new Rook(PieceColor.White, new Point(7, 7)); //bottom right rook
		
		initializePawns(6, PieceColor.White);
	}
	
	/**
	 * Initializes one full row with pawns provided the color.
	 * @param row
	 * @param color
	 */
	private void initializePawns(int row, PieceColor color) {
		for(int i = 0; i < 8; i++) {
			myBoard[row][i] = new Pawn(color, new Point(row, i));
		}
	}
	
	/**
	 * Prints the current state of the board.
	 */
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
	
	/**
	 * Prints out all of the moves that the Piece at the given point is allowed to make.
	 * O is the piece.
	 * X is where it can go.
	 * 
	 * @Before A piece has to exist at that point or null pointer exception will be thrown.
	 * 
	 * @param piece
	 */
	public void printAllowedMoves(Point point) {
		String[][] myPlot = new String[8][8];
		Piece daPiece = myBoard[point.y][point.x];
		List<Point> moves = daPiece.getAvailableMoves(myBoard);
		System.out.println(daPiece.toString() + " " + daPiece.getColor());
		System.out.println(moves.size());
		
		myPlot[point.y][point.x] = "O";
		for(Point p : moves) {
			myPlot[p.y][p.x] = "X";
		}
		
		for(String[] row : myPlot) {
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

