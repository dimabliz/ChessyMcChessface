package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Enums.PieceColor;
import Pieces.Pawn;
import Pieces.Piece;

public class ChessGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8257636818570539745L;

	public static final Color darkerColor = new Color(151, 105, 79);
	public static final Color lighterColor = new Color(235, 199, 158);
	
	/**
	 * the GUI board that holds the squares.
	 */
	private JPanel guiboard;
	private List<Square> squareList;
	private Board myBoard;
	private JButton endButton;
	private boolean whiteTurn = true;

	public ChessGUI() {
		super("ChessyMcChessface");
		myBoard = new Board();
	}

	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setMinimumSize(new Dimension(800, 560));
		setResizable(false);

		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(Color.DARK_GRAY);
		gamePanel.add(createBoard(), BorderLayout.WEST);
		add(gamePanel, BorderLayout.NORTH);
		add(createButtonPanel(), BorderLayout.WEST);

		setCheckeredColor();
		//myBoard.initializePieces();
		//myBoard.initializeQueeningTest();
		myBoard.initializeStalemateTest();
		
		initializeNames();

		pack();
		setVisible(true);

	}
	
	/**
	 * Method that asks the user what piece they would like to promote their pawn.
	 * @return
	 */
	private char askForPromotedPiece() {
		//Custom button text
		Object[] options = {"Knight", "Bishop", "Rook", "Queen"};
		
		int chosenValue = JOptionPane.showOptionDialog(this,
		    "Choose a Piece",
		    "What do you want to promote your pawn to?",
		    JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    options,
		    options[3]);
		
		char chosenChar = ' ';
		if (chosenValue == 3) {
			chosenChar = 'Q';
		} else if (chosenValue == 2) {
			chosenChar = 'R';
		} else if (chosenValue == 1) {
			chosenChar = 'B';
		} else if (chosenValue == 0) {
			chosenChar = 'N';
		}
		return chosenChar;
	}

	/**
	 * Returns the right Square using x and y coordinates.
	 * @param x
	 * @param y
	 * @return
	 */
	public Square getSquare(int y, int x) {
		return squareList.get(y*8 + x);
	}

	/**
	 * Shows the available moves a piece can do that is on these y, x coordinates.
	 * If there is no piece at those coordinates, nothing happens.
	 * 
	 * @param y location on board.
	 * @param x location on board.
	 */
	public List<Point> showAvailableSquares(int y, int x) {
		setCheckeredColor();

		Piece clickedPiece = myBoard.getPiece(y, x);
		List<Point> moves = null;
		if (clickedPiece != null) {
			getSquare(y, x).setBackground(Color.CYAN);
			moves = clickedPiece.getAvailableMoves(myBoard.getMyBoardArray());
			for(Point point : moves) {
				getSquare(point.y, point.x).setBackground(Color.GREEN);
			}
		}
		return moves;
	}

	public void refreshGUI() {
		initializeNames();
		setCheckeredColor();
		//myBoard.printBoard();
	}
	

	/**
	 * To put the names of the pieces inside the Square.
	 */
	private void initializeNames() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (myBoard.getPiece(j, i) != null) {
					getSquare(j, i).setText(myBoard.getPiece(j, i).toString());
				} else {
					getSquare(j, i).setText("");
				}
			}
		}
	}

	/**
	 * Makes the board checkered.
	 */
	private void setCheckeredColor() {
		checkerRow(0, 1);
		checkerRow(8, 0);
		checkerRow(16, 1);
		checkerRow(24, 0);
		checkerRow(32, 1);
		checkerRow(40, 0);
		checkerRow(48, 1);
		checkerRow(56, 0);
	}

	private void checkerRow(int start, int even) {
		for (int i = start; i < start+8; i++) {
			Square square = squareList.get(i);
			if (Integer.parseInt(square.getName()) % 2 == even)
				square.setBackground(darkerColor);
			else
				square.setBackground(lighterColor);
		}
	}

	/**
	 * Creates the chess board panel and returns it.
	 * @return
	 */
	private JPanel createBoard() {
		squareList = new ArrayList<Square>();

		guiboard = new JPanel(new GridLayout(8,8));
		guiboard.setPreferredSize(new Dimension(500, 500));
		guiboard.setBackground(Color.BLACK);
		guiboard.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

		for (int i = 0; i < 64; i++) {
			Square square = new Square(i);
			square.addMouseListener(new BoxListener(this));
			guiboard.add(square);
			squareList.add(square);
		}

		return guiboard;
	}

	/**
	 * Creates and returns the bottom button panel.
	 * @return
	 */
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setPreferredSize(new Dimension(800, 60));
		buttonPanel.setBackground(Color.BLACK);
		
		endButton = new JButton("End Game");
		endButton.addActionListener((theEvent) -> {
            myBoard = new Board();
            start();
        });

		buttonPanel.add(endButton);
		
		return buttonPanel;
	}
	
	//Checks if a piece is a pawn that's about to queen
	private boolean isQueening(Point firstClick) {
		Piece queening = myBoard.getPiece(firstClick.y, firstClick.x);
		boolean returnValue = false;
		if (queening instanceof Pawn) {
			if (queening.isWhite() && firstClick.y == 1) {
				returnValue = true;
			} else if (!queening.isWhite() && firstClick.y == 6) {
				returnValue = true;
			}
		}
		return returnValue;
	}
	
	public static class BoxListener extends MouseAdapter {
		static ChessGUI myGui;
		static Point firstClick;
		static Point secondClick;
		static boolean isSecondClick;
		static List<Point> availableMoves;

		public BoxListener(ChessGUI theBoard) {
			myGui = theBoard;
			firstClick = null;
			secondClick = null;
			isSecondClick = false;
			availableMoves = null;
		}

		@Override
		public void mousePressed(MouseEvent theEvent) {
			if (isSecondClick) {
				secondClick(theEvent);
				
			} else {
				firstClick(theEvent);
			}
		}
		
		// Function to handle the event of a user picking a piece to move.
		// Needs the click event passed in.
		private void firstClick(MouseEvent theEvent) {
			Square clickedBox = (Square) theEvent.getSource(); 
			Piece clickedPiece = myGui.myBoard.getPiece(clickedBox.getMyY(), clickedBox.getMyX());
			
			if (clickedPiece != null && myGui.whiteTurn == clickedPiece.isWhite()) {
				availableMoves = myGui.showAvailableSquares(clickedBox.getMyY(), clickedBox.getMyX());
				//availableMoves = clickedPiece.getAvailableMoves(myGui.myBoard.getMyBoardArray());
				firstClick = new Point(clickedBox.getMyX(), clickedBox.getMyY());
				isSecondClick = true;
			}
			myGui.repaint();
		}
		
		// Function to handle the event if the user picked another square after already choosing a
		// legal piece to move. Needs the click event passed in.
		private void secondClick(MouseEvent theEvent) {
			boolean isCheck = false;
			Square secondSquare = (Square) theEvent.getSource(); 
			secondClick = new Point(secondSquare.getMyX(), secondSquare.getMyY());

			boolean isSecondClickLegalMove = false;
			for (Point possibleMove : availableMoves) {
				if (secondClick.equals(possibleMove))
					isSecondClickLegalMove = true;
			}

			if (isSecondClickLegalMove) {
				if (myGui.isQueening(firstClick)) {
					char promotedPiece = myGui.askForPromotedPiece();
					if (myGui.myBoard.move(firstClick, secondClick, promotedPiece)) { //if puts other side in check
						isCheck = true;
					}
				} else {
					if (myGui.myBoard.move(firstClick, secondClick)) { //if puts other side in check
						isCheck = true;
					}
				}
				myGui.refreshGUI();

				//System.out.println("get count possible moves" + myGui.myBoard.getCountPossibleMoves());

				if (isCheck) {
					Point theKing = myGui.myBoard.getKingLocation(myGui.whiteTurn ? PieceColor.Black : PieceColor.White);
					myGui.getSquare(theKing.x, theKing.y).setBackground(Color.RED);
					isCheck = false;
					
					if (myGui.myBoard.getCountPossibleMoves() == 0) {
						PieceColor winner = myGui.myBoard.getLastPieceMoved().getColor();
						JOptionPane.showMessageDialog(null, winner + " wins by checkmate");
					}
					
				} else if (myGui.myBoard.getCountPossibleMoves() == 0) {
					JOptionPane.showMessageDialog(null, "Stalemate");
				}
				myGui.whiteTurn = !myGui.whiteTurn;
				isSecondClick = false;
			}
			
			Piece clickedPiece = myGui.myBoard.getPiece(secondSquare.getMyY(), secondSquare.getMyX());
			if (clickedPiece != null && clickedPiece.isWhite() == myGui.whiteTurn) { //user chose another piece to move
				firstClick(theEvent);
			}
            myGui.repaint();
		}
	}
}
