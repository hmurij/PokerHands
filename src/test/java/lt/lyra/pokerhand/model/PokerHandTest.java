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
        List<Card> h2 = (new PokerHand("AS KD 3D TD 8H")).getPokerHand();
        assertEquals(h1.toString(), h2.toString());
    }

    @Test
    public void isPokerHandIsStraightFlush(){
        String hand = "AS KS QS JS TS";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.STRAIGHT_FLUSH);

        hand = "TD 9D 8D 7D 6D";
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

    @Test
    public void isPokerHandIsFourOfAKind(){
        String hand = "AS AD AH AC TS";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.FOUR_OF_A_KIND);

        hand = "TD TS TH TC 6D";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.FOUR_OF_A_KIND);
    }

    @Test
    public void isPokerHandIsNotFourOfAKind(){
        String hand = "3S AD 2H AC TS";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.FOUR_OF_A_KIND);

        hand = "5D TS TH 4C 6D";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.FOUR_OF_A_KIND);
    }

    @Test
    public void isPokerHandIsFullHouse(){
        String hand = "6H 6S 6C KS KH";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.FULL_HOUSE);

        hand = "TH TD TC KD KS";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.FULL_HOUSE);
    }

    @Test
    public void isPokerHandIsNotFullHouse(){
        String hand = "6H 6S 5C KS KH";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.FULL_HOUSE);

        hand = "TH 9D TC KD KS";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.FULL_HOUSE);
    }

}


































