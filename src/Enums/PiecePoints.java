package Enums;

public enum PiecePoints {
    PAWN(1),
    KNIGHT(3),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(1000);
    
    private final int myValue;
    
    PiecePoints(int theValue) {
      myValue = theValue;
    }
    
    public int getValue() {
      return myValue;
    }
}
