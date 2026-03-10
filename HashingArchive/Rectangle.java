/**
* Rectangle class that literally just stores a rectangle
* 
* @author Jonny Tang
* @version 1/21/2026
*/
public class Rectangle
{
    private int length;
    private int width;

    /**
     * Constructor for rectangle. 
     * 
     * @param len the length
     * @param w the width
     */
    public Rectangle(int len, int w)
    {
        length = len;
        width = w;
    }

    /**
     * Getter for length
     * @return length
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Getter for width
     * @return width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * To string method. Prints in this format:
     * [LENGTH]x[WIDTH]
     * @return the string in the format above
     */
    @Override
    public String toString()
    {
        return length + "x" + width;
    }
    
    /**
     * Hash code method. Modulus happens in hash set class.
     * 
     * @return the object hashed.
     */
    @Override
    public int hashCode()
    {
        return 2*length + width;
    }
    
    /**
     * Checks if this rectangle equals the other (by checking if the length and
     * width are the same)
     * @param obj the object getting compared to
     * @return whether the object is equal or not
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Rectangle))
        {
            return false;
        }
        else
        {
            Rectangle r = (Rectangle) obj;
            return length == r.getLength() && width == r.getWidth();
        }
    }
}