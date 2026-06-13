import java.awt.*;
import java.util.*;

/**
 * Queen piece extends piece
 *
 * @author Jonny Tang
 * @version 3/20/2026
 */
public class Queen extends Piece
{
    /**
     * Constructor for objects of class Queen
     * @param col the color
     * @param fileName the sprite file
     */
    public Queen(Color col, String fileName)
    {
        super(col, fileName, 9);
    }

    /**
     * Destinations for Queen
     * 8 directions 
     * If valid adds to arraylist
     * @return array list of valid locs
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> valid = new ArrayList<Location>();
        sweep(valid, Location.NORTH);
        sweep(valid, Location.EAST);
        sweep(valid, Location.SOUTH);
        sweep(valid, Location.WEST);
        sweep(valid, Location.NORTHEAST);
        sweep(valid, Location.SOUTHEAST);
        sweep(valid, Location.SOUTHWEST);
        sweep(valid, Location.NORTHWEST);
        return valid;
    }
}