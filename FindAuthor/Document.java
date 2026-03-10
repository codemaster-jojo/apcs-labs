import java.util.*;

/**
 * Document class
 * @author  Jonny Tang
 * @version  2/18/2026
 */
public class Document
{
    Token currentToken;
    Scanner scanner;
    ArrayList<Sentence> sentences;
    
    /**
     * Constructor for objects of class Document
     * @param sc the scanner
     */
    public Document(Scanner sc)
    {
        scanner = sc;
        sentences = new ArrayList<Sentence>();
        getNextToken();
    }

    /**
     * Gets the next token
     */
    private void getNextToken()
    {
        currentToken = scanner.nextToken();
    }
    
    /**
     * Eats the token but first checks that its the right token
     * @param other the one getting checked
     */
    public void eat(Token other)
    {
        if (other.equals(currentToken))
        {
            getNextToken();
        }
        else
        {
            throw new RuntimeException();
        }
    }
    
    /**
     * Parses a phrase
     * @return the phrase that got parsed.
     */
    public Phrase parsePhrase()
    {
        Phrase p = new Phrase(new ArrayList<Token>());
        while (currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_SENTENCE &&
            currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_PHRASE &&
            currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE)
        {
            p.addToken(currentToken);
            eat(currentToken);
        }
        
        if (currentToken.getType() == Scanner.TOKEN_TYPE.END_OF_PHRASE)
        {
            eat(currentToken);
        }
        
        return p;
    }
    
    /**
     * Parses a sentence
     * @return the sentence that got parsed.
     */
    public Sentence parseSentence()
    {
        Sentence p = new Sentence(new ArrayList<Phrase>());
        while (currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_SENTENCE &&
            currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE)
        {
            p.addPhrase(parsePhrase());
        }
        
        if (currentToken.getType() == Scanner.TOKEN_TYPE.END_OF_SENTENCE)
        {
            eat(currentToken);
        }
        
        return p;
    }
    
    /**
     * Parses the whole document
     */
    public void parseDocument()
    {
        while (currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE)
        {
            Sentence s = parseSentence();
            sentences.add(s);
        }
    }
    
    /**
     * Getter for sentences
     * @return array list of sentences
     */
    public ArrayList<Sentence> getSentences()
    {
        return sentences;
    }
}