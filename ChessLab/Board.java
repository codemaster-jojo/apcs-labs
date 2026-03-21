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
}