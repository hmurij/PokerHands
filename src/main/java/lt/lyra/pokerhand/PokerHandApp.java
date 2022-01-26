package lt.lyra.pokerhand;

import lt.lyra.pokerhand.model.Card;
import lt.lyra.pokerhand.type.CardFace;
import lt.lyra.pokerhand.type.CardSuit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Reads file poker.txt 1000 lines. Each line in format 8C TS KC 9H 4S 7D 2S 5D 3S AC - representing
 * random poker hands dealt to two players. For example player1 hand: 8C TS KC 9H 4S and
 * player2 hand: 7D 2S 5D 3S AC. Program evaluates hands and counts wins for both players.
 */
public class PokerHandApp {
    private final static List<String> pokerHands;

    static {
        pokerHands = readHandsFromFile();
    }

    public static void main(String[] args) {
        CardFace [] faces = CardFace.values();
        CardSuit [] suits = CardSuit.values();

        List<Card> deck = new ArrayList<>();

        for (CardFace face : faces) {
            Arrays.stream(suits).map(suit -> new Card(face, suit)).forEach(deck::add);
        }

        Collections.sort(deck, Comparator.reverseOrder());
        deck.forEach(System.out::println);

    }

    /**
     * Reads all lines in poker.txt file and returns list containing all line in the file.
     *
     * @return list containing all lines from the file or empty list if file not found
     */
    public static List<String> readHandsFromFile() {
        try {
            return Files.readAllLines(Path.of("poker.txt"));
        } catch (IOException e) {
            return List.of();
        }
    }
}
