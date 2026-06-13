import java.util.*;

/**
 * WeatherStation class
 *
 * @author   Jonny Tang
 * @version  4/20/2026
 */
public class WeatherStation
{
    private List<Measurement> intervalAverageTemps;
    
    /**
     * Constructs object of the Weather class.
     *
     * @param inputs    array of consecutive temperature measurements objects in
     *                  increasing order of elapsed time
     * @param interval  the fixed time interval length for calculating
     *                  average of polled temperature measurements
     */
    public WeatherStation(Measurement[] inputs, int interval)
    { 
        intervalAverageTemps = createIntervalAverageTemps(inputs, interval);
    }
    
    
    /**
     * Calculates average measures for the given intervals.
     * 
     * @param inputs  array of consecutive input measurements
     * in increasing order of elapsed time
     * @param intervalLength  the interval length
     * 
     * @return list of the averages of all samples in inputs within the given interva
     */
    public List<Measurement> createIntervalAverageTemps(Measurement[] inputs, int intervalLength)
    { 
        ArrayList<Measurement> result = new ArrayList<>();
        if (inputs.length == 0) 
        {
            return result;
        }
    
        int start = inputs[0].getElapsed();
        int last = inputs[inputs.length - 1].getElapsed();
    
        int intervalStart = start;
        int intervalEnd = start + intervalLength;
    
        while (intervalStart <= last)
        {
            int tempSum = 0;
            int count = 0;
    
            for (int i = 0; i < inputs.length; i++)
            {
                int t = inputs[i].getElapsed();
    
                if (t >= intervalStart && t <= intervalEnd)
                {
                    tempSum += inputs[i].getTemp();
                    count++;
                }
            }
    
            if (count == 0)
            {
                result.add(new Measurement(intervalEnd, 0));
            }
            else
            {
                result.add(new Measurement(intervalEnd, tempSum / (double) count));
            }
    
            intervalStart += intervalLength;
            intervalEnd += intervalLength;
        }
    
        return result;
    }
    
    /**
     * Retrieve the list of interval average temperatures.
     *
     * @return the interval average temperature data
     */
    public List<Measurement> getIntervalAverageTemps()
    { 
        return intervalAverageTemps;
    }
    
    /**
     * Returns a list containing the Measurement objects in intervalAverageTemps
     * sorted in ascending order of temperatures.
     *
     * @postcondition intervalAverageTemps is not altered
     *
     * @return a list of measurements sorted by temperatures
     */
    public List<Measurement> sortOnTemperatures()
    { 
        List<Measurement> sorted = new ArrayList<Measurement>();
        
        for (Measurement m : intervalAverageTemps)
        {
            sorted.add(m);
        }
        
        for (int i=0; i<sorted.size(); i++)
        {
            int imin = i;
            for (int j=i+1; j<sorted.size(); j++)
            {
                if (sorted.get(imin).getTemp() > sorted.get(j).getTemp())
                {
                    imin = j;
                }
            }
            Measurement temp = sorted.get(imin);
            sorted.set(imin, sorted.get(i));
            sorted.set(i, temp);
        }
        
        return sorted;
    }
    
    /**
    * Return the median temperature of a list of measurements.
    * If the list is an odd length, the median value is in the middle.
    * Otherwise, the median is a mean (average) of the two temperatures in the middle.
    *
    * @precondition the data is arranged in order of temperature from least to greatest
    * @param list a collection of measurements
    *
    * @return the median temperature
    */
    public double findMedian(List<Measurement> list)
    { 
        if (list == null || list.size() == 0)
        {
            return 0;
        }
        
        if (list.size() % 2 == 1)
        {
            return list.get(list.size()/2).getTemp();
        }
        else
        {
            return (list.get(list.size()/2).getTemp() + list.get(list.size()/2-1).getTemp())/2;
        }
    }
    
    /**
     * Calculates the fences of the measurements in intervalAverageTemps based upon temperature.
     *
     * @precondition Objects in intervalAverageTemps are non-null and will always
     * contain measurements in increasing order by elapsed time.
     * @postcondition the measurements in intervalAverageTemp remain in
     * increasing order of time elapsed and unaltered
     *
     * @return array containing the lower and upper inner fences, 
     * in that order, in intervalAverageTemp
     */
    public double[] findFenceBounds()
    {     
        List<Measurement> sorted = sortOnTemperatures();
        
        double median = findMedian(sorted);
        
        // i js realized this is unnecessary
        int[] firstHalf = new int[] {0, sorted.size()/2};
        int[] secondHalf = new int[] {sorted.size()/2, sorted.size()};
        
        if (sorted.size() % 2 == 1)
        {
            secondHalf[0]+= 1;
        }
        
        // copy over
        List<Measurement> subList1 = new ArrayList<Measurement>();
        for (int i=firstHalf[0]; i<firstHalf[1]; i++)
        {
            subList1.add(sorted.get(i));
        }
        
        List<Measurement> subList2 = new ArrayList<Measurement>();
        for (int i=secondHalf[0]; i<secondHalf[1]; i++)
        {
            subList2.add(sorted.get(i));
        }
                
        double q1 = findMedian(subList1);
        double q3 = findMedian(subList2);
                
        return new double[]{q1 - 1.5 * Math.abs(median-q1), q3 + 1.5 * Math.abs(median-q3)};
    }
    
    /**
     * Removes outliers from the list of measurement samples in intervalAverageTemps.
     * The outliers are determined by the information provided by findFenceBounds.
     * The removed outliers are returned.
     *
     * @precondition intervalAverageTemps is non-null and will always contain measurements
     * in increasing order of time elapsed
     * @postcondition the measurements in intervalAverageTemp remain in increasing order
     * of time elapsed
     *
     * @return list of outlier measurements removed from intervalAverageTemps
     */
    public List<Measurement> filterOutliers()
    { 
        if (intervalAverageTemps.size() == 0)
        {
            return null;
        }
        
        double[] bounds = findFenceBounds();
        List<Measurement> removed = new ArrayList<Measurement>();
        
        while (intervalAverageTemps.size() > 0 &&
            intervalAverageTemps.get(0).getTemp() < bounds[0])
        {
            removed.add(intervalAverageTemps.get(0));
            intervalAverageTemps.remove(0);
        }
        
        while (intervalAverageTemps.size() > 0 &&
            intervalAverageTemps.get(intervalAverageTemps.size()-1).getTemp() > bounds[1])
        {
            removed.add(intervalAverageTemps.get(intervalAverageTemps.size()-1));
            intervalAverageTemps.remove(intervalAverageTemps.size()-1);
        }
        
        return removed;
    }
}