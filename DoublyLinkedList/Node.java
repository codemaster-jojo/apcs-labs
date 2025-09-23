/**
 * Node class for a doubly linked list.
 *
 * @author Jonny Tang
 * @version 9/18/2025
 */
public class Node
{
    // instance variables - replace the example below with your own
    private int value;
    private Node prev;
    private Node next;

    /**
     * Constructor for objects of class Node
     * 
     * @param value  the value you are initializing it to
     * @param next  the next node that you are initializing to
     */
    public Node(int value, Node next)
    {
        this.value=value;
        this.next=next;
        this.prev=null;
    }
    
    /**
     * This method sets all the prevs to the right thing
     */
    public void setPrevs() 
    {
        if (next==null) 
        {
            return;
        }
        next.setPrev(this);
        next.setPrevs();
    }

    /**
     * This method is a getter for value
     *
     * @return    the value integer
     */
    public int value() 
    {
        return value;
    }
    
    /**
     * This method is a getter for next
     *
     * @return    next variable
     */
    public Node next() 
    {
        return next;
    }
    
    /**
     * This method is a getter for prev
     *
     * @return    prev variable
     */
    public Node prev() 
    {
        return prev;
    }
    
    /**
     * This method is a setter for value
     *
     * @param  newValue  the new value that you are setting it to
     */
    public void setValue(int newValue)
    {
        value = newValue;
    }

    /**
     * This method is a setter for next
     *
     * @param  newNext  the new next that you are setting it to
     */
    public void setNext(Node newNext) 
    {
        next = newNext;
    }
    
    /**
     * This method is a setter for next
     *
     * @param  newPrev   the new prev that you are setting it to
     */
    public void setPrev(Node newPrev) 
    {
        prev = newPrev;
    }
    
    /**
     * This method returns the end of this linked list
     *
     * @return    the end of the linked list
     */
    public Node getEnd() 
    {
        if (next==null) 
        {  
            return this;
        }
        return next.getEnd();
    }
    
    /**
     * This method returns the start of this linked list
     *
     * @return    the start of the linked list
     */
    public Node getStart() 
    {
        if (prev==null) 
        {  
            return this;
        }
        return prev.getStart();
    }
    
    /**
     * This method traverses forward through the linked list with recursion
     */
    public void traverseForward() 
    {
        if (next == null) 
        {
            System.out.print(value + " END OF LINKED LIST\n");
            return;
        }
        System.out.print(value + " ");
        next.traverseForward();
    }
    
    /**
     * This method traverses backward through the linked list with recursion
     */
    public void traverseBackward() 
    {
        getEnd().traverseBackwardHelper();
    }
    
    /**
     * This method is the helper class to traverse backward through the linked list with recursion
     */
    private void traverseBackwardHelper() 
    {
        if (prev == null) 
        {
            System.out.print(value + " START OF LINKED LIST\n");
            return;
        }
        System.out.print(value + " ");
        prev.traverseBackwardHelper();
    }
    
    /**
     * This method adds a node to the end of the linked list
     *
     * @param   v  the value getting added to the end
     */
    public void addToEnd(int v) 
    {
        Node temp = getEnd();
        temp.setNext(new Node(v, null));
        getEnd().setPrev(temp);
        return;
    }
    
    /**
     * This method adds a node to the start of the linked list
     *
     * @param   v  the value getting added to the front
     * @return    the pointer to the new first node
     */
    public Node addToStart(int v) 
    {
        Node temp = new Node(v, this);
        this.setPrev(temp);
        return temp;
    }
    
    /**
     * This method adds a node after a given value, or at the end if the value doesn't exist.
     *
     * @param   givenVal  the value that which val is getting addded after
     * @param   v  the value getting added
     */
    public void addAfterValue(int givenVal, int v)
    {
        if (this.value==givenVal) 
        {
            Node temp = new Node(v, next);
            temp.setPrev(this);
            if (next != null)
            {
                next.setPrev(temp);
            }
            setNext(temp);
        }
        else if (next==null) 
        {
            // add to end
            Node temp = new Node(v, null);
            setNext(temp);
            temp.setPrev(this);
        }
        else 
        {
            next.addAfterValue(givenVal, v);
        } 
        return;
    }
    
    
    /**
     * This method reverses the linked list
     *
     * @return the pointer to the new start node
     */
    public Node reverse()
    {
        Node end = getEnd();
        Node tmp = reverseHelper();
        tmp.setNext(null);
        
        end.setPrevs();
        end.setPrev(null);
        
        return end;
    }
    
    /**
     * This method is the helper to reverses the linked list
     *
     * @return the current node in the list thats getting reverse
     */
    private Node reverseHelper()
    {
        if (next==null)
        {
            return this;
        }
        Node tmp = next.reverseHelper();
        tmp.setNext(this);
        return this;
        
    }
    
    /*
    public Node sort()
    {
        boolean running = true;
        Node currNode = this;
        Node startNode = this;
        while (running) 
        {
            if (currNode != null && currNode.next() != null && currNode.next().value()<=currNode.value()) 
            {
                System.out.print(currNode.next().value() + " ");
                System.out.println(currNode.value());
                // if wrong order, swap the two
                Node tmpNext = currNode.next();
                Node tmpPrev = currNode.prev();
                Node tmpNextNext = currNode.next().next();
                
                tmpNext.setPrev(tmpPrev);
                if (tmpPrev!=null) tmpPrev.setNext(tmpNext);
                
                currNode.setNext(tmpNextNext);
                if (tmpNextNext!=null) tmpNextNext.setPrev(currNode);
                
                tmpNext.setNext(currNode);
                currNode.setPrev(tmpNext);
                
                currNode = currNode.next();
            }
            else
            {
                if (checkSorted(Integer.MIN_VALUE)) 
                {
                    running = false;
                }
                else 
                {
                    currNode = startNode;
                }
            }
        }
        
        // now, find the first node!
        return getStart();
    }
    */
   
    // sort with recursion
    public Node sort() {
        boolean notFinished = true;
        while (notFinished) {
            sortHelper();
            if (checkSorted(Integer.MIN_VALUE)) {
                
            }
        }
        return getStart();
    }
    
    private Node sortHelper() {
        return new Node(1,null);
    }
    
    private boolean checkSorted(int prevValue) 
    {
        if (value<=prevValue) return false;
        if (next==null) return true;
        return next.checkSorted(value);
    }
}