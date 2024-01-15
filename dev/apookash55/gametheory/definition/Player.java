package dev.apookash55.gametheory.definition;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private int score;
    private List<Decision[]> roundsHistory;
    private int currentRound;

    public Player() {
        score = 0;
        roundsHistory = new ArrayList<>();
        currentRound = 0;
    }

    public List<Decision[]> getRoundsHistory() {
        return roundsHistory;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getScore() {
        return score;
    }

    public Decision[] getLastRoundHistory() {
        if (currentRound > 0) {
            return roundsHistory.get(currentRound - 1);
        }
        else {
            return null;
        }
    }

    public abstract Decision makeDecision();

    public void recordAttempt(int currentScore, Decision myChoice, Decision playerChoice) {
        score += currentScore;
        roundsHistory.add(new Decision[2]);
        roundsHistory.get(currentRound)[0] = myChoice;
        roundsHistory.get(currentRound)[1] = playerChoice;
        currentRound += 1;
    }

    public void clearAttempts() {
        score = 0;
        roundsHistory = new ArrayList<>();
        currentRound = 0;
    }

    public static String generateResult(Player player1, Player player2) {
        List<Decision[]> attempts = player1.roundsHistory;
        StringBuilder res = new StringBuilder(player1.getClass().getSimpleName() + ", " + player2.getClass().getSimpleName() + '\n');
        for (int i = 0; i < player1.currentRound; i++) {
            res.append(attempts.get(i)[0].toChar()).append(", ").append(attempts.get(i)[1].toChar()).append('\n');
        }
        return res.toString();
    }
}
