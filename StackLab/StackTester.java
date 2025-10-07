/**
 * Tester for my stack class
 * 
 * @author Jonny Tang
 *
 * @version 10/7/2025
 */
public class StackTester 
{

    /**
     * Tests the methods
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        MyStack stack = new MyStack<Integer>();
        System.out.println("pushed 1, 2, 3, 4, 5 in that order");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        
        stack.pop();
        System.out.println("Popped the top");
        
        System.out.println(stack.peek());
        
        System.out.println("Searched for value 3");
        System.out.println(stack.search(3));
        
        System.out.println("Peek to make sure stack is unchanged");
        System.out.println(stack.peek());
        
        System.out.println("Size and empty");
        System.out.println(stack.size());
        System.out.println(stack.empty());
        
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        
        System.out.println("Removed everything");
        System.out.println(stack.size());
        System.out.println(stack.empty());
    }

}