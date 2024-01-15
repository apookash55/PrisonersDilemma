package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.definition.Decision;
import dev.apookash55.gametheory.definition.Player;

public class TitForTat extends Player {
    public TitForTat(int x) {
        super(x);
    }

    @Override
    public Decision makeDecision() {
        int currentAttempt = getCurrentRound();
        Decision[][] attempts = getRoundHistory();
        if(getCurrentRound() > 0) {
            return attempts[currentAttempt - 1][1];
        }
        return Decision.COOPERATE;
    }
}
