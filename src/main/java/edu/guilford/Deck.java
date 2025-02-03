package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a deck of playing cards. Provides functionality to build, shuffle, 
 * deal, and manage a standard deck of 52 playing cards.
 * 
 * @author Miguel A. Nunez Palomares
 * @version 1.0
 * @see Card, java.util.ArrayList, java.util.Random.
 */

public class Deck {

    /**
     * The list of cards in the deck.
     */
    private ArrayList<Card> deck = new ArrayList<Card>();

    /**
     * Random number generator for shuffling the deck.
     */
    private Random rand = new Random();

     
    /**
     * Constructs a new deck of cards and initializes it with all 52 cards.
     */
    public Deck() {
        build();
    }

     /**
     * Retrieves the current list of cards in the deck.
     * 
     * @return An ArrayList containing the cards in the deck.
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Clears the deck, removing all cards.
     */
    public void clear() {
        deck.clear();
    }


     /**
     * Builds a standard deck of 52 playing cards, adding each suit and rank.
     */
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

     /**
     * Shuffles the deck by randomly rearranging the cards.
     */
    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int loc = rand.nextInt(deck.size());
            tempDeck.add(deck.get(loc));
            deck.remove(loc);
        }
        deck = tempDeck;
    }

    // public Card pick(int i) {
    // Card picked = deck.remove(i);
    // return picked;
    // }

    // public Card deal() {
    // return deck.remove(0);
    // }


    /**
     * Picks and removes a card at the specified index in the deck.
     * 
     * @param i The index of the card to be picked.
     * @return The card removed from the deck.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Card pick(int i) {
        if (i < 0 || i >= deck.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
        return deck.remove(i);
    }


    /**
     * Deals (removes and returns) the top card from the deck.
     * 
     * @return The card dealt from the deck.
     * @throws IllegalStateException If the deck is empty.
     */
    public Card deal() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("Cannot deal from an empty deck.");
        }
        return deck.remove(0);
    }

     /**
     * Retrieves the number of cards remaining in the deck.
     * 
     * @return The number of cards left in the deck.
     */
    public int size() {
        return deck.size();
    }

    // public String toString() {
    // String deckString = "";
    // for (Card card : deck) {
    // deckString += card.toString() + "\n";
    // }
    // return deckString;
    // }

     /**
     * Returns a string representation of the deck, listing all remaining cards.
     * 
     * @return A formatted string representing the deck.
     */
    public String toString() {
        StringBuilder deckString = new StringBuilder();
        for (Card card : deck) {
            deckString.append(card.toString()).append("\n");
        }
        return deckString.toString();

    }

     /**
     * Checks if the deck is empty.
     * 
     * @return True if the deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }

}
