import java.awt.*;
import java.util.*;

/**
 * Represesents a rectangular game board, containing Piece objects.
 * @author Jonny Tang, writers of lab
 * @version 3/20/2026
 */ 
public class Board extends BoundedGrid<Piece>
{
    /**
     * Constructor
     * calls constructor with 8x8 grid
     */
    public Board()
    {
        super(8, 8);
    }

    
    /** 
     * Precondition:  move has already been made on the board
     * Postcondition: piece has moved back to its source,
     * and any captured piece is returned to its location
     * @param move the move undone
     */
    public void undoMove(Move move)
    {
        Piece piece = move.getPiece();
        Location source = move.getSource();
        Location dest = move.getDestination();
        Piece victim = move.getVictim();

        piece.moveTo(source);

        if (victim != null)
            victim.putSelfInGrid(piece.getBoard(), dest);
    }
    
    /**
     * Gets all moves for a given color's pieces
     * @param color the colors
     * @return array list of all moves possible
     */
    public ArrayList<Move> allMoves(Color color)
    {
        // dupe locations
        ArrayList<Move> moves = new ArrayList<Move>();
        
        for (int i=0; i<8; i++)
        {
            for (int j=0; j<8; j++)
            {
                Piece p = get(new Location(i, j));
                
                if (p != null && p.getColor().equals(color))
                {
                    ArrayList<Location> validMoves = p.destinations();
                    for (Location l : validMoves)
                    {
                        moves.add(new Move(p, l));
                    }
                }
            }
        }
        
        return moves;
    }
    
    /**
     * Executes the move
     * @param move the move that's getting executed
     */
    public void executeMove(Move move)
    {
        Piece p = move.getPiece();
        remove(move.getDestination());
        
        p.moveTo(move.getDestination());
    }
}