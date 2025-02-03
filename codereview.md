# Code Review for Card Game

- I did all the modefications mention here to the original code.

## Card Class

### Possible Improvements:

1. **Remove Unnecessary Comment:**  
   - The comment `// TODO Auto-generated method stub` in `compareTo` should be removed.

2. **Simplify compareTo Method:**  
   - The `compareTo` method can be simplified as follows:
     ```java
     @Override
     public int compareTo(Card otherCard) {
         int rankComparison = Integer.compare(this.rank.ordinal(), otherCard.rank.ordinal());
         return (rankComparison != 0) ? rankComparison : Integer.compare(this.suit.ordinal(), otherCard.suit.ordinal());
     }
     ```
   - This removes redundant `if-else` statements, making the logic clearer.

3. **Make Card Immutable:**  
   - Since a Card should not change its rank or suit after creation, declare variables as `final`:
     ```java
     private final Suit suit;
     private final Rank rank;
     ```
   - This enforces immutability, preventing accidental modifications.

4. **Optimize Random Object Usage:**  
   - Instead of creating a new `Random` object each time, use a static instance:
     ```java
     private static final Random rand = new Random();
     ```
   - Constructor:
     ```java
     public Card() {
         this.suit = Suit.values()[rand.nextInt(Suit.values().length)];
         this.rank = Rank.values()[rand.nextInt(Rank.values().length)];
     }
     ```
   - This improves performance by avoiding unnecessary object creation.

## Deck Class

### Possible Improvements:

1. **Localize Random Instance:**  
   - The `rand` instance is only used in `shuffle()`. Declare it inside that method instead of as a class attribute.

2. **Optimize toString Method:**  
   - String concatenation using `+=` is inefficient. Use `StringBuilder`:
     ```java
     public String toString() {
         StringBuilder deckString = new StringBuilder();
         for (Card card : deck) {
             deckString.append(card.toString()).append("\n");
         }
         return deckString.toString();
     }
     ```

3. **Exception Handling for `pick(int i)` and `deal()`:**  
   - The `pick(int i)` and `deal()` methods assume the deck is non-empty, leading to exceptions.
   - Solution:
     ```java
     public Card pick(int i) {
         if (i < 0 || i >= deck.size()) {
             throw new IndexOutOfBoundsException("Invalid index: " + i);
         }
         return deck.remove(i);
     }

     public Card deal() {
         if (deck.isEmpty()) {
             throw new IllegalStateException("Cannot deal from an empty deck.");
         }
         return deck.remove(0);
     }
     ```

## Hand Class

### Possible Improvements:

1. **Refactor Value Calculation:**  
   - Move card value logic to the `Card` class:
     ```java
     public int getValue() {
         switch (this.rank) {
             case TWO: return 2;
             case THREE: return 3;
             case FOUR: return 4;
             case FIVE: return 5;
             case SIX: return 6;
             case SEVEN: return 7;
             case EIGHT: return 8;
             case NINE: return 9;
             case TEN: case JACK: case QUEEN: case KING: return 10;
             case ACE: return 11;
             default: return 0;
         }
     }
     ```
   - Update `getTotalValue()`:
     ```java
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
         for (int i = 0; i < aces; i++) {
             value += (value + 11 <= 21) ? 11 : 1;
         }
         return value;
     }
     ```

2. **Return a Copy in `getHand()`:**  
    - The getHand() method returns the raw ArrayList<Card>, which exposes internal representation and can lead to unwanted modifications.
   ```java
   public ArrayList<Card> getHand() {
       return new ArrayList<>(hand);
   }
   ```

3. **Optimize `toString()`:**  
   ```java
   public String toString() {
       StringBuilder handString = new StringBuilder();
       for (Card card : hand) {
           handString.append(card.toString()).append("\n");
       }
       return handString.toString();
   }
   ```

## Blackjack Class
### Possible Improvements:

1. **In `deal()` Method there could be a `NullPointerException`**
    - The `deal()` method initializes playerHand and dealerHand but does not initialize deck if `reset(false)` was previously called.
    
    -If `reset(false)` is used, the deck remains uninitialized, causing a `NullPointerException` when `deck.deal()` is called.
    
    ```java
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
    
    ````

 - Modifing the `deal()` method to check if deck is null and initialize it if necessary can help this this problem. 

2. **`reset()` Method Only Resets the Deck**

    -`reset(true)` creates a new deck but does not reset player or dealer hands.

    ```java
    public void reset(boolean newDeck) {
    if (newDeck || deck == null) {
        deck = new Deck();
        deck.shuffle();
    }
    playerHand = new Hand();
    dealerHand = new Hand();
    }
    ````
- Reset player and dealer hands inside `reset()`.

3. **The methods `playerTurn()` and `dealerTurn()` Could Be Simplified**

    -Both methods use similar while loops.

    -Extract logic into a helper method to reduce redundancy (DRY principle).

    ```java
    private boolean takeTurn(Hand hand, int threshold) {
    while (hand.getTotalValue() < threshold) {
        hand.addCard(safeDeal());
    }
    return hand.getTotalValue() <= 21;
    }
    ````
   ```java
    public boolean playerTurn() {
    return takeTurn(playerHand, 16);
    }
    ````
    ```java
    public boolean dealerTurn() {
    return takeTurn(dealerHand, 17);
    }
    ````
4. **Enhance `toString()` with StringBuilder**
    -String concatenation (+=) inside loops is inefficient.
    
    -Using StringBuilder allows for better performance.

    ```java
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
    ````



## LamarckianPoker Class

### Possible Improvements:

1. **Remove Unnecessary Print Statements:**  
   - There are multiple commented-out `System.out.println()` statements that should be removed.

