package dev.apookash55.gametheory;

public class Result {
    private final int player1;
    private final int player2;

    Result(int x, int y) {
        player1 = x;
        player2 = y;
    }

    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }
}
