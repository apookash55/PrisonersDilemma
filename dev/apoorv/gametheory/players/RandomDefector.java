package dev.apoorv.gametheory.players;

import dev.apoorv.gametheory.Player;

import java.util.Random;

public class RandomDefector extends Player {

    private Random rand = new Random();
    public RandomDefector(int x) {
        super(x);
    }

    @Override
    public char makeDecision() {
        int x = rand.nextInt(10);
        if (x >= 7) {
            return 'D';
        }
        return 'C';
    }
}
