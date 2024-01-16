package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.definition.Decision;
import dev.apookash55.gametheory.definition.Player;

public class TitForTat extends Player {

    @Override
    public Decision makeDecision() {
        Decision[] lastRound = getLastRoundHistory();
        if(getCurrentRound() > 0) {
            return lastRound[1];
        }
        return Decision.COOPERATE;
    }

    @Override
    public void clearValues() {

    }
}
