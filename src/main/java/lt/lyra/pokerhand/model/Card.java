package lt.lyra.pokerhand.model;

import lt.lyra.pokerhand.type.CardFace;
import lt.lyra.pokerhand.type.CardSuit;

/**
 * Class Card represents playing card. Has two final fields {@link CardFace} face and {@link CardSuit} suit.
 * Implements Comparable interface for convenient comparing of two cards.
 */
public class Card implements Comparable<Card> {
    private final CardFace face;
    private final CardSuit suit;

    public Card(CardFace face, CardSuit suit) {
        this.face = face;
        this.suit = suit;
    }

    public CardFace getFace() {
        return face;
    }

    public CardSuit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "face=" + face +
                ", suit=" + suit +
                '}';
    }

    @Override
    public int compareTo(Card card) {
        return Integer.compare(face.getValue(), card.getFace().getValue());
    }

}
