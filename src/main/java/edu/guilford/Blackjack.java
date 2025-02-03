package edu.guilford;

/**
 * The Blackjack class represents a simple game of Blackjack. It manages the
 * game state,
 * including the player's and dealer's hands and the deck of cards.
 * 
 * @author Miguel A. Nunez Palomares
 * @version 1.0
 */
public class Blackjack {
    /**
     * The player's hand in the game.
     */
    private Hand playerHand;
    /**
     * The dealer's hand in the game.
     */
    private Hand dealerHand;
    /**
     * The deck of cards used in the game.
     */
    private Deck deck;

    /**
     * Constructs a new Blackjack game and initializes the deck and hands.
     */
    public Blackjack() {
        reset(true);
    }

    /**
     * Gets the player's hand.
     * 
     * @return the player's hand.
     */
    public Hand getPlayerHand() {
        return playerHand;
    }

    /**
     * Gets the dealer's hand.
     * 
     * @return the dealer's hand.
     */
    public Hand getDealerHand() {
        return dealerHand;
    }

    /**
     * Gets the deck of cards used in the game.
     * 
     * @return the deck of cards.
     */
    public Deck getDeck() {
        return deck;
    }

    // public void reset(boolean newDeck) {
    // if (newDeck) {
    // deck = new Deck();
    // deck.shuffle();
    // }
    // }

    /**
     * Resets the game by clearing the hands and optionally creating a new shuffled
     * deck.
     * 
     * @param newDeck if true, a new deck is created and shuffled.
     */
    public void reset(boolean newDeck) {
        if (newDeck || deck == null) {
            deck = new Deck();
            deck.shuffle();
        }
        playerHand = new Hand();
        dealerHand = new Hand();
    }

    // public void deal() {
    // playerHand = new Hand();
    // dealerHand = new Hand();
    // playerHand.addCard(deck.deal());
    // dealerHand.addCard(deck.deal());
    // playerHand.addCard(deck.deal());
    // dealerHand.addCard(deck.deal());
    // }

    /**
     * Deals two cards to both the player and dealer from the deck.
     * If the deck has too few cards, it resets with a new shuffled deck.
     */
    public void deal() {
        if (deck == null || deck.size() < 4) { // Ensure enough cards exist
            reset(true);
        }
        playerHand = new Hand();
        dealerHand = new Hand();
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
    }

    // public boolean playerTurn() {
    // while (playerHand.getTotalValue() < 16) {
    // playerHand.addCard(deck.deal());
    // }
    // return playerHand.getTotalValue() <= 21;

    // }

    // public boolean dealerTurn() {
    // while (dealerHand.getTotalValue() < 17) {
    // dealerHand.addCard(deck.deal());
    // }
    // return dealerHand.getTotalValue() <= 21;
    // }

    /**
     * Handles the turn logic for a given hand. The hand will draw cards until it
     * reaches
     * or exceeds the threshold value.
     * 
     * @param hand      the hand to take the turn.
     * @param threshold the value at which the hand should stop drawing.
     * @return true if the hand does not exceed 21, false if it busts.
     */
    private boolean takeTurn(Hand hand, int threshold) {
        while (hand.getTotalValue() < threshold) {
            hand.addCard(deck.deal());
        }
        return hand.getTotalValue() <= 21;
    }

    /**
     * Executes the player's turn. The player will continue drawing cards until they
     * reach a total of 16 or more.
     * 
     * @return true if the player does not bust (total value ≤ 21), false otherwise.
     */
    public boolean playerTurn() {
        return takeTurn(playerHand, 16);
    }

    /**
     * Executes the dealer's turn. The dealer will continue drawing cards until they
     * reach a total of 17 or more.
     * 
     * @return true if the dealer does not bust (total value ≤ 21), false otherwise.
     */
    public boolean dealerTurn() {
        return takeTurn(dealerHand, 17);
    }

    // // Override toString
    // public String toString() {
    // String result = "Player's Hand:\n";
    // for (int i = 0; i < playerHand.size(); i++) {
    // result += playerHand.getCard(i) + "\n";
    // }
    // result += "Player's Total: " + playerHand.getTotalValue() + "\n\n";
    // result += "Dealer's Hand:\n";
    // for (int i = 0; i < dealerHand.size(); i++) {
    // result += dealerHand.getCard(i) + "\n";
    // }
    // result += "Dealer's Total: " + dealerHand.getTotalValue() + "\n\n";
    // return result;
    // }

    /**
     * Returns a string representation of the current game state, including the
     * player's
     * and dealer's hands and their respective total values.
     * 
     * @return a formatted string representing the game state.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Player's Hand:\n");
        for (int i = 0; i < playerHand.size(); i++) {
            result.append(playerHand.getCard(i)).append("\n");
        }
        result.append("Player's Total: ").append(playerHand.getTotalValue()).append("\n\n");
        result.append("Dealer's Hand:\n");
        for (int i = 0; i < dealerHand.size(); i++) {
            result.append(dealerHand.getCard(i)).append("\n");
        }
        result.append("Dealer's Total: ").append(dealerHand.getTotalValue()).append("\n\n");
        return result.toString();
    }

}
