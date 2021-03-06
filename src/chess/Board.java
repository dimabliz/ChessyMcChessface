package chess;

import java.util.*;

import java.awt.Point;

import Enums.PieceColor;
import Pieces.*;
import AI.*;
/**
 * 
 * @author Maksimv@uw.edu
 *
 */
public class Board {
	
	private Piece[][] myBoard;
	private King whiteKing;
	private King blackKing;
	/** Keeps track of the last pice that was moved. Will be helpful in en passant. */
	private Piece lastPieceMoved;
	
	/* PAWN */
	/** Keeps track to check if the last piece moved was also a double jump. Needed for en-passant*/
	private boolean lastPieceMovedDouble;
	/** Keeps track of whether we are undoing an en-passant move in simpleMove */
	private boolean undoEnPassant;
	/** Keeps track of the pawn that we are undoing. */
	private Pawn undoPawn;
	/** Keeps track of the location where we are putting the pawn back. */
	private Point undoLocation;
	
	/* ROOK */
	/** Keeps track of the rook that we are undoing. */
	private Rook undoRook;
	/** Keeps track of whether we are undoing a castle. */
	private boolean undoCastle;
	/** Keeps track of the location where we are putting the rook back. */
	private Point undoRookLocation;

	private Queue<Move> repititionMoves;
	
	/* check/stale mate */
	/** Keeps track of the number of possible moves the next opponent can do. */
	private int countPossibleMoves;

	/* Fields pertaining to the AI. */
	private AI myAI;
	
	public Board() {
		myBoard = new Piece[8][8];
		lastPieceMoved = null;
		lastPieceMovedDouble = false;
		undoEnPassant = false;
		undoPawn = null;
		undoLocation = null;
		undoRook = null;
		undoCastle = false;
		undoRookLocation = null;
		countPossibleMoves = 1;
		myAI = new AI(this);
        repititionMoves = new LinkedList<Move>();
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
		myBoard[2][2] = new Pawn(PieceColor.White, new Point(2, 2), this);
		myBoard[1][5] = new Pawn(PieceColor.Black, new Point(1, 5), this);

		myBoard[3][3] = new Rook(PieceColor.Black, new Point(3, 3), this);
        myBoard[2][5] = new Rook(PieceColor.White, new Point(2, 5), this);
		
		//some pieces to make extra moves when needed
		blackKing = new King(PieceColor.Black, new Point(0, 4), this); //top king
		myBoard[0][4] = blackKing;
		lastPieceMoved = blackKing;
		
		whiteKing =  new King(PieceColor.White, new Point(7, 4), this); //bottom king
		myBoard[7][4] = whiteKing;
		
		myBoard[0][3] = new Queen(PieceColor.Black, new Point(0, 3), this); //top queen
		myBoard[7][3] = new Queen(PieceColor.White, new Point(7, 3), this); //bottom queen
	}
	
	/** 
	 * Initializes a stalemate to check if it's working.
	 */
	public void initializeStalemateTest() {
		blackKing = new King(PieceColor.Black, new Point(0, 4), this);
		myBoard[0][4] = blackKing;
		lastPieceMoved = blackKing;
		
		myBoard[1][4] = new Pawn(PieceColor.White, new Point(1, 4), this);
		
		whiteKing = new King(PieceColor.White, new Point(3, 4), this);
		myBoard[3][4] = whiteKing;
		
		
	}
	
