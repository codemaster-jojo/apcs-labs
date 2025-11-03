
/**
 * Simulates a line at a cafeteria
 *
 * @author Jonny Tang
 * @version 10/29/2025
 */
public class CafeteriaQueue
{
    private ArrayQueue<Student> queue;
    
    /**
     * Constructor for objects of class CafeteriaQueue
     */
    public CafeteriaQueue()
    {
        queue = new ArrayQueue<Student>();
    }
    
    /**
     * Adds student to the queue
     * 
     * @param name the name of the student
     * @param studentID student ID
     * @param gradeLevel the grade level of the student
     */
    public void addStudent(String name, int studentID, String gradeLevel)
    {
        Student stud = new Student(name, studentID, gradeLevel);
        queue.offer(stud);
    }
    
    /**
     * Serves the next student
     * 
     * @return the next student in line 
     * (the one that got removed)
     */
    public Student serveStudent()
    {
        return queue.remove();
    }
    
    /**
     * Checks which student is next
     * 
     * @return the next student in line
     */
    public Student peekNextStudent()
    {
        return queue.peek();
    }
    
    /**
     * Gets the size of the line.
     * 
     * @return the size of the line.
     */
    public int getQueueSize()
    {
        return queue.size();
    }
}