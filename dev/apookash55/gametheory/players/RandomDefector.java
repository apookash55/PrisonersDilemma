package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.definition.Decision;
import dev.apookash55.gametheory.definition.Player;

import java.util.Random;

public class RandomDefector extends Player {

    private final Random random = new Random();

    @Override
    public Decision makeDecision() {
        int x = random.nextInt(10);
        if (x >= 7) {
            return Decision.DEFECT;
        }
        return Decision.COOPERATE;
    }

    @Override
    public void clearValues() {

    }
}
