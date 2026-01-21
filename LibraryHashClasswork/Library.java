import java.util.LinkedList;
import java.util.Iterator;

/**
 * Library class, hash table for books
 * using separate chaining
 *
 * @author Jonny Tang
 * @version 1/12/2026
 */
public class Library
{
    // instance variables - replace the example below with your own
    LinkedList[] arr;

    /**
     * Constructor for objects of class Library
     */
    public Library()
    {
        // initialise instance variables
        arr = new LinkedList[10];
        for (int i=0; i<10; i++)
        {
            arr[i] = new LinkedList<Book>();
        }
    }
    
    /**
     * Adds book
     * 
     * @param book the book
     */
    public void addBook(Book book)
    {
        int key = book.hashCode();
        arr[key].add(book);
    }
    
    /**
     * Searches library for book
     * 
     * @param book the book
     * @return if its in the library
     */
    public boolean searchBook(Book book)
    {
        int key = book.hashCode();
        return arr[key].contains(book);
    }
    
    /**
     * Displays the library
     */
    public void display()
    {
        System.out.println();
        for (int i=0; i<10; i++)
        {
            System.out.print(i + ": ");
            
            Iterator<Book> iterator = arr[i].iterator();
            while (iterator.hasNext()) 
            {
                System.out.print(iterator.next() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}