package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8257636818570539745L;

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
        
        pack();
        setVisible(true);
	}
	
	/**
	 * Creates the chess board panel and returns it.
	 * @return
	 */
	private JPanel createBoard() {
		
		JPanel board = new JPanel(new GridLayout(8,8));
		board.setPreferredSize(new Dimension(500, 500));
		board.setBackground(Color.BLACK);
		board.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		
		for (int i = 1; i <= 64; i++) {
        	board.add(new Square(i));
        }
		
		return board;
	}
}
