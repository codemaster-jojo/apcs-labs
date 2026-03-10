import java.util.*;

/**
 * Minesweeper encapsulates the Minesweeper game.
 * 
 * This is where student code goes
 * 
 * @author Mr. Page
 * @author Alexandra Michael modified for Checkstyle
 * @author Susan King modified for comments and moves Main class to main method
 *                    in this class
 * @author Jonny Tang
 * @version May 23, 2022
 */
public class Minesweeper
{
    // constants for the size of the field, can be deleted if using default constructor
    private static final int GRID_ROWS = 9;
    private static final int GRID_COLS = 9;
    private static final int NUM_MINES = 10;
    
    // references to the model and the view
    private MinefieldDisplay theDisplay;
    private Minefield theField;

    private boolean timerStarted;

    /**
     * Initializes the default Minesweeper game.
     */
    public Minesweeper()
    {
        // construct the grid and the display
        theField = new Minefield();
        theDisplay = new MinefieldDisplay(this, theField);
        // set up the mines
        setMines(NUM_MINES);

        timerStarted = false;
    }

    /**
     * Handles button presses.  This method is called whenever the user 
     * selects and clicks on a location within the mine field 
     * that has not been previously selected.
     * 
     * The view actionPerformed method calls this method and 
     * passes the row and col information as int values
     * 
     * @param row   the row the cursor was on when a button was pressed
     * @param col   the column the cursor was on when a button was pressed
     * @param rightButton   true if the right button is pressed; otherwise, 
     *                      false
     */
    public void pressed(int row, int col, boolean rightButton)
    {
        if (!theField.isValid(row,col))
        {
            return;
        }
        if (!rightButton)
        {
            // if mine -> lose
            if (theField.isMine(row,col))
            {
                // lose
                theDisplay.showAll();
                theDisplay.setFace("cry2.gif");
            }
            
            // if no mines -> scan field
            else
            {
                scanField(row, col);
                theDisplay.update();
            }
        }
        else
        {
            theDisplay.setText(row, col, "M");
        }
    }

    /**
     * Recursively scans the field and reveals all squares that do not contain mines.
     * Additionally, it labels the squares adjacent to mines with the number of mines 
     * adjacent.
     * 
     * @param row   the row of the cell to be processed
     * @param col   the column of the cell to be processed
     */
    public void scanField(int row, int col)
    {
        int numMines = countMines(row, col);
        theField.markVisited(row, col);
        if (numMines > 0)
        {
            theDisplay.setText(row, col, numMines);
        }
        else // recurse 8 directions
        {
            if (theField.isValid(row-1, col) && !theField.isVisited(row-1, col))
            {
                scanField(row-1, col);
            }
            if (theField.isValid(row-1, col-1) && !theField.isVisited(row-1, col-1))
            {
                scanField(row-1, col-1);
            }
            if (theField.isValid(row, col-1) && !theField.isVisited(row, col-1))
            {
                scanField(row, col-1);
            }
            if (theField.isValid(row-1, col+1) && !theField.isVisited(row-1, col+1))
            {
                scanField(row-1, col+1);
            }
            if (theField.isValid(row+1, col-1) && !theField.isVisited(row+1, col-1))
            {
                scanField(row+1, col-1);
            }
            if (theField.isValid(row+1, col) && !theField.isVisited(row+1, col))
            {
                scanField(row+1, col);
            }
            if (theField.isValid(row+1, col+1) && !theField.isVisited(row+1, col+1))
            {
                scanField(row+1, col+1);
            }
            if (theField.isValid(row, col+1) && !theField.isVisited(row, col+1))
            {
                scanField(row, col+1);
            }
        }

    }

    /**
     * Sets up the mine field in a random pattern.
     * 
     * @param numMines  the number of mines that the user wants set up.
     */
    public void setMines(int numMines)
    {
        // distribute numMines uniformily around the grid
        while (numMines > 0)
        {
            int row = (int) (Math.random() * GRID_ROWS);
            int col = (int) (Math.random() * GRID_COLS);
            
            if (!theField.isMine(row,col))
            {
                theField.add(row, col);
                numMines--;
            }
        }
    }

    /**
     * Counts the number of mines adjacent to a given location.
     * 
     * @param row   the row of the cell in which adjacent mines are being counted
     * @param col   the column of the cell in which adjacent mines are being counted
     * @return the number of mines adjacent to the given location
     */
    public int countMines(int row, int col)
    {
        int mines = 0;
        if (theField.isValid(row-1, col) && theField.isMine(row-1, col))
        {
            mines++;
        }
        if (theField.isValid(row-1, col-1) && theField.isMine(row-1, col-1))
        {
            mines++;
        }
        if (theField.isValid(row, col-1) && theField.isMine(row, col-1))
        {
            mines++;
        }
        if (theField.isValid(row+1, col) && theField.isMine(row+1, col))
        {
            mines++;
        }
        if (theField.isValid(row+1, col+1) && theField.isMine(row+1, col+1))
        {
            mines++;
        }
        if (theField.isValid(row, col+1) && theField.isMine(row, col+1))
        {
            mines++;
        }
        if (theField.isValid(row-1, col+1) && theField.isMine(row-1, col+1))
        {
            mines++;
        }
        if (theField.isValid(row+1, col-1) && theField.isMine(row+1, col-1))
        {
            mines++;
        }
        return mines;
    }  

    /**
     * Starts the game Minesweeper.
     * 
     * @param args  information from the command line
     */
    public static void main(String[] args)
    {
        Minesweeper gameMaster = new Minesweeper();
    }
}
