package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.definition.Decision;
import dev.apookash55.gametheory.definition.Player;

public class CooperativePlayer extends Player {

    public CooperativePlayer(int x) {
        super(x);
    }

    @Override
    public Decision makeDecision() {
        return Decision.COOPERATE;
    }
}
