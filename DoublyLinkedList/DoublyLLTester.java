/**
 * Tests out linked list methods that I created.
 * 
 * @author  Jonny Tang
 *
 * @version 9/19/2025
 */
public class DoublyLLTester 
{
    /* DoublyLLTester methods: */

    /**
     * Main method. Traverses a created doubly linked list forward and backward.
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Node dll = new Node(1, new Node(2, new Node(3, null)));
        dll.setPrevs();
        
        System.out.println("Normal traverses: ");
        dll.traverseForward();
        dll.traverseBackward();
        
        System.out.println("\nAdding 500 to the end.");
        dll.addToEnd(500);
        dll.traverseForward();
        dll.traverseBackward();
        
        System.out.println("\nAdding 200 to the start.");

        dll = dll.addToStart(200);
        dll.traverseForward();
        dll.traverseBackward();
        
        System.out.println("\nAdding 131 after 3.");
        dll.addAfterValue(3, 131);
        dll.traverseForward();
        dll.traverseBackward();
        
        System.out.println("\nReversing the LinkedList");
        dll = dll.reverse();
        dll.traverseForward();
        dll.traverseBackward();
        
        System.out.println("\nSorting the LinkedList");
        dll = dll.sort();
        dll.traverseForward();
        dll.traverseBackward();
    }

}