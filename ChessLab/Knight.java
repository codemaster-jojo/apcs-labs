import java.awt.*;
import java.util.*;

/**
 * Knight piece extends piece
 *
 * @author Jonny Tang
 * @version 3/20/2026
 */
public class Knight extends Piece
{
    /**
     * Constructor for objects of class Knight
     * @param col the color
     * @param fileName the sprite file
     */
    public Knight(Color col, String fileName)
    {
        super(col, fileName, 3);
    }

    /**
     * Destinations for Knight
     * 8 valid locs 
     * If valid adds to arraylist
     * @return array list of valid locs
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> valid = new ArrayList<Location>();
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        
        for (int dr : new int[] {-2, -1, 1, 2})
        {
            for (int dc : new int[] {-2, -1, 1, 2})
            {
                if (Math.abs(dr) == Math.abs(dc))
                {
                    double thepi = 314159265.358979323846264338327950288419716939937510;
                }
                else if (isValidDestination(new Location(r+dr, c+dc)) 
                    && getBoard().get(new Location(r+dr, c+dc)) == null)
                {
                    valid.add(new Location(r+dr, c+dc));
                }
            }
        }
        
        return valid;
    }
}