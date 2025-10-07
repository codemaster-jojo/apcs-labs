
/**
 * Implementation of stack using linked list
 *
 * @param <E> generic
 * @author Jonny Tang
 * @version 10/7/2025
 */
public class MyStack<E>
{
    private Node top;
    /**
     * Constructor for objects of class MyStack
     */
    public MyStack()
    {
        top = null;
    }

    /**
     * Checks if the stack is empty
     *
     * @return    whether the stack is empty or not
     */
    public boolean empty()
    {
        return top == null;
    }
    
    /**
     * Returns the top
     * 
     * @return top
     */
    
    public E peek()
    {
        return (E) top.getValue();
    }
    
    /**
     * Pops the first element, then returns that element
     * 
     * @return the element that's popped
     */
    public E pop()
    {
        Node tmpTop = top;
        top = top.getNext();
        
        return (E) tmpTop.getValue();
    }
    
    /**
     * Adds one element to the top of the stack
     * 
     * @param n the element added
     * 
     * @return the element added
     */
    public E push(E n)
    {
        Node newN = new Node(n);
        
        if (top==null)
        {
            top=newN;
            return n;
        }
        
        newN.setNext(top);
        top = newN;
        
        return n;
    }
    
    /**
     * Searches for the element passed in
     * 
     * @param val the value getting searched for
     * @return the 1-index of the element
     */
    public int search(E val)
    {
        MyStack tempStack = new MyStack();
        
        int returnValue = 0;
        
        Node curr = top;
        boolean isRunning = true;
        while (curr!=null && isRunning)
        {
            returnValue++;
            if (curr.getValue()==val)
            {
                isRunning = false;
            }
            
            curr=curr.getNext();
            tempStack.push(pop());
        }
        if (isRunning)
        {
            returnValue = -1;
        }
        
        // move all things back
        while (!tempStack.empty())
        {
            push((E) tempStack.pop());
        }
        
        return returnValue;
    }
    
    /**
     * Gets the size by popping all elements and counting and then putting it all back.
     * 
     * @return the size
     */
    public int size()
    {
        MyStack tempStack = new MyStack();
        
        int sz = 0;
        
        Node curr = top;
        while (curr!=null)
        {
            sz++;
            curr = curr.getNext();
            tempStack.push(pop());
        }
        
        // move them back
        while (!tempStack.empty())
        {
            push((E) tempStack.pop());
        }
        
        return sz;
    }
}