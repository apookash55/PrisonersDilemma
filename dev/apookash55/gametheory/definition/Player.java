package dev.apookash55.gametheory.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class with all the defined methods except makeDecision() which can be overridden and implemented<br><br>
 * Available Methods :<br>
 * getRoundsHistory()<br>
 * getCurrentRound()<br>
 * getScore()<br>
 * getLastRoundHistory()
 */
public abstract class Player {
    private int score;
    private List<Decision[]> roundsHistory;
    private int currentRound;

    public Player() {
        score = 0;
        roundsHistory = new ArrayList<>();
        currentRound = 0;
    }

    /**
     * Returns the full rounds' data as List of Array of Decision with first and second index is player's choice and other player's choice respectively
     * @return List of Decision[2] array
     */
    public final List<Decision[]> getRoundsHistory() {
        return roundsHistory;
    }

    /**
     * Returns the current round (0 <= current round <= total rounds - 1)
     *<br>
     * NOTE : total rounds in not known to the player
     * @return int
     */
    public final int getCurrentRound() {
        return currentRound;
    }

    /**
     * Returns the current score
     * @return int
     */
    public final int getScore() {
        return score;
    }

    /**
     * Returns the last round data as an Array of Decision with first and second index is player's choice and other player's choice respectively
     * @return Decision[2] array
     */
    public final Decision[] getLastRoundHistory() {
        if (currentRound > 0) {
            return roundsHistory.get(currentRound - 1);
        }
        else {
            return null;
        }
    }

    /**
     * Return Decision.COOPERATE or Decision.DEFECT<br>
     * NOTE: If null is returned then it will consider it as Decision.DEFECT
     * @return Decision
     */
    public abstract Decision makeDecision();

    /**
     * Records the current round data by adding score to the player, and also records the player's and other player's choice
     * @param currentScore Score gained by the player in the current round
     * @param myChoice Decision made by the player
     * @param playerChoice Decision made by the other player
     */
    public final void recordAttempt(int currentScore, Decision myChoice, Decision playerChoice) {
        score += currentScore;
        roundsHistory.add(new Decision[2]);
        roundsHistory.get(currentRound)[0] = myChoice;
        roundsHistory.get(currentRound)[1] = playerChoice;
        currentRound += 1;
    }

    /**
     * Clears the game data by resetting score, currentRound to 0 and clears the roundHistory once the game is finished
     */
    public final void clearGame() {
        score = 0;
        roundsHistory = new ArrayList<>();
        currentRound = 0;
    }

    /**
     * Generates CSV data from two players' round history once the game is finished
     * @param player1 First Player object
     * @param player2 Second Player object
     * @return CSV of choices made by Player 1 and Player 2 respectively
     */
    public static String generateResult(Player player1, Player player2) {
        List<Decision[]> attempts = player1.roundsHistory;
        StringBuilder res = new StringBuilder(player1.getClass().getSimpleName() + ", " + player2.getClass().getSimpleName() + '\n');
        for (int i = 0; i < player1.currentRound; i++) {
            res.append(attempts.get(i)[0].toChar()).append(", ").append(attempts.get(i)[1].toChar()).append('\n');
        }
        return res.toString();
    }
}
