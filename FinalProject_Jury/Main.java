import java.util.Scanner;


/**
 * Used to simulate a court case
 * 
 * @author Damien and Jonny
 *   With assistance from 
 *
 * @version 12/11/2025
 */
public class Main 
{
    /* Main methods: */

    /**
     * Main method to run the jury simulator
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Scanner sc = new Scanner(System.in);
        
        boolean isRunning = true;
        while (isRunning)
        {
            System.out.println("Enter criminal name: ");
            String name = sc.nextLine();
            
            System.out.println("Enter criminal age: ");
            int age = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Enter criminal gender (male/female): ");
            String gender = sc.nextLine();
            
            System.out.println("Enter place of living: ");
            String placeOfLiving = sc.nextLine();
            
            System.out.println("Enter the crime this person committed: ");
            String crime = sc.nextLine();
            
            Person criminal = new Criminal(age, gender, name, placeOfLiving, crime);
            
            Judge[] judges = new Judge[8];
            String[] names = {"Gary", "Ashley", "Rishik", "Garuette", "Jonny", "Rishika", 
                "Hanwen", "Sunny", "Ameya", "Annabel", "Ethan", "Anna", "Leo", "Sophia",
                "Anchit", "Isabella", "Mason", "Mia", "Aiden", "Mrs. Mittal", 
                "Damien", "Rishikette", "IDP", "Bianca"};
            String[] livings = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", 
                "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose", 
                "Austin", "Jacksonville", "Fort Worth", "Memphis", 
                "Louisville", "Baltimore", "Milwaukee", "Albuquerque", 
                "Tucson", "Fresno", "Sacramento", "Mesa", "Sunnyvale", "Cupertino", 
                "Los Gatos", "Saratoga", "New Delhi", "India", "India", "Africa"}; 
            
            int minAge = 5;
            int ageRange = 100;
            for (int i=0; i<8; i++)
            {
                int judgeAge = (int) (Math.random() * ageRange) + minAge;
                int genderNum = (int) (Math.random() * 2);
                String judgeGender;
                String judgeName;
                
                if (genderNum == 0) // male
                {
                    judgeGender = "male";
                    judgeName = names[(int) (Math.random() * names.length/2) * 2];
                }
                else // male
                {
                    judgeGender = "female";
                    judgeName = names[(int) (Math.random() * names.length/2-1) * 2 + 1];
                }
                
                String judgeLiving = livings[(int) (Math.random() * livings.length)];
                judges[i] = new Judge(judgeAge, judgeGender, judgeName, judgeLiving);
            }
            
            System.out.println("Jury is chosen.");
            Jury jury = new Jury(judges);
            jury.introduce();
            
            System.out.println("Trial will be held shortly.");
            
            System.out.println("—————————————————————————————");
            
            // downcasting
            Criminal crim = (Criminal) criminal;
            crim.selfIntro();
             
            System.out.println("The Jury is coming to a consensus");
            System.out.println("-----------------------------");
            if(jury.isGuilty(crime))
            {
                System.out.println(name + " is guilty!");
                if(crime.toLowerCase().equals("felony"))
                {
                    System.out.println(name + " is sentenced to 10 years in prison");
                }
            }
            else
            {
                System.out.println(name + " is innocent!");
            }
            System.out.println("Do you want to judge another criminal? Y/N");
            System.out.println("------------------------------------------");
            String end = sc.nextLine();
            if(end.toLowerCase().equals("n"))
            {
                isRunning = false;
            }
            
        }
    }

}


