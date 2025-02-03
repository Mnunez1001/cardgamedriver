package edu.guilford;

import java.util.Random;

/**
 * Represents a playing card with a suit and rank.
 * Provides functionality for comparison, value retrieval, and string
 * representation.
 * 
 * @author Miguel A. Nunez Palomares
 * @version 1.0
 * @see java.util.Random
 */

public class Card implements Comparable<Card> {
    /**
     * Enum representing the four suits in a deck of cards.
     */
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    /**
     * Enum representing the ranks of playing cards.
     */
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
        KING
    }

    // instance variables
    // private Suit suit;
    // private Rank rank;

    /**
     * The suit of the card.
     */
    private final Suit suit;

    /**
     * The rank of the card.
     */
    private final Rank rank;

    /**
     * Random number generator for creating random cards.
     */
    private static final Random rand = new Random();

    /**
     * Constructs a Card with the specified suit and rank.
     * 
     * @param suit The suit of the card.
     * @param rank The rank of the card.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // public Card() {
    // // random Card
    // Random rand = new Random();
    // int suit = rand.nextInt(Suit.values().length);
    // int rank = rand.nextInt(Rank.values().length);
    // this.suit = Suit.values()[suit];
    // this.rank = Rank.values()[rank];
    // }

    /**
     * Constructs a Card with a randomly assigned suit and rank.
     */
    public Card() {
        this.suit = Suit.values()[rand.nextInt(Suit.values().length)];
        this.rank = Rank.values()[rand.nextInt(Rank.values().length)];
    }

    /**
     * Gets the suit of the card.
     * 
     * @return The suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the rank of the card.
     * 
     * @return The rank of the card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns a string representation of the card in the format "RANK of SUIT".
     * 
     * @return A string describing the card.
     */

    public String toString() {
        return rank + " of " + suit;
    }

    // @Override
    // public int compareTo(Card otherCard) {

    // if (this.rank.ordinal() > otherCard.rank.ordinal()) {
    // return 1;
    // }
    // else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
    // return -1;
    // }
    // else {
    // if (this.suit.ordinal() > otherCard.suit.ordinal()) {
    // return 1;
    // }
    // else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
    // return -1;
    // }
    // }

    // return 0;
    // }

    /**
     * Compares this card with another card based on rank and suit.
     * 
     * @param otherCard The card to compare to.
     * @return A negative integer if this card is less than the other,
     *         zero if they are equal, and a positive integer if this card is
     *         greater.
     */
    @Override
    public int compareTo(Card otherCard) {
        int rankComparison = Integer.compare(this.rank.ordinal(), otherCard.rank.ordinal());
        return (rankComparison != 0) ? rankComparison : Integer.compare(this.suit.ordinal(), otherCard.suit.ordinal());
    }

    /**
     * Gets the value of the card for use in games like Blackjack.
     * Number cards return their face value, face cards return 10, and Ace returns
     * 11.
     * (Ace handling may be adjusted in other classes.)
     * 
     * @return The numeric value of the card.
     */
    public int getValue() {
        switch (this.rank) {
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case ACE:
                return 11; // The Hand class will adjust for ACE flexibility.
            default:
                return 0;
        }
    }

}