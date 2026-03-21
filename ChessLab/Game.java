import java.awt.*;
import java.util.*;

/**
 * Main game class.
 * 
 * @author  Jonny Tang
 *
 * @version  3/20/2026
 */
public class Game 
{
    /**
     * Runs the chess game
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Board board = new Board();
        
        Piece blackKing = new King(Color.BLACK, "black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));
        Piece whiteKing = new King(Color.WHITE, "white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));

        BoardDisplay display = new BoardDisplay(board);
        
        for (Location loc : blackKing.destinations())
        {
            display.setColor(loc, Color.YELLOW);
        }
        for (Location loc : whiteKing.destinations())
        {
            display.setColor(loc, Color.YELLOW);
        }
    }
}