/**
 * My interface of a queue
 *
 * @author Jonny Tang
 * @version 10/29/2025
 * 
 * @param <E> to make generic; the type of the queue
 */

public interface MyQueue<E>
{
    /**
     * "offers" a value to put at the end of the queue
     * will not put it if the queue is full
     * 
     * @param element the element getting offered
     * @return whether the element was successfully added
     */
    public boolean offer(E element);
    
    /**
     * Removes the next element and returns the element removed
     * Null if no elements left to remove
     * 
     * @return the element returned or null
     */
    public E poll();
    
    /**
     * Checks the next element in line
     * 
     * @return the first in line
     */
    public E peek();
    
    /**
     * Like poll, but will throw exception if nothing left
     * 
     * @return the element returned
     */
    public E remove();
    
    /**
     * Checks if queue is empty
     * 
     * @return whether queue is empty or not
     */
    public boolean isEmpty();
    
    /**
     * Gets size of queue
     * 
     * @return size of queue
     */
    public int size();
}