package lt.lyra.pokerhand.model;

import lt.lyra.pokerhand.type.CardFace;
import lt.lyra.pokerhand.type.CardSuit;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokerHandTest {

    @Test
    public void parsePokerHandTest() {
        List<Card> h1 = List.of(new Card(CardFace.ACE, CardSuit.SPADES),
                new Card(CardFace.KING, CardSuit.DIAMONDS),
                new Card(CardFace.THREE, CardSuit.DIAMONDS),
                new Card(CardFace.JACK, CardSuit.DIAMONDS),
                new Card(CardFace.EIGHT, CardSuit.HEARTS));
        List<Card> h2 = (new PokerHand("AS KD 3D JD 8H")).getPokerHand();
        assertEquals(h1.toString(), h2.toString());
    }
}
