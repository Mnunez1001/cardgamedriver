package edu.guilford;

import java.util.ArrayList;

/**
 * Represents a player's hand in a card game. This class provides methods
 * for adding and removing cards, calculating the hand's total value,
 * and resetting the hand.
 * 
 * @author Miguel A. Nunez Palomares
 * @version 1.0
 * @see Card, java.util.ArrayList
 */
public class Hand {

    /**
     * The list of cards in the hand.
     */
    private ArrayList<Card> hand;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }

    /**
     * Adds a card to the hand.
     * 
     * @param card The card to be added.
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Removes a card from the hand.
     * 
     * @param card The card to be removed.
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Resets the hand by removing all cards.
     */
    public void reset() {
        hand.clear();
    }

    /**
     * Returns the number of cards in the hand.
     * 
     * @return The size of the hand.
     */
    public int size() {
        return hand.size();
    }

    /**
     * Retrieves a card at a specific index in the hand.
     * 
     * @param index The index of the card.
     * @return The card at the specified index.
     */
    public Card getCard(int index) {
        return hand.get(index);
    }

    // Calculate the value of the hand
    // public int getTotalValue() {
    // int value = 0;
    // int aces = 0;
    // for (Card card : hand) {
    // switch (card.getRank()) {
    // case TWO:
    // value += 2;
    // break;
    // case THREE:
    // value += 3;
    // break;
    // case FOUR:
    // value += 4;
    // break;
    // case FIVE:
    // value += 5;
    // break;
    // case SIX:
    // value += 6;
    // break;
    // case SEVEN:
    // value += 7;
    // break;
    // case EIGHT:
    // value += 8;
    // break;
    // case NINE:
    // value += 9;
    // break;
    // case TEN:
    // case JACK:
    // case QUEEN:
    // case KING:
    // value += 10;
    // break;
    // case ACE:
    // aces++;
    // break;
    // }
    // }
    // for (int i = 0; i < aces; i++) {
    // if (value + 11 <= 21) {
    // value += 11;
    // } else {
    // value += 1;
    // }
    // }
    // return value;
    // }

    /**
     * Calculates the total value of the hand based on card values.
     * Aces are adjusted dynamically to fit the best possible hand value.
     * 
     * @return The total value of the hand.
     */
    public int getTotalValue() {
        int value = 0;
        int aces = 0;
        for (Card card : hand) {
            if (card.getRank() == Card.Rank.ACE) {
                aces++;
            } else {
                value += card.getValue();
            }
        }
        // Adjust for Aces
        for (int i = 0; i < aces; i++) {
            value += (value + 11 <= 21) ? 11 : 1;
        }
        return value;
    }

    // // Override toString method
    // public String toString() {
    // String handString = "";
    // for (Card card : hand) {
    // handString += card.toString() + "\n";
    // }
    // return handString;
    // }

    /**
     * Returns a string representation of the hand.
     * 
     * @return A formatted string listing the cards in the hand.
     */
    public String toString() {
        StringBuilder handString = new StringBuilder();
        for (Card card : hand) {
            handString.append(card.toString()).append("\n");
        }
        return handString.toString();
    }

    // public ArrayList<Card> getHand() {
    // return hand;
    // }

    /**
     * Returns a copy of the current hand.
     * 
     * @return A new ArrayList containing the cards in the hand.
     */
    public ArrayList<Card> getHand() {
        return new ArrayList<>(hand);
    }

}
