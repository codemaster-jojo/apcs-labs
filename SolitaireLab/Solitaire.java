import java.util.*;

/**
 * Solitaire game
 * @author writers of this lab, Jonny Tang
 * @version 10/16/2025
 */
public class Solitaire
{
    /**
     * Main method
     * @param args standard main method args
     */
    public static void main(String[] args)
    {
        new Solitaire();
    }

    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations;
    private Stack<Card>[] piles;
    private SolitaireDisplay display;

    /**
     * Constructor for solitaire
     */
    public Solitaire()
    {
        foundations = new Stack[4];
        piles = new Stack[7];
        
        for (int i=0; i<4; i++)
        {
            foundations[i] = new Stack<Card>();
        }
        
        for (int i=0; i<7; i++)
        {
            piles[i] = new Stack<Card>();
        }
        
        stock = new Stack<Card>();
        waste = new Stack<Card>();

        createStock();
        deal();

        display = new SolitaireDisplay(this);
    }

    /**
     * returns the card on top of the stock,
     * or null if the stock is empty
     * 
     * @return the top of the stock stack
     */
    public Card getStockCard()
    {
        if (stock.empty()) 
        {
            return null;
        }
        return stock.peek();
    }

    /**
     * returns the card on top of the waste,
     * or null if the waste is empty
     * 
     * @return the top of the waste stack
     */
    public Card getWasteCard()
    {
        if (waste.empty()) 
        {
            return null;
        }
        return waste.peek();
    }
    
    /**
     * Returns the top 3 cards of the waste in a stack
     * 
     * @return the top 3 cards
     */
    public Stack<Card> getWasteCards()
    {   
        Stack<Card> newCards = new Stack<Card>();
        Stack<Card> tmp = new Stack<Card>();
        
        if (waste.empty()) 
        {
            return newCards;
        }
        
        int indexTmp = 0;
        while (!waste.empty() && indexTmp < 3)
        {
            Card c = waste.pop();
            newCards.push(c);
            tmp.push(c);
            indexTmp++;
        }
        for (int i=0; i<indexTmp; i++)
        {
            Card c = tmp.pop();
            waste.push(c);
        }
        
        return newCards;
    }

    /**
     * Gets the top of a foundation stack
     * @precondition  0 <= index < 4
     * @postcondition returns the card on top of the given
     *                foundation, or null if the foundation
     *                is empty
     * @param index the index of the foundation pile (1-4)
     * @return the top of the stack of the right pile
     */
    public Card getFoundationCard(int index)
    {
        if (foundations[index].empty()) 
        {
            return null;
        }
        return foundations[index].peek();
    }

    /**
     * Gets the stack for the right pile
     * @precondition  0 <= index < 7
     * @postcondition returns a reference to the given pile
     * 
     * @param index the index of the pile
     * @return the stack of the pile of given index
     */
    public Stack<Card> getPile(int index)
    {
        return piles[index];
    }

    /**
     * Called when the stock is clicked
     */
    public void stockClicked()
    {
        if (display.isWasteSelected() || display.isPileSelected())
        {
            return;
        }
        if (stock.empty())
        {
            resetStock();
        }
        else
        {
            dealThreeCards();
        }
    }

    /**
     * Called when the waste is clicked.
     */
    public void wasteClicked()
    {
        if (waste.empty() || display.isWasteSelected())
        {
            display.unselect();
        }
        else
        {
            display.selectWaste();
        }
    }

    /**
     * called when given foundation is clicked
     * @precondition  0 <= index < 4
     * 
     * @param index the index of the foundation
     */
    public void foundationClicked(int index)
    {   
        if (display.isPileSelected())
        {
            if (canAddToFoundation(piles[display.selectedPile()].peek(), index))
            {
                foundations[index].push(piles[display.selectedPile()].pop());
                display.unselect();
            }            
        }
        
        else if (display.isWasteSelected())
        {
            if (canAddToFoundation(waste.peek(), index))
            {
                foundations[index].push(waste.pop());
                display.unselect();
            }   
        }
    }

