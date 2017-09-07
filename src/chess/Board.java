package chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.awt.Point;

import Enums.PieceColor;
import Pieces.*;

public class Board {
	
	private Piece[][] myBoard;
	private King whiteKing;
	private King blackKing;
	
	public Board() {
		myBoard = new Piece[8][8];
	}
	
	/**
	 * Place a piece at that x and y. I used it for testing purposes.
	 * @param piece
	 * @param x
	 * @param y
	 */
	public void placePiece(Piece piece, int x, int y) {
		myBoard[x][y] = piece;
	}
	
	/**
	 * Temporary method to test the queening functionality of the board.
	 * initializes a couple pawns on the board
	 */
	public void initializeQueeningTest() {
		myBoard[2][2] = new Pawn(PieceColor.White, new Point(2, 2));
		myBoard[1][5] = new Pawn(PieceColor.Black, new Point(1, 5));
		
		//some pieces to make extra moves when needed
		blackKing = new King(PieceColor.Black, new Point(0, 4)); //top king
		myBoard[0][4] = blackKing;
		
		whiteKing =  new King(PieceColor.White, new Point(7, 4)); //bottom king
		myBoard[7][4] = whiteKing;
		
		myBoard[0][3] = new Queen(PieceColor.Black, new Point(0, 3)); //top queen
		myBoard[7][3] = new Queen(PieceColor.White, new Point(7, 3)); //bottom queen
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
		blackKing = new King(PieceColor.Black, new Point(0, 4)); //top king
		myBoard[0][4] = blackKing;
		myBoard[0][5] = new Bishop(PieceColor.Black, new Point(0, 5)); //top right bishop
		myBoard[0][6] = new Knight(PieceColor.Black, new Point(0, 6)); //top right knight
		myBoard[0][7] = new Rook(PieceColor.Black, new Point(0, 7)); //top right rook
		
		initializePawns(1, PieceColor.Black);
		
		// set up white major pieces
		myBoard[7][0] = new Rook(PieceColor.White, new Point(7, 0)); //bottom left rook
		myBoard[7][1] = new Knight(PieceColor.White, new Point(7, 1)); //bottom left knight
		myBoard[7][2] = new Bishop(PieceColor.White, new Point(7, 2)); //bottom left bishop
		myBoard[7][3] = new Queen(PieceColor.White, new Point(7, 3)); //bottom queen
		whiteKing =  new King(PieceColor.White, new Point(7, 4)); //bottom king
		myBoard[7][4] = whiteKing;
		myBoard[7][5] = new Bishop(PieceColor.White, new Point(7, 5)); //bottom right bishop
		myBoard[7][6] = new Knight(PieceColor.White, new Point(7, 6)); //bottom right knight
		myBoard[7][7] = new Rook(PieceColor.White, new Point(7, 7)); //bottom right rook
		
		initializePawns(6, PieceColor.White);
	}
	
	public Piece[][] getMyBoardArray() {
		return myBoard;
	}
	
	//returns the location of the king of the passed in color
	public Point getKingLocation(PieceColor color) {
		if(color == PieceColor.White)
			return whiteKing.getLocation();
		else 
			return blackKing.getLocation();
	}
	
	// Checks and returns whether the passed in Color pieces are attacking the opponent's King.
	private boolean checkCheck(PieceColor color) {
		List<Point> attackedSquares = getAttackedSquares(color);
		boolean returnValue = false;
		
//		for (Point p : attackedSquares)
//			System.out.println("[" + p.x + ", " + p.y + "]");
		
		//System.out.println("White King: [" + whiteKing.getLocation().x + ", " + whiteKing.getLocation().y + "]");
		//System.out.println("Black King: [" + blackKing.getLocation().x + ", " + blackKing.getLocation().y + "]");
		
		if (color == PieceColor.White && attackedSquares.contains(blackKing.getLocation())) {
			returnValue = true;
			System.out.println("CHECK");
		} else if (color == PieceColor.Black && attackedSquares.contains(whiteKing.getLocation())) {
			returnValue =  true;
			System.out.println("CHECK");
		}
		
		return returnValue;
	}
	
