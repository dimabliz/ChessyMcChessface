
public interface Piece {
  
	boolean isWhite();
	
	int getX();
	
	int getY();
	
	void moveTo();
	
	int getPointValue();
	
	String toString();
  
}