/**
 * Token class
 *
 * @author Jonny Tang
 * @version 2/17/2026
 */
public class Token
{
    // instance variables - replace the example below with your own
    Scanner.TOKEN_TYPE type;
    String value;

    /**
     * Constructor for objects of class Token
     * @param t token type
     * @param v value
     */
    public Token(Scanner.TOKEN_TYPE t, String v)
    {
        type = t;
        value = v;
    }

    /**
     * Getter for type
     * @return type
     */
    public Scanner.TOKEN_TYPE getType()
    {
        return type;
    }
    
    /**
     * Getter for value
     * @return value
     */
    public String getValue()
    {
        return value;
    }
    
    /**
     * Overrides tostring method
     * @return srting reprsentation
     */
    @Override
    public String toString()
    {
        return type + ": " + value;
    }
    
    /**
     * Overrides equals. If value and token type are equal
     * 
     * @return whether this equals other
     */
    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Token)
        {
            Token othert = (Token) other;
            return othert.getValue().equals(value) && othert.getType() == type;
        }
        return false;
    }
}