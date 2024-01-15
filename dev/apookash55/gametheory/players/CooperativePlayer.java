package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.Player;

public class CooperativePlayer extends Player {

    public CooperativePlayer(int x) {
        super(x);
    }

    @Override
    public char makeDecision() {
        return 'C';
    }
}
