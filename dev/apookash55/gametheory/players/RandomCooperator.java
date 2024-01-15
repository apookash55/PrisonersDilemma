package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.Decision;
import dev.apookash55.gametheory.Player;

import java.util.Random;

public class RandomCooperator extends Player {

    private final Random rand = new Random();
    public RandomCooperator(int x) {
        super(x);
    }

    @Override
    public Decision makeDecision() {
        int x = rand.nextInt(10);
        if (x >= 7) {
            return Decision.COOPERATE;
        }
        return Decision.DEFECT;
    }
}
