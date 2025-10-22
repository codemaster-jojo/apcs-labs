
/**
 * Card class for solitaire
 *
 * @author Jonny Tang
 * @version 10/22/2025
 */
public class Card
{
    // instance variables - replace the example below with your own
    private int rank;
    private String suit;
    private boolean isFaceUp;
    /**
     * Constructor for objects of class Card
     * 
     * @param r the input rank
     * @param s the input suit
     */
    public Card(int r, String s)
    {
        rank=r;
        suit=s;
        isFaceUp=false;
    }

    /**
     * Getter method for rank
     *
     * @return    the rank
     */
    public int getRank()
    {
        return rank;
    }
    
    /**
     * Getter method for suit
     *
     * @return    the suit
     */
    public String getSuit()
    {
        return suit;
    }
    
    /**
     * Checks if color is red
     *
     * @return    if the suit is red (diamond or hearts)
     */
    public boolean isRed()
    {
        return suit.equals("d") || suit.equals("h");
    }
    
    /**
     * Getter method for isFaceUp
     *
     * @return    whether the card is face up or not
     */
    public boolean isFaceUp()
    {
        return isFaceUp;
    }
    
    /**
     * Makes the card face up
     */
    public void turnUp()
    {
        isFaceUp = true;
    }
    
    /**
     * Makes the card face down
     */
    public void turnDown()
    {
        isFaceUp = false;
    }
    
    /**
     * Gets the file name 
     * @return the file name
     */
    public String getFileName()
    {
        if (!isFaceUp)
        {
            return "cards/backapcsds.gif";
        }
        
        String tmp;
        if (rank==1)
        {
            // ace
            tmp = "a";
        }
        else if (rank==11)
        {
            // jack
            tmp = "j";
        }
        else if (rank==12)
        {
            // queen
            tmp = "q";
        }
        else if (rank==13)
        {
            // king
            tmp = "k";
        }
        else if (rank==10)
        {
            // ten
            tmp = "t";
        }
        else
        {
            tmp = "" + rank;
        }
        
        return "cards/"+tmp+suit+".gif";
    }
}