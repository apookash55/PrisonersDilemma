package dev.apookash55.gametheory.definition;

public final class Result {
    private int player1;
    private int player2;
    private final StringBuilder attempts;

    public Result(Player p1, Player p2) {
        player1 = 0;
        player2 = 0;
        this.attempts = new StringBuilder(p1.getClass().getSimpleName() + ", " + p2.getClass().getSimpleName() + '\n');
    }

    public void updateResult(int x, int y, Decision a, Decision b) {
        player1 += x;
        player2 += y;
        attempts.append(a).append(", ").append(b).append('\n');
    }

    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public String getAttempts() {
        return attempts.toString();
    }
}
