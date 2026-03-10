import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tester for the big document class.
 *
 * @author Anu Datar
 * @version 05/17/2018
 */
public class DocumentTester
{
    /**
     * Main tester method 
     *
     * @param  str array of String objects 
     */
    public static void main(String[] str) throws FileNotFoundException
    {
        FileReader reader = new FileReader(new File("./MysteryText/mystery1.txt"));
        Scanner scanner = new Scanner(reader);
        Document document = new Document(scanner);
        
        
        document.parseDocument();
        List<Sentence> s = document.getSentences();
        for (int i = 0; i < s.size(); i++)
        {
            System.out.println(s.get(i).toString());
        }
    }
}
