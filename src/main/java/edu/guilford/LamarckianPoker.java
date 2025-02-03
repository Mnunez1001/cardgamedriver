package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a game of Lamarckian Poker, managing player hands, a pool of
 * cards,
 * a deck, and a discard pile. Implements gameplay mechanics including dealing,
 * determining round winners, and managing the deck and discard pile.
 * 
 * @author Alex
 * @version 1.0
 * @see Hand, Deck, Card, java.util.Random, java.util.ArrayList
 */

public class LamarckianPoker {
    /**
     * The initial size of each player's hand.
     */
    private static final int HAND_INITIAL_SIZE = 4;
    /**
     * The number of cards in the pool.
     */
    private static final int POOL_SIZE = 4;
    /**
     * The maximum size of each player's hand.
     */
    private static final int HAND_MAX_SIZE = 7;

    /**
     * The player's hand in the game.
     */
    private Hand player1Hand;
    /**
     * The opponent's hand in the game.
     */
    private Hand player2Hand;
    /**
     * The pool of cards in the game.
     */
    private Hand pool;
    /**
     * The discard pile of cards.
     */
    private Deck discard;
    /**
     * The deck of cards used in the game.
     */
    private Deck deck;

    // private Random rand = new Random();
    /**
     * Random number generator for creating random cards.
     */
    private static final Random rand = new Random();
    // private int iTurn;
    /**
     * The current turn number.
     */
    private int turnNumber;

    /**
     * Constructs a new game of Lamarckian Poker and initializes the deck.
     */
    public LamarckianPoker() {
        reset(true);
    }

    /**
     * Gets Player 1's hand.
     * 
     * @return Player 1's hand
     */
    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    /**
     * Gets Player 2's hand.
     * 
     * @return Player 2's hand
     */
    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    /**
     * Gets the current pool of cards.
     * 
     * @return The pool of cards
     */
    public Hand getPool() {
        return pool;
    }

