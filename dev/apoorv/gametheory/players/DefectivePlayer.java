package dev.apoorv.gametheory.players;

import dev.apoorv.gametheory.Player;

public class DefectivePlayer extends Player {

    public DefectivePlayer(int x) {
        super(x);
    }

    @Override
    public char makeDecision() {
        return 'D';
    }
}
