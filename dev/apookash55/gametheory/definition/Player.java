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
    private final List<Decision[]> roundsHistory;
    private int currentRound;

    public Player() {
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
     * Records the current round data by adding the player's and other player's choice
     *
     * @param myChoice Decision made by the player
     * @param playerChoice Decision made by the other player
     */
    public final void recordLastRound(Decision myChoice, Decision playerChoice) {
        roundsHistory.add(new Decision[2]);
        roundsHistory.get(currentRound)[0] = myChoice;
        roundsHistory.get(currentRound)[1] = playerChoice;
        currentRound += 1;
    }
}
