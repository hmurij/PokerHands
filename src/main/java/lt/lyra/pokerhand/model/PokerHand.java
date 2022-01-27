package lt.lyra.pokerhand.model;

import lt.lyra.pokerhand.type.CardFace;
import lt.lyra.pokerhand.type.CardSuit;
import lt.lyra.pokerhand.type.HandCombination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class PokerHand represents player's poker hand. Poker hand parsed from String object passed
 * to constructor with one parameter in format "AS KD 3D JD 8H"
 * Implements Comparable interface for convenient comparing of two poker hands.
 */
public class PokerHand implements Comparable<PokerHand> {
    private final List<Card> pokerHand;
    private final HandCombination handCombination;

    // utility hand, to compare hands in case of equal combination
    private List<Card> highestCards;

    public PokerHand(String pokerHand) {
        this.pokerHand = parsePokerHand(pokerHand);

        this.handCombination = evaluateHand();

    }

    /**
     * Evaluates poker hand.
     * @return poker hand combination
     */
    private HandCombination evaluateHand() {
        HandCombination combination = HandCombination.NO_PAIR;

        if (isStraightFlush()) {
            combination = HandCombination.STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            combination = HandCombination.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            combination = HandCombination.FULL_HOUSE;
        } else if (isFlush()) {
            combination = HandCombination.FLUSH;
        } else if (isStraight()) {
            combination = HandCombination.STRAIGHT;
        } else if (isThreeOfAKind()) {
            combination = HandCombination.THREE_OF_A_KIND;
        } else if (isTwoPairs()) {
            combination = HandCombination.TWO_PAIRS;
        } else if (isOnePair()) {
            combination = HandCombination.ONE_PAIR;
        }

        // initialize highestCard utility field
        if (combination == HandCombination.NO_PAIR || combination == HandCombination.STRAIGHT_FLUSH ||
                combination == HandCombination.STRAIGHT || combination == HandCombination.FLUSH) {
            highestCards = new ArrayList<>(pokerHand);
            highestCards.sort(Comparator.reverseOrder());
        }

        return combination;
    }

