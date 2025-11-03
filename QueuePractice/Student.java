
/**
 * Student class for the cafeteria
 *
 * @author Jonny Tang
 * @version 10/29/2025
 */
public class Student
{
    public String name;
    public int studentID;
    public String gradeLevel;

    /**
     * Constructor for objects of class Student
     * 
     * @param n name
     * @param id studentID
     * @param g gradeLevel (freshman to senior)
     */
    public Student(String n, int id, String g)
    {
        name = n;
        studentID = id;
        gradeLevel = g;
    }
    
    /**
     * Getter for name
     * 
     * @return the student's name
     */
    public String name()
    {
        return name;
    }
    
    /**
     * Getter for student ID
     * 
     * @return the student's ID
     */
    public int id()
    {
        return studentID;
    }
    
    /**
     * Getter for grade level
     * 
     * @return the student's grade level
     */
    public String grade()
    {
        return gradeLevel;
    }
}