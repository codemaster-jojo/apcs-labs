
/**
 * Test for nested classes
 *
 * @author Jonny Tang
 * @version  9/24/2025
 */
public class Outer
{


    /**
     * Speaks
     */
    public void speak()
    {
        // put your code here
        System.out.println("Speaking... Speaking from OUTERRRRRRRRRR");
    }
    
    public class Inner
    {
        public void speak() {
            System.out.println("Speaking... Speaking from INNERRRRRRRR");
        }
    }
    
    public static class InnerStatic
    {
        public void speak() {
            System.out.println("Speaking... Speaking from INNERRRRRRRR and I'M STATIC!!!");
        }
    }
}