    /**
     * Resets the game state, optionally creating a new deck.
     * 
     * @param newDeck Whether to create a new shuffled deck
     */
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck();
            discard = new Deck();
            discard.clear();
            deck.shuffle();
        }
        // iTurn = 0;
        turnNumber = 0;
        deal(); // Ensures players get cards at reset
    }

    // public void deal() {
    // player1Hand = new Hand();
    // player2Hand = new Hand();
    // for (int iCard = 0; iCard < 4; iCard++) {
    // player1Hand.addCard(deck.deal());
    // player2Hand.addCard(deck.deal());
    // }
    // }

    /**
     * Deals initial hands to players.
     */
    public void deal() {
        player1Hand = new Hand();
        player2Hand = new Hand();
        for (int i = 0; i < HAND_INITIAL_SIZE; i++) {
            if (!deck.isEmpty())
                player1Hand.addCard(deck.deal());
            if (!deck.isEmpty())
                player2Hand.addCard(deck.deal());
        }
    }

    // public void makePool() {
    // pool = new Hand();
    // for (int iCard = 0; iCard < 4; iCard++) {
    // pool.addCard(deck.deal());
    // }
    // // System.out.println("Deck size: " + deck.size());
    // }

    /**
     * Creates a pool of cards from the deck.
     */
    public void makePool() {
        pool = new Hand();
        for (int i = 0; i < POOL_SIZE; i++) {
            if (!deck.isEmpty())
                pool.addCard(deck.deal());
        }
    }

    // public boolean turn() {
    // if (player1Hand.size() < 7 || player2Hand.size() < 7) {
    // makePool();
    // // System.out.println("Turn " + iTurn + "\n" + pool);
    // Card player1Card = player1Hand.getCard(rand.nextInt(player1Hand.size()));
    // Card player2Card = player2Hand.getCard(rand.nextInt(player2Hand.size()));
    // Hand firstHand, secondHand;
    // Card firstCard, secondCard;
    // if (player1Card.getRank().ordinal() > player2Card.getRank().ordinal()) {
    // firstHand = player1Hand;
    // secondHand = player2Hand;
    // firstCard = player1Card;
    // secondCard = player2Card;
    // } else if (player1Card.getRank().ordinal() < player2Card.getRank().ordinal())
    // {
    // firstHand = player2Hand;
    // secondHand = player1Hand;
    // firstCard = player2Card;
    // secondCard = player1Card;
    // } else {
    // if (player1Card.getSuit().ordinal() > player2Card.getSuit().ordinal()) {
    // firstHand = player1Hand;
    // secondHand = player2Hand;
    // firstCard = player1Card;
    // secondCard = player2Card;
    // } else {
    // firstHand = player2Hand;
    // secondHand = player1Hand;
    // firstCard = player2Card;
    // secondCard = player1Card;
    // }

    // }

    // ArrayList<Card> poolRemove = new ArrayList<Card>();

    // for (Card poolCard : pool.getHand()) {
    // if (firstCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
    // firstCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
    // firstHand.addCard(poolCard);
    // poolRemove.add(poolCard);
    // }
    // }
    // // Remove cards from pool
    // for (Card poolCard : poolRemove) {
    // pool.removeCard(poolCard);
    // }
    // poolRemove.clear();
    // pool.addCard(firstCard);
    // firstHand.removeCard(firstCard);
    // for (Card poolCard : pool.getHand()) {
    // if (secondCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
    // secondCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
    // secondHand.addCard(poolCard);
    // poolRemove.add(poolCard);
    // }
    // }
    // for (Card poolCard : poolRemove) {
    // pool.removeCard(poolCard);
    // }
    // pool.addCard(secondCard);
    // secondHand.removeCard(secondCard);
    // for (Card poolCard : pool.getHand()) {
    // discard.getDeck().add(poolCard);
    // }
    // pool.getHand().clear();
    // // System.out.println("Discard\n" + discard.size());
    // if (deck.size() < 4) {
    // for (Card card : discard.getDeck()) {
    // deck.getDeck().add(card);
    // }
    // discard.clear();
    // // System.out.println("Discard\n" + discard.size());
    // }
    // iTurn++;

    // return true;
    // } else {
    // return false;
    // }

    // }

    /**
     * Executes a turn in the game, determining a winner and redistributing cards.
     * 
     * @return True if the turn was successfully played, false if the game should
     *         end
     */
    public boolean turn() {
        if (player1Hand.size() < HAND_MAX_SIZE || player2Hand.size() < HAND_MAX_SIZE) {
            makePool();
            ensureHandsHaveCards(); // Ensures hands are not empty before determining winner
            WinnerInfo winnerInfo = determineWinner();
            transferPoolCards(winnerInfo.winner, winnerInfo.winnerCard);
            pool.addCard(winnerInfo.winnerCard);
            winnerInfo.winner.removeCard(winnerInfo.winnerCard);
            transferPoolCards(winnerInfo.loser, winnerInfo.loserCard);
            pool.addCard(winnerInfo.loserCard);
            winnerInfo.loser.removeCard(winnerInfo.loserCard);
            handleDiscardAndDeckReload();
            turnNumber++;
            return true;
        }
        return false;
    }

    /**
     * Ensures that each player has at least one card, drawing from the deck or
     * discard pile if needed.
     */
    private void ensureHandsHaveCards() {
        if (player1Hand.size() == 0) {
            if (!deck.isEmpty())
                player1Hand.addCard(deck.deal());
            else if (!discard.isEmpty())
                reloadDeckFromDiscard();
        }
        if (player2Hand.size() == 0) {
            if (!deck.isEmpty())
                player2Hand.addCard(deck.deal());
            else if (!discard.isEmpty())
                reloadDeckFromDiscard();
        }
    }

    /**
     * Determines the winner of a round based on the highest ranked card.
     * 
     * @return A WinnerInfo object containing the winner and loser hands and cards
     */
    private WinnerInfo determineWinner() {
        ensureHandsHaveCards(); // Ensures hands are not empty before determining winner

        Card player1Card = player1Hand.getCard(rand.nextInt(player1Hand.size()));
        Card player2Card = player2Hand.getCard(rand.nextInt(player2Hand.size()));

        if (player1Card.getRank().ordinal() > player2Card.getRank().ordinal()) {
            return new WinnerInfo(player1Hand, player2Hand, player1Card, player2Card);
        } else if (player1Card.getRank().ordinal() < player2Card.getRank().ordinal()) {
            return new WinnerInfo(player2Hand, player1Hand, player2Card, player1Card);
        } else {
            return (player1Card.getSuit().ordinal() > player2Card.getSuit().ordinal())
                    ? new WinnerInfo(player1Hand, player2Hand, player1Card, player2Card)
                    : new WinnerInfo(player2Hand, player1Hand, player2Card, player1Card);
        }
    }

    /**
     * Transfers matching pool cards to the winner's hand.
     * 
     * @param recipient    The winning hand
     * @param matchingCard The card used to determine matching cards
     */
    private void transferPoolCards(Hand recipient, Card matchingCard) {
        ArrayList<Card> toRemove = new ArrayList<>();
        for (Card poolCard : pool.getHand()) {
            if (poolCard.getRank().ordinal() == matchingCard.getRank().ordinal() ||
                    poolCard.getSuit().ordinal() == matchingCard.getSuit().ordinal()) {
                recipient.addCard(poolCard);
                toRemove.add(poolCard);
            }
        }
        pool.getHand().removeAll(toRemove);
    }

    /**
     * Handles transferring pool cards to the discard pile and reloading the deck if
     * necessary.
     */
    private void handleDiscardAndDeckReload() {
        discard.getDeck().addAll(pool.getHand());
        pool.getHand().clear();

        if (deck.size() < POOL_SIZE) {
            reloadDeckFromDiscard();
        }
    }

    /**
     * Reloads the deck from the discard pile and shuffles it.
     */
    private void reloadDeckFromDiscard() {
        deck.getDeck().addAll(discard.getDeck());
        discard.clear();
        deck.shuffle();
    }

    /**
     * Returns a string representation of the game state, including player hands and
     * the pool.
     */
    @Override
    public String toString() {
        return "\nPlayer 1: \n" + player1Hand + "\nPlayer 2: \n" + player2Hand + "\nPool: " + pool + "\n";
    }

    /**
     * Represents the winner and loser of a round, along with the winning and losing
     * cards.
     */
    private static class WinnerInfo {
        Hand winner;
        Hand loser;
        Card winnerCard;
        Card loserCard;

        WinnerInfo(Hand winner, Hand loser, Card winnerCard, Card loserCard) {
            this.winner = winner;
            this.loser = loser;
            this.winnerCard = winnerCard;
            this.loserCard = loserCard;
        }
    }

}
