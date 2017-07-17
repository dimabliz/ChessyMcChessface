package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Pieces.Piece;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class ChessGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8257636818570539745L;

	/**
	 * the GUI board that holds the squares.
	 */
	private JPanel guiboard;
	private List<Square> squareList;
	private Board myBoard;
	private JButton startButton;
	private JButton endButton;

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
		myBoard.initializePieces();
		
		initializeNames();

		pack();
		setVisible(true);
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
	public void showAvailableSquares(int y, int x) {
		setCheckeredColor();

		Piece clickedPiece = myBoard.getPiece(y, x);

		if (clickedPiece != null) {
			getSquare(y, x).setBackground(Color.CYAN);
			List<Point> moves = clickedPiece.getAvailableMoves(myBoard.getMyBoardArray());
			for(Point point : moves) {
				getSquare(point.y, point.x).setBackground(Color.GREEN);
			}
		}
	}

	public void refreshGUI() {
		initializeNames();
		setCheckeredColor();
		myBoard.printBoard();
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
				square.setBackground(Color.GRAY);
			else
				square.setBackground(Color.WHITE);
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

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setPreferredSize(new Dimension(800, 60));
		buttonPanel.setBackground(Color.BLACK);
		startButton = new JButton("Start Game");
		endButton = new JButton("End Game");
		buttonPanel.add(startButton);
		buttonPanel.add(endButton);
		
		return buttonPanel;
	}
	
	public static class BoxListener extends MouseAdapter {
		static ChessGUI myGui;
		static Point firstClick;
		static Point secondClick;
		static boolean isSecondClick;
		static List<Point> avaliableMoves;

		public BoxListener(ChessGUI theBoard) {
			myGui = theBoard;
			firstClick = null;
			secondClick = null;
			isSecondClick = false;
			avaliableMoves = null;
		}

		@Override
		public void mousePressed(MouseEvent theEvent) {
			if (isSecondClick) {
				Square secondSquare = (Square) theEvent.getSource(); 
				secondClick = new Point(secondSquare.getMyX(), secondSquare.getMyY());

				boolean isSecondClickLegalMove = false;
				for (Point possibleMove : avaliableMoves) {
					if (secondClick.equals(possibleMove))
						isSecondClickLegalMove = true;
				}

				if (isSecondClickLegalMove) {
					myGui.myBoard.move(firstClick, secondClick);
					myGui.refreshGUI();
				}		

				isSecondClick = false;
			} else {
				Square clickedBox = (Square) theEvent.getSource(); 
				myGui.showAvailableSquares(clickedBox.getMyY(), clickedBox.getMyX());
				Piece clickedPiece = myGui.myBoard.getPiece(clickedBox.getMyY(), clickedBox.getMyX());

				avaliableMoves = clickedPiece.getAvailableMoves(myGui.myBoard.getMyBoardArray());
				firstClick = new Point(clickedBox.getMyX(), clickedBox.getMyY());
				isSecondClick = true;
				
				myGui.myBoard.printAllowedMoves(clickedPiece);
			}
		}	
	}
}
