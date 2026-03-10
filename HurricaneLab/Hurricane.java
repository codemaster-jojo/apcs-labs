import java.io.*;

/**
 * Models hurricane information, including categories.  
 * Works with HurricaneOrganizer, provides object and comparison skeletons.
 * 
 * @author Susan King, Jonny Tang
 * @version 2/24/2026
 */
public class Hurricane
{
    //Instance variables
    int year;
    String month;
    int pressure;
    int speed;
    String name;

    /**
     * Initializes a Hurricane object with no information.
     */
    public Hurricane( )
    {
        year = 0;
        pressure = 0;
        speed = 0;
        month = "";
        name = "";
    }

    /**
     * Initializes a Hurricane object with historical information.
     * 
     * @param y     year the hurricane took place
     * @param m     month in String format
     * @param p     hurricane's pressure
     * @param s     hurricane's speed in knots
     * @param n     hurricane's name
     */
    public Hurricane(int y, String m, 
        int p, int s, String n)
    {
        year = y;
        month = m;
        pressure = p;
        speed = s;
        name = n;
    }

    /**
     * Based upon Saffir/Simpson Hurricane Scale, figures out
     * the category using wind speed in knots.
     * 
     * Use https://en.wikipedia.org/wiki/Saffir%E2%80%93Simpson_scale.
     * 
     * @param knots     wind speed in knots
     * @return Saffir/Simpson Hurricane Scale category
     */
    public int determineCategory(int knots)
    {
        if (knots <= 95)
        {
            return 1;
        }
        else if (knots <= 110)
        {
            return 2;
        }
        else if (knots <= 129)
        {
            return 3;
        }
        else if (knots <= 156)
        {
            return 4;
        }
        else
        {
            return 5;
        }
    }

    //Getters

    /**
     * Getter for name
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter for month
     * @return month
     */
    public String getMonth()
    {
        return month;
    }

    /**
     * Getter for pressure
     * @return pressure
     */
    public int getPressure()
    {
        return pressure;
    }

    /**
     * Getter for speed
     * @return speed
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Getter for year
     * @return year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Getter for category
     * @return categor
     */
    public int getCategory()
    {
        return determineCategory(speed);
    }

    /**
     * Prints out the hurricane
     */
    public void print()
    {
        System.out.println(toString( ));
    }

    /**
     * To string method of the hurricane
     * @return the string format of the hurricane
     */
    public String toString()
    {
        return String.format("%-4d %-5s %-15s %-5d %5d %5d ", 
            year, month, name, getCategory(), speed, pressure);
    }

    /**
     * Compares year to other hurricane's year
     * works like compareTo
     * 
     * @param h the other hurricane
     * @return the compare to
     */
    public int compareYearTo(Hurricane h)
    {
        return year - h.getYear();
    }

    /**
     * Compares name to other hurricane's name
     * works like compareTo
     * 
     * @param h the other hurricane
     * @return the compare to of the name
     */
    public int compareNameTo(Hurricane h)
    {
        return name.compareTo(h.getName());
    }

    /**
     * Compares pressure to other hurricane's pressure
     * works like compareTo
     * 
     * @param h the other hurricane
     * @return the compare to of pressure
     */
    public int comparePressureTo(Hurricane h)
    {
        return pressure - h.getPressure();
    }

    /**
     * Compares speed to other hurricane's speed
     * works like compareTo
     * 
     * @param h the other hurricane
     * @return the compare to of speed
     */
    public int compareSpeedTo(Hurricane h)
    {
        return speed - h.getSpeed();
    }

    /**
     * Compares category to other hurricane's category
     * works like compareTo
     * 
     * @param h the other hurricane
     * @return the compare to of category
     */
    public int compareCategoryTo(Hurricane h)
    {
        return getCategory() - h.getCategory();
    }
}
