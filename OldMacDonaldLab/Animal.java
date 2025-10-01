
/**
 * Animal abstract class
 *
 * @author Jonny Tang
 * @version 10/1/2025
 */
public abstract class Animal implements Comparable<Animal>
{
    private String latinName;
    private String commonName;

    /**
     * Constructor for objects of class Animal with given latin name and common name
     * 
     * @param ltnN the latin name
     * @param cmnN the common name
     */
    public Animal(String ltnN, String cmnN)
    {
        // initialise instance variables
        latinName = ltnN;
        commonName = cmnN;
    }

    /**
     * Accessor for latin name
     *
     * @return    the latin name
     */
    public String latinName()
    {
        return latinName;
    }
    
    /**
     * Accessor for common name
     *
     * @return    the common name
     */
    public String commonName()
    {
        return commonName;
    }
    
    /**
     * Mutator for latin name
     *
     * @param l    the latin name
     */
    public void setLatinName(String l)
    {
        latinName = l;
    }
    
    /**
     * Mutator for common name
     *
     * @param c    the common name
     */
    public void setCommonName(String c)
    {
        commonName = c;
    }
    
    /**
     * Abstract class to speak
     * 
     * @return a string thats what the animal's speaking
     */
    public abstract String speak();
    
    /**
     * Implements compareTo in comparable interface
     * 
     * @param other the other that its compared to
     * @return integer representing which one is greater and by how much
     */
    public int compareTo(Animal other)
    {
        return commonName.compareTo(other.commonName());
    }
}