/**
 * Card number class. For credit card.
 * Has an algorithm to check if credit card number is valid
 * 
 * @author Jonny Tang
 * @version 4/16/2026
 */
public class CardNumber
{
    private int [] cardNum;
    
    /**
     * Constructor that creates a card number object
     * @param nums  the digits of the credit card numbers being created
     */
    public CardNumber(int[] nums)
    {
        cardNum = nums;
    }
    
    /**
     * Getter for card num array
     * @return the card num
     */
    public int[] cardNum()
    {
        return cardNum;
    }
    
    /**
    * Returns an array with the digits doubled or the same depending
    * on the index of the digit according to the set of rules above.
    * @precondition cardNum is not empty and each element contains
    *
    a single positive digit
    * @return an array of n elements, where n is cardNum.length.
    * The elements at indexes n-2, n-4, n-6, ... are doubled and the
    * elements at n-1, n-3, n-5, ... are the same as corresponding
    * elements in cardNum. cardNum itself is unchanged.
    * Some elements of the returned array may have two digits
    */
    public int[] doublePattern()
    {
        int[] result = new int[cardNum.length];
        
        for (int i=cardNum.length-1; i>=0; i--)
        {
            if ((cardNum.length-1-i) % 2 == 1)
            {
                // double and put in arr
                result[i] = cardNum[i] * 2;
            }
            else
            {
                // just put in arr
                result[i] = cardNum[i];
            }
        }
        
        return result;
    }
    
    /**
    * Validates the digits in a credit card.
    * @return true if credit card number is valid, false otherwise
    *
    */
    public boolean verified()
    {
        int[] modified = doublePattern();
        
        int sum = 0;
        
        for (int num : modified)
        {
            if (num < 10)
            {
                sum += num;
            }
            else
            {
                sum += num/10 + num%10;
            }
        }
                
        return sum % 10 == 0;
    }
    
    /**
     * Fills in the missing number that's -1
     * @precondition one and only one number is missing
     */
    public void fillInMissingNumber()
    {
        int[] modified = doublePattern();
        int indexMissing = 0;
        
        // calculate index missing
        for (int i=0; i<cardNum.length; i++)
        {
            if (cardNum[i] < 0)
            {
                indexMissing = i;
            }
        }
        
        // calculate sum
        int sum = 0;
                
        for (int num : modified)
        {
            if (num < 10)
            {
                sum += num;
            }
            else
            {
                sum += num/10 + num%10;
            }
        }        
        
        // case 1: at index that isn't doubled
        if ((cardNum.length - indexMissing - 1)  % 2 == 0)
        {
            cardNum[indexMissing] = (10 - sum % 10) - 1;
        }
        // case 2: at doubled index
        else
        {
            // case 2.1: even (must be one digit)
            if (sum % 2 == 0)
            {
                cardNum[indexMissing] = (10 - sum % 10) - 2;
            }
            // case 2.2: odd (must be two digits)
            else
            {
                cardNum[indexMissing] = (10 + (10 - sum % 10) - 3)/2;
            }
        }
    }
}