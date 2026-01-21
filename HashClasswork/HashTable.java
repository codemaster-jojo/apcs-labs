
/**
 * Hashtable class made for strings
 *
 * @author Jonny Tang
 * @version 1/8/2026
 */
public class HashTable
{
    String[] array;
    
    /**
     * Constructor for objects of class HashTable
     */
    public HashTable()
    {
        array = new String[10];
    }

    /**
     * Hashes given key by multiplying ascii of each value * index
     * 
     * @param key the key
     * @return the hash
     */
    public int hash(String key)
    {
        int h = 0;
        for (int i=0; i<key.length(); i++)
        {
            h += (i+1) * ((int) (key.charAt(i)));
        }
        h %= 10;
        return h;
    }
    
    /**
     * Inserts using linear probing algorithm
     *
     * @param key the key
     */
    public void insert(String key)
    {
        int index = hash(key);
        while (array[index] != null)
        {
            index++;
        }
        array[index] = key;
    }
    
    /**
     * Searches the key using hash
     * 
     * @param key the key
     * @return whether the thing is in the hashtable
     */
    public boolean search(String key)
    {
        int index = hash(key);
        while (array[index] != null)
        {
            if (array[index].equals(key)) 
            {
                return true;
            }
            index++;
        }
        return false;
    }
    
    /**
     * Displays the hash table
     * 
     * @postcondition prints out the hash table.
     */
    public void display()
    {
        for (int i=0; i<array.length; i++)
        {
            System.out.println(i + ": " + array[i]);
        }
    }
}