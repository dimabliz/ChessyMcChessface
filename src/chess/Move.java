package chess;

import Pieces.Piece;
import java.awt.*;

public class Move {

    public Piece piece;
    public Point from;
    public Point to;
    public double ratio; // taking the piece versus the value of the piece doing the taking ratio
    public boolean queening;

    public Move(Piece piece, Point from, Point to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.queening = false;
    }

    public Move(Piece piece, Point from, Point to, double ratio) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.ratio = ratio;
        this.queening = false;
    }
}
