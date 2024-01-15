package dev.apoorv.gametheory.players;

import dev.apoorv.gametheory.Player;

public class TitForTat extends Player {
    public TitForTat(int x) {
        super(x);
    }

    @Override
    protected char makeDecision() {
        if(currentAttempt > 0) {
            return attempts[currentAttempt-1][1];
        }
        return 'C';
    }
}
