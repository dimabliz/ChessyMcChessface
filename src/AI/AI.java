package AI;

import chess.*;
import Enums.*;
import Pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AI that will play and learn the chess.
 *
 * @author dimabliz@uw.edu
 * @author maksimv@uw.edu
 */

public class AI {

    /** the board on which this AI plays. */
    private Board myGameBoard;

    /**
     * default constructor. Creates AI object.
     */
    public AI(Board theBoard) {
        myGameBoard = theBoard;
    }

    public void makeMoveLevel1(PieceColor turn) {
        List<Move> allPossibleMoves = new ArrayList<>();

        for(Piece[] row : myGameBoard.getMyBoardArray()) {
            for(Piece piece : row) {
                if (piece != null && piece.getColor() == turn) {
                    List<Point> pieceAttacking = piece.getAvailableMoves(myGameBoard.getMyBoardArray());
                    for(Point point : pieceAttacking) {
                        allPossibleMoves.add(new Move(piece, piece.getLocation(), new Point((int) point.getY(), (int)point.getX())));
                    }
                }
            }
        }

        Random randy = new Random();
        Move finalMove = allPossibleMoves.get(randy.nextInt(allPossibleMoves.size()));

        myGameBoard.move(new Point((int) finalMove.from.getY(), (int)finalMove.from.getX()),
                new Point((int) finalMove.to.getY(), (int)finalMove.to.getX()));
    }


}
