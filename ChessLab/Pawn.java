import java.awt.*;
import java.util.*;

/**
 * Pawn piece extends piece
 *
 * @author Jonny Tang
 * @version 4/14/2026
 */
public class Pawn extends Piece
{    
    /**
     * Constructor for objects of class Pawn
     * @param col the color
     * @param fileName the sprite file
     */
    public Pawn(Color col, String fileName)
    {
        super(col, fileName, 1);
    }

    /**
     * Destinations for Pawn
     * 8 directions 
     * If valid adds to arraylist
     * @return array list of valid locs
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> valid = new ArrayList<Location>();
        int r = getLocation().getRow();
        int c = getLocation().getCol();
        
        if (getColor().equals(Color.BLACK) && r == 1)
        {
            // 2 moves
            if (isValidDestination(new Location(r+1, c)) 
                    && getBoard().get(new Location(r+1, c)) == null)
            {
                valid.add(new Location(r+1, c));
                if (isValidDestination(new Location(r+2, c)) 
                    && getBoard().get(new Location(r+2, c)) == null)
                {
                    valid.add(new Location(r+2, c));
                }
            }
        }
        else if (getColor().equals(Color.BLACK))
        {
            // 1 move
            if (isValidDestination(new Location(r+1, c)) 
                    && getBoard().get(new Location(r+1, c)) == null)
            {
                valid.add(new Location(r+1, c));
            }
            
            // check if can capture
            if (isValidDestination(new Location(r+1, c+1)) 
                    && getBoard().get(new Location(r+1, c+1)) != null &&
                getBoard().get(new Location(r+1, c+1)).getColor() != getColor())
            {
                valid.add(new Location(r+1, c+1));
            }
            if (isValidDestination(new Location(r+1, c-1)) 
                    && getBoard().get(new Location(r+1, c-1)) != null &&
                    getBoard().get(new Location(r+1, c-1)).getColor() != getColor())
            {
                valid.add(new Location(r+1, c-1));
            }
        }
        
        if (getColor().equals(Color.WHITE) && r == 6)
        {
            // 2 moves
            if (isValidDestination(new Location(r-1, c)) 
                    && getBoard().get(new Location(r-1, c)) == null)
            {
                valid.add(new Location(r-1, c));
                if (isValidDestination(new Location(r-2, c)) 
                    && getBoard().get(new Location(r-2, c)) == null)
                {
                    valid.add(new Location(r-2, c));
                }
            }
        }
        
        else if (getColor().equals(Color.WHITE))
        {
            // 1 move
            if (isValidDestination(new Location(r-1, c)) 
                    && getBoard().get(new Location(r-1, c)) == null)
            {
                valid.add(new Location(r-1, c));
            }
            
            // check capture
            if (isValidDestination(new Location(r-1, c+1)) 
                    && getBoard().get(new Location(r-1, c+1)) != null &&
                getBoard().get(new Location(r-1, c+1)).getColor() != getColor())
            {
                valid.add(new Location(r-1, c+1));
            }
            if (isValidDestination(new Location(r-1, c-1)) 
                    && getBoard().get(new Location(r-1, c-1)) != null &&
                getBoard().get(new Location(r-1, c-1)).getColor() != getColor())
            {
                valid.add(new Location(r-1, c-1));
            }
        }
        
        return valid;
    }
}