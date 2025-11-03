
/**
 * My implementation of a Queue using array.
 *
 * @author Jonny Tang
 * @version 10/29/2025
 * 
 * @param <E> is to make generic
 */
public class ArrayQueue<E> implements MyQueue<E>
{
    private Object[] queue;
    private int head;
    private int tail;
    
    /**
     * Creates a queue with given capacity (max line length
     * basically)
     * 
     */
    public ArrayQueue()
    {
        queue = new Object[10];
        head = 0;
        tail = 0;
    }
    
    /**
     * Adds an element to the end of the queue
     * @param element the element getting added
     * @return whether the thing was added properly or not.
     */
    public boolean offer(E element)
    {
        int nextTail = (tail + 1) % queue.length;

        if (nextTail == head)
        {
            return false;
        }

        queue[tail] = element;
        
        tail = nextTail;
        
        return true;
    }
    
    /**
     * Removes and returns the top of the queue (first in). 
     * Returns null if the queue is empty.
     * @return the element that just got removed.
     */
    public E poll()
    {
        if (head == tail) // empty
        {
            return null;
        }

        E element = (E) queue[head];
        queue[head] = null;
        
        head = (head + 1) % queue.length;

        return element;
    }
    
    /**
     * Returns the front element without removing it. 
     * @return the element on the top
     */
    public E peek()
    {
        if (head == tail) 
        {
            return null;
        }
        return (E) queue[head];
    }
    
    /**
     * Removes the front element of the queue.
     * 
     * @return the front element of the queue.
     */
    public E remove()
    {
        Object boo = poll();
        if (boo == null)
        {
            throw new RuntimeException("Cannot remove from empty queue");
        }
        return (E) boo;
    }
    
    /**
     * Returns whether the queue is empty or not
     * 
     * @return whether the queue is empty or not.
     */
    public boolean isEmpty()
    {
        return head == tail;
    }
    
    /**
     * Returns the number of elements in the queue
     * right now (size, not capacity)
     * 
     * @return the size of the queue.
     */
    public int size()
    {
        // modulo takes care of loop case and removes length
        // when its not needed.
        
        return (tail - head + queue.length) % queue.length;
    }
    
}