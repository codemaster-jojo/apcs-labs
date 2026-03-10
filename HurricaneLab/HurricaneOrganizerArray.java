import java.io.*;
import java.util.*;

/**
 * Models hurricane information, works with Hurricane class
 * and the user to manipulate an array of hurricane data.
 * 
 * Data came from http://www.aoml.noaa.gov/hrd/tcfaq/E23.html except for 2018.
 * 2018 data came from https://en.wikipedia.org/wiki/2018_Atlantic_hurricane_season.
 *
 * @author Susan King, Jonny Tang
 * @version 2/24/2026
 */
public class HurricaneOrganizerArray
{
    private Hurricane [] hurricanes;

    /**
     * Hurricane organizer reads the file
     * @param filename the file
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public HurricaneOrganizerArray(String filename)throws IOException
    {
        readFile(filename);   
    }

    /**
     * Determines the field length
     * @param filename the file name
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    private static int determineFileLength(String filename) throws IOException
    {
        Scanner inFile = new Scanner(new File(filename));
        int counter = 0;

        while(inFile.hasNextLine())
        {
            counter++;
            inFile.nextLine();
        }
        inFile.close();
        return counter;
    }

    /**
     * Reads the file
     * @param filename the file name
     */
    public void readFile(String filename) throws IOException
    {
        hurricanes = new Hurricane [determineFileLength(filename)];
        int hurYear, hurPressure, hurSpeed;
        String hurName, hurMonth;
        Scanner inFile = new Scanner(new File(filename));

        for(int i = 0; i < hurricanes.length; i++)
        {
            hurYear = inFile.nextInt();
            hurMonth = inFile.next();
            hurPressure = inFile.nextInt();
            hurSpeed = inFile.nextInt();
            String tempName = inFile.nextLine();
            hurName = "";
            for(int k = 0; k < tempName.length(); k++)
            {
                char c = tempName.charAt(k);
                if(('a' <= c && c <= 'z') || ('A' <= c && c <='Z'))
                    hurName += c;
            }
            Hurricane h = new Hurricane(hurYear, hurMonth, hurPressure, hurSpeed, hurName);
            hurricanes [i] = h;
        }
        inFile.close();
    }

    /**
     * Finds the max wind speed
     * @return the max wind speed
     */
    public int findMaxWindSpeed( )
    {
        int maxSpeed = 0;
        for (Hurricane hurricane : hurricanes)
        {
            if (hurricane.getSpeed() > maxSpeed)
            {
                maxSpeed = hurricane.getSpeed();
            }
        }
        return maxSpeed;
    }

    /**
     * Finds the max pressure
     * @return the max pressure
     */
    public int findMaxPressure( )
    {
        int maxPressure = 0;
        for (Hurricane hurricane : hurricanes)
        {
            if (hurricane.getPressure() > maxPressure)
            {
                maxPressure = hurricane.getPressure();
            }
        }
        return maxPressure;
    }
             
    /**
     * Finds the min wind speed
     * @return the min wind speed
     */
    public int findMinWindSpeed( )
    {
        int minSpeed = Integer.MAX_VALUE;
        for (Hurricane hurricane : hurricanes)
        {
            if (hurricane.getSpeed() < minSpeed)
            {
                minSpeed = hurricane.getSpeed();
            }
        }
        return minSpeed;
    }

    /**
     * Finds the min pressure
     * @return the min pressure
     */
    public int findMinPressure( )
    {
        int minPressure = Integer.MAX_VALUE;
        for (Hurricane hurricane : hurricanes)
        {
            if (hurricane.getPressure() < minPressure)
            {
                minPressure = hurricane.getPressure();
            }
        }
        return minPressure;
    }

    /**
     * Calculates average wind speed
     * @return avg wind speed
     */
    public double calculateAverageWindSpeed( )
    {
        int sumSpeed = 0;
        
        for (Hurricane hurricane : hurricanes)
        {
            sumSpeed += hurricane.getSpeed();
        }
        return (double) sumSpeed / hurricanes.length;
    }

    /**
     * Calculates average pressure
     * @return average pressure
     */
    public double calculateAveragePressure( )
    {
        int sumPressure = 0;
        
        for (Hurricane hurricane : hurricanes)
        {
            sumPressure += hurricane.getPressure();
        }
        return (double) sumPressure / hurricanes.length;
    }

