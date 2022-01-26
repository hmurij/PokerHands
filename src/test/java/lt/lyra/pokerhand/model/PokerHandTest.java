package lt.lyra.pokerhand.model;

import lt.lyra.pokerhand.type.CardFace;
import lt.lyra.pokerhand.type.CardSuit;
import lt.lyra.pokerhand.type.HandCombination;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PokerHandTest {

    @Test
    public void parsePokerHandTest() {
        List<Card> h1 = List.of(new Card(CardFace.ACE, CardSuit.SPADES),
                new Card(CardFace.KING, CardSuit.DIAMONDS),
                new Card(CardFace.THREE, CardSuit.DIAMONDS),
                new Card(CardFace.TEN, CardSuit.DIAMONDS),
                new Card(CardFace.EIGHT, CardSuit.HEARTS));
        List<Card> h2 = (new PokerHand("AS KD 3D 10D 8H")).getPokerHand();
        assertEquals(h1.toString(), h2.toString());
    }

    @Test
    public void isPokerHandIsStraightFlush(){
        String hand = "AS KS QS JS 10S";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.STRAIGHT_FLUSH);

        hand = "10D 9D 8D 7D 6D";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.STRAIGHT_FLUSH);
    }

    @Test
    public void isPokerHandIsNotStraightFlush(){
        String hand = "AS KS 2D JS 5H";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.STRAIGHT_FLUSH);

        hand = "AH 9D JS 7D 6H";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.STRAIGHT_FLUSH);
    }

}
