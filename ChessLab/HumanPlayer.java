import java.awt.*;
import java.util.*;

/**
 * Human player
 *
 * @author Jonny Tang
 * @version 4/14/2026
 */
public class HumanPlayer extends Player
{
    BoardDisplay display;
    
    /**
     * Constructor for objects of class HumanPlayer
     * @param b the board
     * @param c the color
     * @param n the player's name
     */
    public HumanPlayer(Board b, Color c, String n, BoardDisplay d)
    {
        super(b, c, n);
        display = d;
    }

    public Move nextMove()
    {
        while (true)
        {
            Move m = display.selectMove();
            if (getBoard().allMoves(getColor()).contains(m))
            {
                return m;
            }
        }
    }
}