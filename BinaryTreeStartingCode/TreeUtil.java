import java.util.*;
import java.util.Iterator;

/**
 * TreeUtil has a variety of methods to call on trees.
 * @author Jonny Tang
 * @version 11/6/2025
 *
 */ 
public class TreeUtil 
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);
    private static final boolean debug = false;


    /**
     * leftmost returns the value in the leftmost node of the tree.  It is 
     * implemented with a loop
     * precondition:  t is non-empty
     * @param t is the tree whose leftmost node is to be found
     * @return the VALUE in the leftmost node of t.
     */
    public static Object leftmost(TreeNode t)
    {
        TreeNode temp = t;
        while (temp.getLeft() != null)
        {
            temp = temp.getLeft();
        }
        
        return temp.getValue();
    }

    /**
     * rightmost returns the value in the rightmost node of the tree.  It is 
     * implemented with a loop
     * precondition:  t is non-empty
     * @param t is the tree whose rightmost node is to be found
     * @return the VALUE in the rightmost node of t.
     */
    public static Object rightmost(TreeNode t)
    {
        TreeNode temp = t;
        while (temp.getRight() != null)
        {
            temp = temp.getRight();
        }
        
        return temp.getValue();
    }
    /**
     * maxDepth calculates the maximum depth of a binary tree.  An empty tree has 
     * depth of 0 and a tree with one node has a depth of 1
     * @param t a pointer to the root of a tree whose depth is to be calculated
     * @return the depth of the tree rooted at t
     */
    public static int maxDepth(TreeNode t)
    {
        if (t == null)
        {
            return 0;
        }
        if (t.getRight() == null && t.getLeft() == null)
        {
            return 1;
        }
        else if (t.getRight() == null)
        {
            return maxDepth(t.getLeft())+1;
        }
        else if (t.getLeft() == null)
        {
            return maxDepth(t.getRight())+1;
        }
        else
        {
            return Math.max(maxDepth(t.getRight())+1, maxDepth(t.getLeft())+1);
        }
    }

    /**
     * create a random tree of the specified depth.  No attempt to balance the tree
     * is provided.
     * @param depth of the tree
     * @return TreeNode object that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
        {
            return null;
        }
        return new TreeNode(((int)(Math.random() * 10)),
            createRandom(depth - 1),
            createRandom(depth - 1));
    }
    /**
     * counts the number of nodes (including leaves) that are in the tree rooted at t
     * @param t the root of the tree
     * @return the number of nodes in the tree rooted at t
     */
    public static int countNodes(TreeNode t)
    {
        if (t.getRight() == null && t.getLeft() == null)
        {
            return 1;
        }
        else if (t.getRight() == null)
        {
            return countNodes(t.getLeft()) + 1;
        }
        else if (t.getLeft() == null)
        {
            return countNodes(t.getRight()) + 1;
        }
        else
        {
            return countNodes(t.getLeft()) + countNodes(t.getRight()) + 1;
        }
    }
    /**
     * counts the number of leaves in the tree rooted at t
     * @param t the root of the binary tree
     * @return the number of leaves in the tree
     */
    public static int countLeaves(TreeNode t)
    {
        if (t == null)
        {
            return 0;
        }
        
        if (t.getRight() == null && t.getLeft() == null)
        {
            return 1;
        }
        else if (t.getRight() == null)
        {
            return countLeaves(t.getLeft());
        }
        else if (t.getLeft() == null)
        {
            return countLeaves(t.getRight());
        }
        else
        {
            return countLeaves(t.getLeft()) + countLeaves(t.getRight());
        }
    }
    /**
     * perform a pre-order traversal of the binary tree rooted at t, lighting
     * up the nodes on the display as the traversal takes place
     * @param t the root of the tree to traverse
     * @param display the display that will show the traversal
     * postcondition: each node in t has been lit up on display
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        display.visit(t);
        
        if (t.getLeft() != null)
        {
            preOrder(t.getLeft(), display);
        }
        if (t.getRight() != null)
        {
            preOrder(t.getRight(), display);
        }
        
    }
    /**
     * perform an in-order traversal of the binary tree rooted at t, lighting
     * up the nodes on the display as the traversal takes place
     * @param t the root of the tree to traverse
     * @param display the display that will show the traversal
     * postcondition: each node in t has been lit up on display
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        if (t.getLeft() != null)
        {
            inOrder(t.getLeft(), display);
        }
        
        display.visit(t);
        
        if (t.getRight() != null)
        {
            inOrder(t.getRight(), display);
        }
    }
    /**
     * perform a post-order traversal of the binary tree rooted at t, lighting
     * up the nodes on the display as the traversal takes place
     * @param t the root of the tree to traverse
     * @param display the display that will show the traversal
     * postcondition: each node in t has been lit up on display
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        if (t.getLeft() != null)
        {
            postOrder(t.getLeft(), display);
        }
        
        if (t.getRight() != null)
        {
            postOrder(t.getRight(), display);
        }
        
        display.visit(t);
    }
    
    /**
     * fill a list with the values of a binary tree rooted at t using a 
     * pre-order traversal with '$' values added to the list whenever
     * a null pointer is encountered
     * @param t the root of the tree
     * @param list the returned list of values in the tree
     */
    public static void fillList(TreeNode t, List<String> list) 
    {
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(t);
    
        while (!nodes.empty()) 
        {
            TreeNode currNode = nodes.pop();
    
            if (currNode == null) 
            {
                list.add("$");
            } 
            else 
            {
                list.add(currNode.getValue() + "");
                nodes.push(currNode.getRight());
                nodes.push(currNode.getLeft());
            }
        }
    }
    
    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name
     * @param fileName is the name of the file to create which will hold the data
     *        values in the tree
     * @param t is the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        ArrayList<String> arr = new ArrayList<String>();
        fillList(t, arr);
        
        Iterator<String> it = arr.iterator();
        FileUtil.saveFile(fileName, it);
    }
    
    /**
     * buildTree takes in an iterator which will iterate through a valid description of
     * a binary tree with String values.  Null nodes are indicated by "$" markers
     * @param it the iterator which will iterate over the tree description
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        if (!it.hasNext())
        {
            return null;
        }
        
        String value = it.next();
        if (value.equals("$"))
        {
            return null;
        }
        
        TreeNode newNode = new TreeNode(value, buildTree(it), buildTree(it));
        return newNode;
    }
    
    /**
     * read a file description of a tree and then build the tree
     * @param fileName is a valid file name for a file that describes a binary tree
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        Iterator<String> it = FileUtil.loadFile(fileName);
        return buildTree(it);
    }
    
    /**
     * utility method that waits for a user to type text into Std Input and then press enter
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }
    /**
     * plays a single round of 20 questions
     * postcondition:  plays a round of twenty questions, asking the user questions as it
     *                 walks down the given knowledge tree, lighting up the display as it goes;
     *                 modifies the tree to include information learned.
     * @param t a pointer to the root of the game tree
     * @param display which will show the progress of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {    
        throw new RuntimeException("Write ME!");
    }
    /** 
     * plays a game of 20 questions
     * Begins by reading in a starting file and then plays multiple rounds
     * until the user enters "quit".  Then the final tree is saved
     */
    public static void twentyQuestions()
    {
        throw new RuntimeException("Write ME!");
    }
    /**
     * copy a binary tree
     * @param t the root of the tree to copy
     * @return a new tree, which is a complete copy
     *         of t with all new TreeNode objects
     *         pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if (t == null)
        {
            return null;
        }
        
        return new TreeNode(t.getValue(), copy(t.getLeft()), copy(t.getRight()));
    }
    
    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return true if t1 and t2 describe trees having the same shape, false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        boolean isSame = true;
        

        if (t1 != null && t2 != null && t1.getLeft() != null && t2.getLeft() != null)
        {
            // both of them have a left node
            isSame = isSame && sameShape(t1.getLeft(), t2.getLeft());
        }
        
        if (t1 != null && t2 != null && t1.getRight() != null && t2.getRight() != null)
        {
            isSame = isSame && sameShape(t1.getRight(), t2.getRight());
        }
        
        return isSame;
    }
    /**
     * Generate a tree for decoding Morse code
     * @param display the display that will show the decoding tree
     * @return the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        return tree;
    }
    /**
     * helper method for building a Morse code decoding tree.
     * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
     * @param decodingTree is the partial decoding tree
     * @param letter is the letter to add
     * @param code is the Morse code for letter
     * @param display is the display that will show progress as the method walks 
     *        down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
                                    String code, TreeDisplay display)
    {
        TreeNode currNode = decodingTree;
        for (int i=0; i<code.length(); i++)
        {
            display.visit(currNode);
            if (code.substring(i, i+1).equals("."))
            {
                // go left: if there's no left create one
                // otherwise just traverse
                if (currNode.getLeft() == null)
                {
                    TreeNode newNode = new TreeNode("");
                    currNode.setLeft(newNode);
                }
                
                currNode = currNode.getLeft();
            }
            else if (code.substring(i, i+1).equals("-"))
            {
                if (currNode.getRight() == null)
                {
                    TreeNode newNode = new TreeNode("");
                    currNode.setRight(newNode);
                }
                
                currNode = currNode.getRight();
            }
        }
        
        currNode.setValue(letter);
    }
    /**
     * decodes Morse code by walking the decoding tree according to the input code
     * @param decodingTree is the Morse code decoding tree
     * @param cipherText is Morse code consisting of dots, dashes, and spaces
     * @param display is the display object that will show the decoding progress
     * @return the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
    {
        String decoded = "";
        
        TreeNode currNode = decodingTree;
        for (int i=0; i<cipherText.length(); i++)
        {
            String v = cipherText.substring(i, i+1);
            if (v.equals(" "))
            {
                decoded += currNode.getValue();
                currNode = decodingTree;
            }
            if (v.equals("."))
            {
                currNode = currNode.getLeft();
            }
            if (v.equals("-"))
            {
                currNode = currNode.getRight();
            }
        }
        
        decoded += currNode.getValue();
        
        return decoded;
    }
    /**
    * Evaluates expression tree.
    * 
    * @param expTree the tree getting evaluated
    * @param display the tree display
    * 
    * @return the number
    */
    public static Number eval(TreeNode expTree, TreeDisplay display)
    {    
        display.visit(expTree);
        
        String value = expTree.getValue().toString();
    
        if (value.equals("*"))
        {
            return eval(expTree.getLeft(), display).doubleValue()
                 * eval(expTree.getRight(), display).doubleValue();
        }
        else if (value.equals("/"))
        {
            return eval(expTree.getLeft(), display).doubleValue()
                 / eval(expTree.getRight(), display).doubleValue();
        }
        else if (value.equals("+"))
        {
            return eval(expTree.getLeft(), display).doubleValue()
                 + eval(expTree.getRight(), display).doubleValue();
        }
        else if (value.equals("-"))
        {
            return eval(expTree.getLeft(), display).doubleValue()
                 - eval(expTree.getRight(), display).doubleValue();
        }
        else
        {
            return Double.parseDouble(value);
        }
    }
    
    /**
    * Creates expression tree by first converting to postfix and then evaluating.
    * 
    * @param exp the expression getting turned into a tree
    * 
    * @return the node to the root
    */
    public static TreeNode createExpressionTree(String exp)
    {
        String expPostfix = infixToPostfix(exp);
        Stack<TreeNode> help = new Stack<TreeNode>();
        
        for (int i=0; i<expPostfix.length(); i++)
        {
            String sub = expPostfix.substring(i, i+1);
            if (sub.equals("*") || sub.equals("/") ||
                    sub.equals("+") || sub.equals("-"))
            {
                TreeNode tr1 = help.pop();
                TreeNode tr2 = help.pop();
                help.push(new TreeNode(sub, tr2, tr1));
            }
            else if (sub.equals(" "))
            {
                int ignore = 1234;
            }
            else 
            {
                help.push(new TreeNode(sub));
            }
        }
        
        return help.pop();
    }

    /**
    * debug printout
    * @postcondition out is printed to System.out
    * @param out the string to send to System.out
    */
    
    private static void debugPrint(String out)
    {
        if(debug) 
        {
            System.out.println("debug: " + out);
        }
    }
    
    /**
     * returns a string in postfix form 
     * if given an expression in infix form as a parameter
     * does this conversion using a Stack
     * THIS IS A HELPER METHOD FOR CREATING THE EXPRESSION TREE. 
     * USING POSTFIX ITS A LOT EASIER.
     * 
     * @precondition everything is separated by spaces: i.e.
     * "( 1 + 3 ) * 5"; "(1 + 3) * 5" doesn't work. 
     * @param expr valid expression in infix form
     * @return equivalent expression in postfix form
     */
    private static String infixToPostfix(String expr)
    {
        Stack<String> postFix = new Stack<String>();
        
        
        String strPostfix = "";

        // Write code here
        
        String currNum = "";
        for (int i=0; i<expr.length(); i++) 
        {
            
            
            String sub = expr.substring(i, i+1);
            
            if (sub.equals("^"))
            {
                while (postFix.peek().equals("^"))
                {
                    strPostfix += postFix.pop() + " ";
                }
                
                postFix.push(sub);
            }
            else if (sub.equals("%") || 
                        sub.equals("*") || 
                        sub.equals("/"))
            {
                while (!postFix.empty() && !postFix.peek().equals("+") && 
                        !postFix.peek().equals("-") && !postFix.peek().equals("("))
                {
                    strPostfix += postFix.pop() + " ";
                }
                
                postFix.push(sub);
            }
            else if (sub.equals("+") ||
                        sub.equals("-"))
            {
                while (!postFix.empty() && !postFix.peek().equals("("))
                {
                    strPostfix += postFix.pop() + " ";
                }
                
                postFix.push(sub);
            }
            
            else if (sub.equals("("))
            {
                postFix.push(sub);
            }
            else if (sub.equals(")"))
            {
                while (!postFix.empty() && !postFix.peek().equals("("))
                {
                    strPostfix += postFix.pop() + " ";
                }
                postFix.pop();
            }
            
            else if (sub.equals(" "))
            {
                strPostfix += currNum;
                if (currNum.length() > 0)
                {
                    strPostfix += " ";
                }
                currNum = "";
            }
            
            else
            {
                currNum += sub;
            }
        }
        strPostfix += currNum + " ";
        
        while (!postFix.empty())
        {
            strPostfix += postFix.pop() + " ";
        }
        return strPostfix;
    }
}
    