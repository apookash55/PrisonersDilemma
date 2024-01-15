package dev.apoorv.gametheory.players;

import dev.apoorv.gametheory.Player;

public class CooperativePlayer extends Player {

    public CooperativePlayer(int x) {
        super(x);
    }

    @Override
    public char makeDecision() {
        return 'C';
    }
}
