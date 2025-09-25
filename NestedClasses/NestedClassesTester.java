import java.util.ArrayList;
import java.util.Iterator;

/**
 * Tester for my nested classes
 * 
 * @author  Jonny Tang
 *
 * @version 9/24/2025
 */
public class NestedClassesTester 
{
    /* NestedClassesTester methods: */

    /**
     * Tests everything
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Outer out = new Outer();
        Outer.Inner in = out.new Inner();
        
        out.speak();
        in.speak();
        
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(32);
        nums.add(14);
        nums.add(65);
        nums.add(0,26);
        nums.add(65);
        nums.add(8);
        
        Iterator<Integer> it = nums.iterator();
        
        while (it.hasNext()) {
            if (it.next() > 10) 
            {
                it.remove();
            }
        }
        
        for (int i=0; i<nums.size(); i++) {
            System.out.println(nums.get(i));
        }
        
        
    }

}