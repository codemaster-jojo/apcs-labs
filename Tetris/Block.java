import java.awt.Color;
/**
* class BLock encapsulates a Block abstraction which 
* can be placed into a Gridworld style grid
* @author Jonny Tang
* @version 3/6/2026
*/
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
    /**
    * constructs a blue block, because blue is the greatest color ever!
    */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }
    
    /**
     * Constructs a block of whatever color
     */
    public Block(Color c)
    {
        color = c;
        grid = null;
        location = null;
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
    * Setter for color
    * @param newColor the new color
    */
    public void setColor(Color newColor)
    {
        color = newColor;
    }
    
    /**
    * Getter for grid
    * @return the grid
    */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }
    
    /**
    * Getter for location
    * @return location
    */
    public Location getLocation()
    {
        return location;
    }
    
    /**
    * Removes self from the grid
    */
    public void removeSelfFromGrid()
    {
        grid.remove(location);
        grid = null;
        location = null;
    }
    
    /**
    * Puts self in the grid provided
    * @param gr the grid
    * @param loc the location
    */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        location = loc;
        
        Block old = gr.get(loc);
        if (old != null)
        {
            old.removeSelfFromGrid();
        }
        
        gr.put(loc, this);
        grid = gr;
    }

    /**
    * Moves block to new location
    * @param the new location
    */
    public void moveTo(Location newLocation)
    {
        grid.remove(location);
        
        Block old = grid.get(newLocation);
        if (old != null)
        {
            old.removeSelfFromGrid();
        }
        
        location = newLocation;
        grid.put(newLocation, this);
    }

    /**
    * returns a string with the location and color of this block
    * @return the string format of the block
    * format: COLOR block at (ROW, COL)
    */
    public String toString()
    {
        return color + " block at (" + location.getRow() + ", "
            + location.getCol() + ")";
    }
}