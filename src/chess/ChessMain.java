//Dmitriy Bliznyuk
//Maksim Voznyarskiy

package chess;

import java.awt.EventQueue;

public class ChessMain {

	/**
     * Private constructor, to prevent instantiation of this class.
     */
    private ChessMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the Chess GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChessGUI().start();
            }
        });
    }

}
