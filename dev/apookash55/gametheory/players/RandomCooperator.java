package dev.apookash55.gametheory.players;

import dev.apookash55.gametheory.Player;

import java.util.Random;

public class RandomCooperator extends Player {

    private Random rand = new Random();
    public RandomCooperator(int x) {
        super(x);
    }

    @Override
    public char makeDecision() {
        int x = rand.nextInt(10);
        if (x >= 7) {
            return 'C';
        }
        return 'D';
    }
}
