package lt.lyra.pokerhand;

import lt.lyra.pokerhand.type.HandCombination;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

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
        Arrays.stream(HandCombination.values()).forEach(c -> System.out.println(c + " : " + c.getValue()));

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
