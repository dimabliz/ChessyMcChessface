package chess;

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
        
        add(createBoard());
        //add(new GridPane(8, 8));
        
        pack();
        setVisible(true);
	}
	
	/**
	 * Creates the chess board panel and returns it.
	 * @return
	 */
	private JPanel createBoard() {
		GridLayout layout = new GridLayout(8,8);
		layout.setHgap(4);
	    layout.setVgap(4);
		
		JPanel board = new JPanel(layout);
		board.setPreferredSize(new Dimension(500, 500));
		board.setBackground(Color.YELLOW);
		board.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; i++) {
				Square square = new Square(i + ", " + j);
				board.add(square);
			}
		}
		
		return board;
	}
	
	public static class GridPane extends JPanel {

        public GridPane(int row, int col) {

            int count = 0 ; // use to give a name to each box so that you can refer to them later
            setLayout(new GridLayout(row, col));
            setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

            for (int i = 1; i <= (row * col); i++) {
                JPanel pan = new JPanel();

                pan.setEnabled(true);
                pan.setBackground(Color.WHITE);
                pan.setPreferredSize(new Dimension(50, 50));
                pan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               // pan.addMouseListener(new BoxListener()); // add a mouse listener to make the panels clickable
                pan.setName(count+"");
                ++count;
                add(pan);
            }
        }
    }
}
