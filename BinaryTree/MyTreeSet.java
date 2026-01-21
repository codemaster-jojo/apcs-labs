import java.util.*;
/**
 * Replace the MyTreeSet.java in your BST project with this skeleton
 * Call the BSTUtilites methods to add (insert), remove and search 
 * for a value. Make sure to return a sensible value where applicable.
 * Test the working using the TreeSetTester
 * Next write an iterator. 
 * Use the inner class skeleton for the Lists Lab to model one for the TreeSet.
 * Make sure to return the values in ascending order when iterating.
 * Use the TreeSetIteratorTester to test the working.
 * Submit your work to Schoology when done.
 * @author Anu Datar, Jonny Tang
 * @version 01/20/2026
 */
public class MyTreeSet<E>
{
    private TreeNode root;
    private int size;
    private TreeDisplay display;

    /**
     * Constructor for tree set
     */
    public MyTreeSet()
    {
        // Initialize all instance variables 
        display = new TreeDisplay();
        
        size = 0;
    }

    /**
     * Getter for size
     * 
     * @return the size
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks if set contains anything
     * @param obj the object getting checked
     * @return whether obj is in the set
     */
    public boolean contains(Object obj)
    {
        return BSTUtilities.contains(root, (Comparable) obj, display);
    }

    /**
     * if obj is not present in this set, adds obj and
     * returns true; otherwise returns false
     * 
     * @param obj the object
     * 
     * @return true if added, false otherwise
     */
    public boolean add(E obj)
    {
        if (contains(obj))
            return false;
    
        root = BSTUtilities.insert(root, (Comparable) obj, display);
        size++;
        return true;
    }

    /**
     * if obj is present in this set, removes obj and
     * returns true; otherwise returns false
     * 
     * @param obj the object
     * @return true if removed or false if not removed
     */
    public boolean remove(Object obj)
    {
        if (!contains(obj))
            return false;
    
        root = BSTUtilities.delete(root, (Comparable) obj, display);
        size--;
        return true;
    }

    /**
     * toString changes root to string
     * 
     * @return string representing the object
     */
    public String toString()
    {
        return toString(root);
    }

    /**
     * to string given node
     * 
     * @param t the node
     * @return the string representing the object
     */
    private String toString(TreeNode t)
    {
        if (t == null)
            return " ";
        return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
    }
    
    /**
     * Creates an iterator for the tree set
     * 
     * @return new tree iterator
     */
    public TreeIterator<E> iterator()
    {
        return new TreeIterator();
    }
    
    
    private class TreeIterator<E> implements Iterator<E>
    {
        //the index of the value that will be returned by next()
        int nextIndex;
        
        ArrayList<E> inOrderArray;

        /**
         * Creates an tree set iterator with nextIndex = 0.
         * Then it stores the linked list into an array list.
         */
        public TreeIterator()
        {
            inOrderArray = inOrder(MyTreeSet.this.root);
            nextIndex = 0;
        }
        
        /**
         * Inorder traversal helper to store tree set in an array list
         * @param node the current node
         * @return the array list of the elements
         */
        private ArrayList<E> inOrder(TreeNode node)
        {
            ArrayList<E> list = new ArrayList<>();
        
            if (node != null)
            {
                list.addAll(inOrder(node.getLeft()));
                list.add((E) node.getValue());
                list.addAll(inOrder(node.getRight()));
            }
        
            return list;
        }

        /**
         * True/false depending on if there's a next value or not
         * @return boolean representing if there's a next or not
         */
        public boolean hasNext()
        {
            return nextIndex < inOrderArray.size();
        }

        /**
         * Sets the pointer to the next element and returns it
         * @precondition has a next value
         * @return the value of the next element
         */
        public E next()
        {
            E value = inOrderArray.get(nextIndex);
            nextIndex++;
            return value;
        }

        /**
         * Removes the element returned by "next"
         * @precondition index not equal to 0
         * @postcondition removes the last element that was returned by next
         */
        public void remove()
        {
        
            E valueToRemove = inOrderArray.get(nextIndex - 1);
        
            MyTreeSet.this.remove(valueToRemove);
        
            // rebuild iterator state
            inOrderArray = inOrder(MyTreeSet.this.root);
            nextIndex--;
        }
    }
}