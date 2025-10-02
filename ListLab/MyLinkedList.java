import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class is my linked list created from DoubleNode
 * @author Jonny Tang and creators of the lab
 * @version 9/26/2025
 * @param <E> the type that the linked list will consist of
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
        {
            return "[]";
        }
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
     * Sets node at index to object
     * @param index the index of the node getting set
     * @param obj the value that it's getting set to
     * @postcondition replaces the element at position index with obj
     *               returns the element formerly at the specified position
     * @return the element formerly at index
     */
    public E set(int index, E obj)
    {
        E n = (E) getNode(index).getValue();
        
        getNode(index).setValue(obj);
        
        return n;
    }

    /**
     * Adds to the end
     * @param obj the value getting appended to the end
     * @postcondition appends obj to end of list; returns true
     * @return true
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
     * Adds obj at index index
     * @param index the index where the object is getting added
     * @param obj the value of the node getting added
     * @precondition  0 <= index <= size
     * @postcondition inserts obj at position index,
     *                moving elements at position index and higher
     *                to the right (adds 1 to their indices) and adjusts size
    */
    public void add(int index, E obj)
    {
        if (index==0) 
        {
            addFirst(obj);
        }
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

    /** 
     * Removes element at index
     * @param index the index of the element getting removed
     * @postcondition removes element from position index, moving elements
     *               at position index + 1 and higher to the left
     *               (subtracts 1 from their indices) and adjusts size
     *               returns the element formerly at the specified position
     * @return the value of the element that was removed
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
     * Adds to the beginning of the linked list.
     * @param obj the value of the object getting added to the beginning
     */
    public void addFirst(E obj)
    {
        DoubleNode n = new DoubleNode(obj);
        
        if (size == 0) {
            first = n;
            last = n;
        } 
        else 
        {
            first.setPrevious(n);
            n.setNext(first);
            first = n;
        }
        
        size++;
    }

    /**
     * Adds to the end of the linked list
     * @param obj the value of the object getting added to the end
     */
    public void addLast(E obj)
    {
        DoubleNode n = new DoubleNode(obj);
        
        if (size == 0) 
        {
            first = n;
            last = n;
        } 
        else 
        {
            last.setNext(n);
            n.setPrevious(last);
            last = n;
        }
        
        size++;
    }

    /**
     * gets the value of the head
     * @return the value of the head
     */
    public E getFirst()
    {
        return (E) first.getValue();
    }

    /**
     * Gets the value of the tail
     * @return the value of the tail
     */
    public E getLast()
    {
        return (E) last.getValue();
    }

    /**
     * Removes the first element of the linked list
     * @return the value of the first element of the linked list
     */
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

    /**
     * Removes the last element of the linked list
     * @return the value of the element getting removed
     */
    public E removeLast()
    {
        E val = (E) last.getValue();
        last = last.getPrevious();
        last.setNext(null);
        size--;
        
        return val;
    }

    /**
     * Creates an iterator
     * @return the iterator geting created
     */
    public Iterator<E> iterator()
    {
        return new MyLinkedListIterator();
    }

    /**
     * Linked list iterator
     * @author Jonny Tang
     * @version 9/29/2025
     */
    private class MyLinkedListIterator implements Iterator<E> {
        private DoubleNode nextNode;
        private DoubleNode lastReturned;
    
        /**
         * Creates a linked list iterator and sets defaults
         */
        public MyLinkedListIterator() {
            nextNode = first;
            lastReturned = null;
        }
    
        /**
         * Returns whether there's a next value in the linked list
         * @return true or false depending on if there's a next value
         */
        public boolean hasNext() {
            return nextNode != null;
        }
    
        /**
         * Traverses to the next element and returns the next element value
         * @return the next element value
         */
        public E next() {
            lastReturned = nextNode;
            E value = (E) nextNode.getValue();
            nextNode = nextNode.getNext();
            return value;
        }
    
        /**
         * Removes the last element returned by next
         * @postcondition removes the last element that was returned by next
         */
        public void remove() {
            DoubleNode prev = lastReturned.getPrevious();
            DoubleNode next = lastReturned.getNext();
    
            if (prev != null) 
            {
                prev.setNext(next);
            } 
            else 
            {
                first = next;
            }
    
            if (next != null) 
            {
                next.setPrevious(prev);
            } 
            else 
            {
                last = prev;
            }
    
            size--;
            lastReturned = null;
        }
    }
}