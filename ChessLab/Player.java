import java.awt.*;
import java.util.*;

/**
 * Abstrat player class. Defines a player (color, board, name)
 *
 * @author Jonny Tang
 * @version 4/5/2026
 */
public abstract class Player
{   
    String name;
    Board board;
    Color color;
    
    /**
     * Constructor for objects of class Player
     * @param b the board
     * @param c the color
     * @param n the player's name
     */
    public Player(Board b, Color c, String n)
    {
        board = b;
        color = c;
        name = n;
    }
    
    /**
     * Getter for board
     * @return board
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * Getter for name
     * @return name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Getter for color
     * @return color
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * determines next move
     * abstract method, requires implementation
     * @return next move
     */
    public abstract Move nextMove();
}