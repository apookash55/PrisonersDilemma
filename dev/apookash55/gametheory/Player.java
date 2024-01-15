package dev.apookash55.gametheory;

public abstract class Player {
    protected int score;
    protected Decision[][] attempts;
    protected final int totalAttempts;
    protected int currentAttempt;

    public Player(int x) {
        score = 0;
        attempts = new Decision[x][2];
        totalAttempts = x;
        currentAttempt = 0;
    }

    protected abstract Decision makeDecision();

    public void recordAttempt(int currentScore, Decision myChoice, Decision playerChoice) {
        score += currentScore;
        attempts[currentAttempt][0] = myChoice;
        attempts[currentAttempt][1] = playerChoice;
        currentAttempt += 1;
    }

    public void clearAttempt() {
        score = 0;
        attempts = new Decision[totalAttempts][2];
        currentAttempt = 0;
    }

    public int getScore() {
        return score;
    }

    public static String getAttempts(Player player1, Player player2) {
        int totalAttempts = player1.totalAttempts;
        Decision[][] attempts = player1.attempts;
        StringBuilder res = new StringBuilder(player1.getClass().getSimpleName() + ", ");
        for (int i = 0; i < totalAttempts; i++) {
            if (i != totalAttempts-1)
                res.append(attempts[i][0].toChar()).append(", ");
            else
                res.append(attempts[i][0].toChar());
        }
        res.append("\n").append(player2.getClass().getSimpleName()).append(", ");
        for (int i = 0; i < totalAttempts; i++) {
            if (i != totalAttempts-1)
                res.append(attempts[i][1].toChar()).append(", ");
            else
                res.append(attempts[i][1].toChar());
        }
        return res.toString();
    }
}
