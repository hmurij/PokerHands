package lt.lyra.pokerhand.model;

import lt.lyra.pokerhand.type.CardFace;
import lt.lyra.pokerhand.type.CardSuit;

import java.util.ArrayList;
import java.util.List;

/**
 * Class PokerHand represents player's poker hand. Poker hand parsed from String object passed
 * to constructor with one parameter in format "AS KD 3D JD 8H"
 */
public class PokerHand {
    private final List<Card> pokerHand;

    public PokerHand(String pokerHand) {
        this.pokerHand = parsePokerHand(pokerHand);

    }

    public List<Card> getPokerHand() {
        return pokerHand;
    }

    /**
     * Parses poker hand from string
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
     * @param cardFace string representation of card face one of
     *             "2", "3", "4", "5", "6", "7", "8", "9", "10",
     *             "J", "Q", "K", "A". Assuming all strings are valid.
     * @return parsed {@link CardFace} object
     */
    private CardFace parseFace(String cardFace) {
        CardFace[] faces = CardFace.values();
        CardFace face = CardFace.DEUCE;

        // parse card face
        try {
            face = faces[Integer.parseInt(cardFace.substring(0, 1)) - 2];
        } catch (NumberFormatException e) {
            switch (cardFace.substring(0, 1)) {
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
     * @param cardSuit string representation of card suit one of
     *             "H", "D", "C", "S". Assuming all strings are valid.
     * @return parsed {@link CardSuit} object
     */
    private CardSuit parseSuit(String cardSuit) {
        CardSuit[] suits = CardSuit.values();
        CardSuit suit = CardSuit.HEARTS;

        // parse suit
        switch (cardSuit.substring(1)) {
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



    @Override
    public String toString() {
        return "PokerHand{" +
                "pokerHand=" + pokerHand +
                '}';
    }
}
































