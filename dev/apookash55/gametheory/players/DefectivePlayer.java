package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.definition.Decision;
import dev.apookash55.gametheory.definition.Player;

public class DefectivePlayer extends Player {

    @Override
    public Decision makeDecision() {
        return Decision.DEFECT;
    }
}
