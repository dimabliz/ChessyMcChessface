package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Square extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1717875125357590175L;
	
	public Square(int id) {
		setPreferredSize(new Dimension(50, 50));
		setBackground(Color.WHITE);
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setEnabled(true);
		setName(Integer.toString(id));	
	}
	
	public void setText(String text) {
		removeAll();
		JLabel label = new JLabel(text);
		label.setFont(new Font("Serif", Font.PLAIN, 18));
		add(label);
	}
	
	public int getX() {
		return Integer.parseInt(this.getName()) % 8;
	}
	
	public int getY() {
		return Integer.parseInt(this.getName()) / 8;
	}
}
