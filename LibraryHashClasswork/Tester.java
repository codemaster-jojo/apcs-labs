import java.util.Scanner;

/**
 * Tester for library
 * 
 * @author  Jonny Tang
 *
 * @version 1/12/2026
 */
public class Tester 
{
    /* Tester methods: */

    /**
     * Main tester class, tests out Library class.
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        while (true)
        {            
            System.out.println("1. Hash item\n2. Insert book into library\n"
                + "3. Search for book in library\n4. Display library catalog"
                + "\n5. End operations.");
            int response = sc.nextInt();
            sc.nextLine();
            
            if (response == 1)
            {
                System.out.println("Enter book title: ");
                String title = sc.nextLine();
                System.out.println("Enter book ID for " + title + ": ");
                String id = sc.nextLine();
                
                Book book = new Book(title, id);
                System.out.println(book.hashCode());
            }
            else if (response == 2)
            {
                System.out.println("Enter book title: ");
                String title = sc.nextLine();
                System.out.println("Enter book ID for " + title + ": ");
                String id = sc.nextLine();
                
                Book book = new Book(title, id);
                
                lib.addBook(book);
                System.out.println("Book added. ");
            }
            else if (response == 3)
            {
                System.out.println("Enter book title: ");
                String title = sc.nextLine();
                System.out.println("Enter book ID for " + title + ": ");
                String id = sc.nextLine();
                
                Book book = new Book(title, id);
                boolean inLib = lib.searchBook(book);
                if (inLib) 
                {
                    System.out.println("Book is in library.");
                }
                else 
                {
                    System.out.println("Book is not in library.");
                }
            }
            else if (response == 4)
            {
                lib.display();
            }
            else
            {
                return;
            }
            System.out.println("\n——————————————————————\n");
        }
    }

}