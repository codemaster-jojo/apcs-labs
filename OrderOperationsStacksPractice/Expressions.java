import java.util.Stack;

/**
 * Class to make infix to postfix and evaluate expressions with order of 
 * operations. Match parenthesis method included.
 * 
 * @author Anu Datar, Jonny Tang
 * @version 10/14/2025
 */

public class Expressions
{
    /**
     * parenthesis matching : An expression is said to be balanced if
     * every opener has a corresponding closer, in the right order
     * {, [ or ( are the only types of brackets allowed
     *     
     * @param   expression containing operands operators 
     *          and any of the 3 supportedbrackets
     * @return  true is the parenthesis are balanced         
     *          false otherwise
     */
    public static boolean matchParenthesis(String expression)
    {
        Stack<String> parentheses = new Stack<String>();
        
        for (int i=0; i<expression.length(); i++)
        {
            if (expression.substring(i, i+1).equals("(") || 
                    expression.substring(i, i+1).equals("{") || 
                    expression.substring(i, i+1).equals("["))
            {
                parentheses.push(expression.substring(i, i+1));
            }
            else if (expression.substring(i, i+1).equals(")"))
            {
                if (parentheses.empty() || !parentheses.peek().equals("("))
                {
                    return false;
                }
                parentheses.pop();
            }
            else if (expression.substring(i, i+1).equals("]"))
            {
                if (parentheses.empty() || !parentheses.peek().equals("[")) 
                {
                    return false;
                }
                parentheses.pop();
            }
            else if (expression.substring(i, i+1).equals("}"))
            {
                if (parentheses.empty() || !parentheses.peek().equals("{")) 
                {
                    return false;
                }
                parentheses.pop();
            }
        }
        return parentheses.empty();
    }
    
    /**
     * returns a string in postfix form 
     * if given an expression in infix form as a parameter
     * does this conversion using a Stack
     * 
     * @precondition everything is separated by spaces: i.e. "( 1 + 3 ) * 5"; "(1 + 3) * 5" doesn't work. 
     * @param expr valid expression in infix form
     * @return equivalent expression in postfix form
     */
    public static String infixToPostfix(String expr)
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
            
            System.out.println(strPostfix);
        }
        strPostfix += currNum + " ";
        
        while (!postFix.empty())
        {
            strPostfix += postFix.pop() + " ";
        }
        return strPostfix;
    }
    
    /**
     * returns the value of an expression in postfix form
     * does this computation using a Stack
     * @param expr valid expression in postfix form
     * @return value of the expression
     * @precondition postfix expression  
     *               contains numbers and operators + - * / and %
     *               and that operands and operators are separated by spaces
     */
    public static double evalPostfix(String expr)
    {
        Stack<Integer> postfixOperands = new Stack<Integer>();
                
        String currNum = "";
        
        // Write code here
        for (int i=0; i<expr.length(); i++) 
        {
            String sub = expr.substring(i, i+1);
            
            if (sub.equals(" ") && currNum.length() > 0)
            {
                // put the number into the stack if its not empty
                postfixOperands.push(Integer.valueOf(currNum));
                currNum = "";
            }
            else if (sub.equals("+"))
            {
                int int2 = postfixOperands.pop();
                int int1 = postfixOperands.pop();
                postfixOperands.push(int1+int2);
            }
            else if (sub.equals("-"))
            {
                int int2 = postfixOperands.pop();
                int int1 = postfixOperands.pop();
                postfixOperands.push(int1-int2);
            }
            else if (sub.equals("*"))
            {
                int int2 = postfixOperands.pop();
                int int1 = postfixOperands.pop();
                postfixOperands.push(int1*int2);
            }
            else if (sub.equals("/"))
            {
                int int2 = postfixOperands.pop();
                int int1 = postfixOperands.pop();
                postfixOperands.push(int1/int2);
            }
            else if (sub.equals("%"))
            {
                int int2 = postfixOperands.pop();
                int int1 = postfixOperands.pop();
                postfixOperands.push(int1%int2);
            }
            else if (sub.equals("^"))
            {
                int int2 = postfixOperands.pop();
                int int1 = postfixOperands.pop();
                postfixOperands.push(int1^int2);
            }
            else if (!sub.equals(" "))
            {
                currNum += sub;
            }
        }
        
        return postfixOperands.peek();
    }

    /**
     * Main method to test my implementations
     * 
     * @param args standard arguments for main method
     */
    public static void main(String[] args)
    {
        String exp = "2 + 3 * 4";
        test(exp, 14);

        exp = "8 * 12 / 2";
        test(exp, 48);

        exp = "5 % 2 + 3 * 2 - 4 / 2";
        test(exp, 5);   

        // test balanced expressions
        testBalanced("{ 2 + 3 } * ( 4 + 3 )", true);
        testBalanced("} 4 + 4 { * ( 4 + 3 )", false);
        testBalanced("[ [ [ ] ]", false);
        testBalanced("{ ( } )", false);
        testBalanced("( ( ( ) ) )", true);
    }

    /**
     * Tester to test my implementations
     * 
     * @param expr the expression that is getting evaluated
     * @param expect the expected output
     */
    public static void test(String expr, double expect)
    {
        String post = infixToPostfix(expr);        
        double val = evalPostfix(post);

        System.out.println("Infix: " + expr);
        System.out.println("Postfix: " + post);
        System.out.println("Value: " + val);
        if (val == expect)
        {
            System.out.println("** Success! Great Job **");
        }
        else
        {
            System.out.println("** Oops! Something went wrong. ");
            System.out.println("Check your postfix and eval methods **");
        }
    }

    /**
     * Tests the match parentheses
     * 
     * @param ex the expression
     * @param expected the expected output (true/false)
     */
    public static void testBalanced(String ex, boolean expected)
    {
        boolean act = matchParenthesis(ex);
        if (act == expected)
        {
            System.out.println("** Success!: matchParenthesis(" + ex + ") returned " + act);
        }
        else
        {
            System.out.println("** Oops! Something went wrong check : matchParen(" + ex + ")");
            System.out.println(" returned " + act + " but should have returned " + expected);
        }
    }
}
