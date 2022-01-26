package lt.lyra.pokerhand.type;

public enum CardSuit {
    HEARTS("Hearts"), DIAMONDS("Diamonds"), CLUBS("Clubs"), SPADES("Spades");

    private final String suit;

    CardSuit(String suit) {
        this.suit = suit;
    }

    public String getSuit(){
        return suit;
    }
}
