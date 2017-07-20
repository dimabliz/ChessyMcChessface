package chess;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Square extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1717875125357590175L;
	
	JLabel label;
	
	public Square(int id) {
		setPreferredSize(new Dimension(50, 50));
		setBackground(Color.WHITE);
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setEnabled(true);
		setName(Integer.toString(id));	
		
		label = new JLabel();
		add(label);
	}
	
	public void setText(String text) {
		if(text.equals("")) {
			label.setIcon(new ImageIcon());
		} else if (text.startsWith("B")) {
			if (text.equals("BP")) {
				ImageIcon icon = new ImageIcon("./images/blackPawn.png");
				label.setIcon(icon);
			} else if (text.equals("BR")) {
				ImageIcon icon = new ImageIcon("./images/blackRook.png");
				label.setIcon(icon);
			} else if (text.equals("BB")) {
				ImageIcon icon = new ImageIcon("./images/blackBishop.png");
				label.setIcon(icon);
			} else if (text.equals("BN")) {
				ImageIcon icon = new ImageIcon("./images/blackKnight.png");
				label.setIcon(icon);
			} else if (text.equals("BQ")) {
				ImageIcon icon = new ImageIcon("./images/blackQueen.png");
				label.setIcon(icon);
			} else if (text.equals("BK")) {
				ImageIcon icon = new ImageIcon("./images/blackKing.png");
				label.setIcon(icon);
			}
		} else {
			if (text.equals("WP")) {
				ImageIcon icon = new ImageIcon("./images/whitePawn.png");
				label.setIcon(icon);
			} else if (text.equals("WR")) {
				ImageIcon icon = new ImageIcon("./images/whiteRook.png");
				label.setIcon(icon);
			} else if (text.equals("WB")) {
				ImageIcon icon = new ImageIcon("./images/whiteBishop.png");
				label.setIcon(icon);
			} else if (text.equals("WN")) {
				ImageIcon icon = new ImageIcon("./images/whiteKnight.png");
				label.setIcon(icon);
			} else if (text.equals("WQ")) {
				ImageIcon icon = new ImageIcon("./images/whiteQueen.png");
				label.setIcon(icon);
			} else if (text.equals("WK")) {
				ImageIcon icon = new ImageIcon("./images/whiteKing.png");
				label.setIcon(icon);
			}
		}
	}
	
	public int getMyX() {
		return Integer.parseInt(this.getName()) % 8;
	}
	
	public int getMyY() {
		return Integer.parseInt(this.getName()) / 8;
	}
}
