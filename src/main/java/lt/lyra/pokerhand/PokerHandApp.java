package lt.lyra.pokerhand;

import lt.lyra.pokerhand.model.PokerHand;
import lt.lyra.pokerhand.type.HandCombination;

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

        pokerHands.forEach(h -> {
//            System.out.println("p1: " + h.substring(0 , h.length() / 2) + " p2: " + h.substring(h.length()/2).trim());
            PokerHand p1 = new PokerHand(h.substring(0 , h.length() / 2));
            PokerHand p2 = new PokerHand(h.substring(h.length() / 2).trim());

            if(p1.getHandCombination() == HandCombination.FLUSH){
                System.out.println(h.substring(0 , h.length() / 2));
            }

            if(p2.getHandCombination() == HandCombination.FLUSH){
                System.out.println(h.substring(h.length() / 2).trim());
            }



//            System.out.println();
        });

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
