package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.Decision;
import dev.apookash55.gametheory.Player;

import java.util.Random;

public class RandomDefector extends Player {

    private final Random rand = new Random();
    public RandomDefector(int x) {
        super(x);
    }

    @Override
    public Decision makeDecision() {
        int x = rand.nextInt(10);
        if (x >= 7) {
            return Decision.DEFECT;
        }
        return Decision.COOPERATE;
    }
}
