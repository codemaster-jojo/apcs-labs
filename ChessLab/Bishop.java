import java.awt.*;
import java.util.*;

/**
 * Bishop class extends Piece
 *
 * @author Jonny Tang
 * @version 4/14/2026
 */
public class Bishop extends Piece
{
    /**
     * Constructor for objects of class Bishop
     * @param col the color
     * @param fileName the sprite file
     */
    public Bishop(Color col, String fileName)
    {
        super(col, fileName, 3);
    }
    
    /**
     * Destinations for bishop
     * 4 diagonals
     * If valid adds to arraylist
     * @return array list of valid
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> valid = new ArrayList<Location>();
        sweep(valid, Location.NORTHEAST);
        sweep(valid, Location.SOUTHEAST);
        sweep(valid, Location.SOUTHWEST);
        sweep(valid, Location.NORTHWEST);
        return valid;
    }
}