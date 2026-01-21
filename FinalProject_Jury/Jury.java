
/**
 * Jury: array of judges
 * This is where the decisions are made
 *
 * @author Jonny Tang, Damien Wang
 * @version 12/08/2025
 */
public class Jury
{
    private Judge[] jury;
    private int size;

    /**
     * Simple constructor for jury. Takes in an array of judges
     * 
     * @param judges the array of judges
     */
    public Jury(Judge[] judges)
    {
        jury = judges;
        size = judges.length;
    }

    /**
     * Getter for jury
     * 
     * @return jury
     */
    public Judge[] getJury()
    {
        return jury;
    }

    /**
     * Getter for size
     * 
     * @return size of jury
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * Determines if person is guilty or innocent
     * Some randomness to it.
     * 
     * @param crime the crime committed
     * @return whether criminal will be guilty or not
     */
    public boolean isGuilty(String crime)
    {
        if(crime.toLowerCase().contains("felony") || crime.toLowerCase().contains("murder"))
        {
            int verdict = (int) (Math.random() * 7) + 4;
            if(verdict >= 5)
            {
                return true;
            }
            return false;
        }
        else
        {
            int verdict = (int) (Math.random()  * 7) +4;
            if(verdict >=8)
            {
                return false;
            }
            return true;
        }
    }
    
    /**
     * Introduces every jury member
     * @postcondition member intros are printed.
     */
    public void introduce()
    {
        for (int i=0; i<size; i++)
        {
            jury[i].selfIntro();
        }
    }
}


