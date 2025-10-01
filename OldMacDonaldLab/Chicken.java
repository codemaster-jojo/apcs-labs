
/**
 * Chicken class
 *
 * @author Jonny Tang
 * @version 10/1/2025
 */
public class Chicken extends Animal
{
    /**
     * Constructors for objects of class Chicken
     */
    public Chicken( )
    {
        this("chicken");
    }
    
    /**
     * Constructors for objects of class Chicken
     * 
     * @param chickenType the common name
     */
    public Chicken(String chickenType)
    {
        super("Gallus Gallus Domesticus", chickenType);
    }

    /**
     * Says bawk bawk
     * 
     * @return bawk
     */
    public String speak()
    {
        return "bawk";
    }
}