import java.util.Iterator;
import java.util.ListIterator;

public class MyArrayList<E>
{
    private int size;
    private Object[] values;  //(Java doesn't let us make an array of type E)

    public MyArrayList()
    {
        size = 0;
        values = new Object[1];
    }

    public String toString()
    {
    
        if (size == 0)
            return "[]";

        String s = "[";
        for (int i = 0; i < size - 1; i++)
        {
            s += values[i] + ", ";
        }

        return s + values[size - 1] + "]";
    }

    /**
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
    * @postcondition returns the length of the array
    */
    public int getCapacity()
    {
        return values.length;
    }

    public int size()
    {
        /*
        for (int i=0; i<values.length; i++) 
        {
            if (values[i]==null)
            {
                return i;
            }
        }
        return values.length;
        */
        return size;
    }

    public E get(int index)
    {
        return (E) values[index];

        //(You will need to promise the return value is of type E.)
    }

    /** 
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

    public Iterator<E> iterator()
    {
        return new MyArrayListIterator();
    }

    /**
    * @precondition  0 <= index <= size
    * @postcondition inserts obj at position index,
    *               moving elements at position index and higher
    *               to the right (adds 1 to their indices) and adjusts size
    */
    public void add(int index, E obj)
    {
        if (values[getCapacity()] != null) {
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

        public MyArrayListIterator()
        {
            nextIndex = 0;
        }

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

        public E next()
        {
            nextIndex += 1;
            
            return (E) MyArrayList.this.values[nextIndex - 1];

            //(You will need to promise the return value is of type E.)
        }

        //@postcondition removes the last element that was returned by next
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