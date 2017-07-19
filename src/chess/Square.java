package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

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
		label.setFont(new Font("Serif", Font.PLAIN, 18));
		
		//ImageIcon icon = new ImageIcon("./images/transparentpawn.png");
		//label.setIcon(icon);
		
		add(label);
	}
	
	public void setText(String text) {
		if(text.equals("")) {
			label.setIcon(new ImageIcon());
		} else if (text.equals("BP")) {
			ImageIcon icon = new ImageIcon("./images/blackPawn.png");
			label.setIcon(icon);
		} else if (text.equals("WP")) {
			ImageIcon icon = new ImageIcon("./images/whitePawn.png");
			label.setIcon(icon);
		}
	}
	
	public int getMyX() {
		return Integer.parseInt(this.getName()) % 8;
	}
	
	public int getMyY() {
		return Integer.parseInt(this.getName()) / 8;
	}
}