    /**
     * Checks whether hand is Straight Flush – the highest possible hand.
     * A straight flush consists of five cards of the same suit in sequence,
     * such as 10, 9, 8, 7, 6 of hearts.
     *
     * @return true if hand is Straight Flush combination and false otherwise
     */
    private boolean isStraightFlush() {

        int numberOfSuits = pokerHand.stream().collect(Collectors.groupingBy(Card::getSuit)).size();
        List<Card> hand = new ArrayList<>(pokerHand);
        hand.sort(Comparator.reverseOrder());

        boolean straight = true;
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i - 1).getFace().getValue() - hand.get(i).getFace().getValue() != 1) {
                straight = false;
                break;
            }
        }

        return numberOfSuits == 1 && straight;

    }

    /**
     * Checks whether hand is Four of a Kind - An example is four aces or four 3s.
     *
     * @return true if hand is Four of a Kind combination and false otherwise
     */
    private boolean isFourOfAKind() {
        var cardGroups = pokerHand.stream().collect(Collectors.groupingBy(Card::getFace));

        boolean fourOfAKind = cardGroups.values().stream().anyMatch(g -> g.size() == 4);

        if (fourOfAKind) {
            highestCards = cardGroups.values().stream().filter(g -> g.size() == 4).findAny().get();
            highestCards.addAll(cardGroups.values().stream().filter(g -> g.size() == 1).findAny().get());
        }


        return fourOfAKind;
    }

    /**
     * Checks whether hand is Full House - hand is made up of three cards of one rank and two cards of another rank,
     * such as three 8s and two 4s, or three aces and two 6s.
     *
     * @return true if hand is Four House combination and false otherwise
     */
    private boolean isFullHouse() {
        var cardGroups = pokerHand.stream().collect(Collectors.groupingBy(Card::getFace));

        boolean threeOfAKind = cardGroups.values().stream().anyMatch(g -> g.size() == 3);
        boolean pair = cardGroups.values().stream().anyMatch(g -> g.size() == 2);

        if (threeOfAKind && pair) {
            highestCards = cardGroups.values().stream().filter(g -> g.size() == 3).findAny().get();
            highestCards.addAll(cardGroups.values().stream().filter(g -> g.size() == 2).findAny().get());
        }

        return threeOfAKind && pair;
    }

    /**
     * Checks whether hand is Flush - five cards, all of the same suit, but not all in sequence,
     * is a flush. An example is Q, 10, 7, 6, and 2 of clubs.
     *
     * @return true if hand is Flush combination and false otherwise
     */
    private boolean isFlush() {
        return pokerHand.stream().collect(Collectors.groupingBy(Card::getSuit)).size() == 1;
    }

    /**
     * Checks whether hand is Straight - Five cards in sequence, but not all of the same suit is a straight.
     * An example is 9S 8H 7C 6D 5H.
     *
     * @return true if hand is Straight combination and false otherwise
     */
    private boolean isStraight() {
        int numberOfSuits = pokerHand.stream().collect(Collectors.groupingBy(Card::getSuit)).size();
        List<Card> hand = new ArrayList<>(pokerHand);
        hand.sort(Comparator.reverseOrder());

        boolean straight = true;
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i - 1).getFace().getValue() - hand.get(i).getFace().getValue() != 1) {
                straight = false;
                break;
            }
        }

        return numberOfSuits > 1 && straight;
    }

    /**
     * Checks whether hand is Three of a Kind -  three cards of the same rank, and the other two cards
     * each of a different rank, such as three jacks, a seven, and a four.
     *
     * @return true if Three of a Kind and false otherwise
     */
    private boolean isThreeOfAKind() {
        var cardGroups = pokerHand.stream().collect(Collectors.groupingBy(Card::getFace));
        boolean threeOfAKind = cardGroups.values().stream().anyMatch(g -> g.size() == 3);
        boolean twoCards = cardGroups.values().stream().filter(g -> g.size() == 1).toArray().length == 2;

        if (threeOfAKind && twoCards) {
            highestCards = cardGroups.values().stream().filter(g -> g.size() == 3).findAny().get();
            highestCards.addAll(cardGroups.values().stream().filter(g -> g.size() == 1)
                    .flatMap(Collection::stream).sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        }

        return threeOfAKind && twoCards;
    }

    /**
     * Checks whether hand is Two Pairs - pair of one rank and another pair of a different rank, plus any
     * fifth card of a different rank, such as Q, Q, 7, 7, 4.
     *
     * @return true if Two Pairs and false otherwise
     */
    private boolean isTwoPairs() {
        var cardGroups = pokerHand.stream().collect(Collectors.groupingBy(Card::getFace));

        boolean twoPairs = cardGroups.values().stream().filter(g -> g.size() == 2).toArray().length == 2;

        if (twoPairs) {
            highestCards = new ArrayList<>();
            highestCards.addAll(cardGroups.values().stream().filter(g -> g.size() == 2)
                    .flatMap(Collection::stream).collect(Collectors.toList()));
            highestCards.sort(Comparator.reverseOrder());
            highestCards.addAll(cardGroups.values().stream().filter(g -> g.size() == 1).findAny().get());
        }

        return twoPairs;
    }

    /**
     * Checks whether had is One Pair -  just one pair with the other three cards being of different rank.
     * An example is 10, 10, K, 4, 3.
     *
     * @return true if One Pair and false otherwise
     */
    private boolean isOnePair() {
        var cardGroups = pokerHand.stream().collect(Collectors.groupingBy(Card::getFace));
        boolean onePair = cardGroups.values().stream().filter(g -> g.size() == 2).toArray().length == 1;
        boolean threeCards = cardGroups.values().stream().filter(g -> g.size() == 1).toArray().length == 3;

        if (onePair && threeCards) {
            highestCards = new ArrayList<>();
            highestCards.addAll(cardGroups.values().stream().filter(g -> g.size() == 2)
                    .flatMap(Collection::stream).collect(Collectors.toList()));
            highestCards.addAll(cardGroups.values().stream().filter(g -> g.size() == 1).
                    flatMap(Collection::stream).sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        }

        return onePair && threeCards;
    }


    /**
     * Parses poker hand from string
     *
     * @param pokerHand string of cards in format "AS KD 3D JD 8H", assuming all strings are valid
     *                  representation of poker hand
     * @return list of {@link Card} objects representing player hand
     */
    private List<Card> parsePokerHand(String pokerHand) {

        List<Card> hand = new ArrayList<>();

        String[] cards = pokerHand.split(" ");

        for (String card : cards) {

            CardFace face = parseFace(card);
            CardSuit suit = parseSuit(card);

            hand.add(new Card(face, suit));
        }

        return hand;
    }

    /**
     * Parses card face from string
     *
     * @param card string representation of card face one of
     *             "2", "3", "4", "5", "6", "7", "8", "9", "T",
     *             "J", "Q", "K", "A". Assuming all strings are valid.
     * @return parsed {@link CardFace} object
     */
    private CardFace parseFace(String card) {
        CardFace[] faces = CardFace.values();
        CardFace face = CardFace.DEUCE;

        // parse card face
        try {
            face = faces[Integer.parseInt(card.substring(0, 1)) - 2];
        } catch (NumberFormatException e) {
            switch (card.substring(0, 1)) {
                case "T":
                    face = faces[8];
                    break;
                case "J":
                    face = faces[9];
                    break;
                case "Q":
                    face = faces[10];
                    break;
                case "K":
                    face = faces[11];
                    break;
                case "A":
                    face = faces[12];
                    break;
            }
        }

        return face;
    }

    /**
     * Parses card suit from string
     *
     * @param card string representation of card suit one of
     *             "H", "D", "C", "S". Assuming all strings are valid.
     * @return parsed {@link CardSuit} object
     */
    private CardSuit parseSuit(String card) {
        CardSuit[] suits = CardSuit.values();
        CardSuit suit = CardSuit.HEARTS;

        // parse suit
        switch (card.substring(1)) {
            case "H":
                suit = suits[0];
                break;
            case "D":
                suit = suits[1];
                break;
            case "C":
                suit = suits[2];
                break;
            case "S":
                suit = suits[3];
                break;
        }
        return suit;
    }

    public List<Card> getPokerHand() {
        return List.copyOf(pokerHand);
    }

    public List<Card> getHighestCards() {
        return List.copyOf(highestCards);
    }

    public HandCombination getHandCombination() {
        return handCombination;
    }

    @Override
    public String toString() {
        return "PokerHand{" +
                "pokerHand=" + pokerHand +
                '}';
    }

    @Override
    public int compareTo(PokerHand pokerHand2) {
        int result = Integer.compare(handCombination.getValue(), pokerHand2.getHandCombination().getValue());

        if (result == 0) {
            for (int i = 0; i < highestCards.size(); i++) {
                result = highestCards.get(i).compareTo(pokerHand2.getHighestCards().get(i));

                if (result != 0) {
                    break;
                }
            }

        }

        return result;
    }
}
































