import java.util.Stack;

/**
 * Util for string, just has palindrome and reverse string implemented using stack.
 * 
 * @author Anu Datar, Jonny Tang
 * @version 10/14/2025
 */
public class StringUtil
{
    /**
     * Reverses a string using stack.
     * 
     * @param str the string getting reversed
     * @return the reversed string
     */
    public static String reverseString(String str)
    {
        Stack<String> reversed = new Stack<String>();
        
        for (int i=0; i<str.length(); i++)
        {
            reversed.push(str.substring(i, i+1));
        }
        
        String ans = "";
        while (!reversed.empty())
        {
            ans += reversed.pop();
        }
        return ans;
    }

    /**
     * Checks if palindrome by comparing string to reverse string
     * 
     * @param s the string thats getting checked
     * @return whether s is a palindrome or not
     */
    public static boolean isPalindrome(String s)
    {
        return s.equals(reverseString(s));
    }

    /**
     * Main method to test my implementations of reverse and isPalindrome
     * 
     * @param args standard arguments for main method
     */
    public static void main(String[] args)
    {
        String test =  "racecar";
        String test2 = "notapalindrome";

        if ( !("".equalsIgnoreCase(reverseString(""))) )
        {
            System.out.println("An empty string is palindrome");
        }

        if ( !("a".equalsIgnoreCase(reverseString("a"))) )
        {
            System.out.println("\"a\" is a palindrome");
        }

        if ( !("aa".equalsIgnoreCase(reverseString("aa"))) )
        {
            System.out.println("Error: \"aa\" is a palindrome");
        }

        if (!test.equalsIgnoreCase(reverseString(test)))
        {
            System.out.println("Error: " + test + " is a palindrome");
        }
        else
        {
            System.out.println("Success " + test + " matched " + reverseString(test));
        }

        if (test2.equalsIgnoreCase(reverseString(test2)))
        {
            System.out.println("Error: " + test2 + " is a palindrome");
        }

    }
}
