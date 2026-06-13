public class Encryptor
{
    String[][] myMat;

    public Encryptor()
    {
        myMat = new String[][] {
            {"S", "T", "U", "V", "W", "X"},
            {"Y", "Z", "0", "1", "2", "3"},
            {"4", "5", "6", "7", "8", "9"},
            {"A", "B", "C", "D", "E", "F"},
            {"G", "H", "I", "J", "K", "L"},
            {"M", "N", "O", "P", "Q", "R"}
        };
    }

    private Point getCoordinates(String target)
    {
        for (int i=0; i<6; i++)
        {
            for (int j=0; j<6; j++)
            {
                if (myMat[i][j].equals(target))
                {
                    return new Point(i, j);
                }
            }
        }
        
        return null;
    }
    
    private String encryptTwo(String pair)
    {
        Point one = getCoordinates(pair.substring(0,1));
        Point two = getCoordinates(pair.substring(1,2));
        
        if (one.getCol() == two.getCol() || one.getRow() == two.getRow()) {
            return pair.substring(1,2) + pair.substring(0,1);
        }
        
        String retOne = myMat[one.getRow()][two.getCol()];
        String retTwo = myMat[two.getRow()][one.getCol()];
        
        return retOne + retTwo;
    }
    
    public String encryptWord(String x)
    {
        String w = "";
        for (int i=0; i<x.length()-1; i+=2)
        {
            String newPair = encryptTwo(x.substring(i, i+2));
            
            w += newPair;
        }
        if (x.length() % 2 == 1) w += x.substring(x.length()-1);
        
        return w;
    }
}