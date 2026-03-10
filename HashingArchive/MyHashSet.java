import java.util.*;
/**
 * Hash set implementation using separate chaining
 * Store in array of linked lists.
 * 
 * @param <E> generic
 * 
 * @author Jonny Tang
 * @version 1/21/2026
 */

public class MyHashSet<E>
{
    private static final int NUM_BUCKETS = 5;
    private LinkedList<E>[] buckets;
    private int size;
    
    /**
     * Creates a hash set object
     */
    public MyHashSet()
    {
        buckets = new LinkedList[NUM_BUCKETS];
        size = 0;

        for (int i=0; i<NUM_BUCKETS; i++)
        {
            buckets[i] = new LinkedList<E>();
        }
    }

    /**
     * returns the index of the bucket where obj might be found
     * 
     * @param obj the object
     * @return the index of the obj bucket
     */
    private int toBucketIndex(Object obj)
    {
        return obj.hashCode() % NUM_BUCKETS;
    }

    /**
     * Getter for size
     * 
     * @return the size
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks if obj in buckets
     * @param obj the object
     * @return whether the hash set contains obj or not
     */
    public boolean contains(Object obj)
    {
        return buckets[toBucketIndex(obj)].contains(obj);
    }

    /**
     * if obj is not present in this set, adds obj and
     * returns true; otherwise returns false
     * 
     * @param obj the object
     * @return whether the object was successfully added
     */
    public boolean add(E obj)
    {
        if (contains(obj))
        {
            return false;
        }
        else
        {
            int index = toBucketIndex(obj);
            buckets[index].add(obj);
            size++;
            return true;
        }
    }

    /**
     * if obj is present in this set, removes obj and
     * returns true; otherwise returns false
     * 
     * @param obj the obj getting removed
     * @return whether the obj was successfully removed
     */
    public boolean remove(Object obj)
    {
        if (contains(obj))
        {
            int index = toBucketIndex(obj);
            buckets[index].remove(obj);
            size--;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Hash set to string.
     * 
     * @return the hash set in string format
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < buckets.length; i++)
        {
            if (buckets[i].size() > 0)
            {
                s += i + ":" + buckets[i] + " ";    
            }
            
        }
            
        return s;
    }
    
    /**
     * Creates an iterator
     * @return iterator
     */
    public HashSetIterator iterator()
    {
        return new HashSetIterator();
    }
    
    /**
     * Hash set iterator that compresses the buckets into an array list
     * and iterates through that basically
     */
    private class HashSetIterator implements Iterator<E>
    {
        private ArrayList<E> elements;
        private int index;
        private boolean canRemove;        
                
        /**
         * Creates an tree set iterator with arrayindex and listindex
         */
        public HashSetIterator()
        {
            elements = new ArrayList<E>();
            index = 0;
            canRemove = false;
            
            // buckets to array
            for (int i = 0; i < buckets.length; i++)
            {
                for (E item : buckets[i])
                {
                    elements.add(item);
                }
            }

        }
        
        /**
         * True/false depending on if there's a next value or not
         * @return boolean representing if there's a next or not
         */
        public boolean hasNext()
        {
            return index < elements.size();
        }

        /**
         * Sets the pointer to the next element and returns it
         * @precondition has a next value
         * @return the value of the next element
         */
        public E next()
        {
            canRemove = true;
            return elements.get(index++);
        }

        /**
         * Removes the element returned by "next"
         * @precondition index not equal to 0
         * @postcondition removes the last element that was returned by next
         */
        public void remove()
        {
            E toRemove = elements.get(index - 1);
            MyHashSet.this.remove(toRemove);
    
            canRemove = false;
        }
    }
}