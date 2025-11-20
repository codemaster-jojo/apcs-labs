
/**
 * Tree node class for a tree
 *
 * @author Jonny Tang
 * @version 11/4/2025
 */
public class TreeNode
{
    private TreeNode left;
    private TreeNode right;
    private Object value;

    /**
     * Constructor for objects of class TreeNode
     * 
     * @param l left chlid
     * @param r right child
     * @param val the value
     */
    public TreeNode(Object val, TreeNode l, TreeNode r)
    {
        left = l;
        right = r;
        value = val;
    }
    
    /**
     * Constructor for the leaf nodes (no children)
     * 
     * @param val the value
     */
    public TreeNode(Object val)
    {
        value = val;
        left = null;
        right = null;
    }
    
    /**
     * Getter for left
     * 
     * @return the left child
     */
    public TreeNode getLeft()
    {
        return left;
    }
    
    /**
     * Getter for right
     * 
     * @return the right child
     */
    public TreeNode getRight()
    {
        return right;
    }
    
    /**
     * Setter for left
     * @param l the left
     */
    public void setLeft(TreeNode l)
    {
        left = l;
    }
    
    /**
     * Setter for right
     * @param r the right 
     */
    public void setRight(TreeNode r)
    {
        right = r;
    }
    
    /**
     * Setter for value
     * @param v the new val
     */
    public void setValue(Object v)
    {
        value = v;
    }
    
    /**
     * Getter for value
     * 
     * @return value
     */
    public Object getValue()
    {
        return value;
    }
}