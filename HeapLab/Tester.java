/**
 * Tests out heap methods
 * 
 * @author  Jonny Tang
 *
 * @version   12/20/2025
 */
public class Tester 
{
    /* Tester methods: */

    /**
     * Main method as tester
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Integer[] arr = new Integer[12];
        
        for (int i=1; i<12; i++)
        {
            int r = (int) (Math.random() * 100);
            arr[i] = r;
        }
        
        HeapDisplay display = new HeapDisplay();
        
        display.displayHeap(arr, 11);
    }

}