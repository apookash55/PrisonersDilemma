package dev.apookash55.gametheory;

public class Result {
    private final int player1;
    private final int player2;
    private final String attempts;

    Result(int x, int y, String attempts) {
        player1 = x;
        player2 = y;
        this.attempts = attempts;
    }

    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public String getAttempts() {
        return attempts;
    }
}
