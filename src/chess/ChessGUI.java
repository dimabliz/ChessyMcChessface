package chess;

import java.awt.Dimension;

import javax.swing.JFrame;

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
        
        setMinimumSize(new Dimension(600, 400));
        setResizable(false);
        
        pack();
        setVisible(true);
	}
}