	/**
	 * Method to set the board into the beginning of the state of the game.
	 */
	public void initializePieces() {
		// set up black major pieces
		myBoard[0][0] = new Rook(PieceColor.Black, new Point(0, 0), this); //top left rook
		myBoard[0][1] = new Knight(PieceColor.Black, new Point(0, 1), this); //top left knight
		myBoard[0][2] = new Bishop(PieceColor.Black, new Point(0, 2), this); //top left bishop
		myBoard[0][3] = new Queen(PieceColor.Black, new Point(0, 3), this); //top queen
		blackKing = new King(PieceColor.Black, new Point(0, 4), this); //top king
		myBoard[0][4] = blackKing;
		lastPieceMoved = blackKing;
		myBoard[0][5] = new Bishop(PieceColor.Black, new Point(0, 5), this); //top right bishop
		myBoard[0][6] = new Knight(PieceColor.Black, new Point(0, 6), this); //top right knight
		myBoard[0][7] = new Rook(PieceColor.Black, new Point(0, 7), this); //top right rook
		
		initializePawns(1, PieceColor.Black);
		
		// set up white major pieces
		myBoard[7][0] = new Rook(PieceColor.White, new Point(7, 0), this); //bottom left rook
		myBoard[7][1] = new Knight(PieceColor.White, new Point(7, 1), this); //bottom left knight
		myBoard[7][2] = new Bishop(PieceColor.White, new Point(7, 2), this); //bottom left bishop
		myBoard[7][3] = new Queen(PieceColor.White, new Point(7, 3), this); //bottom queen
		whiteKing =  new King(PieceColor.White, new Point(7, 4), this); //bottom king
		myBoard[7][4] = whiteKing;
		myBoard[7][5] = new Bishop(PieceColor.White, new Point(7, 5), this); //bottom right bishop
		myBoard[7][6] = new Knight(PieceColor.White, new Point(7, 6), this); //bottom right knight
		myBoard[7][7] = new Rook(PieceColor.White, new Point(7, 7), this); //bottom right rook
		
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
	
	/** 
	 * Sets the whiteKings location.
	 * 
	 * @param theLoc is the loc to set the whiteKing to
	 */
	public void setWhiteKingLocation(Point theLoc) {
		whiteKing.setXY(theLoc.x, theLoc.y);
	}
	
	/**
	 * Sets the blackKings location.
	 * 
	 * @param theLoc is the loc to set the blackKing to
	 */
	public void setBlackKingLocation(Point theLoc) {
		blackKing.setXY(theLoc.x, theLoc.y);
	}
	
	// Checks and returns whether the passed in Color pieces are attacking the opponent's King.
	public boolean checkCheck(PieceColor color) {
		List<Point> attackedSquares = getAttackedSquaresNoKing(color);
		boolean returnValue = false;
		
		if (color == PieceColor.White && attackedSquares.contains(blackKing.getLocation())) {
			returnValue = true;
		} else if (color == PieceColor.Black && attackedSquares.contains(whiteKing.getLocation())) {
			returnValue =  true;
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
	 * Method to get all of the squares that the passed in color pieces are attacking.
	 * All squares except for the squares the king is attacking.
	 * 
	 * @param color
	 * @return List<Point> attacked squares
	 */
	public List<Point> getAttackedSquaresNoKing(PieceColor color) {
		HashSet<Point> set = new HashSet<Point>();
		
		for(Piece[] row : myBoard) {
			for(Piece piece : row) {
				if (piece != null && piece.getColor() == color && !(piece instanceof King)) {
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
     * Method that will make computer move given the piece color.
     * Rn it's random.
     *
     *
     * @param turn what color the computer is going to move.
     */
	public void makeComputerMove(PieceColor turn) {
	    myAI.makeMoveLevel1(turn);
    }
	
	/**
	 * Moves from from point to to point without checking for a check.
	 * Method used to avoid circular checks of checks.
	 * 
	 * it's confusing because in AbstractPiece, when we check for checks we use simpleMove to make a move, then simpleMove to move the pieces back.
	 * This gets confusing when the move is an en-passant or a castle. For example, if we do en-passant moving forwards, we get rid fo the piece,
	 * then when we move back it does not add the piece back to its correct location.
	 * 
	 * @param from
	 * @param to
	 */
	public void simpleMove(Point from, Point to, boolean movingForward) {
		if (!from.equals(to)) {
			Piece movingPiece = myBoard[from.y][from.x];
			movingPiece.setXY(to.y, to.x);
			
			//en passant take
			if (movingPiece instanceof Pawn && Math.abs(to.x - from.x) == 1 && movingForward ) { // check that pawn took diagonally
				if (movingPiece.isWhite()) {
					if (myBoard[to.y + 1][to.x] instanceof Pawn && myBoard[to.y + 1][to.x].equals(lastPieceMoved)
							&& myBoard[to.y][to.x] == null && lastPieceMovedDouble) {
						undoEnPassant = true;
						undoPawn = (Pawn) myBoard[to.y + 1][to.x];
						undoLocation = new Point(to.y + 1, to.x);
						myBoard[to.y + 1][to.x] = null;
					}
				} else {
					if (myBoard[to.y - 1][to.x] instanceof Pawn && myBoard[to.y - 1][to.x].equals(lastPieceMoved)
							&& myBoard[to.y][to.x] == null && lastPieceMovedDouble) {
						undoEnPassant = true;
						undoPawn = (Pawn) myBoard[to.y - 1][to.x];
						undoLocation = new Point(to.y - 1, to.x);
						myBoard[to.y - 1][to.x] = null;
					}
				}
			} else if (undoEnPassant) {// undo a en passant take
				undoEnPassant = false;
				myBoard[undoLocation.x][undoLocation.y] = undoPawn;
			}
			
			//castles
			if(movingPiece instanceof King && Math.abs(to.x - from.x) > 1 && movingForward) {
				if (to.x - from.x > 0 && myBoard[from.y][from.x + 3] instanceof Rook) {//right castle
					Rook rightRook = (Rook) myBoard[from.y][from.x+3];
					undoRook = rightRook;
					undoCastle = true;
					undoRookLocation = new Point(from.y, from.x + 3);
					myBoard[from.y][from.x+3] = null;
					myBoard[from.y][from.x+1] = rightRook;
				} else if (myBoard[from.y][from.x - 4] instanceof Rook) { //left castle
					Rook leftRook = (Rook) myBoard[from.y][from.x-4];
					undoRook = leftRook;
					undoCastle = true;
					undoRookLocation = new Point(from.y, from.x - 4);
					myBoard[from.y][from.x-4] = null;
					myBoard[from.y][from.x-1] = leftRook;
				}
			} else if (undoCastle) {
				undoCastle = false;
				myBoard[undoRookLocation.x][undoRookLocation.y] = undoRook;
				if (undoRookLocation.y == 7)
					myBoard[to.y][to.x + 1] = null;
				else
					myBoard[to.y][to.x - 1] = null;
			}
			
			myBoard[from.y][from.x] = null;
			myBoard[to.y][to.x] = movingPiece;
			
		}
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
			if (movingPiece == null) {
			    System.err.println("Moving from location returns null");
			    return false;
            }
			// set it to false by default, if piece really moved double then it will be set in the condition below.
			lastPieceMovedDouble = false;
			
			movingPiece.setXY(to.y, to.x);
			
			//setting double square move for the pawn
			if (movingPiece instanceof Pawn && ((Pawn)movingPiece).isFirstMove()) {
				//((Pawn)movingPiece).setMoved(); //setting that the pawn has been moved.
				if (Math.abs(to.y - from.y) == 2) {
					lastPieceMovedDouble = true;
					((Pawn)movingPiece).setMoved();
					((Pawn)movingPiece).setMovedTwoSquares();
				}
			}
			
			if (movingPiece instanceof Pawn)
				((Pawn)movingPiece).setMoved();
			
			if (movingPiece instanceof King && !((King)movingPiece).hasMoved()) {
				((King)movingPiece).setMoved();
			}
			
			if (movingPiece instanceof Rook && !((Rook)movingPiece).hasMoved()) {
				((Rook)movingPiece).setMoved();
			}
			
			//en passant take
			if (movingPiece instanceof Pawn && Math.abs(to.x - from.x) == 1) { //pawn took diagonally
				if (movingPiece.isWhite()) {
					if (myBoard[to.y + 1][to.x] instanceof Pawn && myBoard[to.y + 1][to.x].equals(lastPieceMoved))
						myBoard[to.y + 1][to.x] = null;
				} else {
					if (myBoard[to.y - 1][to.x] instanceof Pawn && myBoard[to.y - 1][to.x].equals(lastPieceMoved))
						myBoard[to.y - 1][to.x] = null;
				}
			}
			
			//castles
			if(movingPiece instanceof King && Math.abs(to.x - from.x) > 1) {
				if (to.x - from.x > 0) {//right castle
					Rook rightRook = (Rook) myBoard[from.y][from.x+3];
					rightRook.setXY(from.y, from.x + 1);
					myBoard[from.y][from.x+3] = null;
					myBoard[from.y][from.x+1] = rightRook;
				} else { //left castle
					Rook leftRook = (Rook) myBoard[from.y][from.x-4];
					leftRook.setXY(from.y, from.x - 1);
					myBoard[from.y][from.x-4] = null;
					myBoard[from.y][from.x-1] = leftRook;
				}
			}
			
			myBoard[from.y][from.x] = null;
			myBoard[to.y][to.x] = movingPiece;
			lastPieceMoved = movingPiece;

            if (repititionMoves.size() > 7) {
                repititionMoves.remove();
            }

			repititionMoves.add(new Move(from, to));
			checkForCheckMate(movingPiece.getColor());

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
				movingPiece = new Queen(movingPiece.getColor(), new Point(to.y, to.x), this);
			} else if (upgrade == 'R') {
				movingPiece = new Rook(movingPiece.getColor(), new Point(to.y, to.x), this);
			} else if (upgrade == 'B') {
				movingPiece = new Bishop(movingPiece.getColor(), new Point(to.y, to.x), this);
			} else if (upgrade == 'N') {
				movingPiece = new Knight(movingPiece.getColor(), new Point(to.y, to.x), this);
			} else { //just in case
				movingPiece.setXY(to.y, to.x);
			}
			
			myBoard[from.y][from.x] = null;
			myBoard[to.y][to.x] = movingPiece;
			lastPieceMoved = movingPiece;
			checkForCheckMate(movingPiece.getColor());

			Move repeatedMove = new Move(from, to);
			repeatedMove.queening = true;
			if (repititionMoves.size() > 7) {
			    repititionMoves.remove();
            }
            repititionMoves.add(repeatedMove);
 
			return checkCheck(movingPiece.getColor());
		}
		return false;
	}

    /**
     * Checks if a draw was achieved where the past 8 moves were repeated.
     *
     * @return
     */
	public boolean isDrawByRepetition() {
        if (repititionMoves.size() < 8) {
            return false;
        }
        Move first = repititionMoves.remove();
        Move second = repititionMoves.remove();
        Move third = repititionMoves.remove();
        Move fourth = repititionMoves.remove();
        Move fifth = repititionMoves.remove();
        Move sixth = repititionMoves.remove();
        Move seventh = repititionMoves.remove();
        Move eight = repititionMoves.remove();

//        System.out.println("first: from " + first.from.toString() + " to " + first.to.toString());
//        System.out.println("second: from " + second.from.toString() + " to " + second.to.toString());
//        System.out.println("third: from " + third.from.toString() + " to " + third.to.toString());
//        System.out.println("fourth: from " + fourth.from.toString() + " to " + fourth.to.toString());
//
//        System.out.println("fifth: from " + fifth.from.toString() + " to " + fifth.to.toString());
//        System.out.println("sixth: from " + sixth.from.toString() + " to " + sixth.to.toString());
//        System.out.println("seventh: from " + seventh.from.toString() + " to " + seventh.to.toString());
//        System.out.println("eight: from " + eight.from.toString() + " to " + eight.to.toString());

        System.out.println();

        repititionMoves.add(first);
        repititionMoves.add(second);
        repititionMoves.add(third);
        repititionMoves.add(fourth);
        repititionMoves.add(fifth);
        repititionMoves.add(sixth);
        repititionMoves.add(seventh);
        repititionMoves.add(eight);

        if ((first.to).equals(fifth.to) && (first.from).equals(fifth.from)
                && (second.to).equals(sixth.to) && (second.from).equals(sixth.from)
                && (third.to).equals(seventh.to) && (third.from).equals(seventh.from)
                && (fourth.to).equals(eight.to) && (fourth.from).equals(eight.from))
            return true;
        else return false;

    }

	public void checkForCheckMate(PieceColor movingColor) {
		// Need to check for a checkmate
		PieceColor myColor = lastPieceMoved.getColor() == PieceColor.White ? PieceColor.Black : PieceColor.White;
			int totalMovesAvailable = 0;

			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {
					if (myBoard[i][j] != null && myBoard[i][j].getColor() == myColor)
						totalMovesAvailable += myBoard[i][j].getAvailableMoves(myBoard).size();
				}
			}

			//System.out.println(myColor + "s move, there are " + totalMovesAvailable + " total moves available\n");
			countPossibleMoves = totalMovesAvailable;
	}
	
	/**
	 * Returns the number of possible moves the opponent can perform.
	 * 
	 * @return the countPossibleMoves
	 */
	public int getCountPossibleMoves() {
		return countPossibleMoves;
	}
	
	/**
	 * Initializes one full row with pawns provided the color.
	 * @param row
	 * @param color
	 */
	private void initializePawns(int row, PieceColor color) {
		for(int i = 0; i < 8; i++) {
			myBoard[row][i] = new Pawn(color, new Point(row, i), this);
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

    public Piece getPiece(Point point) {
        Piece result = myBoard[point.x][point.y];
        if (result != null)
            return result;
        else
            return null;
    }

	/**
	 * Returns the AI object.
	 *
	 * @return the AI object.
	 */
	public AI getAI() {
		return myAI;
	}
	
	/**
	 * Prints out all of the moves that the Piece at the given point is allowed to make.
	 * O is the piece.
	 * X is where it can go.
	 * 
	 * @Before A piece has to exist at that point or null pointer exception will be thrown.
	 * 
	 * @param thePiece
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

	//Checks if a piece is a pawn that's about to queen
	public boolean isQueening(Point firstClick) {
		Piece queening = getPiece(firstClick.x, firstClick.y);
		System.out.print(queening);
		boolean returnValue = false;
		if (queening instanceof Pawn) {
			if (queening.isWhite() && firstClick.x == 1) {
				returnValue = true;
			} else if (!queening.isWhite() && firstClick.x == 6) {
				returnValue = true;
			}
		}
		return returnValue;
	}

    /**
     * Get method for lastPieceMoved.
     *
     * @return the lastPieceMoved.
     */
	public Piece getLastPieceMoved() {
	    return lastPieceMoved;
    }
	
	/** 
	 * Get method for lastPieceMOvedDOuble
	 * 
	 * @return the lastPieceMovedDouble
	 */
	public boolean getLastPieceMovedDouble() {
		return lastPieceMovedDouble;
	}
}

