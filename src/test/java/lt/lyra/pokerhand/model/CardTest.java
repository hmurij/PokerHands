package lt.lyra.pokerhand.model;

import lt.lyra.pokerhand.type.CardFace;
import lt.lyra.pokerhand.type.CardSuit;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void compareToTestLess() {
        Card card2H = new Card(CardFace.DEUCE, CardSuit.HEARTS);
        Card card4D = new Card(CardFace.FOUR, CardSuit.DIAMONDS);
        assertEquals(card2H.compareTo(card4D), -1);
    }

    @Test
    public void compareToTestEqual() {
        Card cardQS1 = new Card(CardFace.QUEEN, CardSuit.SPADES);
        Card cardQS2 = new Card(CardFace.QUEEN, CardSuit.SPADES);
        assertEquals(cardQS1.compareTo(cardQS2), 0);
    }

    @Test
    public void compareToTestGreater() {
        Card cardKC = new Card(CardFace.KING, CardSuit.CLUBS);
        Card cardJH = new Card(CardFace.JACK, CardSuit.HEARTS);
        assertEquals(cardKC.compareTo(cardJH), 1);
    }
}
