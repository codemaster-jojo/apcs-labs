import java.util.Scanner;

/**
 * Tests out my cafeteria system
 * 
 * @author  Jonny Tang
 *
 * @version  10/29/2025
 */
public class Tester 
{
    /* Tester methods: */

    /**
     * Tester for my cafeteria
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        CafeteriaQueue cafe = new CafeteriaQueue();
        
        boolean running = true;
        
        Scanner scanner = new Scanner(System.in);
        while (running)
        {
            System.out.println("Please provide student name: ");
            String name = scanner.nextLine();
            System.out.println("Please give their student ID: ");
            int id = scanner.nextInt();
            
            System.out.println("Please give grade level: ");
            int grd = scanner.nextInt();
            scanner.nextLine();
            String gradeLevel = "INVALID";
            if (grd == 9)
            {
                gradeLevel = "freshman";
            }
            else if (grd == 10)
            {
                gradeLevel = "sophomore";
            }
            else if (grd == 11)
            {
                gradeLevel = "junior";
            }
            else if (grd == 12)
            {
                gradeLevel = "senior";
            }
            
            if (gradeLevel.equals("INVALID"))
            {
                System.out.println("Grade level not valid.");
            }
            else
            {
                System.out.println("Adding student to queue: ");
                System.out.println("Name: " + name + "\nStudent ID: " + 
                    id + "\nGrade Level: " + gradeLevel);
                    
                cafe.addStudent(name, id, gradeLevel);
                
                System.out.println("\n");
            }
            
            System.out.println("KEEP ADDING STUDENTS TO THE LINE? Y/N");
            String yesno = scanner.nextLine();
            
            if (yesno.equals("N"))
            {
                running = false;
            }
        }
        
        // serve
        
        System.out.println("\nSERVING STUDENTS....");
        

        while (cafe.peekNextStudent() != null)
        {
            System.out.println("SERVE or PEEK or SIZE?");
            String ans = scanner.nextLine();
            if (ans.equals("SERVE"))
            {
                Student stud = cafe.serveStudent();
                System.out.println("Served " + stud.grade() + " " + stud.name() + 
                    " with student ID" + stud.id());
            }
            else if (ans.equals("PEEK"))
            {
                Student stud = cafe.peekNextStudent();
                System.out.println("Next student: A " + stud.grade() + " " + stud.name() + 
                    " with student ID" + stud.id());
            }
            else if (ans.equals("SIZE"))
            {
                System.out.println("Length of the line: " + cafe.getQueueSize());
            }
        }
        
    }

}