/**
* This class is a node for the doubly linked list.
* @author Jonny Tang, creators of the lab
* @version 9/26/2025
*/
public class DoubleNode
{
    private Object value;
    private DoubleNode previous;
    private DoubleNode next;

    /**
     * Constructor. Creates a node with value v and defaults to setting prev and next to null.
     * @param v the value that the node is getting set to.
     */
    public DoubleNode(Object v)
    {
        value = v;
        previous = null;
        next = null;
    }

    /**
     * Simple getter for value
     * @return value
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * Simple getter for previous
     * @return previous
     */
    public DoubleNode getPrevious()
    {
        return previous;
    }

    /**
     * Simple getter for next
     * @return next
     */
    public DoubleNode getNext()
    {
        return next;
    }

    /**
     * Simple setter for value
     * @param v  the new value that value is getting set to
     */
    public void setValue(Object v)
    {
        value = v;
    }

    /**
     * Simple setter for previous
     * @param p the node that previous is getting set to
     */
    public void setPrevious(DoubleNode p)
    {
        previous = p;
    }

    /**
     * Simple setter for next
     * @param n the node that next is getting set to.
     */
    public void setNext(DoubleNode n)
    {
        next = n;
    }
}