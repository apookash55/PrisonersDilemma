package dev.apookash55.gametheory.players;

import dev.apookash55.definition.Decision;
import dev.apookash55.definition.Player;

import java.util.Random;

public class RandomPlayer extends Player {
    private final Random random = new Random();

    @Override
    public Decision makeDecision() {
        int x = random.nextInt(2);
        if (x == 1) {
            return Decision.COOPERATE;
        }
        else {
            return Decision.DEFECT;
        }
    }
}