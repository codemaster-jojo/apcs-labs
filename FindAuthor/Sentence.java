import java.util.*;

/**
 * Sentence class.
 *
 * @author Jonny Tang
 * @version 2/18/2026
 */
public class Sentence
{
    ArrayList<Phrase> phrases;
    
    /**
     * Constructor for objects of class Sentence
     * @param p the phrases
     */
    public Sentence(ArrayList<Phrase> p)
    {
        phrases = p;
    }
    
    /**
     * Adds phrase to the array list
     * O(1) time complexity
     * @param p the phrase
     */
    public void addPhrase(Phrase p)
    {
        phrases.add(p);
    }
    
    /**
     * Creates a deep copy of the phrases
     * and returns it
     * @return the deep copy of the phrases
     */
    public ArrayList<Phrase> getPhrases()
    {
        ArrayList<Phrase> returnPhrase = new ArrayList<Phrase>();
        for (Phrase p : phrases)
        {
            returnPhrase.add(p);
        }
        return returnPhrase;
    }
    
    /**
     * String representation of the sentence.
     * Literally, the sentence without punctuation
     * 
     * @return string of the sentence
     */
    @Override
    public String toString()
    {
        String r = "";
        for (Phrase p : phrases)
        {
            r += p;
        }
        return r;
    }
}