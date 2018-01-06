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

    private List<Move> getAllPossibleMoves(PieceColor turn) {
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
        return allPossibleMoves;
    }

    // Making a random move selection
    public void makeMoveLevel0(PieceColor turn) {
        List<Move> allPossibleMoves = getAllPossibleMoves(turn);

        Random randy = new Random();
        Move finalMove = allPossibleMoves.get(randy.nextInt(allPossibleMoves.size()));

        myGameBoard.move(new Point((int) finalMove.from.getY(), (int)finalMove.from.getX()),
                new Point((int) finalMove.to.getY(), (int)finalMove.to.getX()));
    }

    // Looking to take the most valuable ratio exchange
    public void makeMoveLevel1(PieceColor turn) {
        List<Move> allPossibleMoves = getAllPossibleMoves(turn);

        double maxRatio = -100.0;

        for (int i = 0; i < allPossibleMoves.size(); i++) {
            Move move = allPossibleMoves.get(i);

            double tempRatio;
            if (myGameBoard.getPiece(move.to) == null) {
                tempRatio = 0.0;
            } else {
                tempRatio = myGameBoard.getPiece(move.to).getPointValue();
            }
            if (myGameBoard.isQueening(move.from)) {
                System.out.println("queening " + move.from.toString());
                move.queening = true;
                tempRatio += 9.0; //worth of a queen
            }else {
                System.out.println("not queening");
            }
            move.ratio = tempRatio - myGameBoard.getPiece(move.from).getPointValue();
            if (move.ratio > maxRatio) {
                maxRatio = move.ratio;
            }
        }

        List<Move> sameRatioMoves = new ArrayList<>();
        for (Move move : allPossibleMoves) {
            if (move.ratio == maxRatio) {
                sameRatioMoves.add(move);
            }
        }

        Random randy = new Random();
        Move finalMove = sameRatioMoves.get(randy.nextInt(sameRatioMoves.size()));

        System.out.println("Ratio is " + maxRatio);
        System.out.println("queening is " + finalMove.queening);


        if (finalMove.queening) {
            System.out.println(finalMove.from);
            System.out.println(finalMove.to);
            myGameBoard.move(new Point((int) finalMove.from.getY(), (int) finalMove.from.getX()),
                    new Point((int) finalMove.to.getY(), (int) finalMove.to.getX()), 'Q');
        } else {
            System.out.println(finalMove.from);
            System.out.println(finalMove.to);
            myGameBoard.move(new Point((int) finalMove.from.getY(), (int) finalMove.from.getX()),
                    new Point((int) finalMove.to.getY(), (int) finalMove.to.getX()));
        }
    }

    // Avoiding a checkmate on next move and looking for a checkmate current move.
    public void makeMoveLevel2(PieceColor turn) {
        List<Move> allPossibleMoves = getAllPossibleMoves(turn);
        boolean level2Used = false;
        myGameBoard.printBoard();
        System.out.println();
        // Code below is searching if any of the moves will checkmate the opponent
        Move checkmatingMove = null;
        for (Move move : allPossibleMoves) {
            Piece savePiece = myGameBoard.getPiece(move.to);
            myGameBoard.simpleMove(new Point((int) move.from.getY(), (int)move.from.getX()),
                    new Point((int) move.to.getY(), (int)move.to.getX()), true);
            myGameBoard.checkForCheckMate(myGameBoard.getLastPieceMoved().getColor() == PieceColor.White ? PieceColor.Black : PieceColor.White);
            if (myGameBoard.getCountPossibleMoves() == 0) {
                checkmatingMove = move;
            }
            myGameBoard.simpleMove(new Point((int) move.to.getY(), (int)move.to.getX()),
                    new Point((int) move.from.getY(), (int)move.from.getX()), false);
            if (savePiece != null)
                myGameBoard.placePiece(savePiece, move.to.y, move.to.x);
        }
        myGameBoard.printBoard();

        if (checkmatingMove != null) {
            System.out.println("Found the checkmating move. Get rekt. Take a bow son, you just lost to a superior mind.");
            myGameBoard.move(new Point((int) checkmatingMove.from.getY(), (int)checkmatingMove.from.getX()),
                    new Point((int) checkmatingMove.to.getY(), (int)checkmatingMove.to.getX()));
            level2Used = true;
        }

        // Code below is avoiding any checkmates that the opponent has in store for them the next move.


        if (!level2Used) {
            makeMoveLevel1(turn);
        }
    }


}