    /**
     * called when given pile is clicked
     * @precondition  0 <= index < 7
     * 
     * @param index the index of the pile
     */
    public void pileClicked(int index)
    {
        if (piles[index].empty() && display.isPileSelected())
        {
            // make king addable
            if (piles[display.selectedPile()].peek().getRank()==13)
            {
                addToPile(removeFaceUpCards(display.selectedPile()), index);
                display.unselect();
            }
        }
        else if (!piles[index].peek().isFaceUp()) // if face down, flip to face up
        {
            piles[index].peek().turnUp();
        }
        
        else if (display.isWasteSelected() && canAddToPile(waste.peek(), index))
        {
            piles[index].push(waste.pop());
            display.unselect();
        }
        else if (display.isPileSelected() && display.selectedPile()==index)
        {
            display.unselect();
        }
        else if (display.isPileSelected() && display.selectedPile()!=index)
        {
            Stack<Card> cards = removeFaceUpCards(display.selectedPile());
            if (canAddToPile(cards.peek(), index))
            {
                addToPile(cards, index);
                display.unselect();
            }
            else 
            {
                // add it back to the original pile
                addToPile(cards, display.selectedPile()); 
            }
            
        }
        else if (!display.isWasteSelected())
        {
            display.selectPile(index);
        }
    }
    
    /**
     * Creates the stock and randomizes it.
     */
    private void createStock()
    {
        ArrayList<Card> orderedStock = new ArrayList<Card>();
        
        
        for (int i=1; i<=13; i++)
        {
            orderedStock.add(new Card(i, "h"));
            orderedStock.add(new Card(i, "d"));
            orderedStock.add(new Card(i, "c"));
            orderedStock.add(new Card(i, "s"));
        }
        
        while (!orderedStock.isEmpty())
        {
            int indexRemoved = (int) (Math.random() * orderedStock.size());  
            
            stock.push(orderedStock.get(indexRemoved));
            orderedStock.remove(indexRemoved);
        }
    }
    
    /**
     * Deals the cards to the piles in 1, 2, 3... to 7. Turns up the last card of each pile
     */
    private void deal()
    {
        for (int i=0; i<7; i++)
        {
            for (int j=0; j<i+1; j++)
            {
                Card c = stock.pop();
                piles[i].push(c);
            }
            piles[i].peek().turnUp();
        }
    }
    
    /**
     * Deals 3 cards from stock to watse
     */
    private void dealThreeCards()
    {
        for (int i=0; i<3; i++)
        {
            if (stock.empty()) 
            {
                return;
            }
            Card c = stock.pop();
            c.turnUp();
            waste.push(c);
        }
    }
    
    /**
     * Resets the stock (moves all cards from waste -> stock)
     */
    private void resetStock()
    {
        while (!waste.empty())
        {
            Card c = waste.pop();
            c.turnDown();
            stock.push(c);
        }
    }
    /**
     * legally moved to the top of the given pile
     * @precondition  0 <= index < 7
     * @postcondition  Returns true if the given card can be
     * 
     * @param card  the card getting added to the pile
     * @param index the index of the pile
     * @return boolean representing whether i can add to pile.
     */

    private boolean canAddToPile(Card card, int index)
    {
        if (piles[index].empty() && card.getRank()==13)
        {
            return true;
        }
        else if (piles[index].empty())
        {
            return false;
        }
        
        Card top = piles[index].peek();
        if (card.getRank() == top.getRank()-1)
        {
            if (((card.getSuit().equals("h") || card.getSuit().equals("d")) && 
                (top.getSuit().equals("c") || top.getSuit().equals("s"))) ||
                ((card.getSuit().equals("c") || card.getSuit().equals("s")) && 
                (top.getSuit().equals("h") || top.getSuit().equals("d"))))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Removes all face up cards from one pile
     * 
     * @precondition 0<=index<7
     * @param index  the index of the pile
     * @return stack representing the cards that got removed.
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card> returnStack = new Stack<Card>();
        while (!piles[index].empty() && piles[index].peek().isFaceUp())
        {
            returnStack.push(piles[index].pop());
        }
        
        return returnStack;
    }
    
    /**
     * Adds given stack to given pile
     * @precondition 0 <= index < 7
     * @postcondition Removes elements from cards, and adds
     * them to the given pile.
     * 
     * @param cards the stack of cards getting added to the stack
     * @param index the index of the pile the cards are getting added to
     */
    private void addToPile(Stack<Card> cards, int index)
    {
        while (!cards.empty())
        {
            piles[index].push(cards.pop());
        }
    }
    
    /**
     * Checks if you can add a given card to the foundation.
     * @precondition 0 <= index < 4
     * @postcondition Returns true if the given card can be
     * legally moved to the top of the given
     * foundation
     * 
     * @param card the card getting added
     * @param index the index of the foundation
     * 
     * @return whether the card can be added to given foundation.
     */
    private boolean canAddToFoundation(Card card, int index)
    {
        if (foundations[index].empty() && card.getRank() == 1)
        {
            return true;
        }
        else if (foundations[index].empty())
        {
            return false;
        }
        return (foundations[index].peek().getRank() + 1 == card.getRank()) && (foundations[index].peek().getSuit() == card.getSuit());
    }
}