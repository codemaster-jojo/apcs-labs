import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * Solitaire display. Mostly written by creators of the lab, but 
 * some extra credit I had to implement here.
 * 
 * @author creators of this lab, Jonny Tang
 * @version 10/23/2025
 */
public class SolitaireDisplay extends JComponent implements MouseListener, KeyListener
{
    private static final int CARD_WIDTH = 73;
    private static final int CARD_HEIGHT = 97;
    private static final int SPACING = 5;  //distance between cards
    private static final int FACE_UP_OFFSET = 15;  //distance for cascading face-up cards
    private static final int FACE_DOWN_OFFSET = 15;  //distance for cascading face-down cards
    private static final int WASTE_CARD_OFFSET = 39; 

    private int totalWasteOffset;
    
    private JFrame frame;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private Solitaire game;

    /**
     * Constructor for solitaire display.
     * 
     * @param game the solitaire game reference
     */
    public SolitaireDisplay(Solitaire game)
    {
        this.game = game;

        frame = new JFrame("Solitaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);

        this.setPreferredSize(new Dimension(CARD_WIDTH * 7 + SPACING * 8,
            CARD_HEIGHT * 2 + SPACING * 3 + FACE_DOWN_OFFSET * 7 + 13 * FACE_UP_OFFSET));
        this.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);
        
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /**
     * Paints the card stacks on the screen. 
     * 
     * @param g the graphics
     */
    public void paintComponent(Graphics g)
    {
        //background
        g.setColor(new Color(0, 128, 0));
        g.fillRect(0, 0, getWidth(), getHeight());

        //face down
        drawCard(g, game.getStockCard(), SPACING, SPACING);

        //stock
        Stack<Card> cards = game.getWasteCards();
        int tmpIndex = 0;
        while (!cards.empty() && tmpIndex<3)
        {
            drawCard(g, cards.pop(), SPACING * 2 + 
                CARD_WIDTH + WASTE_CARD_OFFSET * tmpIndex, SPACING);
            tmpIndex++;
        }
        totalWasteOffset = tmpIndex * WASTE_CARD_OFFSET - WASTE_CARD_OFFSET;
        

        if ((selectedRow == 0 && selectedCol == 1) || (selectedRow == 0 && selectedCol == 2))
        {
            drawBorder(g, SPACING * 2 + CARD_WIDTH + totalWasteOffset, SPACING);
        }

        //aces
        for (int i = 0; i < 4; i++)
        {
            drawCard(g, game.getFoundationCard(i), SPACING * (4 + i) +
                CARD_WIDTH * (3 + i), SPACING);
        }

        //piles
        for (int i = 0; i < 7; i++)
        {
            Stack<Card> pile = game.getPile(i);
            int offset = 0;
            for (int j = 0; j < pile.size(); j++)
            {
                drawCard(g, pile.get(j), SPACING + (CARD_WIDTH + SPACING) * i,
                    CARD_HEIGHT + 2 * SPACING + offset);
                if (selectedRow == 1 && selectedCol == i && j == pile.size() - 1)
                {
                    drawBorder(g, SPACING + (CARD_WIDTH + SPACING) * i,
                        CARD_HEIGHT + 2 * SPACING + offset);
                }

                if (pile.get(j).isFaceUp())
                {
                    offset += FACE_UP_OFFSET;
                }
                else
                {
                    offset += FACE_DOWN_OFFSET;
                }
            }
        }
    }

    /**
     * Method to draw one card at given position
     * 
     * @param g the graphics
     * @param card the card that's getting painted
     * @param x the x pos of the card
     * @param y the y pos of the card
     */
    private void drawCard(Graphics g, Card card, int x, int y)
    {
        if (card == null)
        {
            g.setColor(Color.BLACK);
            g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        }
        else
        {
            String fileName = card.getFileName();
            if (!new File(fileName).exists())
            {
                throw new IllegalArgumentException("bad file name:  " + fileName);
            }
            Image image = new ImageIcon(fileName).getImage();
            g.drawImage(image, x, y, CARD_WIDTH, CARD_HEIGHT, null);
        }
    }

    /**
     * Does nothing. This is to avoid abstract method not defined error.
     * 
     * @param e the mouse event
     */
    public void mouseExited(MouseEvent e)
    {
    }

    /**
     * Does nothing. This is to avoid abstract method not defined error.
     * 
     * @param e the mouse event
     */
    public void mouseEntered(MouseEvent e)
    {
    }

    /**
     * Does nothing. This is to avoid abstract method not defined error.
     * 
     * @param e the mouse event
     */
    public void mouseReleased(MouseEvent e)
    {
    }

    /**
     * Does nothing. This is to avoid abstract method not defined error.
     * 
     * @param e the mouse event
     */
    public void mousePressed(MouseEvent e)
    {
    }

    /**
     * Checks if mouse clicked on any card stacks. If so, trigger actions
     * 
     * @param e the mouse event
     */
    public void mouseClicked(MouseEvent e)
    {
        //none selected previously
        int col = e.getX() / (SPACING + CARD_WIDTH);
        int row = e.getY() / (SPACING + CARD_HEIGHT);
        if (row > 1)
        {
            row = 1;
        }
        if (col > 6)
        {
            col = 6;
        }

        if (row == 0 && col == 0)
        {
            game.stockClicked();
        }
        else if ((row == 0 && col == 1) || (row == 0 && col == 2))
        {
            game.wasteClicked();
        }
        else if (row == 0 && col >= 3)
        {
            game.foundationClicked(col - 3);
        }
        else if (row == 1)
        {
            game.pileClicked(col);
        }
        repaint();
    }

    /**
     * Draws border around selected items
     * 
     * @param g the graphics reference
     * @param x the x pos of the border
     * @param y the y value of the cursor
     */
    private void drawBorder(Graphics g, int x, int y)
    {
        g.setColor(Color.YELLOW);
        g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.drawRect(x + 1, y + 1, CARD_WIDTH - 2, CARD_HEIGHT - 2);
        g.drawRect(x + 2, y + 2, CARD_WIDTH - 4, CARD_HEIGHT - 4);
    }

    /**
     * Unselect the cards.
     */
    public void unselect()
    {
        selectedRow = -1;
        selectedCol = -1;
    }

    /**
     * Checks if waste is selected
     * @return whether the waste is selected.
     */
    public boolean isWasteSelected()
    {
        return (selectedRow == 0 && selectedCol == 1) || (selectedRow == 0 && selectedCol == 2);
    }

    /**
     * Selects the waste
     */
    public void selectWaste()
    {
        selectedRow = 0;
        selectedCol = 1;
    }

    /**
     * Selects piles
     * 
     * @return whether a pile is selected
     */
    public boolean isPileSelected()
    {
        return selectedRow == 1;
    }

    /**
     * Checks which pile is selected
     * 
     * @return the pile that's selected
     */
    public int selectedPile()
    {
        if (selectedRow == 1)
        {
            return selectedCol;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Selects a pile at given index
     * 
     * @param index the index of the pile
     */
    public void selectPile(int index)
    {
        selectedRow = 1;
        selectedCol = index;
    }
    
    /**
     * Checks key presses. R -> restarts game, U -> undoes last move
     * 
     * @param e the key that's pressed
     */
    public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_R) 
        {
            game.restart();
            repaint();
        }
        else if (code==KeyEvent.VK_U)
        {
            game.undo();
            repaint();
        }
    }
    
    /**
     * Does nothing.
     * @param e the key event
     */
    public void keyReleased(KeyEvent e) {}
    
    /**
     * Does nothing.
     * 
     * @param e the key event
     */
    public void keyTyped(KeyEvent e) {}
}