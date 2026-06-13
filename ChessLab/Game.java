import java.awt.*;
import java.util.*;

/**
 * Main game class.
 * 
 * @author  Jonny Tang
 *
 * @version  3/20/2026
 */
public class Game 
{
    /**
     * Runs the chess game
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        Board board = new Board();
        
        Piece blackKing = new King(Color.BLACK, "black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));

        Piece blackRook1 = new Rook(Color.BLACK, "black_rook.gif");
        blackRook1.putSelfInGrid(board, new Location(0, 7));
        Piece blackRook2 = new Rook(Color.BLACK, "black_rook.gif");
        blackRook2.putSelfInGrid(board, new Location(0, 0));
        
        Piece blackBishop1 = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishop1.putSelfInGrid(board, new Location(0, 2));
        Piece blackBishop2 = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishop2.putSelfInGrid(board, new Location(0, 5));
        
        Piece blackKnight1 = new Knight(Color.BLACK, "black_knight.gif");
        blackKnight1.putSelfInGrid(board, new Location(0, 1));
        Piece blackKnight2 = new Knight(Color.BLACK, "black_knight.gif");
        blackKnight2.putSelfInGrid(board, new Location(0, 6));
        
        Piece[] blackPawns = new Piece[8];
        for (int i=0; i<8; i++)
        {
            blackPawns[i] = new Pawn(Color.BLACK, "black_pawn.gif");
            blackPawns[i].putSelfInGrid(board, new Location(1, i));
        }
        
        Piece blackQueen = new Queen(Color.BLACK, "black_queen.gif");
        blackQueen.putSelfInGrid(board, new Location(0, 3));
        
        
        Piece whiteKing = new King(Color.WHITE, "white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));
        
        Piece whiteRook1 = new Rook(Color.WHITE, "white_rook.gif");
        whiteRook1.putSelfInGrid(board, new Location(7, 7));
        Piece whiteRook2 = new Rook(Color.WHITE, "white_rook.gif");
        whiteRook2.putSelfInGrid(board, new Location(7, 0));
        
        Piece whiteBishop1 = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishop1.putSelfInGrid(board, new Location(7, 2));
        Piece whiteBishop2 = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishop2.putSelfInGrid(board, new Location(7, 5));
        
        Piece whiteKnight1 = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnight1.putSelfInGrid(board, new Location(7, 1));
        Piece whiteKnight2 = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnight2.putSelfInGrid(board, new Location(7, 6));
        
        Piece[] whitePawns = new Piece[8];
        for (int i=0; i<8; i++)
        {
            whitePawns[i] = new Pawn(Color.WHITE, "white_pawn.gif");
            whitePawns[i].putSelfInGrid(board, new Location(6, i));
        }
        
        Piece whiteQueen = new Queen(Color.WHITE, "white_queen.gif");
        whiteQueen.putSelfInGrid(board, new Location(7, 3));
        
        BoardDisplay display = new BoardDisplay(board);
        
        HumanPlayer w = new HumanPlayer(board, Color.WHITE, "Random", display);
        RandomPlayer b = new RandomPlayer(board, Color.BLACK, "Random");
        
        play(board, display, w, b);
    }
    
    /**
     * Logic for next turn
     * @param board the board
     * @param display the display
     * @param player the player
     */
    private static void nextTurn(Board board, BoardDisplay display, Player player)
    {
        display.setTitle(player.getName());
        
        Move m = player.nextMove();
        board.executeMove(m);
        
        display.clearColors();
        display.setColor(m.getSource(), Color.YELLOW);
        display.setColor(m.getDestination(), Color.YELLOW);
        try {Thread.sleep(500);} catch(InterruptedException e) {}
    }
    
    /**
     * Plays the game
     * @param board the board
     * @param display the display
     * @param white white player
     * @param black black player
     */
    public static void play(Board board, BoardDisplay display, Player white, Player black)
    {
        while (true)
        {
            nextTurn(board, display, white);
            nextTurn(board, display, black);
        }
    }
}