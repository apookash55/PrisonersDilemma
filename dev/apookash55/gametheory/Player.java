package dev.apookash55.gametheory;

public abstract class Player {
    protected int score;
    protected char[][] attempts;
    protected final int totalAttempts;
    protected int currentAttempt;

    public Player(int x) {
        score = 0;
        attempts = new char[x][2];
        totalAttempts = x;
        currentAttempt = 0;
    }

    protected abstract char makeDecision();

    public void recordAttempt(int currentScore, char myChoice, char playerChoice) {
        score += currentScore;
        attempts[currentAttempt][0] = myChoice;
        attempts[currentAttempt][1] = playerChoice;
        currentAttempt += 1;
    }

    public void clearAttempt() {
        score = 0;
        attempts = new char[totalAttempts][2];
        currentAttempt = 0;
    }

    public int getScore() {
        return score;
    }

    public static String getAttempts(Player player1, Player player2) {
        int totalAttempts = player1.totalAttempts;
        char[][] attempts = player1.attempts;
        StringBuilder res = new StringBuilder("Player 1 : ");
        for (int i = 0; i < totalAttempts; i++) {
            if (i != totalAttempts-1)
                res.append(attempts[i][0]).append(", ");
            else
                res.append(attempts[i][0]);
        }
        res.append("\n").append("Player 2 : ");
        for (int i = 0; i < totalAttempts; i++) {
            if (i != totalAttempts-1)
                res.append(attempts[i][1]).append(", ");
            else
                res.append(attempts[i][1]);
        }
        return res.toString();
    }
}
