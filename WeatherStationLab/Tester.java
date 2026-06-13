import java.util.*;

public class Tester 
{
    public static void main(String [ ] args)
    {
        System.out.println("TESTING CONSTRUCTOR: ----------");
        Measurement[] m = new Measurement[9];
        m[0] = new Measurement(100, 66.0);
        m[1] = new Measurement(130, 67.0);
        m[2] = new Measurement(150, 66.0);
        m[3] = new Measurement(200, 68.0);
        m[4] = new Measurement(260, 66.0);
        m[5] = new Measurement(310, 65.0);
        m[6] = new Measurement(330, 67.0);
        m[7] = new Measurement(355, 71.0);
        m[8] = new Measurement(380, 61.0);
        
        WeatherStation station = new WeatherStation(m, 50);
        List<Measurement> processed = station.getIntervalAverageTemps();
        for (Measurement ms : processed)
        {
            System.out.println(ms);
        }
        
        System.out.println("\n\nTESTING SORT METHOD: ----------");
        List<Measurement> sorted = station.sortOnTemperatures();
        for (Measurement ms : sorted)
        {
            System.out.println(ms);
        }
        
        System.out.println("\n\nTESTING OUTLIER REMOVAL: ----------");
        System.out.println("MEDIAN: " + station.findMedian(sorted));
        System.out.println("FENCES: " + station.findFenceBounds()[0] + ", " + station.findFenceBounds()[1]);
        
        List<Measurement> removed = station.filterOutliers();
        System.out.println("Outliers removed: ");
        processed = station.getIntervalAverageTemps();
        
        for (Measurement ms : removed)
        {
            System.out.println(ms);
        }
        System.out.println("\nRemaining data: ");
        for (Measurement ms : processed)
        {
            System.out.println(ms);
        }
        
    }
}