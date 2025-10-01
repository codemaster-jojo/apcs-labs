/**
 * Pig class
 *
 * @author Jonny Tang
 * @version 10/1/2025
 */
public class Pig extends Animal
{
    /**
     * Constructor for objects of class Pig
     */
    public Pig()
    {
        this("pig");
    }
    
    /**
     * Constructor for objects of class Pig
     * 
     * @param name the name of the pig
     */
    public Pig(String name)
    {
        super("Sus scrofa domesticus", name);
    }

    /**
     * Says oink oink
     *
     * @return    oink
     */
    public String speak()
    {
        return "oink";
    }
}