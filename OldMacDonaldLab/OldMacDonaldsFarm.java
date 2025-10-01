import java.util.ArrayList;


/**
 * Tester for my animals
 * 
 * @author  Jonny Tang
 *
 * @version 10/1/2025
 */
public class OldMacDonaldsFarm 
{
    String farmerName;
    ArrayList<Animal> farmAnimals;

    /**
     * Constructor for my farm
     */
    public OldMacDonaldsFarm()
    {
        farmerName = "Old MacDonald";
        farmAnimals = new ArrayList<Animal>( );
    }
    
    /**
     * Tester for my animals. Sings the song
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        OldMacDonaldsFarm singer = new OldMacDonaldsFarm( );
        singer.farmAnimals.add(new Chicken( ));
        singer.singVerse( );
        singer.farmAnimals.add(new Chick());
        singer.singVerse( );
        singer.farmAnimals.add(new Rooster( ));
        singer.singVerse( );
        singer.farmAnimals.add(new Pig( ));
        singer.singVerse( );
    }
    
    /**
     * Sings the song and prints it out
     */
    public void singVerse()
    {
        String ei = "E-I-E-I-O";
        
        System.out.print(farmerName + " had a farm, " + ei);
        System.out.println(", and on his farm he had some " + 
            farmAnimals.get(farmAnimals.size()-1).commonName() + "s, " + ei + ".");
        for (Animal a : farmAnimals)
        {
            String word = a.speak() + "-" + a.speak();
            System.out.println("With a " + word + " here, and a " + word+ " there,");
            System.out.println("Here a " + a.speak() + ", there a " + a.speak() + 
                ", everywhere a " + word + ",");
        }
        System.out.println(farmerName + " had a farm, " + ei + ".\n\n");
    }
}