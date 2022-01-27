# Poker hand evaluator

Program reads file poker.txt 1000 lines. Each line in format 8C TS KC 9H 4S 7D 2S 5D 3S AC - representing random poker hands dealt to two players. For example player1 hand: 8C TS KC 9H 4S and player2 hand: 7D 2S 5D 3S AC. For each line program evaluates hands and counts wins for both players.

Three enums were used to represent types of fixed values, such as card face, card suit and hand combiation.

Class Card represents playing card. Has two final fields face and suit. Implements Comparable interface for convenient comparing of two cards.

Class PokerHand represents player's poker hand. Poker hand parsed from String object passed to constructor with one parameter in format "AS KD 3D JD 8H" and evaluated by evaluateHand method. Implements Comparable interface for convenient comparing of two poker hands.
