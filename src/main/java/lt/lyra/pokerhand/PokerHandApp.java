package lt.lyra.pokerhand;

import lt.lyra.pokerhand.model.PokerHand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

            int p1Wins = 0;
            int p2Wins = 0;
            int draws = 0;

            for(String h: pokerHands) {


                PokerHand p1 = new PokerHand(h.substring(0, h.length() / 2));
                PokerHand p2 = new PokerHand(h.substring(h.length() / 2).trim());

                int result = p1.compareTo(p2);

                System.out.println("p1: " + h.substring(0, h.length() / 2));
                System.out.println("p1: " + p1.getHandCombination());

                System.out.println("p2: " + h.substring(h.length() / 2).trim());
                System.out.println("p2: " + p2.getHandCombination());
                System.out.println("---------------------------------------------");

                if (result == -1) {
                    System.out.println("p2 wins");
                    p2Wins++;
                } else if (result == 0) {
                    draws++;
                    System.out.println("draw");
                } else if (result == 1) {
                    p1Wins++;
                    System.out.println("p1 wins");
                }
                System.out.println();

            }

        System.out.println("----------------------------------------------------------------");
        System.out.println("Total: p1 wins: " + p1Wins + " p2 wins: " + p2Wins + " draws: " + draws);

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
