/**
* My location class
* 
* @author Jonny Tang
* @version 2/28/2026
*/
public class MyLocation implements Comparable
{
    private int row;
    private int col;

    /**
     * Constructor: MyLocation()
     * Usage: MyLocation loc = new MyLocation(row, col);
     * -----------------------------
     * creates a MyLocation object with the given row & col
     * 
     * @param r - row of this MyLocation
     * @param c - column of this MyLocaiton
     */
    public MyLocation(int r, int c)
    {
        row = r;
        col = c;
    }

    /**
     * Method: getRow()
     * Usage: gets row. getter method
     * @return row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Method: getCol()
     * Usage: get column. getter method.
     * @return col
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Method: equals()
     * Usage: checks if this equals other 
     * @return whether this equals other
     */
    public boolean equals(Object other)
    {
        if (other instanceof MyLocation)
        {
            MyLocation o = (MyLocation) other;
            return row == o.getRow() && col == o.getCol();
        }
        return false;
    }

    /**
     * Method: toString()
     * Usage: changes object o string
     * @return the to string version
       */
    public String toString()
    {
        return "(" + row + ", " + col + ")";
    }

    /**
     * Method: compareTo()
     * Usage: compares this to x
     * 
     * @precondition x is type MyLocation
     * @param x the object
     * @return number comparing the two.
     */
    public int compareTo(Object x)
    {
        if (!(x instanceof MyLocation))
        {
            throw new IllegalArgumentException("object must be a MyLocation object");
        }
        
        MyLocation other = (MyLocation) x;
        int otherX = other.getRow();
        int otherY = other.getCol();
        
        if (otherX == row)
        {
            return col - otherY;
        }
        else
        {
            return row - otherX;
        }
    }
}