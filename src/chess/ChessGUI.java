package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Pieces.Piece;

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
	private Board myBoard = new Board();

	public ChessGUI() {
        super("ChessyMcChessface");
	}
	
	public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setMinimumSize(new Dimension(800, 600));
        setResizable(false);
        
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.DARK_GRAY);
        gamePanel.add(createBoard(), BorderLayout.WEST);
        add(gamePanel, BorderLayout.NORTH);
        
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
	
	public void showAvailableSquares(int y, int x) {
		setCheckeredColor();
		Piece current = myBoard.getPiece(y, x);
		List<Point> moves = current.getAvailableMoves(myBoard.getMyBoardArray());
		for(Point point : moves) {
			getSquare(point.y, point.x).setBackground(Color.GREEN);
		}
	}
	
	/**
	 * To put the names of the pieces inside the Square.
	 */
	private void initializeNames() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (myBoard.getPiece(j, i) != null) {
					getSquare(j, i).setText(myBoard.getPiece(j, i).toString());
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
	
	public static class BoxListener extends MouseAdapter {
		ChessGUI myBoard;
		public BoxListener(ChessGUI theBoard) {
			myBoard = theBoard;
		}
    	public void mouseClicked(MouseEvent me) {
            Square clickedBox = (Square) me.getSource(); 
            clickedBox.setBackground(Color.PINK);
            myBoard.showAvailableSquares(clickedBox.getMyY(), clickedBox.getMyX());
        }
    }
}
