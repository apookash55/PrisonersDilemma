package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.Decision;
import dev.apookash55.gametheory.Player;

public class DefectivePlayer extends Player {

    public DefectivePlayer(int x) {
        super(x);
    }

    @Override
    public Decision makeDecision() {
        return Decision.DEFECT;
    }
}
