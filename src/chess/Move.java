package chess;

import Pieces.Piece;
import java.awt.*;

public class Move {

    public Piece piece;
    public Point from;
    public Point to;

    public Move(Piece piece, Point from, Point to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }
}
