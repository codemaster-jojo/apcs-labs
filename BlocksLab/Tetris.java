/**
 * Tetris lab main class.
 * SCORE SYSTEM: 100 x the level for single clears times
 * the square of the number of rows cleared
 * 5 points per block
 * sleepTime -50 per level
 * advance level every LEVEL_THRESHOLD(2) rows
 * 
 * @author  Jonny Tang
 *
 * @version 3/10/2026
 */
public class Tetris implements ArrowListener
{
    static BlockDisplay display;
    static MyBoundedGrid<Block> grid;
    static Tetrad activeTetrad;
    
    static int sleepTime; // in ms
    static int score;
    static int level;
    static int clearedRows;
    
    public static final int LEVEL_THRESHOLD = 2;
    
    /**
     * Constructor for my tetris
     */
    public Tetris()
    {
        sleepTime = 400;
        score = 0;
        level = 1;
        clearedRows = 0;
        
        grid = new MyBoundedGrid(20, 10);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        
        display.setTitle("Tetris");
        
        activeTetrad = new Tetrad(grid);
        
        display.showBlocks();
    }
    
    /**
     * Main method to run the game
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Tetris t = new Tetris();
        play();
    }
    
    /**
     * Main loop to run the game.
     */
    public static void play()
    {
        while (true)
        {
            display.setTitle("Tetris | Score: " + score);
            try {
                Thread.sleep(sleepTime);
                boolean didTranslate = activeTetrad.translate(1,0);
                
                if (!didTranslate)
                {
                    score += 5;
                    
                    activeTetrad = null;
                    
                    int cleared = clearCompletedRows();
                    clearedRows += cleared;
                    
                    score += 100 * cleared * cleared * level;
                    
                    activeTetrad = new Tetrad(grid);
                    
                    if (clearedRows >= LEVEL_THRESHOLD) // advance level
                    {
                        level++;
                        sleepTime -= 50;
                        clearedRows -= LEVEL_THRESHOLD;
                    }
                }
                
                display.showBlocks();
            }
            catch(InterruptedException e)
            {
                //ignore 
            }
        }
        
    }
    
    /**
     * Checks whether whole row is full
     * @param row the row
     * @return whether row is full or not
     */
    private static boolean isCompletedRow(int row)
    {
        for (int i=0; i<grid.getNumCols(); i++)
        {
            if (grid.get(new Location(row, i)) == null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Clears the row
     * @param row the row
     */
    private static void clearRow(int row)
    {
        // clear row
        for (int i=0; i<grid.getNumCols(); i++)
        {
            grid.remove(new Location(row, i));
        }
        
        // move everything down
        for (int r=row-1; r>=0; r--)
        {
            for (int c=0; c<grid.getNumCols(); c++)
            {
                if (grid.get(new Location(r, c)) != null)
                {
                    grid.get(new Location(r, c)).moveTo(new Location(r+1, c));
                }
            }
        }
    }
    
    /**
     * Uses the 2 helper methods to clear all completed rows
     */
    public static int clearCompletedRows()
    {
        int row = grid.getNumRows()-1;
        int clearedRows = 0;
        while (row >= 0)
        {
            if (isCompletedRow(row))
            {
                clearRow(row);
                clearedRows++;
            }
            else
            {
                row--;
            }
        }
        return clearedRows;
    }

    /**
     * Gets arrow press up. Shifts tetrad accordingly
     */
    public void upPressed()
    {
        // up press shouldnt shift the tile up lol
        // i made it rotate
        activeTetrad.rotate();
        display.showBlocks();
    }
    
    /**
     * Gets arrow press up. Shifts tetrad accordingly
     */
    public void downPressed()
    {
        activeTetrad.translate(1,0);
        display.showBlocks();
    }
    
    /**
     * Gets arrow press up. Shifts tetrad accordingly
     */
    public void leftPressed()
    {
        activeTetrad.translate(0,-1);
        display.showBlocks();
    }
    
    /**
     * Gets arrow press up. Shifts tetrad accordingly
     */
    public void rightPressed()
    {
        activeTetrad.translate(0,1);
        display.showBlocks();
    }
}