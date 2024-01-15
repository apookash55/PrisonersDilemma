package dev.apookash55.gametheory.definition;

public abstract class Player {
    private int score;
    private Decision[][] roundsHistory;
    private final int totalRounds;
    private int currentRound;

    public Player(int x) {
        score = 0;
        roundsHistory = new Decision[x][2];
        totalRounds = x;
        currentRound = 0;
    }

    public Decision[][] getRoundsHistory() {
        return roundsHistory;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getScore() {
        return score;
    }

    public Decision[] getLastRoundHistory() {
        if (currentRound > 0) {
            return roundsHistory[currentRound - 1];
        }
        else {
            return null;
        }
    }

    public abstract Decision makeDecision();

    public void recordAttempt(int currentScore, Decision myChoice, Decision playerChoice) {
        score += currentScore;
        roundsHistory[currentRound][0] = myChoice;
        roundsHistory[currentRound][1] = playerChoice;
        currentRound += 1;
    }

    public void clearAttempts() {
        score = 0;
        roundsHistory = new Decision[totalRounds][2];
        currentRound = 0;
    }

    public static String generateResult(Player player1, Player player2) {
        Decision[][] attempts = player1.roundsHistory;
        StringBuilder res = new StringBuilder(player1.getClass().getSimpleName() + ", " + player2.getClass().getSimpleName() + '\n');
        for (int i = 0; i < player1.totalRounds; i++) {
            res.append(attempts[i][0].toChar()).append(", ").append(attempts[i][1].toChar()).append('\n');
        }
        return res.toString();
    }
}
