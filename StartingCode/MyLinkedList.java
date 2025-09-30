import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class is my linked list created from DoubleNode
 * @author Jonny Tang and creators of the lab
 * @version 9/26/2025
 */
public class MyLinkedList<E>
{
    private DoubleNode first;
    private DoubleNode last;
    private int size;

    /**
     * Creates a linked list initialized with first and last as null and size as 0
     */
    public MyLinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Returns the linked list to string
     * @return the linked list as a string with format [a1, a2...an]
     */
    public String toString()
    {
        DoubleNode node = first;
        if (node == null)
            return "[]";
        String s = "[";
        while (node.getNext() != null)
        {
            s += node.getValue() + ", ";
            node = node.getNext();
        }
        return s + node.getValue() + "]";
    }

    /** 
     * Gets the given node traversing forward
     * @precondition  0 <= index <= size / 2
     * @param index the index of the node you're getting
     * @postcondition starting from first, returns the node
     *               with given index (where index 0
     *               returns first)
     *               
     * @return the reference to the node you're finding
    */               
    private DoubleNode getNodeFromFirst(int index)
    {
        DoubleNode currNode = first;
        int it = 0;
        while (it < index)
        {
            currNode = currNode.getNext();
            it++;
        }
        
        return currNode;
    }

    /** 
     * Gets the given node traversing backward
     * @precondition  size / 2 <= index < size
     * @param index the index of the node you're finding
     * @postcondition starting from last, returns the node
     *               with given index (where index size-1
     *                returns last)
     * @return the reference to the node you're finding
    */              
    private DoubleNode getNodeFromLast(int index)
    {
        DoubleNode currNode = last;
        int it = size-1;
        while (it > index) 
        {
            currNode = currNode.getPrevious();
            it--;
        }
        
        return currNode;
    }

    /** 
     * Gets the node using getNodeFromFirst or getNodeFromLast.
     * @precondition  0 <= index < size
     * @param index the index of the node you're getting
     * @postcondition starting from first or last (whichever
     *               is closer), returns the node with given
     *               index
     * @return the reference to the node at given index
    */               
    private DoubleNode getNode(int index)
    {
        if (index < size/2)
        {
            return getNodeFromFirst(index);
        }
        else
        {
            return getNodeFromLast(index);
        }
    }

    /**
     * Getter for size
     * @return the size
     */
    public int size()
    {
        return size;
    }

    /**
     * Gets the value of the node at given index
     * @param index  the index of the node you're getting
     * @return the value of node at index index
     */
    public E get(int index)
    {
        return (E) getNode(index).getValue();
    }

    /** 
     * @postcondition replaces the element at position index with obj
     *               returns the element formerly at the specified position
     */
    public E set(int index, E obj)
    {
        E n = (E) getNode(index).getValue();
        
        getNode(index).setValue(obj);
        
        return n;
    }

    /**
    * @postcondition appends obj to end of list; returns true
    */
    public boolean add(E obj)
    {
        DoubleNode n = new DoubleNode(obj);
        
        if (size==0) 
        {
            first = n;
            last = n;
            
            size++;
            
            return true;
        }
        
        size++;
        last.setNext(n);
        n.setPrevious(last);
        last = n;
        
        return true;
    }

    /** 
    * @postcondition removes element from position index, moving elements
    *               at position index + 1 and higher to the left
    *               (subtracts 1 from their indices) and adjusts size
                   returns the element formerly at the specified position
    */
    public E remove(int index)
    {
        if (index==0) 
        {
            return removeFirst();
        }
        
        if (index==size-1)
        {
            return removeLast();
        }
        
        DoubleNode currNode = first;
        while (index > 0)
        {
            currNode = currNode.getNext();
            index--;
        }
        
        E val = (E) currNode.getValue();
        
        if (currNode.getPrevious() != null) 
        {
            currNode.getPrevious().setNext(currNode.getNext());
        }
        if (currNode.getNext() != null) 
        {
            currNode.getNext().setPrevious(currNode.getPrevious());
        }
        

        size--;
        return val;
    }

    /** 
    * @precondition  0 <= index <= size
    * @postcondition inserts obj at position index,
    *                moving elements at position index and higher
    *                to the right (adds 1 to their indices) and adjusts size
    */
    public void add(int index, E obj)
    {
        if (index==0) addFirst(obj);
        else
        {
            DoubleNode n = new DoubleNode(obj);
        
            DoubleNode curr = first;
            while (index>0) 
            {
                curr = curr.getNext();
                index--;
            }
            
            curr.getPrevious().setNext(n);
            n.setPrevious(curr.getPrevious());
            n.setNext(curr);
            size++;
        }
    }

    public void addFirst(E obj)
    {
        DoubleNode n = new DoubleNode(obj);
        first.setPrevious(n);
        n.setNext(first);
        first = n;
        size++;
    }

    public void addLast(E obj)
    {
        DoubleNode n = new DoubleNode(obj);
        last.setNext(n);
        n.setPrevious(last);
        last = n;
        size++;
    }

    public E getFirst()
    {
        return (E) first.getValue();
    }

    public E getLast()
    {
        return (E) last.getValue();
    }

    public E removeFirst()
    {
        E val = (E) first.getValue();
        first = first.getNext();
        
        if (first != null) 
        {
            first.setPrevious(null);
        }
        size--;
        
        return val;
    }

    public E removeLast()
    {
        E val = (E) last.getValue();
        last = last.getPrevious();
        last.setNext(null);
        size--;
        
        return val;
    }

    public Iterator<E> iterator()
    {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<E>
    {
        private DoubleNode nextNode;

        public MyLinkedListIterator()
        {
            nextNode = new DoubleNode(1);
            nextNode.setNext(MyLinkedList.this.first);
        }

        public boolean hasNext()
        {
           return nextNode.getNext()==null;
        }

        public E next()
        {            
            nextNode = nextNode.getNext();
            
            return (E) nextNode.getValue();

        }

        //@postcondition removes the last element that was returned by next
        public void remove()
        {
            nextNode.getPrevious().setNext(nextNode.getNext());
            nextNode.getNext().setPrevious(nextNode.getPrevious());
        }
    }
}