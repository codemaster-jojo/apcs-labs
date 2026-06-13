import java.awt.*;
import java.util.*;

/**
 * Subclass of Player
 * Implements nextMove
 * 
 * @author Jonny Tang
 * @version 4/6/2026
 */
public class RandomPlayer extends Player
{
    /**
     * Constructor for objects of class RandomPlayer
     * @param b the board
     * @param c the color
     * @param n the player's name
     */
    public RandomPlayer(Board b, Color c, String n)
    {
        super(b, c, n);
    }

    /**
     * Gets a random move
     */
    public Move nextMove()
    {
        ArrayList<Move> possible = getBoard().allMoves(getColor());
        int random = (int) (Math.random() * possible.size());
        return possible.get(random);
    }
}