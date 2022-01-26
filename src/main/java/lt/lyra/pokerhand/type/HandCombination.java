package lt.lyra.pokerhand.type;

public enum HandCombination {
    // Straight Flush
    STRAIGHT_FLUSH(9),

    // Four of a Kind
    FOUR_OF_A_KIND(8),

    // Full House
    FULL_HOUSE(7),

    // Flush
    FLUSH(6),

    // Straight
    STRAIGHT(5),

    // Three of a Kind
    THREE_OF_A_KIND(4),

    // Two Pairs
    TWO_PAIRS(3),

    // One Pair
    ONE_PAIR(2),

    // No Pair
    NO_PAIR(1);

    private final int value;

    HandCombination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
