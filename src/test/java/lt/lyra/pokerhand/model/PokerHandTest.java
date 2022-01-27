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
    public void isPokerHandIsStraightFlushTest(){
        String hand = "AS KS QS JS TS";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.STRAIGHT_FLUSH);

        hand = "TD 9D 8D 7D 6D";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.STRAIGHT_FLUSH);
    }

    @Test
    public void isPokerHandIsNotStraightFlushTest(){
        String hand = "AS KS 2D JS 5H";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.STRAIGHT_FLUSH);

        hand = "AH 9D JS 7D 6H";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.STRAIGHT_FLUSH);
    }

    @Test
    public void isPokerHandIsFourOfAKindTest(){
        String hand = "AS TD AH AC AS";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.FOUR_OF_A_KIND);

        hand = "TD TS 6H TC TD";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.FOUR_OF_A_KIND);
    }

    @Test
    public void isPokerHandIsNotFourOfAKindTest(){
        String hand = "3S AD 2H AC TS";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.FOUR_OF_A_KIND);

        hand = "5D TS TH 4C 6D";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.FOUR_OF_A_KIND);
    }

    @Test
    public void isPokerHandIsFullHouseTest(){
        String hand = "6H KS 6C KS 6H";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.FULL_HOUSE);

        hand = "TH KD TC KD TS";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.FULL_HOUSE);
    }

    @Test
    public void isPokerHandIsNotFullHouseTest(){
        String hand = "6H 6S 5C KS KH";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.FULL_HOUSE);

        hand = "TH 9D TC KD KS";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.FULL_HOUSE);
    }

    @Test
    public void isPokerHandIsFlushTest(){
        String hand = "6H 2H 8H KH 4H";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.FLUSH);

        hand = "7C 4C 9C 2C 5C";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.FLUSH);
    }

    @Test
    public void isPokerHandIsNotFlushTest(){
        String hand = "6H 2H 8D KH 4S";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.FLUSH);

        hand = "7S 4D 9C 2C 5C";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.FLUSH);
    }

    @Test
    public void isPokerHandIsStraightTest(){
        String hand = "TS 8H 9S 6S 7S";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.STRAIGHT);

        hand = "2C 5S 4D 3D 6H";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.STRAIGHT);
    }

    @Test
    public void isPokerHandIsNotStraightTest(){
        String hand = "TS 8H KS 6S 7S";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.STRAIGHT);

        hand = "AC 5S TD 3D 6H";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.STRAIGHT);
    }

    @Test
    public void isPokerHandIsThreeOfAKindTest(){
        String hand = "4H 3C QD 3S 3H";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.THREE_OF_A_KIND);

        hand = "5D QH QS KH QC";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.THREE_OF_A_KIND);
    }

    @Test
    public void isPokerHandIsNotThreeOfAKindTest(){
        String hand = "4H 3C QD 2S 3H";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.THREE_OF_A_KIND);

        hand = "5D TH QS KH QC";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.THREE_OF_A_KIND);
    }

    @Test
    public void isPokerHandIsTwoPairsTest(){
        String hand = "4S 2H 2C 4C 8S";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.TWO_PAIRS);

        hand = "AS 6S QC 6H AD";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.TWO_PAIRS);
    }

    @Test
    public void isPokerHandIsNotTwoPairsTest(){
        String hand = "4S 2H 3C 4C 8S";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.TWO_PAIRS);

        hand = "AS 6S QC 6H JD";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.TWO_PAIRS);
    }

    @Test
    public void isPokerHandIsOnePairTest(){
        String hand = "4S 7H QS 4C 2H";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.ONE_PAIR);

        hand = "JH 2D JS QD AC";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.ONE_PAIR);
    }

    @Test
    public void isPokerHandIsNotOnePairTest(){
        String hand = "4S QH QS 4C 2H";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.ONE_PAIR);

        hand = "JH 2D TS QD AC";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.ONE_PAIR);
    }

    @Test
    public void isPokerHandIsNoPairTest(){
        String hand = "4S 7H QS 3C 2H";
        PokerHand ph = new PokerHand(hand);

        assertEquals(ph.getHandCombination(), HandCombination.NO_PAIR);

        hand = "JH 2D KS QD AC";
        ph = new PokerHand(hand);
        assertEquals(ph.getHandCombination(), HandCombination.NO_PAIR);
    }

    @Test
    public void isPokerHandIsNotNoPairTest(){
        String hand = "4S 7H QS 3C 3H";
        PokerHand ph = new PokerHand(hand);

        assertNotEquals(ph.getHandCombination(), HandCombination.NO_PAIR);

        hand = "JH 2D KS JD JC";
        ph = new PokerHand(hand);
        assertNotEquals(ph.getHandCombination(), HandCombination.NO_PAIR);
    }

    @Test
    public void compareToTestLess() {
        PokerHand p1 = new PokerHand("5S 8D TH 6C 9D");
        PokerHand p2 = new PokerHand("7D KH 8C 9S 6D");

        assertEquals(p1.compareTo(p2), -1);
    }

    @Test
    public void compareToTestEqual() {
        PokerHand p1 = new PokerHand("5S 8D TH 6C 9D");
        PokerHand p2 = new PokerHand("TD 5H 8C 9S 6D");

        assertEquals(p1.compareTo(p2), 0);
    }

    @Test
    public void compareToTestGreater() {
        PokerHand p1 = new PokerHand("5D KC 3H 5S AC");
        PokerHand p2 = new PokerHand("4S 7H QS 4C 2H");

        assertEquals(p1.compareTo(p2), 1);
    }

}


































