/**
 * a collection of static methods for operating on binary search trees
 * 
 * @author Jonny Tang
 * @version 11/10/2025
 */
public abstract class BSTUtilities
{
    /**
     * Checks if tree contains x
     * 
     * @precondition  t is a binary search tree in ascending order
     * @postcondition returns true if t contains the value x;
     * otherwise, returns false
     * 
     * @param t the tree node currently on
     * @param x the value getting checked
     * @param display the display
     */
    //
    public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
    {
        
        if (t == null)
        {
            return false;
        }
        
        display.visit(t);

        if (x.compareTo(t.getValue()) == 0)
        {
            return true;
        }
        else if (x.compareTo(t.getValue()) < 0)
        {
            return contains(t.getLeft(), x, display);
        }
        else
        {
            return contains(t.getRight(), x, display);
        }
    }

    /**
     * @precondition:  t is a binary search tree in ascending order
     * @postcondition: if t is empty, returns a new tree containing x;
     * otherwise, returns t, with x having been inserted
     * at the appropriate position to maintain the binary
     * search tree property; x is ignored if it is a
     * duplicate of an element already in t; only one new
     * TreeNode is created in the course of the traversal
     * 
     * @param t the tree node currently on
     * @param x the value getting added
     * @param display the display
     */
    public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
    {
        if (t == null)
        {
            return new TreeNode(x);            
        }
        
        display.visit(t);
        
        if (x.compareTo(t.getValue()) == 0)
        {
            return t;
        }
        else if (x.compareTo(t.getValue()) < 0)
        {
            if (t.getLeft() == null)
            {
                t.setLeft(new TreeNode(x));
                return t;
            }
            insert(t.getLeft(), x, display);
        }
        else if (x.compareTo(t.getValue()) > 0)
        {
            if (t.getRight() == null)
            {
                t.setRight(new TreeNode(x));
                return t;
            }
            insert(t.getRight(), x, display);
        }
        return t;
    }

    /**
     * Deletes node reference t from tree
     * 
     * @precondition  t is a binary search tree in ascending order
     * @postcondition returns a pointer to a binary search tree,
     * in which the value at node t has been deleted
     * (and no new TreeNodes have been created)
     * 
     * @param t the one getting deleted
     * @param display the one getting displayed
     */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    {
        if (t.getRight() == null && t.getLeft() == null)
        {
            return null;
        }
        
        else if (t.getRight() == null) // implied left != null
        {
            return t.getLeft();
        }
        else if (t.getLeft() == null) // implied right != null
        {
            return t.getRight();
        }
        else // 2 children
        {
            TreeNode curr = t.getRight();
            
            // if no lefter node, set t's value, then 
            if (curr.getLeft() == null)
            {
                t.setValue(curr.getValue());
                t.setRight(curr.getRight());
                
                return t;
            }
            
            // get leftmost node
            
            while (curr.getLeft() != null && curr.getLeft().getLeft() != null)
            {
                curr = curr.getLeft();
            }
            
            t.setValue(curr.getLeft().getValue()); // error here.
            curr.setLeft(null);
            
            return t;
        }
    }

    /**
     * deletes given node value from bst
     * 
     * @precondition  t is a binary search tree in ascending order
     * @postcondition returns a pointer to a binary search tree,
     * in which the value x has been deleted (if present)
     * (and no new TreeNodes have been created)
     * 
     * @param t the root 
     * @param x the value getting deleted
     * @param display the display
     *  
     * @return the new root node
     */
    public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
    {
        // base case: if t's value is x
        if (x.compareTo(t.getValue()) == 0)
        {
            t = deleteNode(t, display);
                                    
            return t;
        }
        
        if (!contains(t, x, display))
        {
            return t;
        }
        
        // traverse
        TreeNode curr = t;
        int side = -1;
        while (curr != null && side == -1)
        {
            if (x.compareTo(curr.getValue()) > 0)
            {
                if (curr.getRight() == null)
                {
                    curr = null;
                }
                else if (x.compareTo(curr.getRight().getValue()) == 0) 
                {
                    // if right child is the right value
                    side = 0;
                }
                else
                {
                    curr = curr.getRight();
                }
            }
            else
            {
                if (curr.getLeft() == null)
                {
                    curr = null;
                }
                else if (x.compareTo(curr.getLeft().getValue()) == 0) 
                {
                    // if left child is the right value
                    side = 1;
                }
                else
                {
                    curr = curr.getLeft();
                }
            }
        }
        if (curr == null)
        {
            int a = 1;
        }
        else if (side == 0)
        {
            curr.setRight(deleteNode(curr.getRight(), display));
        }
        else if (side == 1)
        {
            curr.setLeft(deleteNode(curr.getLeft(), display));
        }
        else
        {
            return t;
        }
        
        return t;
    }
}