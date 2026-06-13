import java.awt.*;
import java.util.*;

/**
 * Rook piece worth 5 points
 *
 * @author Jonny Tang
 * @version  3/24/2026
 */
public class Rook extends Piece
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Rook
     * @param col the color
     * @param fileName the sprite file
     */
    public Rook(Color col, String fileName)
    {
        super(col, fileName, 5);
    }


    /**
     * Gets all valid destinations using sweep
     * @return location array list with all valid destinations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> valid = new ArrayList<Location>();
        sweep(valid, Location.NORTH);
        sweep(valid, Location.EAST);
        sweep(valid, Location.SOUTH);
        sweep(valid, Location.WEST);
        return valid;
    }
}