    /**
     * Calculates average category
     * @return the average category
     */
    public double calculateAverageCategory( )
    {
        int sumCategory = 0;
        
        for (Hurricane hurricane : hurricanes)
        {
            sumCategory += hurricane.getCategory();
        }
        return (double) sumCategory / hurricanes.length;
    }

    /**
     * Sorts ascending based upon the hurricanes' years,
     * The algorithm is selection sort.
     */
    public void sortYears()
    {
        for (int i = 0; i<hurricanes.length; i++)
        {
            int indexMin = i;
            for (int j=i+1; j<hurricanes.length; j++)
            {
                if (hurricanes[j].compareYearTo(hurricanes[indexMin]) < 0)
                {
                    indexMin = j;
                }
            }
            Hurricane temp = hurricanes[indexMin];
            hurricanes[indexMin] = hurricanes[i];
            hurricanes[i] = temp;
        }
    }

    /**
     * Lexicographically sorts hurricanes based on the hurricanes' name, 
     * using insertion sort.
     */
    public void sortNames()
    {
        // array list because its SO MUCH EASIER to insert things in the middle
        ArrayList<Hurricane> hurs = new ArrayList<>(Arrays.asList(hurricanes));
        for (int i=1; i<hurricanes.length; i++)
        {
            // i is the object I'm "inserting"
            for (int j=0; j<i; j++)
            {
                if (hurs.get(j).compareNameTo(hurs.get(i)) > 0) // put i before j
                {
                    Hurricane removed = hurs.remove(i);
                    hurs.add(j, removed);
                    // end loop
                    j = i;
                }
            }
        }
        // put everything back
        for (int i=0; i<hurricanes.length; i++)
        {
            hurricanes[i] = hurs.get(i);
        }
    }

    /**
     * Sorts descending based upon the hurricanes' categories,
     * using selection sort.
     */
    public void sortCategories()
    {
        for (int i = 0; i<hurricanes.length; i++)
        {
            int indexMax = i;
            for (int j=i+1; j<hurricanes.length; j++)
            {
                if (hurricanes[j].compareCategoryTo(hurricanes[indexMax]) > 0)
                {
                    indexMax = j;
                }
            }
            Hurricane temp = hurricanes[indexMax];
            hurricanes[indexMax] = hurricanes[i];
            hurricanes[i] = temp;
        }
    }  

    /**
     * Sorts descending based upon pressures using a non-recursive merge sort.
     */
    public void sortPressures()
    {
        int n = hurricanes.length;
        
        for (int subArrSize = 1; subArrSize < n; subArrSize *= 2)
        {
            for (int left = 0; left < n; left += 2 * subArrSize)
            {
                int mid = Math.min(left + subArrSize, n);
                int right = Math.min(left + 2 * subArrSize, n);
                
                if (mid < right) // only merge if two halves exist
                {
                    sortPressureHelper(left, mid, right);
                }
            }
        }
    }
    
    /**
     * Sorts descending a portion of array based upon pressure, 
     * using selection sort.
     * 
     * @param   leftStart   the first index to start the sort
     * @param   mid    the start of the right side. extra param for convenience
     * @param   rightEnd     one past the last index to sort; hence, end position
     *                  is excluded in the sort
     */
    
    private void sortPressureHelper(int leftStart, int mid, int rightEnd)
    {
        Hurricane[] tempArr = new Hurricane[rightEnd - leftStart];
        
        int left = leftStart;
        int right = mid;
        int index = 0;
        
        while (left < mid && right < rightEnd)
        {
            if (hurricanes[left].comparePressureTo(hurricanes[right]) > 0)
            {
                tempArr[index] = hurricanes[left];
                index++;
                left++;
            }
            else
            {
                tempArr[index] = hurricanes[right];
                index++;
                right++;
            }
        }

        // add the remaining stuff to the end
        while (left < mid)
        {
            tempArr[index] = hurricanes[left];
            index++;
            left++;
        }
        
        while (right < rightEnd)
        {
            tempArr[index] = hurricanes[right];
            index++;
            right++;
        }
        
        // copy from tmp -> actual
        for (int i = 0; i < tempArr.length; i++)
        {
            hurricanes[leftStart + i] = tempArr[i];
        }
    }