	/**
	 * Method to get all of the squares that the passed in color pieces are attacking.
	 * 
	 * @param color
	 * @return List<Point> attacked squares
	 */
	public List<Point> getAttackedSquares(PieceColor color) {
		HashSet<Point> set = new HashSet<Point>();
		
		for(Piece[] row : myBoard) {
			for(Piece piece : row) {
				if (piece != null && piece.getColor() == color) {
					List<Point> pieceAttacking = piece.getAvailableMoves(myBoard);
					for(Point point : pieceAttacking) {
						set.add(new Point((int) point.getY(), (int)point.getX()));
						//set.add(point);
					}
				}
			}
		}
		
		List<Point> allAttackedSquares = new ArrayList<Point>();
		allAttackedSquares.addAll(set);
		return allAttackedSquares;
	}
	
	/**
	 * Moves from from point to to point.
	 * return whether move puts the other king in check.
	 * @param from
	 * @param to
	 */
	public boolean move(Point from, Point to) {
		if (!from.equals(to)) {
			Piece movingPiece = myBoard[from.y][from.x];
			
			movingPiece.setXY(to.y, to.x);
			
			//setting double square move for the pawn
			if (movingPiece instanceof Pawn && ((Pawn)movingPiece).isFirstMove()) {
				((Pawn)movingPiece).setMoved(); //setting that the pawn has been moved.
				if (Math.abs(to.y - from.y) == 2) {
					((Pawn)movingPiece).setMovedTwoSquares();
				}
			}
			
			if (movingPiece instanceof King && !((King)movingPiece).hasMoved()) {
				((King)movingPiece).setMoved();
			}
			
			if (movingPiece instanceof Rook && !((Rook)movingPiece).hasMoved()) {
				((Rook)movingPiece).setMoved();
			}
			
			//en passant take
			if (movingPiece instanceof Pawn && Math.abs(to.x - from.x) == 1) { //pawn took diagonally
				if (movingPiece.isWhite()) {
					myBoard[to.y + 1][to.x] = null;
				} else {
					myBoard[to.y - 1][to.x] = null;
				}
			}
			
			//castles
			if(movingPiece instanceof King && Math.abs(to.x - from.x) > 1) {
				if (to.x - from.x > 0) {//right castle
					Rook rightRook = (Rook) myBoard[from.y][from.x+3];
					myBoard[from.y][from.x+3] = null;
					myBoard[from.y][from.x+1] = rightRook;
				} else { //left castle
					Rook leftRook = (Rook) myBoard[from.y][from.x-4];
					myBoard[from.y][from.x-4] = null;
					myBoard[from.y][from.x-1] = leftRook;
				}
			}
			
			myBoard[from.y][from.x] = null;
			myBoard[to.y][to.x] = movingPiece;
			
			return checkCheck(movingPiece.getColor());
		}
		return false;
	}
	
	/**
	 * Moves from from point to to point, upgrading it to the piece in the char upgrade.
	 * If an invalid upgrade char is passed, the piece will remain a Pawn.
	 * 
	 * @param from
	 * @param to
	 * @param upgrade 'Q' for queen and so on
	 */
	public boolean move(Point from, Point to, char upgrade) {
		if (!from.equals(to)) {
			Piece movingPiece = myBoard[from.y][from.x];
			
			if (upgrade == 'Q') {
				movingPiece = new Queen(movingPiece.getColor(), new Point(to.y, to.x));
			} else if (upgrade == 'R') {
				movingPiece = new Rook(movingPiece.getColor(), new Point(to.y, to.x));
			} else if (upgrade == 'B') {
				movingPiece = new Bishop(movingPiece.getColor(), new Point(to.y, to.x));
			} else if (upgrade == 'N') {
				movingPiece = new Knight(movingPiece.getColor(), new Point(to.y, to.x));
			} else { //just in case
				movingPiece.setXY(to.y, to.x);
			}
			
			myBoard[from.y][from.x] = null;
			myBoard[to.y][to.x] = movingPiece;
			
			return checkCheck(movingPiece.getColor());
		}
		return false;
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
	 * Returns a piece on the board. 0,0 is top left, 7,7 is bottom right.
	 * 
	 * @return a piece in that coordinate.
	 */
	public Piece getPiece(int y, int x) {
		Piece result = myBoard[y][x];
		if (result != null)
			return result;
		else
			return null;
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
	public void printAllowedMoves(Piece thePiece) {
		String[][] myPlot = new String[8][8];
		//Piece daPiece = myBoard[thePiece.getX()][thePiece.getY()];
		List<Point> moves = thePiece.getAvailableMoves(myBoard);
		System.out.println(thePiece.toString() + " " + thePiece.getColor());
		System.out.println(moves.size());
		
		myPlot[thePiece.getX()][thePiece.getY()] = "O";
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

