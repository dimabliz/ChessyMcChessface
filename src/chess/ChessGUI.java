package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8257636818570539745L;
	
	private JPanel board;
	private List<Square> squareList;

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
        getSquare(3, 6);
        
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
			if (Integer.parseInt(square.getName()) % 2 == even) {
				square.setBackground(Color.GRAY);
			}
		}
	}
	
	/**
	 * Creates the chess board panel and returns it.
	 * @return
	 */
	private JPanel createBoard() {
		squareList = new ArrayList<Square>();
		
		board = new JPanel(new GridLayout(8,8));
		board.setPreferredSize(new Dimension(500, 500));
		board.setBackground(Color.BLACK);
		board.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		
		for (int i = 0; i < 64; i++) {
			Square square = new Square(i);
			square.addMouseListener(new BoxListener());
        	board.add(square);
        	squareList.add(square);
        }
		
		return board;
	}
	
	public static class BoxListener extends MouseAdapter {
    	public void mouseClicked(MouseEvent me) {
            Square clickedBox = (Square) me.getSource(); 
            clickedBox.setBackground(Color.PINK);
        }
    }
}