2. **Replace Magic Numbers with Constants:**  
   ```java
   private static final int HAND_INITIAL_SIZE = 4;
   private static final int POOL_SIZE = 4;
   private static final int HAND_MAX_SIZE = 7;
   ```

3. **Rrand is an instance variable but could be   made static since it doesn’t need to be tied to an instance.**
    ```java
    private static final Random rand = new Random();
    ```

4. **Refactor `turn()` Method:**  
   - Extract into helper methods:
     ```java
     private void determineWinner() { ... }
     private void transferPoolCards() { ... }
     private void handleDiscardAndDeckReload() { ... }
     ```
     -Extract a Winner Determination Method
    ```java
    private WinnerInfo determineWinner(Card p1, Card p2) {
    if (p1.getRank().ordinal() > p2.getRank().ordinal()) {
        return new WinnerInfo(player1Hand, player2Hand, p1, p2);
    } else if (p1.getRank().ordinal() < p2.getRank().ordinal()) {
        return new WinnerInfo(player2Hand, player1Hand, p2, p1);
    } else {
        return (p1.getSuit().ordinal() > p2.getSuit().ordinal()) ? 
            new WinnerInfo(player1Hand, player2Hand, p1, p2) :
            new WinnerInfo(player2Hand, player1Hand, p2, p1);
    }
    }
    ```

    -Move Pool Transfers Into a Method
    ```java
    private void transferPoolCards(Hand winner, Card winningCard) {
    ArrayList<Card> toRemove = new ArrayList<>();
    for (Card poolCard : pool.getHand()) {
        if (winningCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
            winningCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
            winner.addCard(poolCard);
            toRemove.add(poolCard);
        }
    }
    for (Card card : toRemove) {
        pool.removeCard(card);
    }
    }
    ```

    -Separate Discard Handling
    ```java
    private void handleDiscardAndDeckReload() {
    for (Card poolCard : pool.getHand()) {
        discard.getDeck().add(poolCard);
    }
    pool.getHand().clear();
    if (deck.size() < POOL_SIZE) {
        deck.getDeck().addAll(discard.getDeck());
        discard.clear();
    }
    }
    ```


5. **Ensure Hands Have Cards:**  
   ```java
   private void ensureHandsHaveCards() {
       if (player1Hand.size() == 0) {
           if (!deck.isEmpty()) player1Hand.addCard(deck.deal());
           else if (!discard.isEmpty()) reloadDeckFromDiscard();
       }
       if (player2Hand.size() == 0) {
           if (!deck.isEmpty()) player2Hand.addCard(deck.deal());
           else if (!discard.isEmpty()) reloadDeckFromDiscard();
       }
   }
   ```

5.5 **Variables in `turn()` sometimes change purpose, which makes it harder to read:**

- `firstHand`, `secondHand`, `firstCard`, `secondCard` are reassigned based on the outcome. This could be replaced with an object like `WinnerInfo { Hand winner, Hand loser, Card winnerCard, Card loserCard }`.

```java
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
```


## Bug Fixes

### Issue:

- **Exception in `determineWinner()` due to empty player hand.**
- **Error: `IllegalArgumentException: bound must be positive`**

### Solution:

- Add an `isEmpty()` method to `Deck`:
  ```java
  public boolean isEmpty() {
      return deck.isEmpty();
  }
  ```

- Modify `reset()`, `deal()`, `makePoll()`, `turn`, `ensureHandsHaveCards`, `determineWinner`, `handleDiscardAndDeckReload`, `reloadDeckFromDiscard`  to ensure each players hands have cards (some of this methods are new so you have to add them):
  ```java
  public void reset(boolean newDeck) {
      if (newDeck) {
          deck = new Deck();
          discard = new Deck();
          discard.clear();
          deck.shuffle();
      }
      turnNumber = 0;
      deal();
  }
  ```
  ```java
  public void deal() {
      player1Hand = new Hand();
      player2Hand = new Hand();
      for (int i = 0; i < HAND_INITIAL_SIZE; i++) {
          if (!deck.isEmpty()) player1Hand.addCard(deck.deal());
          if (!deck.isEmpty()) player2Hand.addCard(deck.deal());
      }
  }
  ```
  ```java
  public void makePool() {
        pool = new Hand();
        for (int i = 0; i < POOL_SIZE; i++) {
            if (!deck.isEmpty()) pool.addCard(deck.deal());
        }
    }

     public boolean turn() {
        if (player1Hand.size() < HAND_MAX_SIZE || player2Hand.size() < HAND_MAX_SIZE) {
            makePool();
            ensureHandsHaveCards(); //Ensures hands are not empty before determining winner
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
    ```

    ```java 
    New method

     private void ensureHandsHaveCards() {
        if (player1Hand.size() == 0) {
            if (!deck.isEmpty()) player1Hand.addCard(deck.deal());
            else if (!discard.isEmpty()) reloadDeckFromDiscard();
        }
        if (player2Hand.size() == 0) {
            if (!deck.isEmpty()) player2Hand.addCard(deck.deal());
            else if (!discard.isEmpty()) reloadDeckFromDiscard();
        }
    }
    ```

    ```java
     private WinnerInfo determineWinner() {
        ensureHandsHaveCards(); //Ensures hands are not empty before determining winner

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
    ```

    ```java
    private void handleDiscardAndDeckReload() {
        discard.getDeck().addAll(pool.getHand());
        pool.getHand().clear();

        if (deck.size() < POOL_SIZE) {
            reloadDeckFromDiscard();
        }
    }
    ```

    ```java
    private void reloadDeckFromDiscard() {
        deck.getDeck().addAll(discard.getDeck());
        discard.clear();
        deck.shuffle();
    }
    ```







These changes improve code robustness and prevent runtime errors.
