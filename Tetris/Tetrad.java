import java.awt.Color;
import java.util.concurrent.Semaphore;
import java.util.*;

/**
 * Tetrad class
 * 7 types: I T O L J S Z
 *
 * @author Jonny Tang
 * @version 3/10/2026
 */
public class Tetrad
{
    // instance variables - replace the example below with your own
    Block[] blocks;
    Color color;
    MyBoundedGrid<Block> grid;
    Location[] locs;
    boolean isSquare;
    
    private Semaphore lock;
    
    /**
     * Constructor for objects of class Tetrad
     * @param g the grid
     */
    public Tetrad(MyBoundedGrid<Block> g)
    {
        isSquare = false;
        lock = new Semaphore(1,true);
        
        grid = g;
        
        int rand = (int) (Math.random() * 7);
        
        locs = new Location[4];
        
        if (rand == 0) // I
        {
            locs[0] = new Location(2,4); // p0
            locs[1] = new Location(0,4);
            locs[2] = new Location(1,4);
            locs[3] = new Location(3,4);
            color = Color.RED;
        }
        else if (rand == 1) // T
        {
            locs[0] = new Location(0,5); // p0
            locs[1] = new Location(0,4);
            locs[2] = new Location(1,5);
            locs[3] = new Location(0,6);
            color = Color.GRAY;
        }
        if (rand == 2) // O
        {
            // p0 doesnt matter here
            isSquare = true;
            locs[0] = new Location(0,4);
            locs[1] = new Location(1,4);
            locs[2] = new Location(1,5);
            locs[3] = new Location(0,5);
            color = Color.CYAN;
        }
        if (rand == 3) // L
        {
            locs[0] = new Location(1,4); // p0
            locs[1] = new Location(0,4);
            locs[2] = new Location(2,4);
            locs[3] = new Location(2,5);
            color = Color.YELLOW;
        }
        if (rand == 4) // J
        {
            locs[0] = new Location(1,5); // p0
            locs[1] = new Location(0,5);
            locs[2] = new Location(2,5);
            locs[3] = new Location(2,4);
            color = Color.MAGENTA;
        }
        if (rand == 5) // S
        {
            locs[0] = new Location(1,5); // p0
            locs[1] = new Location(0,4);
            locs[2] = new Location(0,5);
            locs[3] = new Location(1,6);
            color = Color.BLUE;
        }
        if (rand == 6) // Z
        {
            locs[0] = new Location(1,5); // p0
            locs[1] = new Location(1,4);
            locs[2] = new Location(0,5);
            locs[3] = new Location(0,6);
            color = Color.GREEN;
        }
        
        blocks = new Block[4];
        for (int i=0; i<4; i++)
        {
            blocks[i] = new Block(color);
        }
        
        addToLocations(grid, locs);
    }

    /**
     * Adds blocks to assigned locations
     * @precondition  blocks are not in any grid;
     *                locs.length = 4.
     * @postcondition The locations of blocks match locs,
     *                and blocks have been put in the grid.
     *                
     * @param grid the grid
     * @param locs array of 4 locations
     */
    private void addToLocations(MyBoundedGrid<Block> grid,
                                 Location[] locs)
    {
        int i = 0;
        for (Location loc : locs)
        {
            blocks[i].putSelfInGrid(grid, loc);
            i++;
        }
        this.locs = locs;
    }
    
    /**
     * Removes block from grid
     * 
     * @precondition  Blocks are in the grid.
     * @postcondition Returns old locations of blocks;
     *                  blocks have been removed from grid.
     * 
     * @return locations of the 4 old blocks
     */
    private Location[] removeBlocks()
    {
        Location[] returnLocs = locs;
        grid.remove(locs[0]);
        grid.remove(locs[1]);
        grid.remove(locs[2]);
        grid.remove(locs[3]);
        
        locs = new Location[4];
        return returnLocs;
    }
    
    /**
     * Checks if 4 locations are all empty
     * @postcondition return true if each of the locs
     *      is empty. false otherwise.
     * @return whether the given locations are empty
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (Location loc : locs)
        {
            if (grid.get(loc) != null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * @postcondition Attempts to move this tetrad deltaRow rows down.
     * 
     * @return  true if succeeds in translating. False if not.
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        try
        {
            lock.acquire();
            
            Location[] newLocs = new Location[4];
            for (int i=0; i<4; i++)
            {
                newLocs[i] = new Location(locs[i].getRow() + deltaRow,
                    locs[i].getCol() + deltaCol);
                if (newLocs[i].getRow() >= grid.getNumRows()
                    || newLocs[i].getCol() >= grid.getNumCols()
                    || newLocs[i].getRow() < 0
                    || newLocs[i].getCol() < 0)
                {
                    return false;
                }
            }
            
            // if all 4 locations are in grid
            Location[] tempLocs = removeBlocks();
            
            if (areEmpty(grid, newLocs))
            {
                addToLocations(grid, newLocs);
                return true;
            }
            else
            {
                // add stuff back into old locations
                addToLocations(grid, tempLocs);
                return false;
            }
        }
        catch (InterruptedException e)
        {
            return false;
        }
        finally
        {
            lock.release();
        }
    }
    
    /**
     * Attempts to rotate clockwise 90 degrees about center.
     * Uses blocks[0] as the centerpiece to rotate around
     * 
     * @reutrn true if rotate succeeds, false if it doesnt
     */
    public boolean rotate()
    {
        if (isSquare)
        {
            return true;
        }
        
        try
        {
            lock.acquire();
            
            Location[] rotated = new Location[4];
            for (int i=0; i<4; i++)
            {
                int row = locs[0].getRow() - locs[0].getCol() + locs[i].getCol();
                int col = locs[0].getRow() + locs[0].getCol() - locs[i].getRow();
                rotated[i] = new Location(row, col);
                
                if (rotated[i].getRow() >= grid.getNumRows()
                    || rotated[i].getCol() >= grid.getNumCols()
                    || rotated[i].getRow() < 0
                    || rotated[i].getCol() < 0)
                {
                    return false;
                }
            }
            
            // if all 4 locations are in grid
            Location[] tempLocs = removeBlocks();
            
            if (areEmpty(grid, rotated))
            {
                addToLocations(grid, rotated);
                return true;
            }
            else
            {
                // add stuff back into old locations
                addToLocations(grid, tempLocs);
                return false;
            }
        }
        catch (InterruptedException e)
        {
            return false;
        }
        finally
        {
            lock.release();
        }
    }
}