package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.Player;

public class DefectivePlayer extends Player {

    public DefectivePlayer(int x) {
        super(x);
    }

    @Override
    public char makeDecision() {
        return 'D';
    }
}
