
/**
 * Basic person class storing the person's information
 *
 * @author Jonny Tang, Damien Wang
 * @version 12/08/2025
 */
public abstract class Person
{
    // instance variables - replace the example below with your own
    private int age;
    private String gender;
    private String name;
    private String placeOfLiving;

    /**
     * Constructor for objects of class Person
     * 
     * @param a the age
     * @param g the gender
     * @param n the name
     * @param l the place of living
     */
    public Person(int a, String g, String n, String l)
    {
        age = a;
        gender = g;
        name = n;
        placeOfLiving = l;
    }

    /**
     * Is a getter method for age
     *
     * @return returns age
     *
     */
    public int getAge()
    {
        return age;
    }

    /**
     *Is a getter method for gender
     *
     *@return returns gender
     *
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * Is a getter method for name
     *
     * @return returns name
     *
     */
    public String getName()
    {
        return name;
    }

    /**
     * Is a getter method for place of living
     *
     * @return returns placeOfLiving
     *
     */

    public String getPlaceOfLiving()
    {
        return placeOfLiving;
    }

    /**
     * Is a setter method for age
     *
     * @param a is age
     *
     */
    public void setAge(int a)
    {
        age = a;
    }

    /**
     * Is a setter method for name
     *
     * @param n is name
     *
     */
    public void setName(String n)
    {
        name = n;
    }

    /**
     * Is a setter method for gender
     *
     * @param g is gender
     *
     */
    public void setGender(String g)
    {
        gender = g;
    }

    /**
     * Is a setter method for Place of Living
     *
     * @param pol is place of living
     *
     */
    public void setPlaceOfLiving(String pol)
    {
        placeOfLiving = pol;
    }
    
    /**
     * Is an abstract method to self introduce. Different for criminals and judges.
     */
    public abstract void selfIntro();
}