import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating the input into tokens.  A token is defined as:
 *  1. A 'word' which is defined as a non-empty sequence of characters that 
 *     begins with an alpha character and then consists of alpha characters, 
 *     numbers, the single quote character "'", or the hyphen character "-". 
 *  2. An 'end-of-sentence' delimiter defined as any one of the characters 
 *     ".", "?", "!".
 *  3. An end-of-file token which is returned when the scanner is asked for a
 *     token and the input is at the end-of-file.
 *  4. A phrase separator which consists of one of the characters ",",":" or
 *     ";".
 *  5. A digit.
 *  6. Any other character not defined above.
 * @author Mr. Page, Jonny Tang
 * @version 2/4/2026
 */

public class Scanner
{
    private Reader in;
    private String currentChar;
    private boolean endOfFile;
    /**
     * define symbolic constants for each type of token
     */ 
    public static enum TOKEN_TYPE{WORD, END_OF_SENTENCE, END_OF_FILE, 
        END_OF_PHRASE, DIGIT, UNKNOWN};
    /**
     * Constructor for Scanner objects.  The Reader object should be one of
     *  1. A StringReader
     *  2. A BufferedReader wrapped around an InputStream
     *  3. A BufferedReader wrapped around a FileReader
     *  The instance field for the Reader is initialized to the input parameter,
     *  and the endOfFile indicator is set to false.  The currentChar field is
     *  initialized by the getNextChar method.
     * @param in is the reader object supplied by the program constructing
     *        this Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
    }
    
    /**
     * The getNextChar method attempts to get the next character from the input
     * stream.  It sets the endOfFile flag true if the end of file is reached on
     * the input stream.  Otherwise, it reads the next character from the stream
     * and converts it to a Java String object.
     * postcondition: The input stream is advanced one character if it is not at
     * end of file and the currentChar instance field is set to the String 
     * representation of the character read from the input stream.  The flag
     * endOfFile is set true if the input stream is exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if(inp == -1) 
                endOfFile = true;
            else 
                currentChar = "" + (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    /**
     * Takes a string + compares to the currentChar
     * 
     * @param str the string
     */
    private void eat(String str)
    {
        if (str.equals(currentChar))
        {
            getNextChar();
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks whether given string is letter or not
     * @param chr the char getting compared (still type string)
     * @return whether its a letter
     */
    private boolean isLetter(String chr)
    {
        chr = chr.toLowerCase();

        String[] letters = {"a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z"};
        
        return Arrays.asList(letters).contains(chr);
    }
    
    /**
     * Checks whether given string is digit or not
     * @param chr the char getting compared (still type string)
     * @return whether its a letter
     */
    private boolean isDigit(String chr)
    {
        String[] letters = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9"};
        
        return Arrays.asList(letters).contains(chr);
    }
    
    /**
     * Checks whether given string is a special character or not
     * @param chr the char getting compared (still type string)
     * @return whether its a letter
     */
    private boolean isSpecialChar(String chr)
    {
        String[] letters = {"'", "-"};
        
        return Arrays.asList(letters).contains(chr);
    }
    
    /**
     * Checks whether given string is a phrase terminator or not
     * @param chr the char getting compared (still type string)
     * @return whether its a letter
     */
    private boolean isPhraseTerminator(String chr)
    {
        String[] letters = {",", ";", ":"};
        
        return Arrays.asList(letters).contains(chr);
    }
    
    /**
     * Checks whether given string is a sentence terminator or not
     * @param chr the char getting compared (still type string)
     * @return whether its a letter
     */
    private boolean isSentenceTerminator(String chr)
    {
        String[] letters = {".", "!", "?"};
        
        return Arrays.asList(letters).contains(chr);
    }
    
    /**
     * Checks whether given string is a white space or not
     * @param chr the char getting compared (still type string)
     * @return whether its a letter
     */
    private boolean isWhiteSpace(String chr)
    {
        return chr.equals(" ");
    }
    
    /**
     * Checks if theres a next token
     * @return see method description
     */
    public boolean hasNextToken()
    {
        return !endOfFile;
    }
    
    /**
     * Gets the next token
     * @return the next token
     */
    public Token nextToken()
    {
        String token = "";
        TOKEN_TYPE type = TOKEN_TYPE.UNKNOWN;
        
        if (isLetter(currentChar))
        {
            token += currentChar;
            eat(currentChar);
            type = TOKEN_TYPE.WORD;
        }
        else if (isDigit(currentChar))
        {
            token += currentChar;
            eat(currentChar);
            type = TOKEN_TYPE.DIGIT;
        }
        else if (!hasNextToken())
        {
            type = TOKEN_TYPE.END_OF_FILE;
            
            return new Token(type, "");
        }
        else if (isSentenceTerminator(currentChar))
        {
            token += currentChar;
            eat(currentChar);
            type = TOKEN_TYPE.END_OF_SENTENCE;
            
            // return new Token(type, token);
        }
        else if (isPhraseTerminator(currentChar))
        {
            token += currentChar;
            eat(currentChar);
            type = TOKEN_TYPE.END_OF_PHRASE;
            
            // return new Token(type, token);
        }
        else if (isWhiteSpace(currentChar))
        {
            eat(currentChar);
            return new Token(TOKEN_TYPE.UNKNOWN, " ");
        }
        else 
        {
            type = TOKEN_TYPE.UNKNOWN;
            eat(currentChar);
        }
        
        if (type == TOKEN_TYPE.DIGIT)
        {
            // check until not a digit
            while (isDigit(currentChar))
            {
                token += currentChar;
                eat(currentChar);
            }
            return new Token(type, token);
        }
        else if (type == TOKEN_TYPE.WORD)
        {
            // check until not a word
            while (isDigit(currentChar) || isLetter(currentChar) ||
                isSpecialChar(currentChar))
            {
                token += currentChar;
                eat(currentChar);
            }
            return new Token(type, token);
        }
        
        
        return new Token(type, token);
    }
}
