/**
 * Measurement class
 *
 * @author  Jonny Tang
 * @version 4/20/2026
 */
public class Measurement
{
    private int elapsed;
    private double temp;
    
    /**
     * Sets the elapsed and temp instance variables using the given parameters.
     * @param e  the time elapsed
     * @param t  the temperature
     */
    public Measurement(int e, double t)
    {
        elapsed = e;
        temp = t;
    }
    
    /** 
     * Retrieves the elapsed time in milliseconds since measurements began. 
     * @return elapsed
     */
    public int getElapsed()
    { 
        return elapsed; 
    }
    
    /** 
     * Returns the temperature recorded. 
     * @return the temperature
     */
    public double getTemp()
    { 
        return temp; 
    }
    
    /** 
     * Returns the elapsed time and temperature 
     * associated with the Measurement object. 
     * @return the measurement in string form
     */
    public String toString()
    {
        return "\tElapsed time: " + elapsed + 
            " temperature: " + temp ;
    }
}