    /**
     * Sorts ascending based upon wind speeds using a recursive merge sort. 
     * 
     * @param low the start point of the sort
     * @param high the end point of the sort
     */
    public void sortWindSpeeds(int low, int high)
    {
        mergeWindSpeedsSortHelper(low, high+1);
    }

    /**
     * Merges two consecutive parts of an array, using wind speed as a criteria
     * and a temporary array.  The merge results in an ascending sort between
     * the two given indices.
     * 
     * @precondition the two parts are sorted ascending based upon wind speed
     * 
     * @param low   the starting index of one part of the array.
     *              This index is included in the first half.
     * @param high  the ending index of the second part of the array.  
     *              This index is included in the merge.
     */
    private void mergeWindSpeedsSortHelper(int low, int high)
    {
        // make sure blocks are more than 1 size or else its gonna infinite loop
        if (high - low <= 1)
        {
            return; // single element is already sorted
        }
        
        // break array in half
        int mid = (low + high) / 2;
        
        // break up the first half and sort
        mergeWindSpeedsSortHelper(low, mid);
        
        // break up second half and sort
        mergeWindSpeedsSortHelper(mid, high);
        
        // merge two halves
        Hurricane[] tempArr = new Hurricane[high - low];
        
        int p1 = low;
        int p2 = mid;
        int index = 0;
        
        while (p1 < mid && p2 < high)
        {
            if (hurricanes[p1].compareSpeedTo(hurricanes[p2]) < 0)
            {
                // p1 < p2
                tempArr[index] = hurricanes[p1];
                p1++;
                index++;
            }
            else
            {
                tempArr[index] = hurricanes[p2];
                p2++;
                index++;
            }
        }
        
        // dump the remaining stuff
        while (p1 < mid)
        {
            tempArr[index] = hurricanes[p1];
            p1++;
            index++;
        }
        while (p2 < high)
        {
            tempArr[index] = hurricanes[p2];
            p2++;
            index++;
        }
        
        // copy back
        for (int i=0; i<tempArr.length; i++)
        {
            hurricanes[i + low] = tempArr[i];
        }
    }

    /**
     * Sequential search for all the hurricanes in a given year.
     * 
     * @param   year the year
     * @return  an array of objects in Hurricane that occured in
     *          the parameter year
     */
    public Hurricane [] searchYear(int year)
    {
        int counter = 0;
        for (Hurricane h : hurricanes)
        {
            if (h.getYear() == year)
            {
                counter++;
            }
        }

        Hurricane[] matches = new Hurricane[counter];
        
        int index = 0;
        for (Hurricane h : hurricanes)
        {
            if (h.getYear() == year)
            {
                matches[index] = h;
                index++;
            }
        }
        
        return matches;
    }     

