
/**
 * Book class
 * ID format: DD-LLL
 * D = digit, L = letter of alphabet
 *
 * @author Jonny Tang
 * @version 1/12/2026
 */
public class Book
{
    String id;
    String title;

    /**
     * Constructor for objects of class Book
     * 
     * @param t the title
     * @param i the id
     */
    public Book(String t, String i)
    {
        id = i;
        title = t;
    }

    /**
     * getter for id
     * 
     * @return id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * getter for title
     * 
     * @return title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Overrides equals to check if id is same
     * @param obj the object getting equaled to
     * @return whether the object is equal to obj
     */
    @Override
    public boolean equals(Object obj)
    {
        if (! (obj instanceof Book))
        {
            return false;
        }
        
        return ((Book) obj).getId().equals(id);
    }
    
    /**
     * Overrides hash method using id
     * 
     * @return the hash code of object
     */
    @Override
    public int hashCode()
    {
        int digits = Integer.parseInt(id.substring(0, 2));
        String letters = id.substring(3, 6);
        
        return (digits % 10 + 3 * digits / 10 + letters.charAt(0) 
            + letters.charAt(1) * 2 + letters.charAt(2) * 3) % 10;
    }
    
    /**
     * Item to string.
     */
    @Override
    public String toString()
    {
        return title + " " + id;
    }
}