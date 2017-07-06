package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		add(new JLabel(text));
	}
}