    /**
     * Binary search for a hurricane name.
     * 
     * @param  name   hurricane name being search
     * @return a Hurricane array of all objects in hurricanes with specified name. 
     *         Returns null if there are no matches
     */
    public Hurricane[ ] searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.length - 1, 
            new ArrayList<Hurricane>());
    }

    /**
     * Recursive binary search for a hurricane name.  This is the helper
     * for searchHurricaneName.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name  hurricane name to search for
     * @param   low   the smallest index that needs to be checked
     * @param   high  the highest index that needs to be checked
     * @param   result the resulting array list
     * 
     * @return  a Hurricane array of all Hurricane objects with a specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] searchHurricaneNameHelper(String name, int low,
        int high, ArrayList<Hurricane> result)
    {
        int mid = (low + high) / 2;
        
        // Test for the base case when a match is not found
        if (low > high)
        {
            return new Hurricane[0];
        }

        // Test for match
        if (hurricanes[mid].getName().equals(name))
        {
            return retrieveMatchedNames(name, mid);
        }
        
        
        // Determine if the potential match is in the 
        // "first half" of the considered items in the array
        else if (hurricanes[mid].getName().compareTo(name) > 0)
        {
            // go to the left
            // exclude mid bc already checked
            return searchHurricaneNameHelper(name, low, mid-1, result);
        }
        
        // The potential match must be in the
        // "second half" of the considered items in the array
        else
        {
            // go to the right, exclude mid
            return searchHurricaneNameHelper(name, mid+1, high, result);
        }
    }

    /**
     * Supports Binary Search method to get the full range of matches.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name hurricane name being search for
     * @param   index  the index where a match was found
     * @return  a Hurricane array with objects from hurricanes with specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] retrieveMatchedNames (String name, int index)
    {
        // Find the start where the matches start:
        int lowIndex = index;
        while (hurricanes[lowIndex].compareNameTo(hurricanes[index]) == 0)
        {
            lowIndex--;
        }
        lowIndex++;
        
        // Find the end of the matches:
        int highIndex = index;
        while (hurricanes[highIndex].compareNameTo(hurricanes[index]) == 0)
        {
            highIndex++;
        }
        highIndex--;
        
        // Copy the objects whose names match:
        Hurricane[] foundHurs = Arrays.copyOfRange(hurricanes, lowIndex, highIndex+1);
        
        return foundHurs;
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n", 
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Prints out all hurricane data
     * @param hurs the hurricanes
     */
    public void printHurricanes(Hurricane [] hurs)
    {
        if(hurs.length == 0)
        {
            System.out.println("\nVoid of hurricane data.");
            return;
        }
        printHeader();
        for(Hurricane h: hurs)
        {
            System.out.println(h);
        }
    }

    /**
     * Prints out the menu
     */
    public void printMenu()
    {
        System.out.println("\n\nEnter option: ");
        System.out.println("\t 1 - Print all hurricane data \n" +
            "\t 2 - Print maximum and minimum data \n" +
            "\t 3 - Print averages \n" +
            "\t 4 - Sort hurricanes by year \n" +
            "\t 5 - Sort hurricanes by name \n" +
            "\t 6 - Sort hurricanes by category, descending \n" +
            "\t 7 - Sort hurricanes by pressure, descending \n" +
            "\t 8 - Sort hurricanes by speed \n" + 
            "\t 9 - Search for hurricanes for a given year \n" +
            "\t10 - Search for a given hurricane by name \n" +
            "\t11 - Quit \n");
    }

    /**
     * Prints maxes and min values of stuff
     */
    public void printMaxAndMin( )
    {
        System.out.println("Maximum wind speed is " + 
            findMaxWindSpeed( ) +
            " knots and minimum wind speed is " + 
            findMinWindSpeed( ) + " knots.");
        System.out.println("Maximum pressure is " + 
            findMaxPressure( ) +
            " and minimum pressure is " + 
            findMinPressure( ) + ".");
    }

    /**
     * Prints the averages
     */
    public void printAverages( )
    {
        System.out.printf("Average wind speed is %5.2f knots. \n" , 
            calculateAverageWindSpeed( ));
        System.out.printf("Average pressure is %5.2f. \n" , 
            calculateAveragePressure( ));
        System.out.printf("Average category is %5.2f. \n" , 
            calculateAverageCategory( ));
    }

    /**
     * Interacts with user and takes input
     * @return whether it completed task or not
     */
    public boolean interactWithUser( )
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        printMenu();
        int choice = in.nextInt();
        // clear the input buffer
        in.nextLine();

        if(choice == 1)
        {
            printHurricanes( ); 
        }
        else if (choice == 2)
        {
            printMaxAndMin( );
        }
        else if (choice == 3)
        {
            printAverages( );
        }
        else if(choice == 4)
        {
            sortYears();
            printHurricanes( );
        }
        else if(choice == 5)
        {
            sortNames();
            printHurricanes( );
        }
        else if(choice == 6)
        {
            sortCategories();
            printHurricanes( );
        }
        else if(choice == 7)
        {
            sortPressures();
            printHurricanes( );
        }
        else if(choice == 8)
        {
            sortWindSpeeds(0, hurricanes.length - 1);
            printHurricanes( );
        }
        else if(choice == 9)
        {
            System.out.print("\n\tWhich year do you want to search for?\n\t");
            int year = in.nextInt();
            printHurricanes(searchYear(year));
        }
        else if(choice == 10)
        {
            System.out.print("\n\tWhich name do you want to search for?\n\t");
            String name = in.next();
            printHurricanes(searchHurricaneName(name));
        }
        else if (choice == 11)
        {
            done = true;
        }  
        return done;
    }

    /**
     * Comment the method even though you did not write it.
     * 
     * @param args  user's information from the command line
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main (String [] args) throws IOException
    {
        HurricaneOrganizerArray cane = new HurricaneOrganizerArray("hurricanedata.txt");
        boolean areWeDoneYet = false;
        while ( ! areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser( );    
        }
    }
}
