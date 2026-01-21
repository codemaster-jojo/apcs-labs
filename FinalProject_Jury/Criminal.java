
/**
 * Criminal on trial. Inherits from person class
 *
 * @author Jonny Tang, Damien Wang
 * @version 12/08/2025
 */
public class Criminal extends Person
{
    private String crime;
    /**
     * Constructor for objects of class Criminal
     * 
     * @param a the age
     * @param g the gender
     * @param n the name
     * @param l place of living
     * @param c crime
     */
    public Criminal(int a, String g, String n, String l, String c)
    {
        super(a, g, n, l);
        crime = c;
    }

    /**
     * Gives all of info about the cirminal
     */
    @Override
    public void selfIntro()
    {
        System.out.println("Criminal " + getName() + " is a " + getAge()
            + " year old " + getGender() + ".");
        if (getGender().toLowerCase().equals("male"))
        {
            System.out.print("He ");
        }
        else
        { 
            System.out.print("She ");
            System.out.println("lives in " + getPlaceOfLiving()
                + " and is being tried for " + crime + ".");
            System.out.println();
        }
    }
}