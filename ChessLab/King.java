import java.awt.*;
import java.util.*;

/**
 * King piece extends piece
 *
 * @author Jonny Tang
 * @version 3/20/2026
 */
public class King extends Piece
{
    /**
     * Constructor for objects of class King
     */
    public King(Color col, String fileName)
    {
        super(col, fileName, 1000);
    }

    /**
     * Destinations for king
     * 8 adjacent pieces
     * If valid adds to arraylist
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        
        for (int dr = -1; dr <= 1; dr++)
        {
            for (int dc = -1; dc <= 1; dc++)
            {
                if (dr == 0 && dc == 0)
                {
                }
                else if (isValidDestination(new Location(r+dr, c+dc)))
                {
                    locs.add(new Location(r+dr, c+dc));
                }
            }
        }
        return locs;
    }
}