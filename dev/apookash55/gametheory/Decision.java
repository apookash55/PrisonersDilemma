package dev.apookash55.gametheory;

public enum Decision {
    COOPERATE('C'),
    DEFECT('D');

    private final char value;

    Decision(char value) {
        this.value = value;
    }

    public char toChar() {
        return value;
    }
}
