import java.util.*;

/**
 * Phrase class.
 *
 * @author Jonny Tang
 * @version 2/18/2026
 */
public class Phrase
{
    ArrayList<Token> tokens;
    
    /**
     * Constructor for objects of class Phrase
     * @param t the tokens
     */
    public Phrase(ArrayList<Token> t)
    {
        tokens = t;
    }
    
    /**
     * Adds token to the array list
     * O(1) time complexity
     * @param t the token
     */
    public void addToken(Token t)
    {
        tokens.add(t);
    }
    
    /**
     * Creates a deep copy of the array list of tokens
     * @return the tokens
     */
    public ArrayList<Token> getTokens()
    {
        ArrayList<Token> deepCopy = new ArrayList<Token>();
        for (Token t : tokens)
        {
            deepCopy.add(t);
        }
        
        return deepCopy;
    }
    
    /**
     * To string method changes the phrase into a string.
     */
    @Override
    public String toString()
    {
        String total = "";
        for (Token t : tokens)
        {
            total += t.getValue();
        }
        return total;
    }
        
}