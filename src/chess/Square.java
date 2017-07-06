package chess;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Square extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1717875125357590175L;
	
	
	
	public Square(String type) {
		setPreferredSize(new Dimension(50, 50));
		setBackground(Color.BLUE);
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setEnabled(true);
		setName(type);
		
	}

}
