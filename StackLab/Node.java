/**
* This class is a node for the singly linked list.
* @author Jonny Tang
* @version 9/26/2025
*/
public class Node
{
    private Object value;
    private Node next;

    /**
     * Constructor. Creates a node with value v and defaults to setting prev and next to null.
     * @param v the value that the node is getting set to.
     */
    public Node(Object v)
    {
        value = v;
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
     * Simple getter for next
     * @return next
     */
    public Node getNext()
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
     * Simple setter for next
     * @param n the node that next is getting set to.
     */
    public void setNext(Node n)
    {
        next = n;
    }
}