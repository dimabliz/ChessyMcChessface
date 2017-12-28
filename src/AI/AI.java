package AI;

import chess.*;
import Enums.*;
import Pieces.*;

/**
 * AI that will play and learn the chess.
 *
 * @author dimabliz@uw.edu
 * @author maksimv@uw.edu
 */

public class AI {

    /** the board on which this AI plays. */
    private Board myBoard;
    /** The color of this AI. If white, it will play as white. */
    private PieceColor myColor;

    /**
     * default constructor. Creates AI object.
     */
    public AI(Board theBoard, PieceColor theColor) {
        myBoard = theBoard;
        myColor = theColor;
    }

    public void makeMove() {

    }


}
