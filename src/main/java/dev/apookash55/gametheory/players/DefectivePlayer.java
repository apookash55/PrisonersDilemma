package dev.apookash55.gametheory.players;

import dev.apookash55.definition.Decision;
import dev.apookash55.definition.Player;

public class DefectivePlayer extends Player {

    @Override
    public Decision makeDecision() {
        return Decision.DEFECT;
    }

}
