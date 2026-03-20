import java.util.*;
/**
 * Bounded Grid class
 *
 * @author Jonny Tang
 * @version 3/6/2026
 */
public class MyBoundedGrid<E>
{
    // instance variables - replace the example below with your own
    int rows;
    int cols;
    Object[][] grid;

    /**
     * Creates a bounded grid
     * @param r the row
     * @param c the column
     */
    public MyBoundedGrid(int r, int c)
    {
        rows = r;
        cols = c;
        grid = new Object[r][c];
    }
    
    /**
     * Getter for rows
     * @return rows
     */
    public int getNumRows()
    {
        return rows;
    }
    
    /**
     * Getter for cols
     * @return cols
     */
    public int getNumCols()
    {
        return cols;
    }
    
    /**
     * Checks if location is valid
     * @param loc the location
     * @return whether location is valid
     */
    public boolean isValid(Location loc)
    {
        return (loc.getRow() < rows && loc.getCol() < cols
            && loc.getRow() >=0 && loc.getCol() >= 0);
    }
    
    /**
     * Puts something in the location
     * @param loc the location
     * @param obj the obj placing
     * @return the object previously at location
     */
    public E put(Location loc, E obj)
    {
        E o = get(loc);
        grid[loc.getRow()][loc.getCol()] = (Object) obj;
        return o;
    }
    
    /**
     * Removes item at location
     * @param loc the location
     * @return the item removed
     */
    public E remove(Location loc)
    {
        Object o = grid[loc.getRow()][loc.getCol()];
        grid[loc.getRow()][loc.getCol()] = null;
        return (E) o;
    }
    
    /**
     * Gets the item at location
     * @param loc the location
     * @return the item at location
     */
    public E get(Location loc)
    {
        return (E) grid[loc.getRow()][loc.getCol()];
    }
    
    /**
     * Gets all filled locations
     * @return array list of all locations
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (int r=0; r<rows; r++)
        {
            for (int c=0; c<cols; c++)
            {
                if (grid[r][c] != null)
                {
                    locs.add(new Location(r, c));
                }
            }
        }
        return locs;
    }
}