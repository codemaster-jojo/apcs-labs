import java.util.Iterator;
import java.util.ListIterator;

/**
 * This is my implementation of an arraylist
 * @author Jonny Tang and writers of the lab
 * @version 9/29/2025
 */
public class MyArrayList<E>
{
    private int size;
    private Object[] values;  //(Java doesn't let us make an array of type E)

    /**
     * Constructor for the array list
     */
    public MyArrayList()
    {
        size = 0;
        values = new Object[1];
    }

    /**
     * Returns the array to a string in the format [a1, a2..an]
     * @return the string
     */
    public String toString()
    {
    
        if (size == 0)
        {
            return "[]";
        }

        String s = "[";
        for (int i = 0; i < size - 1; i++)
        {
            s += values[i] + ", ";
        }

        return s + values[size - 1] + "]";
    }

    /**
     * Doubles the capacity of the array
     * @postcondition replaces the array with one that is
     *               twice as long, and copies all of the
     *               old elements into it
    */
    private void doubleCapacity()
    {
        Object[] newArrLst = new Object[values.length * 2];
        for (int i=0; i<values.length; i++) 
        {
            newArrLst[i] = values[i];
        }
        
        values = newArrLst;
    }

    /**
     * Gets the capacity of the arraylist
     * @postcondition returns the length of the array
     * @return the length of the array (the capacity)
    */
    public int getCapacity()
    {
        return values.length;
    }
    
    /**
     * Getter for the size of the array
     * @return  the size of the arraylist (i.e. the number of filled slots in the array)
     */
    public int size()
    {
        return size;
    }

    /**
     * Gets the value at the index
     * @precondition index must be in range
     * @param index  the index of the value you're getting
     * @return the value at the given index
     */
    public E get(int index)
    {
        return (E) values[index];
    }

    /** 
     * Sets value at index [index] to value [object]
     * @param index  the index of the value you're setting
     * @param obj  the object that you're setting the value to
     * @postcondition replaces the element at position index with obj
     *               returns the element formerly at the specified position
    */
    public E set(int index, E obj)
    {
        Object temp = values[index];
        values[index] = obj;
        return (E) temp;
        //(You will need to promise the return value is of type E.)
    }

    /**
     * Adds object to the end
     * @param obj the object you're appending
     * @postcondition appends obj to end of list; returns true
    */
    public boolean add(E obj)
    {
        /* if values is already full, call doubleCapacity before adding */

        if (values[values.length-1] != null)
        {
            doubleCapacity();
        }
        values[size] = obj;
        
        size += 1;
        
        return true;
    }

    /**
     * @Removes element at index index
     * @param index the index of the element getting removed
     * @postcondition removes element from position index, moving elements
     *               at position index + 1 and higher to the left
     *               (subtracts 1 from their indices) and adjusts size
     *               returns the element formerly at the specified position
    */
    public E remove(int index)
    {
        size -= 1;
        
        Object elementRemoved = values[index];
        
        for (int i=index; i<values.length-1; i++) 
        {
            values[i] = values[i+1];
        }
        values[values.length-1] = null;
                
        // Shrink capacity code
        
        if (size>=2 && values[size/2-1] == null) 
        {
            Object[] newValues = new Object[size/2];
            for (int i=0; i<size; i++) {
                newValues[i] = values[i];
            }
            values = newValues;
        }
        
        return (E) elementRemoved;
    }

    /**
     * Creates iterator and returns it
     * @return the iterator
     */
    public Iterator<E> iterator()
    {
        return new MyArrayListIterator();
    }

    /**
     * Adds value at index index and value of object
     * @precondition  0 <= index <= size
     * @postcondition inserts obj at position index,
     *               moving elements at position index and higher
     *               to the right (adds 1 to their indices) and adjusts size
    */
    public void add(int index, E obj)
    {
        if (values[getCapacity()] != null) 
        {
            doubleCapacity();
        }
        size += 1;
        
        for (int i=getCapacity()-1; i>index; i--) 
        {
            values[i]=values[i-1];
        }
        
        values[index] = obj;
                
    }

    private class MyArrayListIterator implements Iterator<E>
    {
        //the index of the value that will be returned by next()
        private int nextIndex;

        /**
         * Creates an arraylist iterator with nextIndex = 0.
         */
        public MyArrayListIterator()
        {
            nextIndex = 0;
        }

        /**
         * True/false depending on if there's a next value or not
         * @return boolean representing if there's a next or not
         */
        public boolean hasNext()
        {
            if (MyArrayList.this.values[nextIndex+1] == null) 
            {
                return false;
            }
            else 
            {
                return true;
            }
        }

        /**
         * Sets the pointer to the next element and returns it
         * @return the value of the next element
         */
        public E next()
        {
            nextIndex += 1;
            
            return (E) MyArrayList.this.values[nextIndex - 1];

            //(You will need to promise the return value is of type E.)
        }

        /**
         * Removes the element returned by "next"
         * @postcondition removes the last element that was returned by next
         */
        public void remove()
        {
            // do not shrink here
            
            // remove the element + shift over
            MyArrayList.this.size -= 1;
        
            Object elementRemoved = values[nextIndex];
            
            for (int i=nextIndex; i<values.length-1; i++) 
            {
                values[i] = values[i+1];
            }
            values[values.length-1] = null;
            
            nextIndex -= 1;
        }
    }
}