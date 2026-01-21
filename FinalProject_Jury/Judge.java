
/**
 * Judge on the case. Inherits from person class
 *
 * @author Jonny Tang, Damien Wang
 * @version 12/08/2025
 */
public class Judge extends Person
{
    private boolean isCorrupt;
    private boolean isNice;

    /**
     * Constructor for objects of class Judge
     * 
     * @param a the age
     * @param g the gender
     * @param n the name
     * @param l place of living
     * 
     * @param ic is corrupt
     * @param in is nice
     */
    public Judge(int a, String g, String n, String l, boolean ic, boolean in)
    {
        super(a, g, n, l);
        isCorrupt = ic;
        isNice = in;
    }

    /**
     * Constructor for objects of class Judge
     * OVERLOADING -> corrupt and nice defaults to both false.
     * 
     * @param a the age
     * @param g the gender
     * @param n the name
     * @param l place of living
     */
    public Judge(int a, String g, String n, String l)
    {
        super(a, g, n, l);
        isCorrupt = false;
        isNice = false;
    }

    /**
     * Is a method that introduces the judge
     */
    @Override
    public void selfIntro()
    {
        System.out.println("Judge " + getName() + " is a " + getAge()
            + " year old " + getGender() + ".");
        if (getGender().equals("male")) 
        {
            System.out.print("He ");
        }
        else
        {

            System.out.print("She ");
            System.out.println("lives in " + getPlaceOfLiving() 
                + " and is a member of the jury on this case.");
            System.out.println();
        }
    }
}