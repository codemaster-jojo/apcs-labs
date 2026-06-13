/**
 * Tester class. Literally what it sounds like.
 * Tests out CardNumber.
 * 
 * @author  Jonny Tang
 *
 * @version  4/16/2026
 */
public class Tester 
{
    /* Tester methods: */

    /**
     * Gets the array in string format
     * @param arr the array
     * @return the string format of the array
     */
    public static String arrStr(int[] arr)
    {
        String array = "[";
        for (int i : arr)
        {
            array += i + ", ";
        }
        array = array.substring(0, array.length()-1) + "]";
        return array;
    }
    
    /**
     * Main method to test out the CardNumber class
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        CardNumber num = new CardNumber(new int[]{4, 5, 2, 3, 4, 6, 1, 8, 2, 7, 7, 5, 2, 7, 1, 2});
        System.out.println("CARD NUMBER: " + arrStr(num.cardNum()));
        
        int[] doubled = num.doublePattern();
        System.out.println("DOUBLE PATTERN: " + arrStr(num.doublePattern()));
        
        System.out.println("TRUE/FALSE: IS THE CARD NUMBER VERIFIED? " + num.verified());
        
        System.out.println("\n--------\n");
        System.out.println("TESTING DIGIT THAT ISN'T DOUBLED");
        num = new CardNumber(new int[]{4, 5, 2, 3, 4, 6, 1, 8, 2, 7, 
            7, 5, 2, 7, 1, -1}); // should be 2
        System.out.println("CARD NUMBER: " + arrStr(num.cardNum()));
        System.out.println("DETERMINING MISSING DIGIT...");
        num.fillInMissingNumber();
        System.out.println("NEW CARD NUMBER: " + arrStr(num.cardNum()));
        System.out.println("EXPECTED:        " 
            + arrStr(new int[]{4, 5, 2, 3, 4, 6, 1, 8, 2, 7, 7, 5, 2, 7, 1, 2}));
            
        System.out.println("\n--------\n");
        System.out.println("TESTING DIGIT THAT IS DOUBLED");
        num = new CardNumber(new int[]{4, 5, 2, 3, 4, 6, 1, 8, 2, 7, 
            -1, 5, 2, 7, 1, 2}); // should be 7
        System.out.println("CARD NUMBER: " + arrStr(num.cardNum()));
        System.out.println("DETERMINING MISSING DIGIT...");
        num.fillInMissingNumber();
        System.out.println("NEW CARD NUMBER: " + arrStr(num.cardNum()));
        System.out.println("EXPECTED:        " 
            + arrStr(new int[]{4, 5, 2, 3, 4, 6, 1, 8, 2, 7, 7, 5, 2, 7, 1, 2}));
    }

}