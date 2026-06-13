public class Teter
{
    public Teter()
    {
    }

    public static void main(String[] args)
    {
        Encryptor e = new Encryptor();
        
        System.out.println("ENCRPYTED: " + e.encryptWord("COMPUTER"));
        System.out.println("EXPECTED:  OCPMTUFQ");
        
        System.out.println("ENCRPYTED: " + e.encryptWord("SCIENCE"));
        System.out.println("EXPECTED:  UAKCOBE");
    }
}