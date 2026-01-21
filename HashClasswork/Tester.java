import java.util.Scanner;
/**
 * Tester class for my hash table
 * 
 * @author  Jonny Tang
 *
 * @version 1/10/2026
 */
public class Tester 
{
    /* Tester methods: */

    /**
     * Main tester class, tests out HashTable class.
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Scanner sc = new Scanner(System.in);
        HashTable ht = new HashTable();
        while (true)
        {
            System.out.println("1. Hash item\n2. Insert element\n3. Search for element\n4."
                + " Display hash table\n5. End operations.");
            int response = sc.nextInt();
            sc.nextLine();
            
            if (response == 1)
            {
                System.out.println("Give the key: ");
                String key = sc.nextLine();
                System.out.println(ht.hash(key));
            }
            else if (response == 2)
            {
                System.out.println("Give the key: ");
                String key = sc.nextLine();
                
                ht.insert(key);
            }
            else if (response == 3)
            {
                System.out.println("Give the key: ");
                String key = sc.nextLine();
                
                if (ht.search(key))
                {
                    System.out.println("The key is in the hash table.");
                }
                else
                {
                    System.out.println("The key is not in the hash table.");
                }
            }
            else if (response == 4)
            {
                ht.display();
                System.out.println("\n");
            }
            else
            {
                return;
            }
            System.out.println("——————————————————————\n");
        }
    }

}