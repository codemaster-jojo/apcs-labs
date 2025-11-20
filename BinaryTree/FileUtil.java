import java.io.*;
import java.util.*;

/**
 * File utility to save and load trees
 * 
 * @author authors of the lab
 * @version 11/7/2025
 */
public class FileUtil
{
    /**
     * Loads a file in 
     * @param fileName the name of the file
     * 
     * @return iterator to the first element of the file's contents
     */
    public static Iterator<String> loadFile(String fileName)
    {
        try
        {
            Scanner in = new Scanner(new File(fileName));
            List<String> list = new ArrayList<String>();
            while (in.hasNextLine())
            {
                list.add(in.nextLine());
            }
            in.close();
            return list.iterator();
        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * This saves a given tree into a file
     * 
     * @param fileName the name of the file
     * @param data the data to save into the file
     */
    public static void saveFile(String fileName, Iterator<String> data)
    {
        try
        {
            PrintWriter out = new PrintWriter(
                new FileWriter(fileName), true);
            while (data.hasNext())
            {
                out.println(data.next());
            }
            out.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}